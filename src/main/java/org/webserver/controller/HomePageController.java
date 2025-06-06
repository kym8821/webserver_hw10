package org.webserver.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.webserver.consts.UserAuthConst;
import org.webserver.dto.UserResponse;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        UserResponse user = (UserResponse) session.getAttribute(UserAuthConst.LOGIN_KEY);
        if(user!=null) model.addAttribute("username", user.getUsername());
        return "HomePage";
    }
}
