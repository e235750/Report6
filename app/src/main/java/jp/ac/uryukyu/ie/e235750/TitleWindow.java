package jp.ac.uryukyu.ie.e235750;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleWindow extends JFrame {
    private int WINDOW_WIDTH  = 600; // ウィンドウの横幅
    private int WINDOW_HEIGHT = 600; // ウィンドウの縦幅

    public static void main(String[] args) {
            TitleWindow title = new TitleWindow();
            title.showTitleWindow();
            title.setVisible(true);
    }

    public void showTitleWindow(){
        setTitle("Minesweeper");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panelButton = new JPanel();
        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(new GridBagLayout());

        JButton start = new JButton("スタート");
        start.setPreferredSize(new Dimension(150, 80));
        start.setFont(new Font("San Francisco", Font.BOLD, 15));
        start.setMaximumSize(new Dimension(Integer.MAX_VALUE, start.getMinimumSize().height)); // 幅を最大まで
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                contentPane.removeAll();
                Cell gamePanel = new Cell();
                gamePanel.showGameWindow();

                contentPane.add(gamePanel.getContentPane());

                revalidate();
                repaint();
            }
        });

        JButton exit = new JButton("終了");
        exit.setPreferredSize(new Dimension(150, 80));
        exit.setFont(new Font("San Francisco", Font.BOLD, 15));
        exit.setMaximumSize(new Dimension(Integer.MAX_VALUE, exit.getMinimumSize().height)); // 幅を最大まで
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
        

        // パネルをフレームに追加
        contentPane.add(panelTitle);
        contentPane.add(panelButton);
    }
}
