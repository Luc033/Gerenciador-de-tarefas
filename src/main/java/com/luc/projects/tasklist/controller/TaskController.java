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

    // Acessar a página de criação de tarefa form-task.html e repopula os objetos do thymeleaf
    @GetMapping("task/new")
    public String novaTarefa(ModelMap model) {
        model.addAttribute("task", new Task());
        model.addAttribute("responsavel", new Responsavel());
        model.addAttribute("resps", responsavelService.findAllResponsavel());
        return "form-task";
    }

    // Criar nova tarefa após validar os dados com @Valid e com BindingResult retornamos os erros ao usuários.
    // Além de verificar os dados de entrada, também é verificado se a descrição da tarefa será duplicada.
    @PostMapping("/task/save")
    public String novaTarefa(@Valid @ModelAttribute("task") Task newTask,
                             BindingResult result, ModelMap model,
                             RedirectAttributes redirectAttributes) { // 2. Adicione como parâmetro

        if (result.hasErrors()) {
            // 3. Adicione a mensagem de SUCESSO aqui
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Tarefa atualizada com sucesso!");

            return "redirect:/home";
        }
        // Verifica se a descrição da nova tarefa já existe e retorna um erro antes de salvar no banco de dados.
        if (taskService.descricaoJaExiste(newTask.getDescricao())) {
            result.rejectValue("descricao", "error.descricao", "Este nome de tarefa já está em uso.");
            model.addAttribute("responsavel", new Responsavel());
            model.addAttribute("resps", responsavelService.findAllResponsavel());
            return "form-task";
        }

        // Define por padrão o atributo concluido como falso (tarefa em aberto)
        newTask.setConcluido(false);

        // Try-catch pode retornar duas exceções, conforme definido no métido saveTask(), contudo, é feito um tratamento genérico, pois o resultado é quase o mesmo: a terfa já existe
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

    // Remove a tarefa por ID
    @GetMapping("task/delete/{id}")
    public String deletarTarefa(@PathVariable("id") Long id, ModelMap modalMap) {
        taskService.deleteByIdTask(id);
        return "redirect:/home";
    }

    // Retorna os dados de uma tarefa antes de EDITAR para visuação no HTML
    @GetMapping("task/edit/{id}")
    public String preEditarTarefa(@PathVariable Long id, ModelMap model) throws NoSuchFieldException {
        model.addAttribute("task", taskService.findByIdTask(id));
        model.addAttribute("resps", responsavelService.findAllResponsavel());
        model.addAttribute("responsavel", new Responsavel());

        return "form-task";
    }

    // As tarefas já salvas, possuem a mesma validação de dados das tarefas novas, com exceção do nome da tarefa, pois aqui é permitido manter a descricação da tarefa para não se caracterizar como duplicidade, uma vez que a descrição desta tarefa já existia.
    @PostMapping("/task/edit")
    public String editarTarefa(@Valid @ModelAttribute("task") Task taskEditada,
                               BindingResult result, ModelMap model,
                               RedirectAttributes redirectAttributes) throws NoSuchFieldException {
        //Validação dos dados de entrada e retorno visual com os erros ao usuário
        if (result.hasErrors()) {
            model.addAttribute("responsavel", new Responsavel());

            return "redirect:/home";
        }

        // taskOriginal é instanciado para manter o atributo concluído da tarefa que está sendo editada, uma vez que este atributo somente é alterado em /home
        Task taskOriginal = taskService.findByIdTask(taskEditada.getId());
        taskEditada.setConcluido(taskOriginal.getConcluido());

        taskService.saveTask(taskEditada);

        // Adicione a mensagem de SUCESSO aqui
        redirectAttributes.addFlashAttribute("mensagemSucesso", "Tarefa atualizada com sucesso!");

        return "redirect:/home";
    }


    // Retorna uma lista de tarefas ao HTML
    @GetMapping("/home")
    public String verListaTarefas(ModelMap model) {
        model.addAttribute("tasks", taskService.findAllTask());
        return "home";
    }

    // Redireciona ao /home em caso de URL sem endpoint
    @GetMapping("/")
    public String redirecionarHome(ModelMap model) {
        return verListaTarefas(model);
    }

    // Altera o atributo 'concluido' da tarefa de 'false' (em aberto) para 'true' (Concluído)
    // Também é verificado se a tarefa já está concluída para não realizar a mesma operação.
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

    // Endpoint /aboutme para mais informações do sistema
    @GetMapping("aboutme")
    public String aboutMe(ModelMap model) {
        return "aboutme";
    }


}
