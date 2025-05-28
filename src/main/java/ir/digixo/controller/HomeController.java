package ir.digixo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {


    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/config")
    public String config() {
        return "config";
    }

    @GetMapping("/system")
    public String system() {
        return "system";
    }

}
