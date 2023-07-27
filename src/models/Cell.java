package models;

public class Cell {
    private  Player player;
    private  int row;
    private int col;
    private CellState cellState;

    public Cell(int r, int c  ){
        this.row=r;
        this.col=c;
        this.cellState=CellState.EMPTY;

    }
    public Cell(int r, int c, CellState state){
        this.row =r;
        this.col =c;
        this.cellState = state;

    }
    public Player getPlayer(){
        return player;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public CellState getCellState(){
        return cellState;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setCellState(CellState cellState) {
        this.cellState = cellState;
    }
}
