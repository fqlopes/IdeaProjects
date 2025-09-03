package academy.learnprogramming.service;


import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String getHelloMessage(String user) {
        return "Hello, I am " + user;
    }

    @Override
    public String getWelcomeMessage() {
        return "This is my Demo Application using SpringMVC";
    }
}
