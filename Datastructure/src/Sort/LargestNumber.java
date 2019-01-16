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
    /*
     *   경우의 수
     *   한자리 수가 들어가야하는 곳?
     *   두번째 자리수가 한자리수보다 작을 때
     *   두자리 수가 들어가야하는 곳?
     *   두자리까지 같고 세번째자리가 작다? /
     *
     *   스트링정렬하면 앞 자리가 높은순으로 정렬됨.
     *   순차적으로 더해주면서 삽입해야할 경우만 체크하면됨
     *   언제 삽입해야하지?
     *   비교하는 자리수가 더 큰 경우. 같거나 작은 경우 무조건 앞이 더 크다.(내림차순 또는 감소하면서 반복)
     *   만약 비교하는 자리수가 더 클 때 현재 확인하는 수의 자리 수만큼 반복하여 각자리수를 비교하고 반복이 끝나기 전에
     *   현재 확인하는 수가 더 큰 경우가 있다면. 삽입.
     *
     *   삽입할 위치를 어떻게 관리하지?
     *   삽입하는 경우가 아닐 때 문자 길이만큼 증가시킴
     *   하나 확인 끝날때마다 0으로 리셋
     *
     * *//*
     *   경우의 수
     *   한자리 수가 들어가야하는 곳?
     *   두번째 자리수가 한자리수보다 작을 때
     *   두자리 수가 들어가야하는 곳?
     *   두자리까지 같고 세번째자리가 작다? /
     *
     *   스트링정렬하면 앞 자리가 높은순으로 정렬됨.
     *   순차적으로 더해주면서 삽입해야할 경우만 체크하면됨
     *   언제 삽입해야하지?
     *   비교하는 자리수가 더 큰 경우. 같거나 작은 경우 무조건 앞이 더 크다.(내림차순 또는 감소하면서 반복)
     *   만약 비교하는 자리수가 더 클 때 현재 확인하는 수의 자리 수만큼 반복하여 각자리수를 비교하고 반복이 끝나기 전에
     *   현재 확인하는 수가 더 큰 경우가 있다면. 삽입.
     *
     *   삽입할 위치를 어떻게 관리하지?
     *   삽입하는 경우가 아닐 때 문자 길이만큼 증가시킴
     *   하나 확인 끝날때마다 0으로 리셋
     *
     *   시간복잡도 : O(n^2)
     *   점수 : 63.6
     *   테스트케이스 1, 3, 5, 6 시간초과.
     * */
    public StringBuffer solution(int[] numbers) {
        StringBuffer answer = new StringBuffer();
        String[] numbersToStr = new String[numbers.length];

        for(int i=0; i<numbers.length; i++){
            numbersToStr[i] = Integer.toString(numbers[i]);
        }
        Arrays.sort(numbersToStr);
        //문자열 정렬이 부하가 큼. 빼니 5900ms 에서 1400ms까지 떨어짐

        if(numbersToStr[numbers.length-1].equals("0")) {
            answer.append("0");
            return answer;
        }

        int targetNum;
        int compareNum;
        int offset = 0;
        boolean isInsert = false;

        for(int i=numbersToStr.length-1; i>=0; i--) {
            for(int j=numbersToStr.length-1; j>=i; j--) {
                if(numbersToStr[i].length() < numbersToStr[j].length()) {
                     targetNum = Integer.parseInt(numbersToStr[i] + numbersToStr[j]);
                     compareNum = Integer.parseInt(numbersToStr[j] + numbersToStr[i]);
                     if(targetNum > compareNum) {
                         answer.insert(offset, numbersToStr[i]);
                         isInsert = true;
                         break;
                     }
                }
                offset += numbersToStr[j].length();
            }
            if(!isInsert)
                answer.append(numbersToStr[i]);
            else
                isInsert = false;
            offset = 0;
        }
        return answer;
    }
    /*
    *
    * */

    public StringBuffer solution2(int[] numbers) {
        StringBuffer answer = new StringBuffer();
        String[] numbersToStr = new String[numbers.length];

        for(int i=0; i<numbers.length; i++){
            numbersToStr[i] = Integer.toString(numbers[i]);
        }

        if(numbersToStr[numbers.length-1].equals("0")) {
            answer.append("0");
            return answer;
        }

        int targetNum;
        int compareNum;
        int offset = 0;
        boolean isInsert = false;
        for(int i=numbersToStr.length-1; i>=0; i--) {
            for(int j=numbersToStr.length-1; j>=i; j--) {
                if(numbersToStr[i].length() <= numbersToStr[j].length()) {
                    targetNum = Integer.parseInt(numbersToStr[i] + numbersToStr[j]);
                    compareNum = Integer.parseInt(numbersToStr[j] + numbersToStr[i]);
                    if(targetNum > compareNum) {
                        answer.insert(offset, numbersToStr[i]);
                        isInsert = true;
                        break;
                    }
                }
                else {

                }
                offset += numbersToStr[j].length();
            }
            if(!isInsert)
                answer.append(numbersToStr[i]);
            else
                isInsert = false;
            offset = 0;
        }
        return answer;
    }
}
