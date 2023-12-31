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
        setResizable(false);
    }

    /**
     * タイトルパネルをフレームにセットして表示する
     */
    public void showTitlePanel() {
        TitlePanel titlePanel = new TitlePanel(this);
        setPanel(titlePanel);
    }

    /**
     * ユーザーに難易度を選択させる３つのオプションを表示する
     * @return int index //1 ->初級, 2 ->中級, 3 -> 上級 
     */
    private int selectDifficulty(){
        String[] difficulty = {"初級", "中級", "上級"};
        int index = JOptionPane.showOptionDialog(this, 
        "難易度を選択してください",
            "難易度選択",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            difficulty,
            null
            );
        return index;
    }

    /**
     * gamePanel(Cell)をセットしてフレームに表示する
     * selectDifficultyで取得した難易度からゲームパネルを生成する
     */
    public void showGamePanel() {
        int index = selectDifficulty();
        Cell gamePanel = new Cell(this, index);
        setPanel(gamePanel);
    }

    /**
     * 遊び方を表示するパネル
     */
    public void showRolePanel(){
        RolePanel rolePanel = new RolePanel(this);
        setPanel(rolePanel);
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
