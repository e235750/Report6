package jp.ac.uryukyu.ie.e235750;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClickTest {
    private int FIELD_ROW         = 5;
    private int FIELD_COLUMN      = 5;
    private int NUM_BOMB          = 10;
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
