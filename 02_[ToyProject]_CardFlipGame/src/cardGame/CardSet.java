package cardGame;

import javax.swing.*;
import java.awt.datatransfer.FlavorListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileAlreadyExistsException;

public class CardSet extends JButton {
    /*
    ### 컨셉 ###
    화투패는 1~12월로 분류되고 각 월당 4장, 4장 x 12월 48장
    48장 중 짝과 상관없이 랜덤하게 24장을 추출하여 짝맞추기 게임
    남아있는 패 중 맞출 수 있는 짝이 없다면 게임 종료

    1. 고스톱의 규칙을 적용해서 점수를 계산하는 함수도 만들어볼까?
    2. 지난시간에 했던 스탑워치를 적용해서 클리어한 시간을 점수계산에 반영하여 정밀한 점수 산출하면 재밌을ㄷ긋
    //////////////////////////

    ### 해결포인트 ###
    1. 48장의 패를 어떤 규칙으로 구분할것인가???
    2. 짝 일치 여부를 어떻게 판단할 것인가??
    3. 고스톱의 점수계산 규칙을 어떻게 구현할 것인가??
    4. CSS같은 정밀한 애니메이션 구현이 가능한가??? Y축회전 가능? 카드뒤집는모션
    /////////////////////////


    ### 생각1 ###
    각 4장단위로 그룹이 생기니까
    0~47의 인식코드를 부여하고, 4로 나눈 몫이 같은 것은 같은 그룹에 속하겠지?
    ==> 1부터 시작하면 1,2,3과 4의 몫이 달라지니까 0부터 시작하는게 맞을듯 ==> 완료

    각 그룹의 1~2번은 광,단,동물패에 해당하는 특수패고, 3~4번은 일반패로 분류(일부 월 제외)
    위의 규칙과 유사하게 4로 나눈 나머지에 따라 패의 종류를 구분할수 있을듯? ==> 일부완료. 정밀한규칙 보완 필요
    /////////////////////////


    ### 아이디어 ###
    변수 이름에 함수를 넣는 방법이 있는지?? 없을듯? ==> 없음..
    이미지경로가 String이니까 각 패에 해당하는 이미지를 넘버링해서 불러올수있을듯? ==> 완료
    고도리, 광, 피, 어쩌구 하는 규칙들 구현하면 재밌을듯 찾아보기 ==> 시간남으면하기
    /////////////////////////
   */

    int cNum; // 0~47까지 카드 식별번호 부여
    boolean isChecking; // 클릭 후 검사배열에 올라가있는지 여부를 표시. 한 패를 여러번 클릭하여 검사배열에 올리는것을 방지
    boolean isOpened = false; // 오픈된 카드인지 여부를 표시

    void nonFlipped() {
        this.setIcon(new ImageIcon("./src/cardGame/images/card.png"));
        this.isChecking = false;
    }
    void flipped() {
        this.setIcon(new ImageIcon(String.format("./src/cardGame/images/%d.png", this.cNum)));
    }

}
