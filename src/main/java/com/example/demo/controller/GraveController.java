package com.example.demo.controller;

import com.example.demo.entity.Grave;
import com.example.demo.service.GraveService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class GraveController {

    private GraveService graveService;

    public GraveController(GraveService graveService) {
        this.graveService = graveService;
    }

    public String getGravesPage(Model model, HttpServletRequest request){
        List<Grave> graves = graveService.getGraves();
        model.addAttribute("graves", graves);
        return "graves";
    }

    @GetMapping("graveCreator")
    public String getGraveCreatorPage(Model model){
        return "graveCreator";
    }
}
