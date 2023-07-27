package strategies.winningStrategy;

import models.Board;
import models.Cell;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements  GameWinningStrategy{
        
    private List<HashMap<Character,Integer>> rowCounts = new ArrayList<>();
    private List<HashMap<Character,Integer>> colCounts = new ArrayList<>();
    private HashMap<Character,Integer> leftDiagonalCounts  = new HashMap<>();
    private HashMap<Character,Integer> rightDiagonalCounts  = new HashMap<>();
    
    public OrderOneWinningStrategy(int dimension){
        for(int i=0;i<dimension;++i){
            rowCounts.add(new HashMap<>());
            colCounts.add(new HashMap<>());
        }
    }
    public boolean isleftDiagonal(int row, int col){
        return row==col;
    }
    public boolean isrightDiagonal(int row,int col, int dimension){
        return row+col == dimension-1;

    }
    @Override
    public void updateMaps(Board board,  Cell moveCell) {
        char symbol = moveCell.getPlayer().getSymbol();
        int row= moveCell.getRow();
        int col= moveCell.getCol();
        int dimension = board.getDimension();
        rowCounts.get(row).put(symbol, rowCounts.get(row).get(symbol) -1);
        colCounts.get(col).put(symbol,colCounts.get(col).get(symbol)-1);
        if(isleftDiagonal(row,col)){
            leftDiagonalCounts.put(symbol, leftDiagonalCounts.get(symbol)-1);
        }
        if(isrightDiagonal(row,col,dimension)){
            rightDiagonalCounts.put(symbol,rightDiagonalCounts.get(symbol)-1);
        }
    }
    @Override
    public boolean checkWinner(Board board, Player lastMovePlayer, Cell moveCell) {
        char symbol = moveCell.getPlayer().getSymbol();
        int row = moveCell.getRow();
        int col = moveCell.getCol();
        int dimension = board.getBoard().size();
        //check row wise
        if(!rowCounts.get(row).containsKey(symbol)){
            rowCounts.get(row).put(symbol,1);
        }else {
            rowCounts.get(row).put(symbol, rowCounts.get(row).get(symbol) + 1);
        }

        //check col wise
        if(!colCounts.get(col).containsKey(symbol)){
            colCounts.get(col).put(symbol,1);
        }else {
            colCounts.get(col).put(symbol, colCounts.get(col).get(symbol) + 1);
        }
        //check diag wise

        if(isleftDiagonal(row,col )){
            if(!leftDiagonalCounts.containsKey(symbol)){
                leftDiagonalCounts.put(symbol,0);
            }
                leftDiagonalCounts.put(symbol, leftDiagonalCounts.get(symbol) +1 );

        }

        if(isrightDiagonal(row,col,dimension)){
            if(!rightDiagonalCounts.containsKey(symbol)){
                rightDiagonalCounts.put(symbol,0);
            }
                rightDiagonalCounts.put(symbol, rightDiagonalCounts.get(symbol)+1 );
        }

        if( rowCounts.get(row).get(symbol) ==dimension || colCounts.get(col).get(symbol) == dimension ){
            return true;
        }

        if(isleftDiagonal(row,col) && leftDiagonalCounts.get(symbol)==dimension)
            return true;

        if(isrightDiagonal(row,col,dimension) && rightDiagonalCounts.get(symbol)==dimension)
            return true;

        return false;
    }
}
