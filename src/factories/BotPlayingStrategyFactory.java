package factories;

import models.Board;
import models.BotDifficultyLevel;
import models.Move;
import strategies.botPlayingStrategy.BotPlayingStrategy;
import strategies.botPlayingStrategy.RandomBotPlayingStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getStrategyForDifficultyLevel(BotDifficultyLevel level){
        return new RandomBotPlayingStrategy();
    }


}
