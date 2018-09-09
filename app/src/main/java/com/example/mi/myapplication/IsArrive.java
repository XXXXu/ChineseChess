package com.example.mi.myapplication;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/7.
 */

public class IsArrive {

    private static int lineCount = 9;      //横向格子数
    private static int columnCount = 10;   //纵向格子数


    /**
     * 判断車的路线
     * @param startPoint    車的当前位置
     * @param endPoint      車的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfCastle(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){
        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）
        int min,max;

        //当前位置不存在棋子或者越界，退出
        if(color == -1 || endPoint.x > 8){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }
        //路线判断
        if(startPoint.x == endPoint.x){
            min = Math.min(startPoint.y,endPoint.y);
            max = Math.max(startPoint.y,endPoint.y);
            for(int i = min + 1;i<max;i++){
                //两个位置间有其他棋子    退出
                if(isColor(new Point(endPoint.x,i),pieceArrayList) != -1){
                    return false;
                }
            }
        }else if(startPoint.y == endPoint.y){
            min = Math.min(startPoint.x,endPoint.x);
            max = Math.max(startPoint.x,endPoint.x);
            for(int i = min + 1;i<max;i++){
                //两个位置间有其他棋子    退出
                if(isColor(new Point(i,endPoint.y),pieceArrayList) != -1){
                    return false;
                }
            }
        }else{//不符合車的路线 退出
            return false;
        }
        //排除错误路线    返回正确路线
        return true;
    }

    /**
     * 判断马的路线
     * @param startPoint    马的当前位置
     * @param endPoint      马的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfKnight(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){

        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）

        //当前位置不存在棋子，退出
        if(color == -1 || endPoint.x > 8){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }
        //判断马的路线
        if(Math.abs(endPoint.x - startPoint.x) == 1 && endPoint.y-startPoint.y == 2){
            if(isColor(new Point(startPoint.x,startPoint.y + 1),pieceArrayList) != -1){
                return false;
            }
        }else if(Math.abs(endPoint.x - startPoint.x) == 1 && startPoint.y-endPoint.y == 2){
            if(isColor(new Point(startPoint.x,startPoint.y -1),pieceArrayList) != -1){
                return false;
            }
        }else if(Math.abs(endPoint.y - startPoint.y) == 1 && startPoint.x-endPoint.x == 2){
            if(isColor(new Point(startPoint.x - 1,startPoint.y ),pieceArrayList) != -1){
                return false;
            }
        }else if(Math.abs(endPoint.y - startPoint.y) == 1 && endPoint.x-startPoint.x == 2){
            if(isColor(new Point(startPoint.x + 1,startPoint.y ),pieceArrayList) != -1){
                return false;
            }
        }else {
            return false;
        }

        return true;
    }

    /**
     * 判断象的路线
     * @param startPoint    象的当前位置
     * @param endPoint      象的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfBishop(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){
        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）

        //当前位置不存在棋子，退出
        if(color == -1 || endPoint.x > 8){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }

        if(color == 1){ //红
            if(endPoint.y < 5){
                return false;
            }
            if(startPoint.x - endPoint.x == 2 && startPoint.y - endPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x - 1,startPoint.y - 1)) == false ){
                return true;
            }else if(startPoint.x - endPoint.x == 2 && endPoint.y - startPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x - 1,startPoint.y + 1)) == false){
                return true;
            }else if(endPoint.x - startPoint.x == 2 && endPoint.y - startPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x + 1,startPoint.y + 1)) == false){
                return true;
            }else if(endPoint.x - startPoint.x == 2 && startPoint.y - endPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x + 1,startPoint.y - 1)) == false){
                return true;
            }
        }else{          //黑
            if(endPoint.y >= 5){
                return false;
            }
            if(startPoint.x - endPoint.x == 2 && startPoint.y - endPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x - 1,startPoint.y - 1)) == false ){
                return true;
            }else if(startPoint.x - endPoint.x == 2 && endPoint.y - startPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x - 1,startPoint.y + 1)) == false){
                return true;
            }else if(endPoint.x - startPoint.x == 2 && endPoint.y - startPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x + 1,startPoint.y + 1)) == false){
                return true;
            }else if(endPoint.x - startPoint.x == 2 && startPoint.y - endPoint.y == 2 &&  pieceArrayList.contains(new Piece(startPoint.x + 1,startPoint.y - 1)) == false){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断士的路线
     * @param startPoint    士的当前位置
     * @param endPoint      士的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfChancellor(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){
        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）

        //当前位置不存在棋子，退出
        if(color == -1){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }

        if(color == 1){ //   红
            if(endPoint.x >=3 && endPoint.x <= 5 && endPoint.y >=7 && endPoint.y <=9){
                if(Math.abs(endPoint.x - startPoint.x) == 1 && Math.abs(endPoint.y - startPoint.y) == 1){
                    return true;
                }
            }
        }else{          //   黑
            if(endPoint.x >=3 && endPoint.x <= 5 && endPoint.y >=0 && endPoint.y <=2){
                if(Math.abs(endPoint.x - startPoint.x) == 1 && Math.abs(endPoint.y - startPoint.y) == 1){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断将的路线
     * @param startPoint    将的当前位置
     * @param endPoint      将的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfKing(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){
        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）

        //当前位置不存在棋子，退出
        if(color == -1){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }

        if(color == 1){ //   红
            if(endPoint.x >=3 && endPoint.x <= 5 && endPoint.y >=7 && endPoint.y <=9){
                if(Math.abs(endPoint.x - startPoint.x) == 1 && endPoint.y == startPoint.y) {
                    return true;
                }else if(Math.abs(endPoint.y - startPoint.y) == 1 && endPoint.x == startPoint.x){
                    return true;
                }
            }
        }else{          //   黑
            if(endPoint.x >=3 && endPoint.x <= 5 && endPoint.y >=0 && endPoint.y <=2){
                if(Math.abs(endPoint.x - startPoint.x) == 1 && endPoint.y == startPoint.y) {
                    return true;
                }else if(Math.abs(endPoint.y - startPoint.y) == 1 && endPoint.x == startPoint.x){
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 判断兵的路线
     * @param startPoint    兵的当前位置
     * @param endPoint      兵的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfSoldier(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){
        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）

        //当前位置不存在棋子，退出
        if(color == -1 || endPoint.x > 8){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }

        if(color == 1){ //   红
            if(startPoint.y <=6 && startPoint.y>=5){
                if(startPoint.y - endPoint.y == 1 && startPoint.x == endPoint.x) {
                    return true;
                }
            }else if(startPoint.y <= 4){
                if(startPoint.y - endPoint.y == 1 && startPoint.x == endPoint.x) {
                    return true;
                }else if(startPoint.y == endPoint.y && Math.abs(startPoint.x - endPoint.x) == 1){
                    return true;
                }
            }
        }else{          //   黑
            if(startPoint.y <=4 && startPoint.y>=3){
                if(endPoint.y - startPoint.y == 1 && startPoint.x == endPoint.x) {
                    return true;
                }
            }else if(startPoint.y >= 5){
                if(endPoint.y - startPoint.y == 1 && startPoint.x == endPoint.x) {
                    return true;
                }else if(startPoint.y == endPoint.y && Math.abs(startPoint.x - endPoint.x) == 1){
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * 判断炮的路线
     * @param startPoint    炮的当前位置
     * @param endPoint      炮的目标位置
     * @param pieceArrayList    当前棋盘布局
     * @return
     */
    public static boolean isRouteOfCannon(Point startPoint,Point endPoint,ArrayList<Piece> pieceArrayList){
        int color = isColor(startPoint,pieceArrayList);     //当前位置棋子状态（-1：不存在。0,1：颜色）
        int endColor = isColor(endPoint,pieceArrayList);    //目标位置棋子状态（-1：不存在。0,1：颜色）
        int min,max;

        //当前位置不存在棋子，退出
        if(color == -1 || endPoint.x > 8){
            return false;
        }
        //目标位置有我方棋子，退出
        if(color == endColor){
            return false;
        }
        //路线判断
        //目标位置没有敌方棋子，正常路线
        if(endColor == -1){
            if(startPoint.x == endPoint.x){
                min = Math.min(startPoint.y,endPoint.y);
                max = Math.max(startPoint.y,endPoint.y);
                for(int i = min + 1;i<max;i++){
                    //两个位置间有其他棋子    退出
                    if(isColor(new Point(endPoint.x,i),pieceArrayList) != -1){
                        return false;
                    }
                }
            }else if(startPoint.y == endPoint.y){
                min = Math.min(startPoint.x,endPoint.x);
                max = Math.max(startPoint.x,endPoint.x);
                for(int i = min + 1;i<max;i++){
                    //两个位置间有其他棋子    退出
                    if(isColor(new Point(i,endPoint.y),pieceArrayList) != -1){
                        return false;
                    }
                }
            }else{//不符合炮的路线 退出
                return false;
            }
        }else{//目标位置有敌方棋子，判断是否符合吃子条件
            int flag = 0;
            if(startPoint.x == endPoint.x){
                min = Math.min(startPoint.y,endPoint.y);
                max = Math.max(startPoint.y,endPoint.y);
                for(int i = min + 1;i<max;i++){
                    //两个位置间有其他棋子    退出
                    if(isColor(new Point(endPoint.x,i),pieceArrayList) != -1){
                        flag++;
                        if(flag > 1){//中间不止一个棋子，不能吃子，退出
                            return false;
                        }
                    }
                }
                if(flag == 0){
                    return false;
                }
            }else if(startPoint.y == endPoint.y){
                min = Math.min(startPoint.x,endPoint.x);
                max = Math.max(startPoint.x,endPoint.x);
                for(int i = min + 1;i<max;i++){
                    //两个位置间有其他棋子    退出
                    if(isColor(new Point(i,endPoint.y),pieceArrayList) != -1){
                        flag++;
                        if(flag > 1){//中间不止一个棋子，不能吃子，退出
                            return false;
                        }
                    }
                }
                if(flag == 0){
                    return false;
                }
            }else{//不符合炮的路线 退出
                return false;
            }
        }
        //排除错误路线    返回正确路线
        return true;
    }



    public static int isColor(Point point,ArrayList<Piece> pieceArrayList){//-1表示该点无棋子
        Piece piece = new Piece(point.x,point.y);
        int color;
        if(pieceArrayList.contains(piece)){
            color = pieceArrayList.get(pieceArrayList.indexOf(piece)).getColor();
            return color;
        }else{
            return -1;
        }
    }
}
