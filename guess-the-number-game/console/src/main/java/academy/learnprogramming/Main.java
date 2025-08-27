package academy.learnprogramming;

import academy.learnprogramming.config.GameConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Guess the Number Game");

        //Creating the context (container)
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        //Forcing start (Spring 7 bullshit)
        Game game = context.getBean(Game.class);
        game.reset();

        //Getting the numberGenerator bean from context (container)
        NumberGenerator numberGenerator = context.getBean(NumberGenerator.class);

        //closing the container (context)
        context.close(); //It's done to prevent memory resource leaks.
    }
}
