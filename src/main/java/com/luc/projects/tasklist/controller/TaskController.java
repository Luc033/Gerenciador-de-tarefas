package com.luc.projects.tasklist.controller;


import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.service.ResponsavelService;
import com.luc.projects.tasklist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {
    TaskService taskService;
    ResponsavelService responsavelService;

    public TaskController(TaskService taskService, ResponsavelService responsavelService) {
        this.taskService = taskService;
        this.responsavelService = responsavelService;
    }

    // Acessar a página de criação de tarefa form-task.html
    @GetMapping("task/new")
    public String novaTarefa(ModelMap model) {
        model.addAttribute("task", new Task());
        model.addAttribute("responsavel", new Responsavel());
        model.addAttribute("resps", responsavelService.findAllResponsavel());
        return "form-task";
    }

    // Criar nova tarefa
    @PostMapping("/task/save")
    public String novaTarefa(@Valid Task newTask, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("responsavel", new Responsavel());
            model.addAttribute("resps", responsavelService.findAllResponsavel());

            return "form-task";
        }
        newTask.setConcluido(false);
        System.out.println("Tarefa criada: " + newTask);
        taskService.saveTask(newTask);
        return "redirect:/home";
    }

    // Remover
    @GetMapping("task/delete/{id}")
    public String deletarTarefa(@PathVariable("id") Long id, ModelMap modalMap) {
        taskService.deleteByIdTask(id);
        return "redirect:/home";
    }

    // Ver uma tarefa antes de EDITAR
    @GetMapping("task/edit/{id}")
    public String preEditarTarefa(@PathVariable Long id, ModelMap model) throws NoSuchFieldException {
        model.addAttribute("task", taskService.findByIdTask(id));
        model.addAttribute("resps", responsavelService.findAllResponsavel());
        model.addAttribute("responsavel", new Responsavel());

        return "form-task";
    }


    // Editar uma tarefa antes de EDITAR
    @PostMapping("task/edit/{id}")
    public String editarTarefa(@PathVariable Long id, ModelMap model) throws NoSuchFieldException {
        model.addAttribute("task", taskService.findByIdTask(id));
        model.addAttribute("resps", responsavelService.findAllResponsavel());

        return "form-task";
    }

    // Editar uma tarefa antes de EDITAR
    @PostMapping("task/edit")
    public String editarTarefa(@Valid @ModelAttribute("task") Task taskEdited, BindingResult result, ModelMap model) throws NoSuchFieldException {
        String msg = "Tarefa para editar: ";
        if (result.hasErrors()) {
            // Se houver erros, re-popule o que a página precisa e retorne
            model.addAttribute("responsavel", new Responsavel());
            model.addAttribute("resps", responsavelService.findAllResponsavel());
            return "form-task"; // Retorna para a página de formulário com os erros
        }
            System.err.println(msg + taskEdited);
        if ( taskEdited.getConcluido()) {
            System.err.println(msg + taskEdited);
            Task taskOld = taskService.findByIdTask(taskEdited.getId());
            taskEdited.isConcluido();
        }else {
            System.err.println(msg + taskEdited);
        }
            System.err.println(msg + taskEdited);

        taskService.saveTask(taskEdited);

        return "redirect:/home";
    }


    // Ver lista
    @GetMapping("/home")
    public String verListaTarefas(ModelMap model) {
        model.addAttribute("tasks", taskService.findAllTask());
        return "home";
    }

    @GetMapping("/")
    public String redirecionarHome(ModelMap model) {
        return verListaTarefas(model);
    }

    @GetMapping("task/completed/{id}")
    public String completarTarefa(@PathVariable Long id, ModelMap model) throws NoSuchFieldException {
        var t = taskService.findByIdTask(id);
        if (!t.getConcluido()) {
            t.setConcluido(true);
            taskService.saveTask(t);
            return "redirect:/home";
        } else {
            throw new IllegalArgumentException("\"Tarefa já concluída: \" + t");
        }
    }

    @GetMapping("aboutme")
    public String aboutMe(ModelMap model) {
        return "aboutme";
    }


}
