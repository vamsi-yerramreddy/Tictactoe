package models;

import java.util.ArrayList;
import java.util.List;

public class Board  {
    private List<List<Cell>> board;



    public Board(int dimension){
        this.board = new ArrayList<>();
        for(int i=0;i<dimension;++i){
           this.board.add(new ArrayList<>());
             for(int j=0;j<dimension;++j){
                this.board.get(i).add(new Cell(i,j));
             }
        }
    }


    public int getDimension(){
        return this.board.size();
    }
      public Board deepCopy(int dimension) {
          Board clonedBoard = new Board(dimension);

          // Perform a deep copy of the board's cells and their states
          for (int row = 0; row < dimension; row++) {
              for (int col = 0; col < dimension; col++) {
                  Cell originalCell = this.board.get(row).get(col);
                  Cell clonedCell = new Cell(row, col,originalCell.getCellState());
                  if (originalCell.getPlayer() != null) {
                      Player player = new Player(
                              originalCell.getPlayer().getName(),
                              originalCell.getPlayer().getSymbol(),
                              originalCell.getPlayer().getType());

                      clonedCell.setPlayer(player);
                  }
                  clonedBoard.getBoard().get(row).set(col, clonedCell);
              }
          }

          return clonedBoard;
      }


    public void display(){
        for (int i=0;i<board.size();++i){
            for(int  j=0;j<board.size();++j){
                if(board.get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    System.out.print("|    | ");
                } else
                    System.out.print("| " + board.get(i).get(j).getPlayer().getSymbol() + " |");
            }
            System.out.println("\n");
        }
    }
    public List<List<Cell>> getBoard(){

        return board;
    }
    public void setBoard(List<List<Cell>> board){

        this.board= board;
    }

}
