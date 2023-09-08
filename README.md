# Tic Tac Toe Game

This is a console  implementation of the classic Tic Tac Toe game in Java. 
It allows you to play the game with human players and, optionally, with a bot player. 
The game supports boards of various dimensions, and you can customize the names and symbols of the players.

## How to Play

1. Clone the repository or download the source code.

2. Compile the Java program:

    ```bash
   javac Tictactoe.java
   ```

3. Run the game:

   ```bash
   java Tictactoe
   ```

4. Follow the on-screen prompts to set up the game:
    - Enter the dimensions of the game board (e.g., 3 for a standard 3x3 board).
    - Specify if there will be any bot players (yes/no).
    - Provide names and symbols for human players.
    - If a bot player is included, enter its name and symbol.

5. Play the game by making moves according to the board layout displayed.

6. Enjoy the game and have fun!

## How the Code Works

The code is structured around the `GameController` class, which manages the game's logic. 
It allows you to create games with customizable dimensions and player configurations. 
The game continues until a player wins, it's a draw, or you choose to end it.
Used Builder pattern for Building the Game, Strategy pattern for Bot and Winning strategy
Implemented undo functionality 

## Contributors

Feel free to modify and extend the game as you see fit. Enjoy playing Tic Tac Toe!
