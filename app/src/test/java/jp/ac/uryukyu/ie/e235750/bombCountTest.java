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
    //enumで設定した値
    int[] NUM_CELL   = {4, 5, 7};  //難易度別一辺のセル数
    int[] NUM_BOMB   = {4, 5, 10}; //難易度別の爆弾数

    MinesweeperGame game = new MinesweeperGame();

    @Test
    void test(){
        int INDEX = Difficulty.values().length;
        for(int i = 0; i < INDEX; i ++){
            int bombCounter  = 0;
            Cell gamePanel = new Cell(game, i);
            CustomButton[][] buttons = gamePanel.buttons;

            for (int x = 0; x < NUM_CELL[i]; x++) {
                for (int y = 0; y < NUM_CELL[i]; y++) {
                    if(buttons[x][y].isBomb()){
                        bombCounter ++;
                    }                
                }
            }
            assertEquals((int)NUM_BOMB[i], (int)bombCounter);
        }
       
    }
}
