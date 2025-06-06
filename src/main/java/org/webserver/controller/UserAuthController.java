package org.webserver.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.webserver.consts.UserAuthConst;
import org.webserver.dto.UserAuthDto;
import org.webserver.dto.UserResponse;
import org.webserver.service.UserService;

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
            @ModelAttribute UserAuthDto userAuthDto,
            HttpSession session,
            Model model) {
        UserResponse userResponse = userService.signInUser(userAuthDto);
        if(userResponse == null) {
            model.addAttribute("error", "Invalid username or password");
            return "SignInPage";
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
}
