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
 *    [[crow_mask, face], [blue_sunglasses, face], [smoky_makeup, face]]	             3
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
 * */

package Hash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Camouflage {

    /*
    *   solution
    *   96.4점.
    *   케이스1 시간초과. 최악의 해싱인 경우 인 것 같다.
    *   무조건 하나를 꺼낸다. 여러개의 옷장을 동시에 고르는 경우의 수
    *   선택한 옷장에서 옷을 꺼내는 경우의 수를 활용하려면 직접 모두 접근하는 방식으로 구현해야함
    *   조합(nCr), 팩토리얼 응용
    *   직접 모두 접근 => nCr에서 재귀호출에서 주목하고있는 옷장이 선택되거나(r-1) 선택되지 않을 경우(r)를 합한 값
    *   Combination 재귀호출 없이 while구현할 수 없는 것 같다.
    *   트리구조가 필요함.
    *
    *   최악의 경우를 따로 처리해주면서 100점.
    * */
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

        //해쉬맵에서 최악의 경우는 각 버킷에 한개만 들어가는 경우.

        if(closetList.size() == clothes.length) {
            for(int i = 1; i < closetList.size(); i++) {
                answer += combination(closetList.size(), i);
            }
            return answer + 1; //모든 옷장을 고르는 경우 + 1
        }


        //모든 경우의 수 = nC1 + nC2 + ... +nCn
        int [] arrayForCombination = new int [closetList.size()];
        for(int i=1; i<=closetList.size(); i++) {
            answer += Combination(arrayForCombination, closetList ,closetList.size(), i, 0, 0);
        }
        return answer;
    }
    public int combination(int n, int r) {
        if(n == r || r == 0) return 1; else return combination(n - 1, r - 1) + combination(n - 1, r);
    }
    public int Combination(int[] selectedCloset, ArrayList closetList, int n, int r, int closetListIndex, int selectedClosetIndex) {

        if(r == 0) {
            int result = 1;
            for(int i=0; i<selectedClosetIndex; i++) {
                result *= selectedCloset[i];
            }
            return result;
        }
        else if(r == n) {
            int result = 1;
            for(int i=0; i<r; i++) {
                result *= (int)closetList.get(i);
            }
            return result;
        }
        //선택이 완료된 시점의 else문에서 선택을 하지 않았다고 가정된 경우 접근됨
        //모든 옷장을 선택하는 경우(r==n)가 처리되고 있기 때문에 값이 추가되어선 안됨. 0반환
        else if(closetListIndex == n) return 0;
        else {
            selectedCloset[selectedClosetIndex] = (int)closetList.get(closetListIndex);
            return
                    //현재 주목하는 옷장을 선택한 경우
                    Combination(selectedCloset, closetList, n, r-1, closetListIndex + 1, selectedClosetIndex + 1) +
                    //선택 하지 않은 경우
                    Combination(selectedCloset, closetList, n, r, closetListIndex + 1, selectedClosetIndex);
        }
    }
    /*
    *    solution2
    *    다른사람 생각
    *    100점
    *    모든 경우의 수를 구하려 할 필요 없음.
    *    옷장을 선택하지 않는 경우의 수를 더한후 순열. 모든 옷장에서 입지 않는 경우의 수는 없으므로 -1
    *    예를 들어
    *    머리 3, 상의 2, 하의 1 이라면,
    *    (3 + 1) * (2 + 1) * (1 + 1) -1 = 23
    *    ..대단
    * */
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

    public int solution3(String[][] clothes) {
        var map = new HashMap<String, Integer>();
        for (String[] strings : clothes) {
            int p = 0;
            String key = strings[1];
            if(map.containsKey(key)){
                p = map.get(key);
            }
            map.put(key, p+1);
        }
        Collection<Integer> values = map.values();
        Integer[] counts = new Integer[values.size()];
        values.toArray(counts);

        int[][] dp = new int[values.size()][2];
        dp[0][0] = 1;
        dp[0][1] = counts[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1];
            dp[i][1] = dp[i-1][0] * counts[i] + dp[i-1][1] * counts[i];
        }

        return dp[dp.length-1][0] + dp[dp.length-1][1] -1;
    }

}
