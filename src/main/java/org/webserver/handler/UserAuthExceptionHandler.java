package org.webserver.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.webserver.exception.InvalidUserException;

@Slf4j
@ControllerAdvice
public class UserAuthExceptionHandler {
    @ExceptionHandler({InvalidUserException.class})
    public String handleInvalidUser(RuntimeException e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        return "SignInPage";
    }
}


