/*
*   시작일 2019. 1. 15
*   프로그래머스 코딩 테스트 연습문제 가장 큰 수
*   출처 : https://programmers.co.kr/learn/courses/30/lessons/42746?language=java
*   문제 설명
0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

제한 사항
numbers의 길이는 1 이상 100,000 이하입니다.
numbers의 원소는 0 이상 1,000 이하입니다.
정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
입출력 예
numbers	return
[6, 10, 2]	6210
[3, 30, 34, 5, 9]	9534330
*
* */

package Sort;

import java.util.Arrays;

public class LargestNumber {
    public StringBuffer solution(int[] numbers) {
        StringBuffer answer = new StringBuffer();
        String[] numbersToStr = new String[numbers.length];
        int offset = 0;
        char compareNum = 0;

        for(int i=0; i<numbers.length; i++){
            numbersToStr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numbersToStr);

        for(int i=numbersToStr.length-1; i>=0; i--) {
            if(numbersToStr[i].length() == 1) {
                answer.insert(offset, numbersToStr[i]);
                offset = answer.length();
                compareNum = 0;
            }
            else if(numbersToStr[i].charAt(numbersToStr[i].length()-1) < numbersToStr[i].charAt(0)){
                if(numbersToStr[i].charAt(1) > compareNum ) {
                    compareNum = numbersToStr[i].charAt(1);
                    offset = answer.length();
                }
                answer.append(numbersToStr[i]);
            }
            else if(numbersToStr[i].charAt(numbersToStr[i].length()-1) > numbersToStr[i].charAt(0)) {
                answer.insert(offset, numbersToStr[i]);
                offset = answer.length();
            }
            else {
                answer.append(numbersToStr[i]);
            }
        }
        return answer;
    }
}
