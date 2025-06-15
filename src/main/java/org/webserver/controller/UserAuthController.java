package org.webserver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.webserver.consts.UserAuthConst;
import org.webserver.dto.UserAuthDto;
import org.webserver.exception.InvalidUserException;
import org.webserver.service.UserService;

import static org.webserver.dto.UserAuthDto.*;

@Controller
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userService;

    @GetMapping("/sign-in")
    public String signIn() {
        return "SignInPage";
    }

    @PostMapping("/sign-in")
    public String signInPost(
            @ModelAttribute UserAuthRequest userAuthRequest,
            HttpSession session,
            Model model) {
         UserAuthResponse userResponse = userService.signInUser(userAuthRequest);
        if(userResponse == null) {
            throw new InvalidUserException("sign in error");
        }else{
            session.setAttribute(UserAuthConst.LOGIN_KEY, userResponse);
            return "redirect:/";
        }
    }

    @GetMapping("/sign-out")
    public String signOut(HttpSession session) {
        session.removeAttribute(UserAuthConst.LOGIN_KEY);
        return "redirect:/";
    }

    @GetMapping("/sign-up")
    public String signUp() {
        return "SignUpPage";
    }

    @PostMapping("/sign-up")
    public String signUpPost(
            @ModelAttribute UserAuthRequest userAuthRequest,
            HttpSession session,
            Model model) {
        UserAuthResponse userResponse = userService.signUpUser(userAuthRequest);
        if(userResponse == null) {
            throw new InvalidUserException("sign in error");
        }else{
            session.setAttribute(UserAuthConst.LOGIN_KEY, userResponse);
            return "redirect:/";
        }
    }
}
