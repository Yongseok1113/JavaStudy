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


import java.util.*;


public class Phonebook {
    /*
    *   100점
    *   입력된 목록의 값들 중 최소길이를 구함
    *   전화번호의 맨 앞부터 최소길이만큼 잘라내어 해시키로 사용한다.
    *   값을 해시맵에 추가하는 과정에서 키 중복이 일어난다면, (최소길이 까지는 접두사일 가능성이 존재한다)
    *   이 때 (확인하는 전화번호가 해시맵에 저장된 값을 포함 또는 저장된값이 확인하는 전화번호를 포함) 하면
    *   즉, 둘 중 하나가 접두사로 사용되면 false를 리턴한다.
    *
    *   ["1234", "6543219", "6543218", "65432199"] Expected value : false , Result : true
    *   최소길이만큼 같으면서 서로 다른 경우가 생기면 키가 덮어씌워진다.
    *   A저장, A와 최소길이부분이 같지만 나머지는 다른 B저장(B로 덮어씌워짐), A를 접두어로 쓰는 C확인 false가 되어야하지만,
    *   덮어씌워져 A정보가 사라졌기 때문에 체크하지 못함.
    *   잘못된 해결방법.
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
    //다른사람 풀이는 정렬 후 패턴탐색이 대부분임. N제곱 시간이지만 배열 정렬 탐색이라 매우 빠른 것 같음.

    //해시 저장 과정과 검색 과정이 동시에 처리되선 안됨.
    //정렬은 고려할만함. 길이가짧고 접두가 같아지는 순으로 정렬될 것임.
    //정렬 2중for문 패턴탐색보다 효율적이기 힘들다.

    //이 문제를 해시로 풀려면, 서로 접두어가 될수 있는 가능성이 있는 것들을 쉽게 탐색할 수 있어야 한다.
    //해시맵 버킷이 서로 연관된 여러 value를 보는 기능을 제공하기 위해서는 키 중복을 허용해야 한다.
    //HashMap 클래스에 해시충돌 회피 구현이 되어있지 않다. (키중복을 허용하지 않는다.)
    //Seperate Chainnig 을 구현하여 해결해보자.

    //키 중복 없이 구현하는 시나리오
    //1버킷 1정보, 버킷간 관련성을 찾기 위해서는 value 중복 할 수 밖에 없음.
    //원본 값중 최소길이까지 잘라 value에 보관한다면 키는 원본 값이 되어야 함.
    //원본에서 최소길이만큼 자른 문자열이 해시에 존재하면 해시를 확인하면서 같은 value의 키를 받아 원본과 비교한다.
    //이 방법도 결국 이중반복으로 하나하나 모두 찾아야 한다.
    //해시 전체를 확인하지 않기 위해서는 관련 키별로 리스트를 짜는 수 밖에 없음.


    //java에서 해시맵은 덮어씌우는 것이 아니었음. chainning 방식으로 해시 충돌 허용하고있음. 8개미안 - LinkedList
    //8개 이상 - tree

    //출처 - 스택오버플로우 , 검색된 제목 : Java: Hashtable stores multiple items with same hash in one bucket
    //https://stackoverflow.com/questions/26287852/java-hashtable-stores-multiple-items-with-same-hash-in-one-bucket

    //덮어 씌워지는 것처럼 보이는 이유는 equals,와 hashcode 재정의 하지 않아서.
    //equals는 재정의 하지 않고 hashcode만 재정의 한 결과
    //key값이 같은 여러 다른 value들이 저장되어 리스트형태로 연결된 상태가 되었다.







    public boolean Solution2(String[] phone_book) {
        boolean answer = true;
        int minPhoneSize = 999;

        //데이터 용량 : 100만 , initialCapacity (= 버킷 수 != element 수)
        //기본값 16, -> 데이터 용량 16만 => 버킷 하나당 element 1만개 비교
        //버킷 확장 프로세스 호출시 (rehashing) 부하 큼. 버킷수 많으면 속도 빠름, 메모리 낭비 높음. 적으면 속도 느림 낭비 적음
        //loadFactor는 0.75가 기본값
        Map<PhonebookKey, String> hashMap = new HashMap(1000000);

        for(String phoneNumber : phone_book) {
            if(phoneNumber.length() < minPhoneSize)
                minPhoneSize = phoneNumber.length();
        }

        for(String phoneNumber :phone_book ) {
            String prefix = phoneNumber.substring(0, minPhoneSize);
            hashMap.put(new PhonebookKey(prefix), phoneNumber);
        }
        for(PhonebookKey key : hashMap.keySet()) {
            System.out.println(hashMap.get(key));
        }


        return answer;
    }//Solution2 end

    public class PhonebookKey {
        private final String key;


        public PhonebookKey (String key) {
            this.key = key;

        }

        /*
        사용하지 않으니 원하는 결과가 나옴.
        @Override
        public final boolean equals(Object o) {
            if(o == null) { return false; }
            if(o == this) { return true; }
            if(this.getClass() != o.getClass()) {return false; }

            final PhonebookKey check = (PhonebookKey) o;

            if( key == null ) {
                if (check.key == null) {
                    return false;
                }
            } else if (!key.equals(check.key)) {
                    return false;
            }

            return true;
        }*/

        public int hashCode() {
            final int PRIME = 31;
            int result = 1;
            result = PRIME * result + ((key == null) ? 0 : key.hashCode());
            return result;
        }
    }

    //HashMap<String, ArrayList> 형태로 구현

    public boolean Solution3(String[] phone_book) {
        boolean answer = true;
        LinkedList linkedList = new LinkedList();
        HashMap<String, ArrayList<String>> hashMap = new HashMap(1000000);
        int minPhoneSize = 999;
        for(String phoneNumber : phone_book) {
            if(phoneNumber.length() < minPhoneSize)
                minPhoneSize = phoneNumber.length();
        }
        for(String phoneNumber : phone_book) {
            String prefix = phoneNumber.substring(0, minPhoneSize);
            if(hashMap.containsKey(prefix)) {

            } else {
                hashMap.put(prefix, new ArrayList<>());
            }

        }


        //hashmap.put(new LinkedList(?, ?), element);
        //if( key 존재) add LinkedList

        return answer;
    }  //Solution3 end

}
