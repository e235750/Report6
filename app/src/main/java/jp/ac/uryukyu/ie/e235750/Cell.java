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

    private int FIELD_ROW         = 5;
    private int FIELD_COLUMN      = 5;
    private int IMAGE_WIDTH       = 50;
    private int IMAGE_HEIGHT      = 50;
    private int NUM_BOMB          = 1;
    private int flagCounter       = 0;
    private int openCell          = 0;

    private CustomButton[][] buttons; 

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
        //ボム配置
        bombSetter(buttons);
        //セルに周りの爆弾の数情報を追加
        bombCountNearby(buttons);
    }

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

    private void toggleFlag(CustomButton button){
        if(!button.isOpen()){
            if(!button.isFlag() && flagCounter < NUM_BOMB){
                button.setFlag(true);
                button.setIcon(new ImageIcon(flagIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
                flagCounter ++;
            } else if(button.isFlag()){
                button.setFlag(false);
                button.setIcon(new ImageIcon(defaultIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
                flagCounter --;
        }
        }
    }


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
            clickedButton.setOpen(true);
            if(!clickedButton.isOpen()){
                openCell ++;
            } else{
                ; //何もしない
            }
            

            if(openCell == (FIELD_COLUMN * FIELD_ROW) - NUM_BOMB){
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