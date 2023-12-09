import jp.ac.uryukyu.ie.e235750.*;

/**
 * MinesweeperGame実装用クラス
 */
public class Main{
    /**
     * ゲーム開始
     * @param args
     */
    public static void main(String[] args) {
        MinesweeperGame game = new MinesweeperGame();
        game.showTitlePanel();

        game.setVisible(true);
    }
}
