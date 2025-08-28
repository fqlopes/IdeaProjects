package academy.learnprogramming;

import academy.learnprogramming.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
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
