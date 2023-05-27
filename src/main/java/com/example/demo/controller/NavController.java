package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Grave;
import com.example.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NavController {
    @GetMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }


    @GetMapping("/home")
    public String getHomePage(HttpServletRequest request, Model model){
        UserDto user=(UserDto) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }
        model.addAttribute("user", user);
        return "home";
    }
}
