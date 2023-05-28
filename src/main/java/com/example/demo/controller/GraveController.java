package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Grave;
import com.example.demo.exception.MainException;
import com.example.demo.service.GraveService;
import com.sun.tools.javac.Main;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GraveController {

    private GraveService graveService;

    public GraveController(GraveService graveService) {
        this.graveService = graveService;
    }

    @GetMapping("/graves")
    public String getGravesPage(Model model, HttpServletRequest request){
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        if(userDto==null){
            return "redirect:/";
        }
        model.addAttribute("user", userDto);
        List<Grave> graves = graveService.getGravesByUser(userDto.getUserId());
        model.addAttribute("graves", graves);
        return "graves";
    }

    @GetMapping("/graveCreator")
    public String getGraveCreatorPage(Model model, HttpServletRequest request) {
        UserDto user=(UserDto) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        model.addAttribute("user", user);
        model.addAttribute("grave", new Grave());
        return "graveCreator";
    }

    @PostMapping("/graveCreator")
    public String createGrave(@ModelAttribute Grave grave, Model model, HttpServletRequest request){
        UserDto userDto = (UserDto) request.getSession().getAttribute("user");
        graveService.saveGrave(grave, userDto.getUserId());
        return "redirect:/graves";
    }

    @GetMapping("/deleteGrave/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        System.out.println("HH");
        graveService.deleteGrave(id);
        return "redirect:/graves";
    }

    @GetMapping("/search")
    public String getSearchPage(Model model, HttpServletRequest request){
        UserDto user=(UserDto) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        model.addAttribute("user", user);
        List<Grave> graves = graveService.getGraves();
        model.addAttribute("graves", graves);
        return "search";
    }

    @PostMapping("/search")
    public String searchGraves(@RequestParam("name") String name, Model model){
        List<Grave> graves = null;
        try {
            if(name.isEmpty()){
                graves = graveService.getGraves();
            }
            else {
                graves = graveService.findGravesByName(name);
            }
            model.addAttribute("graves", graves);
        } catch (MainException ex){
            model.addAttribute("errorMessage", ex);
        }
        return "search";
    }
}
