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
//key, value에 무엇을 저장할 것인지?
//key에 원본값? => 접두어인지 확인하는게 목적, 값을 서로 비교해야함,특정 값을 빠르게 찾는 것이 아님.
//최소 1, 첫번째 값으로 0~9까지 key로?
//String 해시는 문자 순서도 구별됨.
//키값 첫번째 문자로 -> 다시해싱가능?
// (hash1).value.charAt() 가능?
//String의 부분검색이 가능한가?
//문자열 포함여부 검색 : contains, IndexOf
//contains(CharSequence s) : 문자순서 s가 포함되있을 경우 true 리턴 -> 접두어인지 아닌지 따로 확인해야함.
//IndexOf(String str) : str과 같은 문자열이 포함되있는 경우에 첫번째로 발생한 색인을 반환함.
//substring(int beginIndex, int endIndex) : 해당 인덱스에 포함된 문자열을 반환
//앞부분이 비슷한 번호들끼리 뭉치게해야.
//원본 값은 해시에 반드시 저장되어야 한다. (비교하기 위해서)

//원본값들 중에 서로 중복되는 경우는 없음.

//다른값을 맨앞에서 부터 시작하여 완전히 포함하는 값이 있는지 확인하는 문제


import java.util.HashMap;


public class Phonebook {
    /*
    *   100점
    *   입력된 목록의 값들 중 최소길이를 구함
    *   전화번호의 맨 앞부터 최소길이만큼 잘라내어 해시키로 사용한다.
    *   값을 해시맵에 추가하는 과정에서 키 중복이 일어난다면, (최소길이 까지는 접두사일 가능성이 존재한다)
    *   이 때 (확인하는 전화번호가 해시맵에 저장된 값을 포함 또는 저장된값이 확인하는 전화번호를 포함) 하면
    *   즉, 둘 중 하나가 접두사로 사용되면 false를 리턴한다.
    *
    * */
    public boolean Solution(String[] phone_book) {
        boolean answer  = true;
        int minLength = 20;
        HashMap<String, String> hashMap = new HashMap();
        for(String element : phone_book) {
            if(element.length() < minLength)
                minLength = element.length();
        }

        for(String element : phone_book) {
            String preCheck = element.substring(0, minLength);
            if(hashMap.containsKey(preCheck)) {
                if(hashMap.get(preCheck).contains(element) ||  element.contains(hashMap.get(preCheck))) {
                    return false;
                }
            }
            hashMap.put(preCheck, element);
        }
        return answer;
    }
}
