package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Grave;
import com.example.demo.service.GraveService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<Grave> graves = graveService.getGravesByUser(userDto.getUserId());
        model.addAttribute("graves", graves);
        return "graves";
    }

    @GetMapping("/graveCreator")
    public String getRegistrationPage(Model model) {
        model.addAttribute("grave", new Grave());
        return "graveCreator";
    }

    @PostMapping("/graveCreator")
    public String createMovie(@ModelAttribute Grave grave, Model model, HttpServletRequest request){
        System.out.println(grave);
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
}
