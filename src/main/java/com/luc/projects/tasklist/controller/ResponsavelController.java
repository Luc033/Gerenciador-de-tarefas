package com.luc.projects.tasklist.controller;

import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.service.impl.ResponsavelServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("responsavel")
public class ResponsavelController {
    ResponsavelServiceImpl responsavelService;

    public ResponsavelController(ResponsavelServiceImpl responsavelService) {
        this.responsavelService = responsavelService;
    }

    // criar
    @PostMapping("save")
    public String newResponsavel(Responsavel responsavel){
        responsavelService.createResponsavel(responsavel);
        return "redirect:/task/new";
    }
    // ver um

    // deletar

}
