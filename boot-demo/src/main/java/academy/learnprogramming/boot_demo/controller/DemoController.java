package academy.learnprogramming.boot_demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    @ResponseBody
    @GetMapping("demo") //sets the page name
    public String demo(){
        log.info("METHOD demo() CALLED");
        return "Hello Spring boot";
    }


    @GetMapping("welcome")
    public String welcome(Model model) {
        log.info("METHOD welcome() CALLED");
        model.addAttribute("message", "Welcome to Spring Boot");
        return "welcome";
    }
}
