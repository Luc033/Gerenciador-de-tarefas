package com.luc.projects.tasklist.controller;


import com.luc.projects.tasklist.model.Responsavel;
import com.luc.projects.tasklist.model.Task;
import com.luc.projects.tasklist.service.ResponsavelService;
import com.luc.projects.tasklist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String novaTarefa(@Valid @ModelAttribute("task") Task newTask,
                             BindingResult result, ModelMap model,
                             RedirectAttributes redirectAttributes) { // 2. Adicione como parâmetro

        if (result.hasErrors()) {
            // 3. Adicione a mensagem de SUCESSO aqui
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Tarefa atualizada com sucesso!");

            return "redirect:/home";
        }
        if (taskService.descricaoJaExiste(newTask.getDescricao())) {
            result.rejectValue("descricao", "error.descricao", "Este nome de tarefa já está em uso.");
            model.addAttribute("responsavel", new Responsavel());
            model.addAttribute("resps", responsavelService.findAllResponsavel());
            return "form-task";
        }

        newTask.setConcluido(false);
        try{
            taskService.saveTask(newTask);
        }catch(Exception e){
            result.rejectValue("descricao", "error.descricao", "Este nome de tarefa já está em uso.");
            model.addAttribute("responsavel", new Responsavel());
            model.addAttribute("resps", responsavelService.findAllResponsavel());
            return "form-task";
        }

        // 3. Adicione a mensagem de SUCESSO aqui
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Tarefa criada com sucesso!");

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


    @PostMapping("/task/edit")
    public String editarTarefa(@Valid @ModelAttribute("task") Task taskEditada,
                               BindingResult result, ModelMap model,
                               RedirectAttributes redirectAttributes) throws NoSuchFieldException {

        if (result.hasErrors()) {
            model.addAttribute("responsavel", new Responsavel());

            return "redirect:/home";
        }


        Task taskOriginal = taskService.findByIdTask(taskEditada.getId());
        taskEditada.setConcluido(taskOriginal.getConcluido());

        taskService.saveTask(taskEditada);

        // Adicione a mensagem de SUCESSO aqui
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Tarefa atualizada com sucesso!");

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
