package step_by_step.s07_수학1;

import java.math.BigInteger;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {

        Solution ss = new Solution();
        String[] in = {"aya", "yee", "u", "maa", "wyeoo"};
        System.out.println("결과 : " + ss.solution(in));

    }
}
class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        for(int i = 3 ; i< 4; i++){
            boolean isChecking = false;
            boolean isF = false;
            int index = 0;
            while (babbling[i].length()>index){
                if(!isChecking && babbling[i].charAt(index)=='a'){
                    isF = false;
                    isChecking=true;
                    System.out.println(index);
                        if(babbling[i].charAt(index+1)=='y'){
                            index++;
                            if(babbling[i].charAt(index)=='a'){
                                isF = true;
                                index++;
                                isChecking = false;
                            }
                        } else {
                            isChecking = false;
                            isF = false;
                        }
                }
                else if(!isChecking && babbling[i].charAt(index)=='y'){
                    isChecking=true;
                    index++;
                    if(babbling[i].charAt(index)=='e'){

                        isF = true;
                        index++;
                        isChecking = false;
                    } else isChecking = false;
                }
                else if(!isChecking && babbling[i].charAt(index)=='m'){
                    isChecking=true;

                    if(babbling[i].charAt(index+1)=='a'){
                        isF = true;
                        index++;
                        isChecking = false;
                        index++;
                        System.out.println(index);
                    } else isChecking = false;
                }
                else if(!isChecking && babbling[i].charAt(index)=='w'){
                    isChecking=true;
                    index++;
                    if(babbling[i].charAt(index)=='o'){
                        index++;
                        if(babbling[i].charAt(index)=='o'){

                            isF = true;
                            index++;
                            isChecking = false;
                        }
                    } else isChecking = false;
                } else {
                    index=99;
                    isF = false;
                }

                }
            if(isF) answer++;
            }

        return answer;
    }
}

