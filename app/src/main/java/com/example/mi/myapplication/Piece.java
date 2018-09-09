package com.example.mi.myapplication;

/**
 * Created by mi on 2018/1/7.
 */

public class Piece {
    private String part;
    private int x;
    private int y;//(x,y)坐标
    private int color;//0表示黑色，1表示红色

    public Piece() {
    }
    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Piece(String part, int x, int y, int color) {
        this.part = part;
        this.x = x;
        this.y = y;
        this.color = color;
    }
    @Override
    public boolean equals(Object obj) {
         if(this == obj) {
            return true;
         }
        if(null == obj) {
            return  false;
        }
        if(getClass() != obj.getClass()) {
             return false;
        }
        Piece p = (Piece)obj;
         if(x == p.getX() && y == p.getY() ) {
             return true;
         }
         return false;
    }



    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
