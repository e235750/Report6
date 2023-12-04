package jp.ac.uryukyu.ie.e235750;

import java.awt.*;
import javax.swing.*;

public class Cell extends JFrame{
    private int FIELD_ROW         = 5;   //フィールドの行
    private int FIELD_COLUMN      = 5;   //フィールドの列
    private int WINDOW_WIDTH      = 600; //ウィンドウの横幅
    private int WINDOW_HEIGHT     = 600; //ウィンドウの縦幅

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

        JButton[][] buttons = new JButton[FIELD_ROW][FIELD_COLUMN];
        //セルの作成
        for (int x = 0; x < FIELD_ROW; x++) {
            for (int y = 0; y < FIELD_COLUMN; y++) {
                buttons[x][y] = new JButton();
                contentPane.add(buttons[x][y]);
            }
        }
    }
}
