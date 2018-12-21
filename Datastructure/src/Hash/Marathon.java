package Hash;



import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
동명이인이 있는 참가자가 미완주자인 경우?

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

*/
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
            hashMap.put(completion.hashCode(), completion);
        }


        return answer;
    }
    public String Solution4(String[] participant, String[] completion) {
        String answer = "";
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

