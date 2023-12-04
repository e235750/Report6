package jp.ac.uryukyu.ie.e235750;

import javax.swing.JButton;
import javax.swing.Icon;

class CustomButton extends JButton{
    private boolean flag = false;
    private boolean bomb = false;
    private int bombCountNearby = 0;

    CustomButton(Icon icon){
        super(icon);
    }
    CustomButton(String text){
        super(text);
    }

    public boolean isFlag(){
        return flag;
    }

    public void setFlag(boolean flag){
        this.flag = flag;
    }

    public boolean isBomb(){
        return bomb;
    }

    public void setBomb(){
        bomb = true;
    }

    public void setBombCountNearby(int bombCountNearby){
        this.bombCountNearby = bombCountNearby;
    }

    public int getBombCountNearby(){
        return bombCountNearby;
    }
}