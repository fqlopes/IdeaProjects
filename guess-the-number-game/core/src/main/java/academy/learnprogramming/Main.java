package academy.learnprogramming;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        //Creating the context (container)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//        //Forcing start (Spring 7 bullshit)
//        Game game = context.getBean(Game.class);
//        game.reset();

        //Getting the numberGenerator bean from context (container)
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        //Calling next() to get a number
        int number = numberGenerator.next();

        //Logging the generated number
        log.info("number = {}", number); //Similarly to printf %d,through logging, curly braces are used instead

        //CHALLENGE
        //Getting the messages from the MessageGenerator

        MessageGenerator messageGenerator = context.getBean(MessageGenerator.class);

        //Logging the message
        log.info("getMainMessage= {}", messageGenerator.getMainMessage());
        log.info("getResultMessage= {}", messageGenerator.getResultMessage());

        //closing the container (context)
        context.close(); //It's done to prevent memory resource leaks.
    }
}
