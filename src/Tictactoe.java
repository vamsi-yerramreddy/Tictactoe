import controllers.GameController;
import models.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Tictactoe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        GameController gameController = new GameController();
        String option;
        do {
            System.out.println("Enter the dimensions of the game");
            int dim = input.nextInt();
            System.out.println("Will there be any Bots in the game? (y/n)");
            String isBotString = input.next();
            List<Player> players = new ArrayList<>();
            /*
            we need to have one less player than the dimension of the board
            */
            int toIterate = dim - 1;
            if (isBotString.equals("y")) {
                toIterate = dim - 2;
            }
            for (int i = 0; i < toIterate; ++i) {
                System.out.println("What is the name of player " + i);
                String playerName = input.next();
                System.out.println("What is the char of player " + i);
                String playerSymbol = input.next();
                players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));
            }

            if (isBotString.equals("y")) {
                System.out.println("What is the name of bot?");
                String playerName = input.next();

                System.out.println("What is the char of bot?");
                String playerSymbol = input.next();

                players.add(new Bot(playerName, playerSymbol.charAt(0), BotDifficultyLevel.EASY));
            }
            Game game = gameController.createGame(dim, players);
            int first=0;
            String  undoOption="n";
            while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
                System.out.println("This is the current status of the board");
                gameController.displayBoard(game);
                if(first!=0) {
                    System.out.println("Does anyone wants to undo(y/n)");
                    undoOption = input.next();
                }
                if(undoOption.equals("y")){
                    gameController.undo(game);
                    gameController.executeNextMove(game);
                }
                else {
                   gameController.executeNextMove(game);
                   first++;
                }
            }
            System.out.println("Game has ended .Result was ");
            if (!game.getGameStatus().equals(GameStatus.DRAW)) {
                System.out.println("Winner is : " + gameController.getWinner(game).getName());
            }
            System.out.println("Do you want to play a fresh game gain?(y/n) ");
             option = input.next();

        }while(option.equals("y"));
    }

}