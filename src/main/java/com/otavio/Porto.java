package com.otavio;

public class Porto {

    private int id;
    private int x;
    private int y;

    public Porto(char idASCII,int x, int y) {
        this.x = x;
        this.y = y;
        this.id = idASCII - '0';
    }

    @Override
    public String toString(){
        return "Porto: " + id + "\nX: " + x + "\nY: " + y + "\n";
    }

}
