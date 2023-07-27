package models;

import factories.BotPlayingStrategyFactory;
import strategies.botPlayingStrategy.BotPlayingStrategy;

public class Bot extends  Player {
    private BotDifficultyLevel difficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;



    public Bot(String name, char symbol,BotDifficultyLevel level){
        super(name,symbol,PlayerType.BOT);
        this.difficultyLevel=level ;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategyForDifficultyLevel(level);


    }

    public void setDifficultyLevel(BotDifficultyLevel level){
        this.difficultyLevel=level;
    }
    public BotDifficultyLevel getDifficultyLevel(){
        return difficultyLevel;

    }
    @Override
    public  Move decideMove(Board board){
        return botPlayingStrategy.decideMove(this, board);


    }

}
