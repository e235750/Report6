package jp.ac.uryukyu.ie.e235750;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * タイトルパネルを実装するクラス
 *  MinesweeperGame game; //starGameを実行するため
 */
public class TitlePanel extends JPanel {
    MinesweeperGame game = new MinesweeperGame();

    /**
     * TitlePanelのコンストラクタ。タイトル画面を実装する
     * @param game
     */
    public TitlePanel(MinesweeperGame game){
        this.game = game;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //ボタンを配置するパネル
        JPanel panelButton = new JPanel();
        //タイトルテキストを配置するパネル
        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(new GridBagLayout());

        JButton start = new JButton("スタート");
        start.setPreferredSize(new Dimension(150, 80));
        start.setFont(new Font("San Francisco", Font.BOLD, 15));
        start.setMaximumSize(new Dimension(Integer.MAX_VALUE, start.getMinimumSize().height)); // 幅を最大まで
        //アクションリスナー追加
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                game.startGame();
            }
        });

        JButton exit = new JButton("終了");
        exit.setPreferredSize(new Dimension(150, 80));
        exit.setFont(new Font("San Francisco", Font.BOLD, 15));
        exit.setMaximumSize(new Dimension(Integer.MAX_VALUE, exit.getMinimumSize().height)); // 幅を最大まで
        //アクションリスナー追加
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        JLabel gameTitle = new JLabel("マインスイーパー(prog2課題)");
        gameTitle.setFont(new Font("San Francisco", Font.BOLD, 25));
        
        
        panelButton.add(start);
        panelButton.add(exit);
        panelTitle.add(gameTitle);
        panelTitle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //ボタンをTitlePanelに追加
        this.add(panelTitle);
        //ラベルをTitlePaneに追加
        this.add(panelButton);
    }
}
