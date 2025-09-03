package academy.learnprogramming.controller;

import academy.learnprogramming.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller -> Is a web controller
@Slf4j
@Controller
public class DemoController {

    // == fields ==
    private final DemoService service;

    // == constructor ==

    @Autowired
    public DemoController(DemoService service) {
        this.service = service;
    }

    // http://localhost:8080/todo-list/hello
    @ResponseBody //return value bound to the web response body
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    // http://localhost:8080/todo-list/welcome
    // http://localhost:8080/todo-list/welcome?user=Felipe
    @GetMapping("welcome")
    public String welcome(@RequestParam String user, @RequestParam int age, Model model) {
        model.addAttribute("helloMessage", service.getHelloMessage(user));
        model.addAttribute("age", age);
        log.info("model= {}",model);

        // prefix + name + suffix
        // WEB-INF/welcome.jsp
        return "welcome";
    }

    // == Model Attribute ==
    @ModelAttribute ("welcomeMessage")
    public String welcomeMessage (){
        log.info("welcomeMessage() called!");
        return service.getWelcomeMessage();
    }
}
