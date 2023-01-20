package cardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Controler extends JFrame {
    class Cardp extends JButton{

    }
    JPanel mainP = new JPanel(); // 배경이 되는 패널
    JPanel gameP = new JPanel(); // 짝 맞추기를 진행하는 패널
    JPanel resultP = new JPanel(); // 결과가 표시되는 패널
    JPanel cntP = new JPanel(); // 결과패널 안에서 남은시간을 표시하는 패널

    JButton btnT;
    ImageIcon img = new ImageIcon("./src/cardGame/images/card.png");
    Cardp[] cards=new Cardp[24];

    public Controler() throws HeadlessException {
        super("[### 화투패 맞추기 게임ㅋㅋ ###]");


        gameP.setBackground(new Color(0.0F,0.0F,0.0F,0.0F));
        gameP.setPreferredSize(new Dimension(1000,600));


        resultP.setBackground(new Color(0.0F, 0.0F , 0.0F, 0.3F) );
        resultP.setPreferredSize(new Dimension(1000,200));

        cntP.setBackground(new Color(100,100,100));
        cntP.setPreferredSize(new Dimension(200,200));

        resultP.add(cntP);

        for(int i = 0 ; i<cards.length ; i++) {
            cards[i] = new Cardp();
            cards[i].setIcon(img);

            gameP.add(cards[i]);
        }
        gameP.setLayout(new GridLayout(3,8,15,15));
        gameP.setBorder(BorderFactory.createEmptyBorder(15 , 15, 15 , 15));


        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        mainP.setBackground(new Color(0,100,0));
        mainP.add(gameP);
        mainP.add(resultP);

        this.add(mainP);
        this.setBounds(0,0,1000,800);
        this.setVisible(true);

        this.setResizable(false);//창의 크기를 변경하지 못하게
        this.setLocationRelativeTo(null);//창이 가운데 나오게







    }
}

