package com.example.mi.myapplication;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by mi on 2018/1/11.
 */

public class PieceRoute {

    public static ArrayList<Point> RouteOfCastle(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        for (int i = piecePoint.x + 1; i < 9; i++) {
           if(IsArrive.isRouteOfCastle(piecePoint, new Point(i, piecePoint.y), pieceArrayList)) {
               pointArrayList.add(new Point(i, piecePoint.y));
            }else {
               break;
           }
        }
        for (int i = piecePoint.x - 1; i >= 0; i--) {
            if(IsArrive.isRouteOfCastle(piecePoint, new Point(i, piecePoint.y), pieceArrayList)) {
                pointArrayList.add(new Point(i, piecePoint.y));
            }else {
                break;
            }
        }
        for (int i = piecePoint.y + 1; i < 10; i++) {
            if(IsArrive.isRouteOfCastle(piecePoint, new Point(piecePoint.x, i), pieceArrayList)) {
                pointArrayList.add(new Point(piecePoint.x, i));
            }else {
                break;
            }
        }
        for (int i = piecePoint.y - 1; i >= 0; i--) {
            if(IsArrive.isRouteOfCastle(piecePoint, new Point(piecePoint.x, i), pieceArrayList)) {
                pointArrayList.add(new Point(piecePoint.x, i));
            }else {
                break;
            }
        }
        return pointArrayList;
    }

    public static ArrayList<Point> RouteOfKnight(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        //判断馬周围“日”字型的点
        if(piecePoint.x - 2 >= 0 && piecePoint.x - 2 < 9 && piecePoint.y -1 >= 0 && piecePoint.y - 1 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x-2, piecePoint.y-1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-2, piecePoint.y-1));
        }
        if(piecePoint.x - 1 >= 0 && piecePoint.x - 1 < 9 && piecePoint.y -2 >= 0 && piecePoint.y - 2 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x-1, piecePoint.y-2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-1, piecePoint.y-2));
        }
        if(piecePoint.x + 1 >= 0 && piecePoint.x + 1 < 9 && piecePoint.y - 2 >= 0 && piecePoint.y - 2 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x+1, piecePoint.y-2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+1, piecePoint.y-2));
        }
        if(piecePoint.x + 2 >= 0 && piecePoint.x + 2 < 9 && piecePoint.y - 1 >= 0 && piecePoint.y - 1 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x+2, piecePoint.y-1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+2, piecePoint.y-1));
        }
        if(piecePoint.x - 2 >= 0 && piecePoint.x - 2 < 9 && piecePoint.y + 1 >= 0 && piecePoint.y + 1 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x-2, piecePoint.y+1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-2, piecePoint.y+1));
        }
        if(piecePoint.x - 1 >= 0 && piecePoint.x - 1 < 9 && piecePoint.y + 2 >= 0 && piecePoint.y + 2 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x-1, piecePoint.y+2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-1, piecePoint.y+2));
        }
        if(piecePoint.x + 1 >= 0 && piecePoint.x + 1 < 9 && piecePoint.y + 2 >= 0 && piecePoint.y + 2 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x+1, piecePoint.y+2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+1, piecePoint.y+2));
        }
        if(piecePoint.x + 2 >= 0 && piecePoint.x + 2 < 9 && piecePoint.y + 1 >= 0 && piecePoint.y + 1 < 10
                && IsArrive.isRouteOfKnight(piecePoint, new Point(piecePoint.x+2, piecePoint.y+1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+2, piecePoint.y+1));
        }
        return pointArrayList;
    }
    public static ArrayList<Point> RouteOfBishop(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        //判断象周围“田”字型的点
        if(piecePoint.x - 2 >= 0 && piecePoint.x - 2 < 9 && piecePoint.y - 2 >= 0 && piecePoint.y - 2 < 10
                && IsArrive.isRouteOfBishop(piecePoint, new Point(piecePoint.x-2, piecePoint.y-2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-2, piecePoint.y-2));
        }
        if(piecePoint.x + 2 >= 0 && piecePoint.x + 2 < 9 && piecePoint.y - 2 >= 0 && piecePoint.y - 2 < 10
                && IsArrive.isRouteOfBishop(piecePoint, new Point(piecePoint.x+2, piecePoint.y-2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+2, piecePoint.y-2));
        }
        if(piecePoint.x - 2 >= 0 && piecePoint.x - 2 < 9 && piecePoint.y + 2 >= 0 && piecePoint.y + 2 < 10
                && IsArrive.isRouteOfBishop(piecePoint, new Point(piecePoint.x-2, piecePoint.y+2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-2, piecePoint.y+2));
        }
        if(piecePoint.x + 2 >= 0 && piecePoint.x + 2 < 9 && piecePoint.y + 2 >= 0 && piecePoint.y + 2 < 10
                && IsArrive.isRouteOfBishop(piecePoint, new Point(piecePoint.x+2, piecePoint.y+2), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+2, piecePoint.y+2));
        }
        return pointArrayList;
    }
    public static ArrayList<Point> RouteOfChancellor(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        if(piecePoint.x - 1 >= 0 && piecePoint.x - 1 < 9 && piecePoint.y - 1 >= 0 && piecePoint.y - 1 < 10
                && IsArrive.isRouteOfChancellor(piecePoint, new Point(piecePoint.x-1, piecePoint.y-1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-1, piecePoint.y-1));
        }
        if(piecePoint.x + 1 >= 0 && piecePoint.x + 1 < 9 && piecePoint.y - 1 >= 0 && piecePoint.y - 1 < 10
                && IsArrive.isRouteOfChancellor(piecePoint, new Point(piecePoint.x+1, piecePoint.y-1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+1, piecePoint.y-1));
        }
        if(piecePoint.x - 1 >= 0 && piecePoint.x - 1 < 9 && piecePoint.y + 1 >= 0 && piecePoint.y + 1 < 10
                && IsArrive.isRouteOfChancellor(piecePoint, new Point(piecePoint.x-1, piecePoint.y+1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-1, piecePoint.y+1));
        }
        if(piecePoint.x + 1 >= 0 && piecePoint.x + 1 < 9 && piecePoint.y + 1 >= 0 && piecePoint.y + 1 < 10
                && IsArrive.isRouteOfChancellor(piecePoint, new Point(piecePoint.x+1, piecePoint.y+1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+1, piecePoint.y+1));
        }
        return pointArrayList;
    }
    public static ArrayList<Point> RouteOfKing(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        if(piecePoint.x - 1 >= 0 && piecePoint.x - 1 < 9
                && IsArrive.isRouteOfKing(piecePoint, new Point(piecePoint.x-1, piecePoint.y), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-1, piecePoint.y));
        }
        if(piecePoint.x + 1 >= 0 && piecePoint.x + 1 < 9
                && IsArrive.isRouteOfKing(piecePoint, new Point(piecePoint.x+1, piecePoint.y), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+1, piecePoint.y));
        }
        if(piecePoint.y - 1 >= 0 && piecePoint.y - 1 < 10
                && IsArrive.isRouteOfKing(piecePoint, new Point(piecePoint.x, piecePoint.y-1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x, piecePoint.y-1));
        }
        if(piecePoint.y + 1 >= 0 && piecePoint.y + 1 < 10
                && IsArrive.isRouteOfKing(piecePoint, new Point(piecePoint.x, piecePoint.y+1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x, piecePoint.y+1));
        }
        return pointArrayList;
    }
    public static ArrayList<Point> RouteOfSoldier(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        if(piecePoint.x - 1 >= 0 && piecePoint.x - 1 < 9
                && IsArrive.isRouteOfSoldier(piecePoint, new Point(piecePoint.x-1, piecePoint.y), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x-1, piecePoint.y));
        }
        if(piecePoint.x + 1 >= 0 && piecePoint.x + 1 < 9
                && IsArrive.isRouteOfSoldier(piecePoint, new Point(piecePoint.x+1, piecePoint.y), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x+1, piecePoint.y));
        }
        if(piecePoint.y - 1 >= 0 && piecePoint.y - 1 < 10
                && IsArrive.isRouteOfSoldier(piecePoint, new Point(piecePoint.x, piecePoint.y-1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x, piecePoint.y-1));
        }
        if(piecePoint.y + 1 >= 0 && piecePoint.y + 1 < 10
                && IsArrive.isRouteOfSoldier(piecePoint, new Point(piecePoint.x, piecePoint.y+1), pieceArrayList)) {
            pointArrayList.add(new Point(piecePoint.x, piecePoint.y+1));
        }
        return pointArrayList;
    }
    public static ArrayList<Point> RouteOfCannon(Point piecePoint, ArrayList<Piece> pieceArrayList) {
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        for (int i = piecePoint.x + 1; i < 9; i++) {
            if(IsArrive.isRouteOfCannon(piecePoint, new Point(i, piecePoint.y), pieceArrayList)) {
                pointArrayList.add(new Point(i, piecePoint.y));
            }
        }
        for (int i = piecePoint.x - 1; i >= 0; i--) {
            if(IsArrive.isRouteOfCannon(piecePoint, new Point(i, piecePoint.y), pieceArrayList)) {
                pointArrayList.add(new Point(i, piecePoint.y));
            }
        }
        for (int i = piecePoint.y + 1; i < 10; i++) {
            if(IsArrive.isRouteOfCannon(piecePoint, new Point(piecePoint.x, i), pieceArrayList)) {
                pointArrayList.add(new Point(piecePoint.x, i));
            }
        }
        for (int i = piecePoint.y - 1; i >= 0; i--) {
            if(IsArrive.isRouteOfCannon(piecePoint, new Point(piecePoint.x, i), pieceArrayList)) {
                pointArrayList.add(new Point(piecePoint.x, i));
            }
        }
        return pointArrayList;
    }

   /* public int getColor(Point point,ArrayList<Piece> pieceArrayList){//-1表示该点无棋子
        Piece piece = new Piece(point.x,point.y);
        int color;
        if(pieceArrayList.contains(piece)){
            color = pieceArrayList.get(pieceArrayList.indexOf(piece)).getColor();
            return color;
        }else{
            return -1;
        }
    }*/
}
