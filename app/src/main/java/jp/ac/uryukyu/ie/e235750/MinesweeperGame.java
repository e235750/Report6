package jp.ac.uryukyu.ie.e235750;

import javax.swing.*;

/**
 * int WINDOW_WIDTH;    // ウィンドウの横幅
 * int WINDOW_HEIGHT;   // ウィンドウの縦幅
 * JPanel currentPanel; // 現在表示しているパネル
 */
public class MinesweeperGame extends JFrame {

    private int WINDOW_WIDTH  = 600; // ウィンドウの横幅
    private int WINDOW_HEIGHT = 600; // ウィンドウの縦幅

    private JPanel currentPanel;  // 現在表示しているパネル

    /**
     * MinesweeperGameのコンストラクタ
     * フレームの初期化を行う
     */
    public MinesweeperGame() {
        setTitle("Minesweeper");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * タイトルパネルをフレームにセットして表示する
     */
    public void showTitlePanel() {
        TitlePanel titlePanel = new TitlePanel(this);
        setPanel(titlePanel);
    }

    /**
     * gamePanel(Cell)をセットしてフレームに表示する
     */
    public void showGamePanel() {
        Cell gamePanel = new Cell(this);
        setPanel(gamePanel);
    }

    /**
     * 引数に指定したパネルをフレームにセットする
     * @param panel //titlePanelとgamePanel(Cell))を指定
     */
    private void setPanel(JPanel panel) {
        currentPanel = panel;
        getContentPane().removeAll();
        getContentPane().add(currentPanel);
        revalidate();
        repaint();
    }

    /**
     * showGamepanelを実行してフレームにgamePanel(Cell)を追加
     */
    public void startGame() {
        showGamePanel();
    }
}
