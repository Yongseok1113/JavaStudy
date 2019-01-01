/*
 *    시작일 : 2018. 12. 18
 *    프로그래머스 코딩테스트 연습문제 : 완주하지 못한 선수
 *    입력 : 참가자 명단 participant , 완주자 명단 completion
 *    조건 : 참가자 수(1~100,000), completion = participant - 1, 참가자 동명이인 존재함, 이름은 1~20개 알파벳 소문자
 *    완주하지 못한 한명을 찾아내서 반환하는 프로그램
 * */
/*
==상황
삭제, 갱신이 없는 상황.
삽입, 검색만 있음.
가능하면 배열로
삭제는 필요할지도?
동명이인이 있는 참가자가 미완주자인 경우 명단에 있을 때 삭제하는 방식으로 간단히 구현가능.
삭제작업 시간복잡도가 O(N), 시간복잡도 N제곱으로 너무높음.
재사용도 좋지 않음. 메소드 호출때마다 해시맵을 새로 만들어야 함.
삭제 사용하지 않는 방향으로 구현할 것.

==해결방법
각각의 참가자가 완주자 명단에 있는 지 알아야함.
전부 확인하지 않아도 존재 여부를 체크할 수 있는 방법
해시.
해시코드는?
동명이인을 어떻게 처리하지?

참가자명단과 완주자명단은 모두 같은데 한개 비는 형태.
참가자에서 하나씩 확인할 때 해시코드가 어디든 첫번째만 확인하고 맞으면 지우는 식으로 한다면.
지우는 작업을 10만번이나 할 순 없음.
안맞는 순간이 서로 있는건데 엇갈린 건지, 정말 없는 것인지 판단되야 한다.

==샘플
1, 2, 2, 3, 3, 4, 5
1, 2, 3, 3, 4, 5
해시 1, (2, 5), 3, 4
해시 (2, 5)에 들어간 자료, 2, 5
확인되는 참가자 순서 2, 2, 5
정말 없는 것이다.
yso 숫자값 = 2,  oys 숫자값 = 2 인 상황이라면?
아니다 자료값이 모두 같고 하나만 다름.
정말 없는것이라 간주해도 된다.
String.hashCode() 공부
ex)
public int hashfunction(String parameter) {
    //parameter = "ysoh"
    int hash = 0;
    int g = 31;
    for(int i=0; i<parameter.length; i++) {
        hash = g * hash + parameter.charAt(i);
    }
    return hash;
}
스트링 배열처음 부터 문자단위로 자르면서 g값을 곱하고 문자값을 더해준다.
=> 'y' * g의 0제곱 + 's' * g의 1제곱 + 'o' * g의 2제곱 + 'h' * g의 3제곱과 같다.
각 문자의 ASCII 값에 곱해지는 g값이 순서에 따라 다르게 곱해지기 때문에
"yso"와 "oys"의 해시코드는 다른 값이 된다.
hashCode에 moduler 연산하면 같아질 가능성 있음.
String.hashCode() % 101 => 검색 단위 천개 정도로 축소가능.
HashMap 클래스 hash관리 형태 공부 필요. chainning/ open addr?
HashMap 클래스에서 hash를 자동으로 생성함, key에 String값만 넣어도 된다.
HashMap hashMap;
hashMap.put("target", int targetValue);
*/
package Hash;

import java.util.Arrays;
import java.util.HashMap;

public class Marathon {
    //30점;;
    public String Solution(String[] participant, String[] completion) {
        String answer = "initial data";
        HashMap hashMap = new HashMap();

        for(String element : completion) {
            hashMap.put(element.hashCode(), element);
        }

        for(String element : participant) {
            if(!hashMap.containsKey(element.hashCode())) {
                return element;
            } else  {
                if(!hashMap.containsValue(element))
                    return element;

                if(element.equals(hashMap.get(element.hashCode())))
                    hashMap.remove(element.hashCode(), element);
            }
        }
        return answer;
    }
    /*
     * 해시코드값이 같은데 문자가 아예 다른 경우도 있음
     * 확인하는 이름이 완전히 똑같은지 확인해야 함
     * 확인하고 지우는 방식은 시간복잡도 너무 높음
     * O(n)유지해야함
     * */

    //100점..
    //데이터가 단 하나만 다른 상황에서 같은 방식으로 정렬한다면 같은 위치를 서로 비교할 때 다른 값이 미완주자일 것이다.
    //예상대로 나왔지만 해시를 활용한 효율적인 자료관리가 아니다
    //참고만 할 것
    public String Solution2(String[] participant, String[] completion) {
        Arrays.sort(participant);
        Arrays.sort(completion);

        for(int i=0; i<completion.length; i++) {
            if(!participant[i].equals(completion[i])) {
                return participant[i];
            }
        }
        return participant[participant.length -1];
    }
    //해시맵을 활용한 풀이
    public String Solution3 (String[] participant, String[] completion) {
        String answer = "";
        HashMap hashMap = new HashMap();

        for(String element : completion)  {
            hashMap.put(participant.hashCode(), participant);
        }
        //해싱방식을 잘못이해함.

        return answer;
    }
    /*  다른 사람의 풀이
     *   참가자 이름을 value로 쓰는 것이 아니라 key로 사용하고 중복 값을 value로 사용한다는 발상.(핵심 생각)
     *   첫번째 루프에서 value입력에서 hm.hetOrDefault(player, defaultValue : 0) + 1 을
     *   hm.hetOrDefault(player, defaultValue : 1)하면 된다고 생각 했지만 결과는 동명이인 체크 실패.
     *   마지막 + 1 의미는 존재하는 player가 있다면, 현재 값에서 + 1증가 시켜준다는 의미
     *   기본값이 중요한게 아니라 + 1 해주는 것이 중요
     *
     *   풀이
     *   참가자로 해싱, 해싱할 때 참가자가 존재한다면 존재하는 값에서 + 1, 또는 존재하지 않으면 기본값 삽입(put)
     *   완주자로 참가자 해시맵을 다시 해싱, 존재할 때마다 1씩 감소
     *   키셋을 확인하면서 키가 0이 아니라면(= 완주자 명단에서 걸러내지 못한 한명, 미완주자)
     *   해당 player 반환
     *
     *   느낀점
     *   해싱할 때 키 값을 단순히 위치만 식별하는 색인으로 이해하고 숫자값으로 바꿀 생각만 하고 있었음
     *   그래서 중복 값(이 문제에서 동명이인)을 값으로 저장하여 처리한다는 생각을 못하고 다른 곳에서 해결할 생각만 하게된 것 같음.
     *   키 값이 분할된 리스트의 색인으로 기능하면서, 원하는 값으로 쓰일 수도 있다.
     *   이 문제에서만 가능한 것일 수 도 있지만 생각을 다양한 방식으로 하려고 노력 해야겠다.
     *
     *   대용량 데이터에서 특정값을 검색할 때 해시는 효율적이다.
     *   자바 HashMap 클래스에서는 엔트리에 해시코드가 같이 보관되어있다.
     *   키는 검색데이터가 있을 수도 있는 집합(리스트)의 색인으로 검색범위를 줄여 시간단축한다는 기능과
     *   키 자체로 찾고자 하는 데이터가 되어 value가 데이터의 속성(중복값 카운터 등)으로 사용될 수도 있다.
     *
     *   키는 찾고자 하는 데이터의 유일한 식별자가 아니라, 찾고자 하는 데이터일 가능성이 있는 데이터들이 모여있는 곳.
     *   값은 찾고자 하는 데이터의 현재 상태. 데이터의 실제 값일 수도 있고 데이터가 현재 어떤 상태(카운트 값, 중복횟수, 호출횟수 등등)
     *   일 수도 있다.
     *
     *   해시를 써야 할 때
     *   특정 위치를 검색하는 것이 아니라 특정위치가 있을 수도 있는 검색범위를 만들어 검색시간을 줄이고자 할 때 사용
     *
     * */

    public String Solution4(String[] participant, String[] completion) {
        String answer = "Retrive fail";
        HashMap<String, Integer> hm = new HashMap<>();

        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);

        for (String key : hm.keySet()) {
            if (hm.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }

}
