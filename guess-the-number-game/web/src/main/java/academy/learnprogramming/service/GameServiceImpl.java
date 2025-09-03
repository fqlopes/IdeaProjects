package academy.learnprogramming.service;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GameServiceImpl  implements GameService{

    //fields to link with spring
    private final Game game;
    private final MessageGenerator messageGenerator;

    //constructor

    @Autowired
    public GameServiceImpl(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    @Override
    public boolean isGameOver() {
        return game.isGameLost() || game.isGameWon();
    }

    @Override
    public String getMainMessage() {
        return messageGenerator.getMainMessage();
    }

    @Override
    public String getResultMessage() {
        return messageGenerator.getResultMessage();
    }

    @Override
    public void checkGuess(int guess) {
        game.setGuess(guess);
        game.check();
    }

    @Override
    public void reset() {
        game.reset();
    }

    // == innit ==
    @PostConstruct
    public void init (){
        log.info("LOGGING: game = {}", game);
        log.info("LOGGING: the number is = {}", game.getNumber());
    }



}
