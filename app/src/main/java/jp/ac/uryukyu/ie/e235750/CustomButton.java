package jp.ac.uryukyu.ie.e235750;

import javax.swing.JButton;
import javax.swing.Icon;

/**
 * 各セルに爆弾や旗の情報を持たせるためのクラス
 *  boolean flag        //旗が立っているかどうか。true->立っている, false->立っていない
 *  boolean bomb        //セルに爆弾が配置されているかどうか。true->配置されている, false->されていない
 *  boolean open        //セルが開いているかどうか。true->開いている, false->開いていない
 *  int bombCountNearby //セルの周囲の爆弾数。
 */
public class CustomButton extends JButton{
    private boolean flag = false;
    private boolean bomb = false;
    private boolean open = false;
    private int bombCountNearby = 0;

    /**
     * JButtonクラスのコンストラクタ。ボタンに画像を配置できるようにする
     * @param icon
     */
    CustomButton(Icon icon){
        super(icon);
    }

    /**
     * JButtonクラスのコンストラクタ。ボタンに文字を配置できるようにする
     * @param icon
     */
    CustomButton(String text){
        super(text);
    }

    /**
     * flagのgetter
     * @return boolean flag
     */
    public boolean isFlag(){
        return flag;
    }

    /**
     * flagのsetter
     * @param flag
     */
    public void setFlag(boolean flag){
        this.flag = flag;
    }

    /**
     * bombのgetter
     * @return boolean bomb
     */
    public boolean isBomb(){
        return bomb;
    }

    /**
     * bombのsetter
     */
    public void setBomb(){
        bomb = true;
    }

    /**
     * openのgetter
     * @return boolean open
     */
    public boolean isOpen(){
        return open;
    }

    /**
     * openのgetter
     */
    public void setOpen(boolean open){
        this.open = open;
    }

    /**
     * セルに周囲の爆弾数をセットする
     * @param bombCountNearby
     */
    public void setBombCountNearby(int bombCountNearby){
        this.bombCountNearby = bombCountNearby;
    }

    /**
     * bombCountNearbyのgetter
     * @return int bombCountNearby
     */
    public int getBombCountNearby(){
        return bombCountNearby;
    }
}