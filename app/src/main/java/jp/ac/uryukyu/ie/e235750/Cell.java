package jp.ac.uryukyu.ie.e235750;

import java.awt.*;
import java.util.Random;

import javax.swing.*;

public class Cell extends JFrame{
    private ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/default.png")); //デフォルトのボタンアイコン
    private ImageIcon bombIcon    = new ImageIcon(getClass().getResource("/bomb.png"));    //爆弾のボタンアイコン
    private ImageIcon flagIcon    = new ImageIcon(getClass().getResource("/flag.png"));    //旗のアイコン

    private int FIELD_ROW         = 5;   //フィールドの行
    private int FIELD_COLUMN      = 5;   //フィールドの列
    private int WINDOW_WIDTH      = 600; //ウィンドウの横幅
    private int WINDOW_HEIGHT     = 600; //ウィンドウの縦幅
    private int IMAGE_WIDTH       = 50;  //画像の横幅
    private int IMAGE_HEIGHT      = 50;  //画像の縦幅

    public Cell(String title){
        this.showGameWindow(title);
    }

    public void showGameWindow(String title){
        //ウィンドウの設定
        setTitle(title);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(FIELD_COLUMN, FIELD_ROW));

        CustomButton[][] buttons = new CustomButton[FIELD_ROW][FIELD_COLUMN];
        //セルの作成
        for (int x = 0; x < FIELD_ROW; x++) {
            for (int y = 0; y < FIELD_COLUMN; y++) {
                buttons[x][y] = new CustomButton(new ImageIcon(defaultIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                contentPane.add(buttons[x][y]);
            }
        }

        bombSetter(buttons);
    }

    //ボムを設置する
    public void bombSetter(CustomButton[][] buttons){
        Random rand = new Random();
        int numBomb = 0;
        while(numBomb < 10){
            int i = rand.nextInt(FIELD_ROW);
            int j = rand.nextInt(FIELD_COLUMN);

            if(!buttons[i][j].isBomb()){
                buttons[i][j].setBomb();
                numBomb ++;
            }
        }
    }

    //セルに周りの爆弾の数情報を追加
    public void bombCountNearby(CustomButton[][] buttons){
        for(int x = 0; x < FIELD_ROW; x ++){
            for(int y = 0; y < FIELD_COLUMN; y ++){
                if(!buttons[x][y].isBomb()){
                    int bombCountNearby = 0;
                    for(int i = x - 1; i <= x + 1; i ++){
                        for(int j = y - 1; j <= y + 1; j ++){
                            if(i >= 0 && i < FIELD_ROW && j >= 0 && j < FIELD_COLUMN){
                                if(buttons[i][j].isBomb()){
                                    bombCountNearby ++;
                                }
                            }
                        }
                    }
                    buttons[x][y].setBombCountNearby(bombCountNearby);
                }
            }
        }
    }

    //旗の入れ替え
    public void toggleFlag(CustomButton buttons){
        buttons.setFlag(!buttons.isFlag());

        if(buttons.isFlag()){
            buttons.setIcon(new ImageIcon(flagIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
        } else{
            buttons.setIcon(new ImageIcon(defaultIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
        }
    }
}
