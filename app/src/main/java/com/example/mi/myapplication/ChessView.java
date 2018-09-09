package com.example.mi.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/6.
 */

public class ChessView extends View {

    private int mPanelWidth;
    private float mLineHeight;
    private int MAX_LINE = 10;
    private TextView tv_time;
    private boolean isRedTurn = true;
    private boolean isMove = false;
    private boolean stopThread = true;
    private Piece TouchPiece = null;
    private ArrayList<Point> movePointList = null;
    int remainTime = 20;
    private ArrayList<Piece> pieceArrayList = new ArrayList<Piece>();
    private int[][] chessArray= {{1,2,3,4,5,6,7,8,9},
                                    {0,0,0,0,0,0,0,0,0},
                                    {0,10,0,0,0,0,0,11,0},
                                    {12,0,13,0,14,0,15,0,16},
                                    {0,0,0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,0,0},
                                    {17,0,18,0,19,0,20,0,21},
                                    {0,22,0,0,0,0,0,23,0},
                                    {0,0,0,0,0,0,0,0,0},
                                    {24,25,26,27,28,29,30,31,32}};


    private Point myPoint;
    private float myRadius;

//    private Bitmap mWhitePiece;
//    private Bitmap mBlackPiece;

    private float ratioPieceLineHeight = 3 * 1.0f / 4;

    //白棋
    private boolean mIsWhite = true;
    private List<Point> mWhiteArray = new ArrayList<>();
    private List<Point> mBlackArray = new ArrayList<>();

    private Paint BoradPaint = new Paint();
    private Paint PiecePaint = new Paint();

    public ChessView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setBackgroundColor(0xCC897A67);
        init();
    }

    private void init() {


        initArrayList();
        //threadTime.start();
        threadDrawCicle.start();
    }

    public void initArrayList() {
        Piece piece1 = new Piece("車", 0, 0, 0);
        pieceArrayList.add(piece1);
        Piece piece2 = new Piece("馬", 1, 0, 0);
        pieceArrayList.add(piece2);
        Piece piece3 = new Piece("象", 2, 0, 0);
        pieceArrayList.add(piece3);
        Piece piece4 = new Piece("士", 3, 0, 0);
        pieceArrayList.add(piece4);
        Piece piece5 = new Piece("将", 4, 0, 0);
        pieceArrayList.add(piece5);
        Piece piece6 = new Piece("士", 5, 0, 0);
        pieceArrayList.add(piece6);
        Piece piece7 = new Piece("象", 6, 0, 0);
        pieceArrayList.add(piece7);
        Piece piece8 = new Piece("馬", 7, 0, 0);
        pieceArrayList.add(piece8);
        Piece piece9 = new Piece("車", 8, 0, 0);
        pieceArrayList.add(piece9);
        Piece piece10 = new Piece("炮", 1, 2, 0);
        pieceArrayList.add(piece10);
        Piece piece11 = new Piece("炮", 7, 2, 0);
        pieceArrayList.add(piece11);
        Piece piece12 = new Piece("卒", 0, 3, 0);
        pieceArrayList.add(piece12);
        Piece piece13 = new Piece("卒", 2, 3, 0);
        pieceArrayList.add(piece13);
        Piece piece14 = new Piece("卒", 4, 3, 0);
        pieceArrayList.add(piece14);
        Piece piece15 = new Piece("卒", 6, 3, 0);
        pieceArrayList.add(piece15);
        Piece piece16 = new Piece("卒", 8, 3, 0);
        pieceArrayList.add(piece16);
        Piece piece17 = new Piece("兵", 0, 6, 1);
        pieceArrayList.add(piece17);
        Piece piece18 = new Piece("兵", 2, 6, 1);
        pieceArrayList.add(piece18);
        Piece piece19 = new Piece("兵", 4, 6, 1);
        pieceArrayList.add(piece19);
        Piece piece20 = new Piece("兵", 6, 6, 1);
        pieceArrayList.add(piece20);
        Piece piece21 = new Piece("兵", 8, 6, 1);
        pieceArrayList.add(piece21);
        Piece piece22 = new Piece("炮", 1, 7, 1);
        pieceArrayList.add(piece22);
        Piece piece23 = new Piece("炮", 7, 7, 1);
        pieceArrayList.add(piece23);
        Piece piece24 = new Piece("車", 0, 9, 1);
        pieceArrayList.add(piece24);
        Piece piece25 = new Piece("馬", 1, 9, 1);
        pieceArrayList.add(piece25);
        Piece piece26 = new Piece("相", 2, 9, 1);
        pieceArrayList.add(piece26);
        Piece piece27 = new Piece("仕", 3, 9, 1);
        pieceArrayList.add(piece27);
        Piece piece28 = new Piece("帅", 4, 9, 1);
        pieceArrayList.add(piece28);
        Piece piece29 = new Piece("仕", 5, 9, 1);
        pieceArrayList.add(piece29);
        Piece piece30 = new Piece("相", 6, 9, 1);
        pieceArrayList.add(piece30);
        Piece piece31 = new Piece("馬", 7, 9, 1);
        pieceArrayList.add(piece31);
        Piece piece32 = new Piece("車", 8, 9, 1);
        pieceArrayList.add(piece32);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(isMove && movePointList.size() > 0) {
            drawMovePlace(canvas);
            canvas.drawCircle(myPoint.x, myPoint.y, myRadius, PiecePaint);
        }

        //如果是选中,则画出底部阴影和可行路线
        if(TouchPiece != null) {
            stopThread = false;//停止线程
            drawShadow(canvas, TouchPiece.getX(), TouchPiece.getY());
            isMove = false;//选中棋子时，对手行动提示消失
        }
        drawBorad(canvas);
        drawPieces(canvas);

    }

    public void drawMovePlace(final Canvas canvas) {
        drawStroke(canvas, getCoord(movePointList.get(0).x, movePointList.get(0).y), 4);
        //drawStroke(canvas, getCoord(movePointList.get(1).x, movePointList.get(1).y), 5);
        PiecePaint.setColor(0xFFFFFF00);
        PiecePaint.setStrokeWidth(4.0f);
        PiecePaint.setStyle(Paint.Style.STROKE);
        myPoint = getCoord(movePointList.get(1).x, movePointList.get(1).y);

    }
/*    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_time = findViewById(R.id.tv_time);
            tv_time.setText("倒计时: "+msg.what+"s");
            if(msg.what == 0) {
                remainTime = 20;
                isRedTurn = !isRedTurn;
            }
        }
    };

    //倒计时线程
    Thread threadTime = new Thread(){
        @Override
        public void run() {
            super.run();
            try {
                remainTime--;
                Message message = new Message();
                message.what = remainTime;
                handler.sendMessage(message);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };*/

    //画底部圆圈线程
    Thread threadDrawCicle = new Thread() {
        @Override
        public void run() {
            super.run();
            while(stopThread) {
                try {
                    //使半径保持在0.43倍mLineHeight到0.57倍mLineHeight直间变化
                    for (int i = 43; i <= 57; i++) {
                        myRadius = i;
                        Thread.sleep(80);
                        postInvalidate();
                        if (i == 57) {
                            for( ; i >= 43; i--) {
                                myRadius = i;
                                Thread.sleep(80);
                                postInvalidate();
                            }

                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    };

    private void drawPieces(Canvas canvas) {

        for(int i = 0; i < pieceArrayList.size(); i++) {
            Piece piece = pieceArrayList.get(i);
            drawOnePiece(canvas, piece.getPart(), piece.getX(), piece.getY(), piece.getColor());
        }
    }

    //str : 象棋文字  pieceColor:象棋颜色, 0代表黑色,1代表红色
    private void drawOnePiece(Canvas canvas, String str,int x, int y, int pieceColor) {
        int RadiusPiece = (int)(0.4*mLineHeight);
        Point p = getCoord(x, y);
        //获取更清晰的图样，防抖动
        PiecePaint.setDither(true);
        //抗锯齿
        PiecePaint.setFilterBitmap(true);
        PiecePaint.setColor(0xEEa57a35);
        PiecePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        PiecePaint.setStrokeWidth(5.0f);
        canvas.drawCircle(p.x, p.y, RadiusPiece, PiecePaint);
        PiecePaint.setStrokeWidth(7.0f);
        if(pieceColor == 1) {
            PiecePaint.setColor(0xEEc9000b);
        }else {
            PiecePaint.setColor(Color.BLACK);
        }
        PiecePaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(p.x, p.y, RadiusPiece*0.8f, PiecePaint);

        if(pieceColor == 1) {
            PiecePaint.setColor(0xEEc9000b);
        }else {
            PiecePaint.setColor(Color.BLACK);
        }

        PiecePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        PiecePaint.setTextSize(40);
        PiecePaint.setStrokeWidth(1.0f);


        Rect rect = new Rect();
        //获取文字高度与宽度
        PiecePaint.getTextBounds(str, 0, str.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        canvas.drawText(str, p.x-textWidth*0.5f, p.y+textHeight*0.4f, PiecePaint);
    }

    //点中象棋后，象棋底下变色
    private void drawShadow(Canvas canvas, int x, int y) {
        PiecePaint.setStyle(Paint.Style.STROKE);
        PiecePaint.setStrokeWidth(4.0f);
        Point p1 = getCoord(x, y);
        PiecePaint.setColor(0xDDC6FBFF);
        canvas.drawCircle(p1.x, p1.y, mLineHeight*0.45f, PiecePaint);
        PiecePaint.setColor(0xDDABFAFF);
        canvas.drawCircle(p1.x, p1.y, mLineHeight*0.5f, PiecePaint);
        PiecePaint.setColor(0xDD7EF7FF);
        canvas.drawCircle(p1.x, p1.y, mLineHeight*0.55f, PiecePaint);
        PiecePaint.setColor(0xDD42F4FF);
        canvas.drawCircle(p1.x, p1.y, mLineHeight*0.6f, PiecePaint);

        //可行路线
        ArrayList<Point> pointArrayList = new ArrayList<Point>();
        Log.w("AAA", x+" "+ y + " "+""+pieceArrayList.size());
        String part = pieceArrayList.get(pieceArrayList.indexOf(new Piece(x, y))).getPart();
        if(part.equals("車")){
            pointArrayList = PieceRoute.RouteOfCastle(new Point(x, y), pieceArrayList);
        }else if(part.equals("馬")){
            pointArrayList = PieceRoute.RouteOfKnight(new Point(x, y), pieceArrayList);
        }else if(part.equals("象") || part.equals("相")){
            pointArrayList = PieceRoute.RouteOfBishop(new Point(x, y), pieceArrayList);
        }else if(part.equals("士") || part.equals("仕")){
            pointArrayList = PieceRoute.RouteOfChancellor(new Point(x, y), pieceArrayList);
        }else if(part.equals("将") || part.equals("帅")){
            pointArrayList = PieceRoute.RouteOfKing(new Point(x, y), pieceArrayList);
        }else if(part.equals("炮")){
            pointArrayList = PieceRoute.RouteOfCannon(new Point(x, y), pieceArrayList);
        }else if(part.equals("卒") || part.equals("兵")){
            pointArrayList = PieceRoute.RouteOfSoldier(new Point(x, y), pieceArrayList);
        }
        //Log.w("AAA", ""+pointArrayList.size());
        for(Point p: pointArrayList) {
            //有棋子和没棋子的方框提示不同
            if(pieceArrayList.contains(new Piece(p.x, p.y))) {
                drawStroke(canvas, getCoord(p.x, p.y), 5);
            }else {
                drawStroke(canvas, getCoord(p.x, p.y), 4);
            }
        }
    }

    //将坐标转换为位置
    private Point getCoord(int x, int y) {
        return new Point((int)((x+1) * mLineHeight), (int)((y+0.5) * mLineHeight));
    }


    private void drawBorad(Canvas canvas) {
        BoradPaint.setColor(Color.BLACK);
        BoradPaint.setAntiAlias(true);
        BoradPaint.setDither(true);
        BoradPaint.setStyle(Paint.Style.STROKE);
        BoradPaint.setStrokeWidth(3.0f);

        int w = mPanelWidth;
        float lineHeight = mLineHeight;

        for(int i = 0; i<MAX_LINE; i++){
            int startX = (int)(lineHeight  );
            int endX = (int)(w-lineHeight  );
            int y = (int)((0.5 + i) * lineHeight);

            canvas.drawLine(startX, y, endX, y, BoradPaint);
        }
        int startX = (int)(lineHeight / 2 );
        int endX = (int)(w-lineHeight / 2 );
        int y = (int)((0.5 ) * lineHeight);

        //竖向左边界
        canvas.drawLine(y + lineHeight / 2 , startX, y +lineHeight / 2, endX,BoradPaint);

        y = (int)((0.5 + 8) * lineHeight);
        //竖向右边界
        canvas.drawLine(y + lineHeight / 2 , startX, y +lineHeight / 2, endX,BoradPaint);

        //竖向中间线条
        for(int i = 1; i<MAX_LINE - 2; i++){

            y = (int)((0.5 + i) * lineHeight);

            canvas.drawLine(y + lineHeight / 2 , startX, y +lineHeight / 2, endX / 2 - lineHeight / 4,BoradPaint);
            canvas.drawLine(y + lineHeight / 2 , startX + endX / 2 + lineHeight / 4, y +lineHeight / 2, endX,BoradPaint);
        }

        startX = (int)(lineHeight / 2 );
        y = (int)((0.5 ) * lineHeight);

        //将军区斜线
        canvas.drawLine(y + lineHeight / 2 + 3 * lineHeight,startX,y + lineHeight / 2 + 5 * lineHeight,startX + 2 * lineHeight,BoradPaint);
        canvas.drawLine(y + lineHeight / 2 + 5 * lineHeight,startX,y + lineHeight / 2 + 3 * lineHeight,startX + 2 * lineHeight,BoradPaint);

        canvas.drawLine(y + lineHeight / 2 + 3 * lineHeight,startX + 7 * lineHeight,y + lineHeight / 2 + 5 * lineHeight,startX + 9 * lineHeight,BoradPaint);
        canvas.drawLine(y + lineHeight / 2 + 5 * lineHeight,startX + 7 * lineHeight,y + lineHeight / 2 + 3 * lineHeight,startX + 9 * lineHeight,BoradPaint);

        //炮与兵位置装饰
        int x1 = (int)(lineHeight);
        int y1 = (int)((0.5) * lineHeight);
        int lineHeight1 = (int)lineHeight;

        //炮
        drawStroke(canvas,new Point(x1 + lineHeight1,y1 + lineHeight1 * 2),2);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 7,y1 + lineHeight1 * 2),2);
        drawStroke(canvas,new Point(x1 + lineHeight1,y1 + lineHeight1 * 7),2);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 7,y1 + lineHeight1 * 7),2);

        //中间六个兵
        drawStroke(canvas,new Point(x1 + lineHeight1 * 2,y1 + lineHeight1 * 3),2);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 4,y1 + lineHeight1 * 3),2);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 6,y1 + lineHeight1 * 3),2);

        drawStroke(canvas,new Point(x1 + lineHeight1 * 2,y1 + lineHeight1 * 6),2);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 4,y1 + lineHeight1 * 6),2);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 6,y1 + lineHeight1 * 6),2);

        //左边两个兵
        drawStroke(canvas,new Point(x1 ,y1 + lineHeight1 * 3),1);
        drawStroke(canvas,new Point(x1 ,y1 + lineHeight1 * 6),1);

        //右边两个兵
        drawStroke(canvas,new Point(x1 + lineHeight1 * 8,y1 + lineHeight1 * 3),3);
        drawStroke(canvas,new Point(x1 + lineHeight1 * 8,y1 + lineHeight1 * 6),3);

        //添加中间文字
        Paint textPaint = new Paint();
        textPaint.setTextSize(60f);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        canvas.drawText("楚  河",(int)(x1 + lineHeight1 * 1.3),(int)(y1 + lineHeight1 * 4.7),textPaint);
        canvas.drawText("漢  界",(int)(x1 + lineHeight1 * 5.3),(int)(y1 + lineHeight1 * 4.7),textPaint);

        //添加外边框


    }

    //给炮与兵进行位置装饰
    private void drawStroke(Canvas canvas, Point point,int sign) {//point修饰的点，sign：要展现的类型（1：只画右半边，2：完整，3：只画左半边, 4:画出显示能走路线的框

        float lineHeight = mLineHeight;

        switch (sign){
            case 1 :
                //右上
                canvas.drawLine(point.x+lineHeight/20,point.y-lineHeight/20,point.x+lineHeight/20,point.y-lineHeight/4,BoradPaint);
                canvas.drawLine(point.x+lineHeight/20,point.y-lineHeight/20,point.x+lineHeight/4 ,point.y-lineHeight/20,BoradPaint);
                //右下
                canvas.drawLine(point.x+lineHeight/20,point.y+lineHeight/20,point.x+lineHeight/20,point.y+lineHeight/4 ,BoradPaint);
                canvas.drawLine(point.x+lineHeight/20,point.y+lineHeight/20,point.x+lineHeight/4 ,point.y+lineHeight/20,BoradPaint);
                break;
            case 2 :
                //左上
                canvas.drawLine(point.x-lineHeight/20,point.y-lineHeight/20,point.x-lineHeight/20,point.y-lineHeight/4 ,BoradPaint);
                canvas.drawLine(point.x-lineHeight/20,point.y-lineHeight/20,point.x-lineHeight/4 ,point.y-lineHeight/20,BoradPaint);
                //右上
                canvas.drawLine(point.x+lineHeight/20,point.y-lineHeight/20,point.x+lineHeight/20,point.y-lineHeight/4,BoradPaint);
                canvas.drawLine(point.x+lineHeight/20,point.y-lineHeight/20,point.x+lineHeight/4 ,point.y-lineHeight/20,BoradPaint);
                //右下
                canvas.drawLine(point.x+lineHeight/20,point.y+lineHeight/20,point.x+lineHeight/20,point.y+lineHeight/4 ,BoradPaint);
                canvas.drawLine(point.x+lineHeight/20,point.y+lineHeight/20,point.x+lineHeight/4 ,point.y+lineHeight/20,BoradPaint);
                //左下
                canvas.drawLine(point.x-lineHeight/20,point.y+lineHeight/20,point.x-lineHeight/20,point.y+lineHeight/4 ,BoradPaint);
                canvas.drawLine(point.x-lineHeight/20,point.y+lineHeight/20,point.x-lineHeight/4 ,point.y+lineHeight/20,BoradPaint);
                break;
            case 3 :
                //左上
                canvas.drawLine(point.x-lineHeight/20,point.y-lineHeight/20,point.x-lineHeight/20,point.y-lineHeight/4 ,BoradPaint);
                canvas.drawLine(point.x-lineHeight/20,point.y-lineHeight/20,point.x-lineHeight/4 ,point.y-lineHeight/20,BoradPaint);
                //左下
                canvas.drawLine(point.x-lineHeight/20,point.y+lineHeight/20,point.x-lineHeight/20,point.y+lineHeight/4 ,BoradPaint);
                canvas.drawLine(point.x-lineHeight/20,point.y+lineHeight/20,point.x-lineHeight/4 ,point.y+lineHeight/20,BoradPaint);
                break;
            case 4 :
                PiecePaint.setColor(0xFFFFFF00);
                PiecePaint.setStrokeWidth(3.0f);
                //左上
                canvas.drawLine(point.x-lineHeight*0.3f, point.y-lineHeight*0.3f, point.x-lineHeight*0.2f, point.y-lineHeight*0.3f, PiecePaint);
                canvas.drawLine(point.x-lineHeight*0.3f, point.y-lineHeight*0.3f, point.x-lineHeight*0.3f, point.y-lineHeight*0.2f, PiecePaint);
                //右上
                canvas.drawLine(point.x+lineHeight*0.3f, point.y-lineHeight*0.3f, point.x+lineHeight*0.2f, point.y-lineHeight*0.3f, PiecePaint);
                canvas.drawLine(point.x+lineHeight*0.3f, point.y-lineHeight*0.3f, point.x+lineHeight*0.3f, point.y-lineHeight*0.2f, PiecePaint);
                //左下
                canvas.drawLine(point.x-lineHeight*0.3f, point.y+lineHeight*0.3f, point.x-lineHeight*0.2f, point.y+lineHeight*0.3f, PiecePaint);
                canvas.drawLine(point.x-lineHeight*0.3f, point.y+lineHeight*0.3f, point.x-lineHeight*0.3f, point.y+lineHeight*0.2f, PiecePaint);
                //右下
                canvas.drawLine(point.x+lineHeight*0.3f, point.y+lineHeight*0.3f, point.x+lineHeight*0.2f, point.y+lineHeight*0.3f, PiecePaint);
                canvas.drawLine(point.x+lineHeight*0.3f, point.y+lineHeight*0.3f, point.x+lineHeight*0.3f, point.y+lineHeight*0.2f, PiecePaint);
                break;
            case 5 :
                PiecePaint.setColor(0xFFFFFF00);
                PiecePaint.setStrokeWidth(5.0f);
                //左上
                canvas.drawLine(point.x-lineHeight*0.4f, point.y-lineHeight*0.4f, point.x-lineHeight*0.3f, point.y-lineHeight*0.4f, PiecePaint);
                canvas.drawLine(point.x-lineHeight*0.4f, point.y-lineHeight*0.4f, point.x-lineHeight*0.4f, point.y-lineHeight*0.3f, PiecePaint);
                //右上
                canvas.drawLine(point.x+lineHeight*0.4f, point.y-lineHeight*0.4f, point.x+lineHeight*0.3f, point.y-lineHeight*0.4f, PiecePaint);
                canvas.drawLine(point.x+lineHeight*0.4f, point.y-lineHeight*0.4f, point.x+lineHeight*0.4f, point.y-lineHeight*0.3f, PiecePaint);
                //左下
                canvas.drawLine(point.x-lineHeight*0.4f, point.y+lineHeight*0.4f, point.x-lineHeight*0.3f, point.y+lineHeight*0.4f, PiecePaint);
                canvas.drawLine(point.x-lineHeight*0.4f, point.y+lineHeight*0.4f, point.x-lineHeight*0.4f, point.y+lineHeight*0.3f, PiecePaint);
                //右下
                canvas.drawLine(point.x+lineHeight*0.4f, point.y+lineHeight*0.4f, point.x+lineHeight*0.3f, point.y+lineHeight*0.4f, PiecePaint);
                canvas.drawLine(point.x+lineHeight*0.4f, point.y+lineHeight*0.4f, point.x+lineHeight*0.4f, point.y+lineHeight*0.3f, PiecePaint);
                //斜线
                canvas.drawLine(point.x-lineHeight*0.4f, point.y-lineHeight*0.4f, point.x+lineHeight*0.4f, point.y+lineHeight*0.4f, PiecePaint);
                canvas.drawLine(point.x-lineHeight*0.4f, point.y+lineHeight*0.4f, point.x+lineHeight*0.4f, point.y-lineHeight*0.4f, PiecePaint);
                break;
            default:
                break;
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) { //指明控件可获得的空间,前面2个bit用于区分不同的布局模式，后面30个bit存放的是尺寸的数据
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);//获取尺寸大小（父View提供的参考大小）
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);//获取测量模式（UNSPECIFIED	父容器没有对当前View有任何限制，当前View可以任意取尺寸
                                                                // EXACTLY	当前的尺寸就是当前View应该取的尺寸（match_parent）
                                                                //AT_MOST	当前尺寸是当前View能取的最大尺寸（wrap_content）

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = Math.min(widthSize,heightSize);

        if(widthMode == MeasureSpec.UNSPECIFIED){
            width = heightSize;
        }else if(heightMode == MeasureSpec.UNSPECIFIED){
            width = widthSize;
        }

        setMeasuredDimension(width,width);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {    //w，h是view当前的宽和高;oldw ,oldh是改变之前的宽和高
        super.onSizeChanged(w, h, oldw, oldh);

        mPanelWidth = w;
        mLineHeight = mPanelWidth * 1.0f / MAX_LINE;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_UP) {
            int x = (int)event.getX();
            int y = (int)event.getY();

            Point p = getValidPoint(x,y);
            Piece piece = new Piece(p.x, p.y);
            //是否是红方回合
            if(isRedTurn) {
                movePiece(piece, p, 1);
            }else {
                movePiece(piece, p, 0);
            }

        }

        return true;
    }
    public void movePiece(Piece piece, Point p, int color) {
        //点中的是否为棋子
        if(pieceArrayList.contains(piece)) {
            int place = pieceArrayList.indexOf(piece);
            //点中的是否为己方棋子
            if(pieceArrayList.get(place).getColor() == color) {
                TouchPiece = pieceArrayList.get(place);

                //重绘,显示底部阴影
                invalidate();
//Toast.makeText(getContext(),color+" "+TouchPiece.getPart() , Toast.LENGTH_SHORT).show();
            }else {
                //已经选定己方棋子,吃掉对方棋子
                if(TouchPiece != null) {
                    if(moveRule(TouchPiece, p, pieceArrayList)){
                        //事先存下移动之前和移动之后的位置
                        movePointList = new ArrayList<Point>();
                        movePointList.add(new Point(TouchPiece.getX(), TouchPiece.getY()));
                        movePointList.add(p);
                      //移除集合中该对方棋子
                        pieceArrayList.remove(piece);
                     //己方棋子放在移除棋子位置
                        int TouchPiecePlace = pieceArrayList.indexOf(TouchPiece);
                        pieceArrayList.get(TouchPiecePlace).setX(p.x);
                        pieceArrayList.get(TouchPiecePlace).setY(p.y);
                        if(color == 1){
                            if(KillKing.isKillKing(pieceArrayList,0)){
                                Toast.makeText(getContext(),"红方将军", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            if(KillKing.isKillKing(pieceArrayList,1)){
                                Toast.makeText(getContext(),"黑方将军", Toast.LENGTH_SHORT).show();
                            }
                        }

                        //标志移动完成，重绘后框出移动之前和移动之后的位置
                        isMove = true;
                        //重绘
                        invalidate();
                        TouchPiece = null;
                        //回合结束
                        isRedTurn = !isRedTurn;
                    }else {
// Toast.makeText(getContext(),"规则限制" , Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }else if(!pieceArrayList.contains(piece) && TouchPiece != null) {
            if(moveRule(TouchPiece, p, pieceArrayList)) {

                //事先存下移动之前和移动之后的位置
                movePointList = new ArrayList<Point>();
                movePointList.add(new Point(TouchPiece.getX(), TouchPiece.getY()));
                movePointList.add(p);

                //点中的是非棋子且已经选定己方棋子，则移动位置
                int place = pieceArrayList.indexOf(TouchPiece);
                pieceArrayList.get(place).setX(p.x);
                pieceArrayList.get(place).setY(p.y);
//Toast.makeText(getContext(),"移动位置" , Toast.LENGTH_SHORT).show();
                if(color == 1){
                    if(KillKing.isKillKing(pieceArrayList,0)){
                        Toast.makeText(getContext(),"红方将军", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if(KillKing.isKillKing(pieceArrayList,1)){
                        Toast.makeText(getContext(),"黑方将军", Toast.LENGTH_SHORT).show();
                    }
                }

                //标志移动完成，重绘后框出移动之前和移动之后的位置
                isMove = true;
                //重绘
                invalidate();
                TouchPiece = null;
                //回合结束
                isRedTurn = !isRedTurn;
            }else {
//Toast.makeText(getContext(),"规则限制" , Toast.LENGTH_SHORT).show();
            }
        }
    }
    //棋子移动规则，判断该棋子能否走到该位置
    public boolean moveRule(Piece piece, Point endPoint, ArrayList<Piece> pieceArrayList) {
        String part = piece.getPart();
        Point startPoint = new Point(piece.getX(), piece.getY());
//System.out.println(startPoint+" "+endPoint);
        if(part.equals("車")){
            return IsArrive.isRouteOfCastle(startPoint, endPoint, pieceArrayList);
        }else if(part.equals("馬")){
            return IsArrive.isRouteOfKnight(startPoint, endPoint, pieceArrayList);
        }else if(part.equals("象") || part.equals("相")){
            return IsArrive.isRouteOfBishop(startPoint, endPoint, pieceArrayList);
        }else if(part.equals("士") || part.equals("仕")){
            return IsArrive.isRouteOfChancellor(startPoint, endPoint, pieceArrayList);
        }else if(part.equals("将") || part.equals("帅")){
            return IsArrive.isRouteOfKing(startPoint, endPoint, pieceArrayList);
        }else if(part.equals("炮")){
            return IsArrive.isRouteOfCannon(startPoint, endPoint, pieceArrayList);
        }else if(part.equals("卒") || part.equals("兵")){
            return IsArrive.isRouteOfSoldier(startPoint, endPoint, pieceArrayList);
        }
        return false;
    }


    private Point getValidPoint(int x, int y) {
        return new Point((int)((x-0.5*mLineHeight) / mLineHeight),(int)(y / mLineHeight));
    }
}
