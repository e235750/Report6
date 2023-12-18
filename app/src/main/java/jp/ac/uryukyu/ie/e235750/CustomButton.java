package jp.ac.uryukyu.ie.e235750;

import javax.swing.*;
import java.awt.*;

/**
 * 各セルに爆弾や旗の情報を持たせるためのクラス
 *  ImageIcon defaultIcon; //デフォルトアイコン
 *  ImageIcon bombIcon;    //爆弾アイコン
 *  ImageIcon flagIcon;    //旗アイコン
 *  int IMAGE_WIDTH;       //アイコンの横幅
 *  int IMAGE_HEIGHT;      //アイコンの縦幅
 *  int FONT_SIZE          //フォントのサイズ
 *  boolean flag           //旗が立っているかどうか。true->立っている, false->立っていない
 *  boolean bomb           //セルに爆弾が配置されているかどうか。true->配置されている, false->されていない
 *  boolean open           //セルが開いているかどうか。true->開いている, false->開いていない
 *  int bombCountNearby    //セルの周囲の爆弾数。
 */
public class CustomButton extends JButton{
    private ImageIcon defaultIcon = new ImageIcon(getClass().getResource("/default.png")); //デフォルトのボタンアイコン
    private ImageIcon bombIcon    = new ImageIcon(getClass().getResource("/bomb.png"));    //爆弾のボタンアイコン
    private ImageIcon flagIcon    = new ImageIcon(getClass().getResource("/flag.png"));    //旗のアイコン

    private int IMAGE_WIDTH       = 50;
    private int IMAGE_HEIGHT      = 50;

    private int FONT_SIZE          = 22;

    private boolean flag = false;
    private boolean bomb = false;
    private boolean open = false;
    private int bombCountNearby = 0;

    /**
     * JButtonクラスのコンストラクタ。
     */
    CustomButton(){
        super();
    }

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
     * bombCountNearbyのgetter
     * @return int bombCountNearby
     */
    public int getBombCountNearby(){
        return bombCountNearby;
    }

    /**
     * セルに周囲の爆弾数をセットする
     * @param bombCountNearby
     */
    public void setBombCountNearby(int bombCountNearby){
        this.bombCountNearby = bombCountNearby;
    }

    /**
     * セルにデフォルトのアイコンを割り当てる
     */
    public void setDefaultIcon(){
        setIcon(new ImageIcon(defaultIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
    }

    /**
     * セルに爆弾のアイコンを割り当てる
     */
    public void setBombIcon(){
        setIcon(new ImageIcon(bombIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
    }

    /**
     * セルに旗のアイコンを割り当てる
     */
    public void setFlagIcon(){
        setIcon(new ImageIcon(flagIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)));
    }

    /**
     * セルにテキストを割り当てる
     */
    public void setTextIcon(){
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(new Font("San Francisco", Font.BOLD, FONT_SIZE));
        setText(Integer.toString(getBombCountNearby()));
    }
}