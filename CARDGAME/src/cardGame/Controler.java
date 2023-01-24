package cardGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controler extends JFrame  {

    JPanel mainP = new JPanel(); // 배경이 되는 패널
    JPanel gameP = new JPanel(); // 짝 맞추기를 진행하는 패널
    JPanel infoP = new JPanel(); // 짝 맞추기를 진행하는 패널22
    JLabel infoL1 = new JLabel("[ 게 임 종 료 ]");
    JLabel infoL2 = new JLabel("계 산");
    JLabel infoL3 = new JLabel("총 점");
    JPanel resultP = new JPanel(); // 결과가 표시되는 패널
    JPanel aCardP = new JPanel(); // 광 카드를 정리하는 패널
    JPanel bCardP = new JPanel(); // 띠,동물 카드를 정리하는 패널
    JPanel cCardP = new JPanel(); // 일반 카드를 정리하는 패널
    JPanel cntP = new JPanel(); // 결과패널 안에서 남은시간을 표시하는 패널
    JButton counter = new JButton("스탑워치"); // 스탑워치 자리
    JButton point = new JButton("점수판"); //점수판
    ArrayList<CardSet> clicked = new ArrayList<CardSet>();
    ArrayList<CardSet> opened = new ArrayList<CardSet>();
    CardSet[] cards=new CardSet[24];
    CardSet[] oCards=new CardSet[24];

   int openCount = 0;
   boolean remainChance = true;
   int time =0;


    public Controler() throws HeadlessException, InterruptedException {
        super("[### 화투패 맞추기 게임ㅋㅋ ###]");

        gameP.setBackground(new Color(0,100,0));
        gameP.setPreferredSize(new Dimension(1000,535));
        gameP.setLayout(new GridLayout(3,8,15,15));
        gameP.setBorder(BorderFactory.createEmptyBorder(15 , 15, 15 , 15));

        infoL1.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
        infoL2.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        infoL3.setFont(new Font(Font.DIALOG, Font.BOLD, 60));

        infoL1.setForeground(Color.black);
        infoL2.setForeground(Color.black);
        infoL3.setForeground(Color.black);

        infoL1.setHorizontalAlignment(JLabel.CENTER);
        infoL2.setHorizontalAlignment(JLabel.CENTER);
        infoL3.setHorizontalAlignment(JLabel.CENTER);



        infoP.setBackground(new Color(0,100,0));
        infoP.setPreferredSize(new Dimension(1000,535));
        infoP.setLayout(new GridLayout(3,1,15,15));
        infoP.setBorder(BorderFactory.createEmptyBorder(15 , 15, 15 , 15));
        infoP.add(infoL1);
        infoP.add(infoL2);
        infoP.add(infoL3);





        resultP.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
        resultP.setBorder(BorderFactory.createEmptyBorder(5 , 5, 5 , 5));
        resultP.setBackground(new Color(0.0F, 0.0F , 0.0F, 0.3F) );
        resultP.setPreferredSize(new Dimension(1000,235));


        aCardP.setBackground(new Color(1,50,15));
        aCardP.setPreferredSize(new Dimension(200,225));
        aCardP.setLayout(new FlowLayout(FlowLayout.LEFT,-15,-30));
        aCardP.setBorder(BorderFactory.createEmptyBorder(40 , 25, 0 , 0));

        bCardP.setBackground(new Color(1,50,15));
        bCardP.setPreferredSize(new Dimension(275,225));
        bCardP.setLayout(new FlowLayout(FlowLayout.LEFT,-15,-30));
        bCardP.setBorder(BorderFactory.createEmptyBorder(40 , 25, 0 , 0));

        cCardP.setBackground(new Color(1,50,15));
        cCardP.setPreferredSize(new Dimension(275,225));
        cCardP.setLayout(new FlowLayout(FlowLayout.LEFT,-15,-30));
        cCardP.setBorder(BorderFactory.createEmptyBorder(40 , 25, 0 , 0));

        cntP.setBackground(new Color(0.0F, 0.0F , 0.0F, 0.2F));
        cntP.setLayout(new GridLayout(2,1,0,10));
        cntP.setPreferredSize(new Dimension(200,225));

        resultP.add(aCardP);
        resultP.add(bCardP);
        resultP.add(cCardP);
        resultP.add(cntP);

        counter.setText("　");
        counter.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
        counter.setForeground(Color.white);
        counter.setBackground(Color.BLACK);
        counter.setBorderPainted(false);
        point.setText("가능조합 : ??");
        point.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        point.setForeground(Color.white);
        point.setBackground(Color.BLACK);
        point.setBorderPainted(false);
        cntP.add(counter);
        cntP.add(point);



        mainP.setLayout(new BoxLayout(mainP, BoxLayout.Y_AXIS));
        mainP.setBackground(new Color(0,100,0));
        mainP.add(gameP);
        mainP.add(infoP);
        mainP.add(resultP);
        infoP.setVisible(false);
        initSet();

//        gameP.setVisible(false);
//        infoP.setVisible(true);

        this.add(mainP);
        this.setBounds(0,0,1000,800);
        this.setVisible(true);
        this.setResizable(false); //창의 크기를 변경하지 못하게
        this.setLocationRelativeTo(null); //창이 가운데 나오게


        startCount();
        Thread.sleep(6000);
        cardSet();
        cardCheck();
        chanceCount ();


    }


    void chanceCount (){ // 남은 패 중 가능한 조합의 수를 표시하고, 가능조합이 0이될경우 게임종료(아직)
        new Thread(()->{
            int chance = 0;
            int a = 0;
            int b = 0;
            int c = 0;
            while (remainChance){
                chance=0;
                opened.clear();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                for (int i = 0 ; i< cards.length ; i++){
                    if (!cards[i].isOpened) {
                        opened.add(cards[i]);
                    }}
                for(int j = 0 ; j< opened.size() ; j++){
                        for (int k = 0 ; k < opened.size() ; k++){
                                if (j!=k && opened.get(j).cNum/4==opened.get(k).cNum/4) chance++;
                        }
                    }


                if (chance==0) {
                    //점수 계산 구간//
                    for (int i = 0 ; i< cards.length ; i++){
                        if (cards[i].isOpened) {
                            if (cards[i].cNum % 4 == 0 && ( cards[i].cNum / 4 == 0 ||  cards[i].cNum / 4 == 11 ||
                                    cards[i].cNum / 4 == 2 ||  cards[i].cNum / 4 == 7 ||  cards[i].cNum / 4 == 10)) {
                                 a++;}
                            else if (cards[i].cNum % 4 == 1 || cards[i].cNum % 4 == 0) b++;
                            else if (cards[i].cNum % 4 == 2 || cards[i].cNum % 4 == 3) c++;
                            infoL2.setText(String.format("광 : %d장, 띠 : %d장, 피 : %d장, 시간점수 : %d", a,b,c,time));
                            infoL3.setText("총 점 : " + (a*1000+b*100+c*11+time*100)+"점");
                        }
                    }


                        gameP.setVisible(false);
                        infoP.setVisible(true);
                        remainChance=false;
                    }
                point.setText("가능조합 : "+ chance/2);
             }
            }).start();


    }
    void startCount (){ // 게임 시간을 체크하는 메서드
        new Thread(()->{
            int cdn = 5;
            int timer = 0;
            while (remainChance){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (cdn==0){
                    counter.setText("START!");
                    counter.setFont(new Font(Font.DIALOG, Font.BOLD, 45));
                    cdn--;
                } else if (cdn>0){
                    counter.setText(cdn+"");
                    counter.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
                    cdn--;
                } else if (cdn<0){
                    counter.setText(String.format("%02d:%02d",(-cdn/60),(-cdn%60)));
                    counter.setFont(new Font(Font.DIALOG, Font.BOLD, 50));
                    cdn--;
                    time=300+cdn;
                }
            }
        }).start();
}


 void initSet() throws InterruptedException { // (1)24개의 랜덤한 패를 뽑아, (2)게임패널에 배치
     int[] nList = new int[24];
     for (int i = 0; i < nList.length; i++) {  //(1) 0~47의 숫자 중에서 24가지를 랜덤하게 추출함
         boolean overCheck = false;
         while (!overCheck) {
             int reCount = 0;
             int preNum = (int) ((Math.random() * 49) - 1); //임의의 수를 뽑아서
             for (int k = 0; k < i; k++) { // 생성된 배열 내에 있는지를 검사
                 if (nList[k] == preNum) reCount = 1; // 배열중에 일치하는 수를 발견하면 표시
             }
             if (reCount == 0) {//무사히 빠져나왔다면 반복문을 종료시킴
                 nList[i] = preNum;
                 overCheck = true;
             }
         }
     }
     for (int i = 0; i < cards.length; i++) { // (2)게임패널에 배치
         cards[i] = new CardSet();
         cards[i].cNum = nList[i];
         cards[i].setIcon(new ImageIcon(String.format("./src/cardGame/images/%d.png", cards[i].cNum)));
         cards[i].setBackground(Color.gray);

         ;
         gameP.add(cards[i]);
     }
 }

 void cardSet() throws InterruptedException {
     for(int i = 0 ; i<cards.length ; i++) {
         cards[i].setIcon(new ImageIcon("./src/cardGame/images/card.png")); //카드뒷면을 기본으로 보여줌
//         cards[i].setIcon(new ImageIcon(String.format("./src/cardGame/images/%d.png",cards[i].cNum)));
         int finalI = i;
         cards[i].addActionListener(new ActionListener() { // 누를시 각 카드의 앞면을 보여주고 체크를 위한 배열에 담음
             @Override
             public void actionPerformed(ActionEvent e) {
                 if (!cards[finalI].isChecked && clicked.size()<2){ //하나의 패를 두 번 클릭하여 배열에 올리는 것을 방지
                     cards[finalI].setIcon(new ImageIcon(String.format("./src/cardGame/images/%d.png",cards[finalI].cNum)));
                     clicked.add(cards[finalI]);
                     cards[finalI].isChecked=true;
                 }
             }
         });
     }
 }



    void cardCheck(){
        new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(clicked.size()>1) { // ArrayList에 담겨진 두 카드가 일치하는 경우
                    if ((clicked.get(0).cNum/4) == (clicked.get(1).cNum/4)){
                        for(int i = 0 ; i < 24 ; i++) { //본래 CardSet에서 원본을 찾아내서 비활성화
                            if(clicked.get(0).cNum == cards[i].cNum) {
                                cards[i].isOpened=true;
                                cards[i].setVisible(false);
                                oCards[openCount] = new CardSet(); // 결과패널에 담기위해 새로운 카드셋객체를 생성
                                oCards[openCount].cNum = cards[i].cNum;
                                oCardsCheck();
                                opened.add(oCards[openCount]);
                                openCount++;
                            };
                            if(clicked.get(1).cNum == cards[i].cNum) {
                                cards[i].isOpened=true;
                                cards[i].setVisible(false);
                                oCards[openCount] = new CardSet();
                                oCards[openCount].cNum = cards[i].cNum;
                                oCardsCheck();
                                opened.add(oCards[openCount]);
                                openCount++;
                           };
                        }
                    }
                    else if ((clicked.get(0).cNum/4) != (clicked.get(1).cNum/4)) { // 두 카드가 일치하지 않는 경우

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i = 0 ; i < 24 ; i++) {
                            if(clicked.get(0).cNum == cards[i].cNum) {
                                cards[i].setIcon(new ImageIcon("./src/cardGame/images/card.png"));
                                cards[i].isChecked = false;
                            };
                            if(clicked.get(1).cNum == cards[i].cNum) {
                                cards[i].setIcon(new ImageIcon("./src/cardGame/images/card.png"));
                                cards[i].isChecked = false;
                            };
                        }
                    }
                    clicked.clear();
                }
            }
            }).start();
        }


    void oCardsCheck () {
                        System.out.println("왜 추가가 늦게되지"); //스레드를 나눠야될거같은데 모르겠음
                        oCards[openCount].setIcon(new ImageIcon(String.format("./src/cardGame/images/output/%d.png",  oCards[openCount].cNum)));
                        oCards[openCount].setPreferredSize(new Dimension(65, 100));
                        oCards[openCount].setBorderPainted(false);
                        oCards[openCount].setFocusable(false);
                        oCards[openCount].setBackground(Color.RED);

                        // 각 패의 종류에 따라 분류해서 결과패널에 배치
                        if ( oCards[openCount].cNum % 4 == 0 && ( oCards[openCount].cNum / 4 == 0 ||  oCards[openCount].cNum / 4 == 11 ||
                                oCards[openCount].cNum / 4 == 2 ||  oCards[openCount].cNum / 4 == 7 ||  oCards[openCount].cNum / 4 == 10)) {
                            aCardP.add( oCards[openCount]);
                        } else if ( oCards[openCount].cNum % 4 == 1 ||  oCards[openCount].cNum % 4 == 0) {
                            bCardP.add(oCards[openCount]);
                        } else if ( oCards[openCount].cNum % 4 == 2 ||  oCards[openCount].cNum % 4 == 3) {
                            cCardP.add( oCards[openCount]);
                        }
                    }


    public static void main(String[] args) throws InterruptedException {
        new Controler();
    }
}

