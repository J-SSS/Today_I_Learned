package cardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Controler extends JFrame {

    JPanel mainP = new JPanel(); // 배경이 되는 패널
    JPanel gameP = new JPanel(); // 짝 맞추기를 진행하는 패널
    JPanel resultP = new JPanel(); // 결과가 표시되는 패널
    JPanel aCardP = new JPanel(); // 광 카드를 정리하는 패널
    JPanel bCardP = new JPanel(); // 띠,동물 카드를 정리하는 패널
    JPanel cCardP = new JPanel(); // 일반 카드를 정리하는 패널
    JPanel cntP = new JPanel(); // 결과패널 안에서 남은시간을 표시하는 패널
    JLabel counter = new JLabel("스탑워치"); // 스탑워치 자리
    JLabel point = new JLabel("점수판"); //점수판

    JButton btnT;
    ImageIcon img = new ImageIcon("./src/cardGame/images/card.png");
    CardSet[] cards=new CardSet[24];



    public Controler() throws HeadlessException {
        super("[### 화투패 맞추기 게임ㅋㅋ ###]");



        int[] nList = new int[24];
        for (int i = 0 ; i<nList.length ; i++){
            boolean  overCheck=false;
            while (!overCheck) {
                int reCount=0;
                System.out.print(reCount);
                int preNum = (int) ((Math.random() * 49) - 1); //임의의 수를 뽑아서
                for (int k = 0; k < i; k++) { // 생성된 배열 내에 있는지를 검사
                    if (nList[k] == preNum) reCount=1; // 배열중에 일치하는 수를 발견하면 표시
                }
                if (reCount==0) {//무사히 빠져나왔다면 반복문을 종료시킴
                    nList[i]=preNum;
                    overCheck = true;
                }
            }}

        for (int i = 0 ; i<nList.length ; i++){
            System.out.print(nList[i] + " ");
        }


        gameP.setBackground(new Color(0.0F,0.0F,0.0F,0.0F));
        gameP.setPreferredSize(new Dimension(1000,500));


        aCardP.setBackground(new Color(100,100,100));
        aCardP.setPreferredSize(new Dimension(250,300));

        bCardP.setBackground(new Color(100,100,100));
        bCardP.setPreferredSize(new Dimension(250,300));

        cCardP.setBackground(new Color(100,100,100));
        cCardP.setPreferredSize(new Dimension(250,300));


        cntP.setBackground(new Color(100,100,100));
        cntP.setPreferredSize(new Dimension(100,300));
        cntP.add(counter);
        cntP.add(point);

        resultP.setBackground(new Color(0.0F, 0.0F , 0.0F, 0.3F) );
        resultP.setPreferredSize(new Dimension(1000,300));
//        resultP.setLayout(new GridLayout(1,4,15,15));

        resultP.add(aCardP);
        resultP.add(bCardP);
        resultP.add(cCardP);
        resultP.add(cntP);



        for(int i = 0 ; i<cards.length ; i++) {
            cards[i] = new CardSet();
            cards[i].cNum = nList[i];
            cards[i].setIcon(new ImageIcon(String.format("./src/cardGame/images/%d.png",cards[i].cNum)));

            System.out.println(cards[i].cNum);
            System.out.println(cards[i].img);
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

