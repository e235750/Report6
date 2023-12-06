package jp.ac.uryukyu.ie.e235750;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;



public class Cell extends JPanel implements ActionListener, MouseListener{
    MinesweeperGame game;

    private ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/default.png")); //デフォルトのボタンアイコン
    private ImageIcon bombIcon    = new ImageIcon(getClass().getResource("/bomb.png"));    //爆弾のボタンアイコン
    private ImageIcon flagIcon    = new ImageIcon(getClass().getResource("/flag.png"));    //旗のアイコン

    private int FIELD_ROW         = 5;   //フィールドの行
    private int FIELD_COLUMN      = 5;   //フィールドの列
    private int IMAGE_WIDTH       = 50;  //画像の横幅
    private int IMAGE_HEIGHT      = 50;  //画像の縦幅

    private CustomButton[][] buttons;    //セル

    //セルを作成する
    public Cell(MinesweeperGame game){
        this.game = game;

        //パネルの設定
        setLayout(new GridLayout(FIELD_COLUMN, FIELD_ROW));
        buttons = new CustomButton[FIELD_COLUMN][FIELD_ROW];

        //セルの作成
        for (int x = 0; x < FIELD_ROW; x++) {
            for (int y = 0; y < FIELD_COLUMN; y++) {
                buttons[x][y] = new CustomButton(new ImageIcon(defaultIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
                buttons[x][y].addActionListener(this);
                buttons[x][y].addMouseListener(this);
                this.add(buttons[x][y]);
            }
        }
        //ボムの設置
        bombSetter(buttons);
        //セルに周りの爆弾の数情報を追加
        bombCountNearby(buttons);
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

        if(buttons.isFlag() && buttons.getText().isEmpty()){
            buttons.setIcon(new ImageIcon(flagIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
        } else{
            buttons.setIcon(new ImageIcon(defaultIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
        }
    }


    //ボタンをクリックした時の処理
    @Override
    public void actionPerformed(ActionEvent e) {
        CustomButton clickedButton = (CustomButton) e.getSource();
        if(clickedButton.isBomb() && !clickedButton.isFlag()){
            clickedButton.setIcon(new ImageIcon(bombIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
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
            clickedButton.setHorizontalAlignment(SwingConstants.LEFT);
            clickedButton.setFont(new Font("San Francisco", Font.BOLD, 30));
            clickedButton.setText(Integer.toString(clickedButton.getBombCountNearby()));
        }
        
    }

    //マウスをクリックした時の処理
    @Override
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