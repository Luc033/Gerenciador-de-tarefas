package com.luc.projects.tasklist.controller;


import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

// Criar


    // Remover

    // Atualizar

    // Ver um

    // Ver lista
    @GetMapping("/home")
    public String verListaTarefas(ModelMap model){
        model.addAttribute("tasks", taskService.findAllTask());
        return "home";
    }

}
