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
    JPanel infoP = new JPanel(); // 최종점수를 보여주는 패널
    JLabel infoL1 = new JLabel("[ 게 임 종 료 ]");
    JLabel infoL2 = new JLabel("계 산");
    JLabel infoL3 = new JLabel("총 점");
    JPanel resultP = new JPanel(); // 정답카드가 표시되는 패널
    JPanel aCardP = new JPanel(); // 광 카드를 정리하는 패널
    JPanel bCardP = new JPanel(); // 띠,동물 카드를 정리하는 패널
    JPanel cCardP = new JPanel(); // 일반 카드를 정리하는 패널
    JPanel cntP = new JPanel(); // 정답패널 안에서 남은시간을 표시하는 패널
    JButton counter = new JButton("스탑워치"); // 스탑워치 자리
    JButton point = new JButton("점수판"); //점수판


    ArrayList<CardSet> clicked = new ArrayList<CardSet>(); // 뒤집은 카드를 검사식에 보내기 위한 List
    ArrayList<CardSet> opened = new ArrayList<CardSet>(); // 남은 패 중 가능한 조합을 검사하기 위한 List
    CardSet[] cards=new CardSet[24]; // 초기카드 24장
    CardSet[] oCards=new CardSet[24]; // 정답패널에 보내기 위한 카드를 생성하기 위한 배열


   int openCount = 0; // 짝 맞추기에 성공한 카드를 새로운 배열에 배치하기 위한 index번호
   boolean remainChance = true; // 맞출 수 있는 짝이 모두 소진되면 게임을 종료하기 위해 사용
   int time; // 점수패널에서 시간점수를 계산하기 위한 데이터


    public Controler() throws InterruptedException {
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
    void chanceCount (){ // 남은 패 중 가능한 조합의 수를 표시하고, 가능조합이 0이될경우 게임종료
        new Thread(()->{
            int chanceCount = 0;
            int aCount = 0;
            int bCount = 0;
            int cCount = 0;
            while (remainChance){ //
                chanceCount=0;
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
                                if (j!=k && opened.get(j).cNum/4==opened.get(k).cNum/4) chanceCount++;
                        }
                    }
                if (chanceCount==0) { // 가능한 패의 조합을 찾아내지 못하고 빠져나오면 점수계산 후 게임종료 및 점수패널 호출
                    //점수 계산 구간//
                    for (int i = 0 ; i< cards.length ; i++){
                        if (cards[i].isOpened) {
                            if (cards[i].cNum % 4 == 0 && ( cards[i].cNum / 4 == 0 ||  cards[i].cNum / 4 == 11 ||
                                    cards[i].cNum / 4 == 2 ||  cards[i].cNum / 4 == 7 ||  cards[i].cNum / 4 == 10)) {
                                 aCount++;}
                            else if (cards[i].cNum % 4 == 1 || cards[i].cNum % 4 == 0) bCount++;
                            else if (cards[i].cNum % 4 == 2 || cards[i].cNum % 4 == 3) cCount++;
                            infoL2.setText(String.format("광 : %d장, 띠 : %d장, 피 : %d장, 시간점수 : %d", aCount,bCount,cCount,time));
                            infoL3.setText("총 점 : " + (aCount*1000+bCount*100+cCount*11+time*100)+"점");
                        }
                    }
                        gameP.setVisible(false); // 게임패널을 안보이게하고
                        infoP.setVisible(true); // 점수패널을 보이게 함
                        remainChance=false; // 카운트 및 조합 계산 종료
                    }
                point.setText("가능조합 : "+ chanceCount/2);
             }
            }).start();


    }
    void startCount (){ // 게임 시간을 체크하는 메서드 => 5부터 --;하여 0에서 "START"표시, 음수가 되면 시간으로 변환하여 표시
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


 void initSet()  { // (1)24개의 랜덤한 패를 뽑아, (2)게임패널에 배치
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
         cards[i].Flipped();//게임 시작시 공개되는 앞면
         cards[i].setBackground(Color.gray);

         gameP.add(cards[i]);
     }
 }

 void cardSet() {
     for(int i = 0 ; i<cards.length ; i++) {
         cards[i].nonFlipped(); // (1)일정 시간이 지난 후엔 카드 뒷면을 기본으로 보여줌
//         cards[i].Flipped();//모두 앞면으로 시작(테스트용)
         int finalI = i;
         cards[i].addActionListener(new ActionListener() { // 누를시 각 카드의 앞면을 보여주고 체크를 위한 배열에 담음
             @Override
             public void actionPerformed(ActionEvent e) { // (2)카드를 클릭하면 앞면을 보여줌
                 if (!cards[finalI].isChecking && clicked.size()<2){
                     // isChecking == true인 카드는 눌러도 반응이 없다. -> 하나의 카드를 두 번 누르는것을 방지
                     // List.size가 2 이하일때 실행 -> 검사식에는 두 장 까지만 올릴 수 있다.
                     cards[finalI].Flipped();
                     clicked.add(cards[finalI]); // 검사용 List에 담음
                     cards[finalI].isChecking=true; // 검사중인 카드임을 표시
                 }
             }
         });
     }
 }


    void cardCheck(){ // 뒤집힌 카드 2장을 검사 후, 정답패널로 보내거나 다시 뒤집음
        new Thread(()->{
            while (true) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if(clicked.size()>1) { // (1) ArrayList에 담겨진 두 카드가 일치하는 경우
                    if ((clicked.get(0).cNum/4) == (clicked.get(1).cNum/4)){
                        for(int i = 0 ; i < 24 ; i++) { //본래 CardSet에서 원본을 찾아내서 비활성화
                            if(clicked.get(0).cNum == cards[i].cNum) {
                                cards[i].isOpened=true; // 오픈된 카드임을 표시
                                cards[i].setVisible(false); // 안보이게 설정 -> 카드 자체를 정답패널로 옮기면 그리드가 무너짐ㅠㅠ
                                oCards[openCount] = new CardSet(); // 정답패널에 담기위해 새로운 카드셋 객체를 생성
                                oCards[openCount].cNum = cards[i].cNum; // 새로운 카드셋 객체에 현재 뒤집힌 카드의 정보를 담음
                                oCardsCheck(); // 만든 카드셋 객체를 검사하여 정답패널에 추가
                                openCount++; // 검사 후 카운트를 증가시킴
                            };
                            if(clicked.get(1).cNum == cards[i].cNum) {
                                cards[i].isOpened=true;
                                cards[i].setVisible(false);
                                oCards[openCount] = new CardSet();
                                oCards[openCount].cNum = cards[i].cNum;
                                oCardsCheck();
                                openCount++;
                           };
                        }
                    }
                    else if ((clicked.get(0).cNum/4) != (clicked.get(1).cNum/4)) { // (2) 두 카드가 일치하지 않는 경우
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        for(int i = 0 ; i < 24 ; i++) {
                            if(clicked.get(0).cNum == cards[i].cNum) {
                                cards[i].nonFlipped();
                            };
                            if(clicked.get(1).cNum == cards[i].cNum) {
                                cards[i].nonFlipped();
                            };
                        }
                    }
                    clicked.clear();
                }
            }
            }).start();
        }

    void oCardsCheck () {
        //정답패널에 추가될 카드의 이미지 셋팅
        oCards[openCount].setIcon(new ImageIcon(String.format("./src/cardGame/images/output/%d.png",  oCards[openCount].cNum)));
        oCards[openCount].setPreferredSize(new Dimension(65, 100));
        oCards[openCount].setBorderPainted(false);
        oCards[openCount].setFocusable(false);
        oCards[openCount].setBackground(Color.RED);

        // 각 패의 종류에 따라 분류해서 정답패널에 배치
        // A 카드
        if ( oCards[openCount].cNum % 4 == 0 && ( oCards[openCount].cNum / 4 == 0 ||  oCards[openCount].cNum / 4 == 11 ||
                oCards[openCount].cNum / 4 == 2 ||  oCards[openCount].cNum / 4 == 7 ||  oCards[openCount].cNum / 4 == 10)) {
            aCardP.add( oCards[openCount]);
        // B 카드
        } else if ( oCards[openCount].cNum % 4 == 1 ||  oCards[openCount].cNum % 4 == 0) {
            bCardP.add(oCards[openCount]);
        // C 카드
        } else if ( oCards[openCount].cNum % 4 == 2 ||  oCards[openCount].cNum % 4 == 3) {
            cCardP.add( oCards[openCount]);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new Controler();
    }
}

