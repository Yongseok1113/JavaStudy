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
링크드리스트도 나쁘지 않지만 가능하면 배열이 베스트.
삭제는 필요할지도?
동명이인이 있는 참가자가 미완주자인 경우?

==해결방법
각각의 참가자가 완주자 명단에 있는 지 알아야함.
전부 확인하지 않아도 존재 여부를 체크할 수 있는 방법
해시.
해시코드는?
이름을 이루는 알파벳 개수, 이름값 모듈러(알파벳 갯수 26개), ..
동명이인을 어떻게 처리하지?
동명이인 인지는 어떻게 판단하지?
무조건 같은 리스트에 들어 갈 것임. 몇번째로 들어갔는지 체크?
참가자 리스트를 정렬?
참가자, 완주자 리스트를 정렬해서 해싱 하면 같은 해시값의 다른 자료값들이라도 해시 리스트에 들어갈 때 정렬될 것 같다.
둘 다 정렬해야 원하는 결과.
Array.sort는 어떤 방식으로 정렬되지?
Array.sort에 맞춰서 해시코드 작성.
둘 다 정렬하면 상관없음.
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
아니다 자료값이 모두 같고 하나만 다름. 정렬메소드가 같다면 똑같이 확인할 것이다.
정말 없는것이라 간주해도 된다.

==만약 지운다면?
장점
리스트에 몇번째 접근인지 관리하지 않아도 리스트의 맨 앞만 확인하면됨.
단점
확인할 때마다 하나씩 지우는 작업. 최악의경우 약 10만번 지움
즉 확정적으로 1회 2번 계산(지움, 첫번째 확인)
배열로 만들기 비효율. 큐. 리스트로 만들어야함.
==지우지 않는다면?
장점
삭제 작업 없음 배열로 만들기 좋음.
단점
리스트가 몇번째에 접근했는지 관리해야함.

결정
지우지 않는다.
둘다 정렬
해시코드는 맨앞자리 알파벳으로 26개 경우.


???
둘다 똑같이 정렬된다면, 매번 그 인덱스만 확인하면 되지 않나?!
문제 발견
String을 정렬 할 수 가 없음.
완주자명단과 참가자명단이 같은 순서로 되어있다는 보장이 없음.
ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ
엎자

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
            } else {
                if(!hashMap.containsValue(element)) {
                    return element;
                }

                hashMap.remove(element.hashCode(), element);

            }
        }



        return answer;
    }
}
