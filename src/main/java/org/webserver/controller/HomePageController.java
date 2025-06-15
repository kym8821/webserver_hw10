package org.webserver.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.webserver.consts.UserAuthConst;

import static org.webserver.dto.UserAuthDto.*;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        UserAuthResponse user = (UserAuthResponse) session.getAttribute(UserAuthConst.LOGIN_KEY);
        if(user!=null) model.addAttribute("username", user.getUsername());
        return "HomePage";
    }
}
