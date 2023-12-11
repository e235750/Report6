package jp.ac.uryukyu.ie.e235750;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 設置された爆弾の数が期待する爆弾の数と等しいことを検証する
 * 検証手順
 *  1.Cellクラスのオブジェクトを作成し、セルの情報を取得。
 *  2.ループ処理で全てのセルにアクセス。
 *  3.各セルでisBombメソッドを実行しtrueの場合int型変数bombCounterをインクリメント。
 *  4.期待する爆弾の数とbombCounterをassertEqualsメソッドで比較する。
 */
public class bombCountTest {
    private int FIELD_ROW         = 5;
    private int FIELD_COLUMN      = 5;
    private int NUM_BOMB          = 7;
    private int bombCounter       = 0;

    @Test
    void test(){
        MinesweeperGame game = new MinesweeperGame();
        Cell gamePanel = new Cell(game);
        CustomButton[][] buttons = gamePanel.buttons;

        for (int x = 0; x < FIELD_ROW; x++) {
            for (int y = 0; y < FIELD_COLUMN; y++) {
                if(buttons[x][y].isBomb()){
                    bombCounter ++;
                }                
            }
        }
        assertEquals((int)NUM_BOMB, (int)bombCounter);
    }
}
