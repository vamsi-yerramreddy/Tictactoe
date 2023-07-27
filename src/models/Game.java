package models;

import exceptions.InvalidConstructorParametersException;
import strategies.winningStrategy.GameWinningStrategy;
import strategies.winningStrategy.OrderOneWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    //private Board boardJunk;
    List<Board> boardState;
    private List<Player> players;
    private List<Move> moves;
    private  GameStatus gameStatus;
    private int nextPlayerIndex;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;


    public List<Board> getBoardState() {
        return boardState;
    }

    public void setBoardState(List<Board> boardState) {
        this.boardState = boardState;
    }

    public Player getWinner(){
        return winner;
    }
    public  void setWinner(Player p){
        this.winner= p;

    }
    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy){
        this.gameWinningStrategy=gameWinningStrategy;
    }
    public GameWinningStrategy getGameWinningStrategy(){
        return gameWinningStrategy;
    }

    public Board getBoard() {
        return board;
    }
     public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public void undo()
    {
        if(boardState.size()==0){
            System.out.println("You are at the start point of the game : Start the game");

        }else{
            //send the last move details to map for update
            gameWinningStrategy.updateMaps(board, moves.get(moves.size()-1).getCell() );
            boardState.remove(boardState.size()-1);
            moves.remove(moves.size()-1);
            //System.out.println("THIS is the state of board after last state removal");
            boardState.get(boardState.size()-1).display();
           // System.out.println("display done. assigning the above board to the new board state");
            board= boardState.get(boardState.size()-1).deepCopy(this.board.getDimension());
            System.out.println("Last undo was success");
            board.display();
            nextPlayerIndex=(nextPlayerIndex-1 + players.size())%players.size();
        }

    }
    public void makeNextMove(){
        Player toMovePlayer = players.get(nextPlayerIndex); //to get the index of player
        System.out.println("It's " + players.get(nextPlayerIndex).getName() + "'s turn");

        Move move = toMovePlayer.decideMove(this.board);
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        System.out.println("Move happened at : " + row +" row and at " +
                col );
        board.getBoard().get(row).get(col).setCellState(CellState.OCCUPIED);
        board.getBoard().get(row).get(col).setPlayer(players.get(nextPlayerIndex));

        Move finalMove= new Move(players.get(nextPlayerIndex), board.getBoard().get(row).get(col));

        this.moves.add(finalMove);
        System.out.println("Before adding to state");
        boardState.add(this.board.deepCopy(this.board.getDimension()));

        System.out.println("Done added to board state ");
        System.out.println("Printing the list ");
        board.stateDisplay(boardState);

        if (gameWinningStrategy.checkWinner(board, players.get(nextPlayerIndex), finalMove.getCell()) ) {
                  gameStatus = GameStatus.ENDED;
                  winner = players.get(nextPlayerIndex);
                  board.display();
        }

        nextPlayerIndex+=1;
        nextPlayerIndex%=players.size();
    }

    public void  displayBoard(){
        this.board.display();
    }
    private Game(){

    }
    public static Builder getBuilder (){
      return new Builder();
    }

    public static class Builder {
        private int dimension;
        private List<Player> players;

        public Builder setDimension(int d){
            this.dimension= d;
            return this;
        }


        public Builder setPlayers(List<Player> players){
            this.players= players;
            return  this;
        }
        public boolean valid() throws InvalidConstructorParametersException {
                if(this.dimension<3){
                    throw new InvalidConstructorParametersException("dim must be more than or equal to 3");
                }
                if(this.players.size()!= this.dimension-1){
                    throw new InvalidConstructorParametersException("number of players must be size -1");
                }


                return true;
        }

        public Game build () throws InvalidConstructorParametersException{
            try{
                valid();
            }catch ( Exception e){
                    throw new InvalidConstructorParametersException(e.getMessage());
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setMoves(new ArrayList<>());
            game.setBoard(new Board(dimension));
            game.setNextPlayerIndex(0);
            game.setGameWinningStrategy(new OrderOneWinningStrategy(dimension));
            game.setBoardState(new ArrayList<>());

            return game;
        }
    }

}

