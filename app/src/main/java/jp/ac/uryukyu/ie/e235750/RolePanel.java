package jp.ac.uryukyu.ie.e235750;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * 遊び方を説明するパネルを実装するクラス
 *  MinesweeperGame game; //showTitlePanelを実行するため
 */
public class RolePanel extends JPanel{
    MinesweeperGame game;

    /**
     * ルールパネルを作成するクラス
     * @param game //starGameを実行するため
     */
    RolePanel(MinesweeperGame game) {
        this.game = game;

        //テキストエリアとボタンを縦方向に配置する
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //テキストエリア作成
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JPanel panelRole = new JPanel();
        panelRole.setLayout(new GridBagLayout());

        //タイトルへ戻るボタン
        JButton title = new JButton("タイトルへ戻る");
        title.setPreferredSize(new Dimension(300, 80));
        title.setFont(new Font("San Francisco", Font.BOLD, 15));
        title.setMaximumSize(new Dimension(Integer.MAX_VALUE, title.getMinimumSize().height)); // 幅を最大まで
        title.addActionListener(new ActionListener(){
            //ボタンを押したらタイトルへ戻る
            public void actionPerformed(ActionEvent e){
                game.showTitlePanel();
            }
        });
        JPanel panelButton = new JPanel();

        try {
            // リソースファイルへの相対パスを指定
            String resourcePath = "role.txt";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);

            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                //ファイルから文字列を取得
                while ((line = br.readLine()) != null) {
                    textArea.append(line + "\n");
                }
                br.close();
            } else {
                System.out.println("ファイルが見つかりません: " + resourcePath);
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        //レイアウト設定
        textArea.setFont(new Font("San Francisco", Font.BOLD, 17));
        panelRole.add(textArea);
        panelRole.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelRole.setAlignmentY(Component.CENTER_ALIGNMENT);

        panelButton.add(title);
        panelButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        this.add(panelRole);
        this.add(panelButton);
    }
}
