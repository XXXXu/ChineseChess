package com.example.mi.myapplication;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/8.
 */

public class KillKing {

    /**
     * 判断color方是否会被将军
     * @param pieceArrayList    //当前棋盘布局
     * @param color                 //1：红方  0：黑方
     * @return
     */
    public static boolean isKillKing(ArrayList<Piece> pieceArrayList,int color){//判断color色是否会被将军

        Point kingPoint = getKingPointByPart(pieceArrayList,color);

        if(kingPoint == null){
            return true;
        }

        if(isCastleKillKing(pieceArrayList,color,kingPoint)) return true;
        if(isCannonKillKing(pieceArrayList,color,kingPoint)) return true;
        if(isKnightKillKing(pieceArrayList,color,kingPoint)) return true;
        if(isSoldierKillKing(pieceArrayList,color,kingPoint)) return true;

        return false;
    }

    /**
     * 判断是否会被对方兵将军
     * @param pieceArrayList
     * @param color
     * @param kingPoint         //当前color方将军位置
     * @return
     */
    private static boolean isSoldierKillKing(ArrayList<Piece> pieceArrayList, int color, Point kingPoint) {
        if(color == 1){//帅
            if(getPartByPoint(pieceArrayList,kingPoint.x,kingPoint.y-1).equals("卒")){
                return true;
            }
            if(getPartByPoint(pieceArrayList,kingPoint.x-1,kingPoint.y).equals("卒")){
                return true;
            }
            if(getPartByPoint(pieceArrayList,kingPoint.x+1,kingPoint.y).equals("卒")){
                return true;
            }
        }else{//将
            if(getPartByPoint(pieceArrayList,kingPoint.x,kingPoint.y+1).equals("兵")){
                return true;
            }
            if(getPartByPoint(pieceArrayList,kingPoint.x-1,kingPoint.y).equals("兵")){
                return true;
            }
            if(getPartByPoint(pieceArrayList,kingPoint.x+1,kingPoint.y).equals("兵")){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否会被对方马将军
     * @param pieceArrayList
     * @param color
     * @param kingPoint         //当前color方将军位置
     * @return
     */
    private static boolean isKnightKillKing(ArrayList<Piece> pieceArrayList, int color, Point kingPoint) {
        int otherColor = -2;
        if(color == 0){
            otherColor = 1;
        }else{
            otherColor = 0;
        }

        if(isColor(kingPoint.x-1,kingPoint.y-1,pieceArrayList) == -1){
            if((isColor(kingPoint.x-1,kingPoint.y-2,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x-1,kingPoint.y-2).equals("馬"))
                    ||(isColor(kingPoint.x-2,kingPoint.y-1,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x-2,kingPoint.y-1).equals("馬"))){
                return true;
            }
        }

        if(isColor(kingPoint.x-1,kingPoint.y+1,pieceArrayList) == -1){
            if((isColor(kingPoint.x-2,kingPoint.y+1,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x-2,kingPoint.y+1).equals("馬"))
                    ||(isColor(kingPoint.x-1,kingPoint.y+2,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x-1,kingPoint.y+2).equals("馬"))){
                return true;
            }
        }

        if(isColor(kingPoint.x+1,kingPoint.y-1,pieceArrayList) == -1){
            if((isColor(kingPoint.x+2,kingPoint.y-1,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x+2,kingPoint.y-1).equals("馬"))
                    ||(isColor(kingPoint.x+1,kingPoint.y-2,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x+1,kingPoint.y-2).equals("馬"))){
                return true;
            }
        }

        if(isColor(kingPoint.x+1,kingPoint.y+1,pieceArrayList) == -1){
            if((isColor(kingPoint.x+2,kingPoint.y+1,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x+2,kingPoint.y+1).equals("馬"))
                    ||(isColor(kingPoint.x+1,kingPoint.y+2,pieceArrayList) == otherColor && getPartByPoint(pieceArrayList,kingPoint.x+1,kingPoint.y+2).equals("馬"))){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否会被对方炮将军
     * @param pieceArrayList
     * @param color
     * @param kingPoint
     * @return
     */
    private static boolean isCannonKillKing(ArrayList<Piece> pieceArrayList, int color, Point kingPoint) {
        int flag,nowColor;
        //判断将下方是否有炮
        flag = 0;
        for(int i = kingPoint.y;i<=9;i++){
            nowColor = isColor(kingPoint.x,i,pieceArrayList);
            if( i != kingPoint.y) {
                if( nowColor != -1){//y方向上有棋子
                    flag++;
                    if(flag == 2){//判断第二个棋子是否是己方
                        if(nowColor == color) {//该位置上是己方棋子，跳出循环
                            break;
                        }else{
                            if(getPartByPoint(pieceArrayList,kingPoint.x,i).equals("炮")){//判断是否是对方的炮
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //判断将上方是否有炮
        flag = 0;
        for(int i = kingPoint.y;i>=0;i--){
            nowColor = isColor(kingPoint.x,i,pieceArrayList);
            if( i != kingPoint.y) {
                if( nowColor != -1){//y方向上有棋子
                    flag++;
                    if(flag == 2){//判断第二个棋子是否是己方
                        if(nowColor == color) {//该位置上是己方棋子，跳出循环
                            break;
                        }else{
                            if(getPartByPoint(pieceArrayList,kingPoint.x,i).equals("炮")){//判断是否是对方的炮
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //判断将左边是否有炮
        flag = 0;
        for(int i = kingPoint.x;i>=0;i--){
            nowColor = isColor(i,kingPoint.y,pieceArrayList);
            if( i != kingPoint.x) {
                if( nowColor != -1){//x方向上有棋子
                    flag++;
                    if(flag == 2){//判断第二个棋子是否是己方
                        if(nowColor == color) {//该位置上是己方棋子，跳出循环
                            break;
                        }else{
                            if(getPartByPoint(pieceArrayList,i,kingPoint.y).equals("炮")){//判断是否是对方的炮
                                return true;
                            }
                        }
                    }
                }
            }
        }
        //判断将右边是否有炮
        flag = 0;
        for(int i = kingPoint.x;i<=9;i++){
            nowColor = isColor(i,kingPoint.y,pieceArrayList);
            if( i != kingPoint.x) {
                if( nowColor != -1){//y方向上有棋子
                    flag++;
                    if(flag == 2){//判断第二个棋子是否是己方
                        if(nowColor == color) {//该位置上是己方棋子，跳出循环
                            break;
                        }else{
                            if(getPartByPoint(pieceArrayList,i,kingPoint.y).equals("炮")){//判断是否是对方的炮
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * 判断車是否能将军，true表示能将军
     * @param pieceArrayList    当前布局
     * @param color               将军角色
     * @param kingPoint         将军位置
     */
    private static boolean isCastleKillKing(ArrayList<Piece> pieceArrayList, int color, Point kingPoint) {
        int nowColor;
        //判断将下方是否有車
        for(int i = kingPoint.y;i<=9;i++){
            nowColor = isColor(kingPoint.x,i,pieceArrayList);
            if( i != kingPoint.y){
                if( nowColor != -1){//y方向上有棋子
                    if(nowColor == color) {//该方向上是己方棋子，跳出循环
                        break;
                    }else{
                        if(getPartByPoint(pieceArrayList,kingPoint.x,i).equals("車")){
                            return true;
                        }
                    }
                }
            }
        }
        //判断将上方是否有車
        for(int i = kingPoint.y;i>=0;i--){
            nowColor = isColor(kingPoint.x,i,pieceArrayList);
            if( i != kingPoint.y){
                if( nowColor != -1){//y方向上有棋子
                    if(nowColor == color) {//该方向上是己方棋子，跳出循环
                        break;
                    }else{
                        if(getPartByPoint(pieceArrayList,kingPoint.x,i).equals("車")){
                            return true;
                        }
                    }
                }
            }
        }
        //判断将左边是否有車
        for(int i = kingPoint.x;i>=0;i--){
            nowColor = isColor(i,kingPoint.y,pieceArrayList);
            if( i != kingPoint.x){
                if( nowColor != -1){//y方向上有棋子
                    if(nowColor == color) {//该方向上是己方棋子，跳出循环
                        break;
                    }else{
                        if(getPartByPoint(pieceArrayList,i,kingPoint.y).equals("車")){
                            return true;
                        }
                    }
                }
            }
        }
        //判断将右边是否有車
        for(int i = kingPoint.x;i<=9;i++){
            nowColor = isColor(i,kingPoint.y,pieceArrayList);
            if( i != kingPoint.x){
                if( nowColor != -1){//y方向上有棋子
                    if(nowColor == color) {//该方向上是己方棋子，跳出循环
                        break;
                    }else{
                        if(getPartByPoint(pieceArrayList,i,kingPoint.y).equals("車")){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    //根据坐标获取角色值
    public static String getPartByPoint(ArrayList<Piece> pieceArrayList,int x,int y){
        Piece piece = new Piece(x,y);
        String part = " ";
        if(pieceArrayList.contains(piece)){
            part = pieceArrayList.get(pieceArrayList.indexOf(piece)).getPart();
        }
        return part;
    }

    //根据颜色获取将军坐标
    public static Point getKingPointByPart(ArrayList<Piece> pieceArrayList,int color){
        Point point = null;
        if(color == 1){
            for(int i = 0;i<pieceArrayList.size();i++){
                if(pieceArrayList.get(i).getPart().equals("帅")){
                    point = new Point(pieceArrayList.get(i).getX(),pieceArrayList.get(i).getY());
                    break;
                }
            }
        }else{
            for(int i = 0;i<pieceArrayList.size();i++){
                if(pieceArrayList.get(i).getPart().equals("将")){
                    point = new Point(pieceArrayList.get(i).getX(),pieceArrayList.get(i).getY());
                    break;
                }
            }
        }
        return point;
    }

    //判断当前坐标是否有棋子
    public static int isColor(int x,int y,ArrayList<Piece> pieceArrayList){//-1表示该点无棋子
        Piece piece = new Piece(x,y);
        int color;
        if(pieceArrayList.contains(piece)){
            color = pieceArrayList.get(pieceArrayList.indexOf(piece)).getColor();
            return color;
        }else{
            return -1;
        }
    }
}
