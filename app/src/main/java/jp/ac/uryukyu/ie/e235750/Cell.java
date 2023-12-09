package jp.ac.uryukyu.ie.e235750;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * ゲームウィンドウを作るクラス
 *  MinesweeperGame        //showTitlePanelとstartGameメソッドを実行する
 *  int FIELD_ROW;         //行のセル数
 *  int FIELD_COLUMN;      //列のセル数
 *  int NUM_BOMB;          //フィールドのボム数
 *  int flagCounter        //旗の数
 *  int openCell           //開かれているセルの数
 *  CustomButton buttons;  //セル
 */
public class Cell extends JPanel implements ActionListener, MouseListener{
    MinesweeperGame game;

    private int FIELD_ROW         = 5;
    private int FIELD_COLUMN      = 5;
    private int NUM_BOMB          = 10;
    private int flagCounter       = 0;
    private int openCell          = 0;

    public CustomButton[][] buttons; 


    /**
     * コンストラクタ。セルを並べたパネルを生成する。
     * @param game Minesweeperクラス。パネル制御、ゲームリスタートをする。
     */
    public Cell(MinesweeperGame game){
        this.game = game;

        //パネルの設定
        setLayout(new GridLayout(FIELD_COLUMN, FIELD_ROW));
        buttons = new CustomButton[FIELD_COLUMN][FIELD_ROW];

        //セルの作成
        for (int x = 0; x < FIELD_ROW; x++) {
            for (int y = 0; y < FIELD_COLUMN; y++) {
                buttons[x][y] = new CustomButton();
                buttons[x][y].setDefaultIcon();
                buttons[x][y].addActionListener(this);
                buttons[x][y].addMouseListener(this);
                this.add(buttons[x][y]);
            }
        }
        //ボム配置
        bombSetter(buttons);
        //セルに周りの爆弾の数情報を追加
        bombCountNearby(buttons);
    }

    /**
     * セルにボムを配置するメソッド
     * RandomクラスnextIntメソッドを使用し、セルにランダムに爆弾を配置
     * @param buttons
     */
    private void bombSetter(CustomButton[][] buttons){
        Random rand = new Random();
        int numBomb = 0;
        while(numBomb < NUM_BOMB){
            int i = rand.nextInt(FIELD_ROW);
            int j = rand.nextInt(FIELD_COLUMN);

            if(!buttons[i][j].isBomb()){
                buttons[i][j].setBomb();
                numBomb ++;
            }
        }
    }

     /**
     * セルの周囲の爆弾の数を数える
     * 爆弾を持たない各セルにカウントした爆弾の数を付与
     * @param buttons //セル
     */
    private void bombCountNearby(CustomButton[][] buttons){
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

    /**
     * 旗の入れ替えをするメソッド
     * @param buttons //セル
     */
    private void toggleFlag(CustomButton button){
        if(!button.isOpen()){
            if(!button.isFlag() && flagCounter < NUM_BOMB){
                button.setFlag(true);
                button.setFlagIcon();
                flagCounter ++;
            } else if(button.isFlag()){
                button.setFlag(false);
                button.setDefaultIcon();
                flagCounter --;
        }
        }
    }

    /**
     * 失敗、勝利時に全ての爆弾を表示する。
     * @param buttons
     */
    private void showAllBomb(CustomButton[][] buttons){
        for(int x = 0; x < FIELD_ROW; x ++){
            for(int y = 0; y < FIELD_COLUMN; y ++){
                if(buttons[x][y].isBomb()){
                    buttons[x][y].setBombIcon();
                }
            }
        }
    }

    @Override
    /**
     * マウスをクリックした時の処理を実装。
     * 右クリックしたらtoggleFlag()を実行
     * @param e マウスをクリックした時のイベント
     */
    public void actionPerformed(ActionEvent e) {
        CustomButton clickedButton = (CustomButton) e.getSource();
        if(clickedButton.isBomb() && !clickedButton.isFlag()){
            clickedButton.setBombIcon();
            showAllBomb(buttons);
            int option = JOptionPane.showConfirmDialog(this, "失敗; ;\nもう一度挑戦しますか", "失敗", 0, JOptionPane.QUESTION_MESSAGE);
            switch (option) {
                case 0:
                    game.startGame();
                    break;
            
                case 1:
                    game.showTitlePanel();
                    break;
            }
        } else if(!clickedButton.isFlag()){
            clickedButton.setTextIcon();
            if(!clickedButton.isOpen()){
                openCell ++;
                clickedButton.setOpen(true);
            } else{
                ; //何もしない
            }
            

            if(openCell == (FIELD_COLUMN * FIELD_ROW) - NUM_BOMB){
                showAllBomb(buttons);
                int option = JOptionPane.showConfirmDialog(this, "成功！\nもう一度挑戦しますか", "成功", 0, JOptionPane.QUESTION_MESSAGE);
                switch (option) {
                    case 0:
                        game.startGame();
                        break;
            
                    case 1:
                        game.showTitlePanel();
                        break;
            }
            }
            
        }
        
    }

    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            CustomButton clickedButton = (CustomButton) e.getSource();
            toggleFlag(clickedButton);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}