/*
*   시작일 : 2018.12.23
*   프로그래머스 코딩테스트 연습문제 전화번호부
*   전화번호부에 적힌 전화번호 중 한 번호가 다른 전화번호의 접두어인 경우가 있는지 확인하려 합니다.
*   ex) 구조대는 영석이의 전화번호 접두사
*   구조대 : 119
*   박준영 : 97 654 223
*   지영석 : 11 9552 4421
*
*   입출력 예제  (접두어인 경우가 있으면 false, 없으면 true)
*   phone_book	                    return
*   [119, 97674223, 1195524421]	    false
*   [123,456,789]	                true
*   [12,123,1235,567,88]	        false
*
*   전화번호부 길이는 1~1,000,000
*   전화번호 길이는 1~20
* */
package Hash;
//풀이방법으로 해시를 선택한 이유 : 대용량 데이터셋, 부분검색
//해싱
//String의 부분검색이 가능한가?
//

public class Phonebook {
    public boolean Solution(String phone_book) {
        boolean answer  = true;

        for(int i=0; i<타겟길이; i++) {
            if(타겟[i] != 리스트[i])
                answer = false;
        }
        return answer;
    }
}
