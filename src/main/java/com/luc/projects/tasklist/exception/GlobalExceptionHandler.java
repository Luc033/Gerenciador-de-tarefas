package com.luc.projects.tasklist.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

// 1. @ControllerAdvice:
// Diz ao Spring que esta classe vai "aconselhar" todos os Controllers.
// Ela vai atuar como um interceptador global.
@ControllerAdvice
public class GlobalExceptionHandler {

    // 2. @ExceptionHandler(NullPointerException.class)
    // Este método só será chamado se um NullPointerException
    // escapar de QUALQUER controller da sua aplicação.
    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, Model model) {

        // 3. Preparamos o Model para o HTML
        // (ex.getMessage() pega a mensagem do erro, como "Cannot invoke...")
        model.addAttribute("mensagemErro", "Erro interno no servidor: " + ex.getMessage());

        // 4. Retornamos o nome do arquivo HTML que criamos
        return "pagina-de-erro";
    }

    // 5. VOCÊ PODE CRIAR UM "PEGA-TUDO"
    // Este método será chamado para QUALQUER outra exceção
    // que você não tratou especificamente (como a de cima).
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, Model model) {

        model.addAttribute("mensagemErro", "Ocorreu um erro inesperado: " + ex.getMessage());

        return "pagina-de-erro";
    }

    // Você pode adicionar quantos handlers quiser para exceções específicas:
    // @ExceptionHandler(MinhaExceptionCustomizada.class)
    // @ExceptionHandler(DataIntegrityViolationException.class)
    // etc.

}

