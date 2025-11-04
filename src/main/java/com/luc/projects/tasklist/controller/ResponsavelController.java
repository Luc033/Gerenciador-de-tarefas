package com.luc.projects.tasklist.controller;

import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.service.impl.ResponsavelServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String newResponsavel(@Valid @ModelAttribute("responsavel") Responsavel responsavel,
                                 BindingResult result, ModelMap model){
        if(result.hasErrors()) {
            model.addAttribute("task", new Task());
            model.addAttribute("resps", responsavelService.findAllResponsavel());
            return "form-task";
        }
        responsavelService.createResponsavel(responsavel);
        return "redirect:/task/new";
    }
    // ver um

    // deletar

}
