package models;

import java.util.Scanner;

public class Player {
    private String name;
    private char symbol;
    private PlayerType type;

    public Player(String name, char symbol,PlayerType type){
        this.name=name;
        this.symbol=symbol;
        this.type=type;
    }
    public Move decideMove(Board board){
        Scanner input  = new Scanner(System.in);
        System.out.println("please tell the row, starting from 0");
        int row = input.nextInt();
        System.out.println("please tell the col, starting from 0 ");
        int col = input.nextInt();
        return  new Move(this,new Cell(row,col));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public PlayerType getType() {
        return type;
    }
}
