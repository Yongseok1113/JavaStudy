/*
 *    시작일 : 2018. 12. 31
 *    프로그래머스 코딩테스트 연습문제 : 위장
 *    입력 : 위장용 의상 (의상의 이름, 의상의 종류) 로 구성된 2차원 배열
 *    조건 : 모든 문자열 길이는 1~20 자연수 , 알파벳 소문자 와 '_'로만 이루어져있음
 *           총 의상 수는 1~30
 *           모든 원소는 문자열로 이루어짐
 *           같은 이름의 의상은 존재하지 않음(의상의 종류는 중복)
 *    모든 서로 다른 의상조합 수를 찾아내서 반환하는 프로그램
 *    예제
 *
 *    [[yellow_hat, headgear], [blue_sunglasses, eyewear], [green_turban, headgear]]	 5
 *    [[crow_mask, face], [blue_sunglasses, face], [smoky_makeup, face]]	            3
 *
 *    생각
 *    같은 종류를 동시에 입을 수 는 없음.
 *
 *    어떻게 해싱 할건지.
 *    최대 경우의 수를 어떻게 구할건지.
 *
 *    최대경우의 수 : 1군데~의상종류갯수전체에서 하나씩 증가하며 뽑는 경우의 합
 *    예를들어 의상종류가 3가지 이고 각각 3개, 5개, 2개씩 의상이 있다고 한다면
 *    최대 경우의 수는 {3 + 5 + 2}(세군데중 1개 뽑기) + {(3 * 5) + (3 * 2) + (5 * 2)}(세군데중 2개 뽑기) + {3 * 5 * 2}세군데중 3개 뽑기
 *    나열된 옷장들로 조합할 수 있는 모든 경우의 수
 *    중복없이 n개중에서 r개를 뽑는다. = nCr

 *
 *    solution2
 *    다른사람 생각
 *    모든 경우의 수를 구하려 할 필요 없음.
 *    옷장을 선택하지 않는 경우의 수를 더한후 순열. 모든 옷장에서 입지 않는 경우의 수는 없으므로 -1
 *    예를 들어
 *    머리 3, 상의 2, 하의 1 이라면,
 *    (3 + 1) * (2 + 1) * (1 + 1) -1 = 23
 *    ..대단
 *
 *
 * */

package Hash;

import java.util.ArrayList;
import java.util.HashMap;

public class Camouflage {
    public int solution(String[][] clothes) {
        int answer = 0;
        HashMap<String, ArrayList<String>> hashMap_closets = new HashMap<>(30);

        //Keep clothes for each wardrobe
        for(int i=0; i<clothes.length; i++) {
            if(!hashMap_closets.containsKey(clothes[i][1])) {
                hashMap_closets.put(clothes[i][1], new ArrayList<>());
            }
            hashMap_closets.get(clothes[i][1]).add(clothes[i][0]);
        }

        //wardrobe list
        ArrayList<Integer> closetList = new ArrayList<>();
        for(String key : hashMap_closets.keySet()) {
            ArrayList<String> closet = hashMap_closets.get(key);
            closetList.add(closet.size());
        }

        //무조건 하나를 꺼낸다.
        //꺼낼 옷장을 고르는 경우위 수

        int i=0;
        int j=1;
        int temp = 1;

        //직접 모두 검사하려면 3차 반복문까지 가야함..
        //3차 구현 : 함수 내 2중for문 + 재귀호출.
        //조합(nCr) + 팩토리얼

        while(i < closetList.size() - 2) {
            for(;j<closetList.size(); j++) {
                answer += closetList.get(i) * closetList.get(j);
            }
            temp *= closetList.get(i);
            i++;
            j = i + 1;

            for(;j<closetList.size(); j++) {
                answer += temp * closetList.get(i) * closetList.get(j);
            }
        }

        return answer;
    }
    public void Combination(int n, int r) {

    }


    public int solution2(String[][] clothes) {
        int answer = 1;
        HashMap<String, ArrayList<String>> hashMap_closets = new HashMap<>(30);

        for(int i=0; i<clothes.length; i++) {
            if(!hashMap_closets.containsKey(clothes[i][1])) {
                hashMap_closets.put(clothes[i][1], new ArrayList<>());
            }
            hashMap_closets.get(clothes[i][1]).add(clothes[i][0]);
        }
        for(String key : hashMap_closets.keySet()) {
            ArrayList<String> closet = hashMap_closets.get(key);
            answer *= (closet.size() + 1);
        }
        answer = answer - 1;
        return answer;
    }
}
