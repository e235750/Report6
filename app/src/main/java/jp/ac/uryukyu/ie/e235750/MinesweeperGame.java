package jp.ac.uryukyu.ie.e235750;

import javax.swing.*;

public class MinesweeperGame extends JFrame {

    private int WINDOW_WIDTH  = 600; // ウィンドウの横幅
    private int WINDOW_HEIGHT = 600; // ウィンドウの縦幅

    private JPanel currentPanel;  // 現在表示しているパネル

    public MinesweeperGame() {
        setTitle("Minesweeper");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Container contentPane = getContentPane();
        // Cell title = new Cell(this);
        // contentPane.add(title);
        // this.setVisible(true);
    }

    public void showTitlePanel() {
        TitlePanel titlePanel = new TitlePanel(this);
        setPanel(titlePanel);
    }

    public void showGamePanel() {
        Cell gamePanel = new Cell(this);
        setPanel(gamePanel);
    }

    private void setPanel(JPanel panel) {
        currentPanel = panel;
        getContentPane().removeAll();
        getContentPane().add(currentPanel);
        revalidate();
        repaint();
    }

    public void startGame() {
        showGamePanel();
    }
}
