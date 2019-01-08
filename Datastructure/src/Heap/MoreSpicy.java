/*
*   시작일 : 2018. 1. 5
*   프로그래머스 코딩 테스트 연습문제 : 더 맵게
*
*   섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
*
*   Leo는 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복하여 섞습니다.
*   Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때,
*   모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.
*
*   제한 사항
*   scoville의 길이는 1 이상 1,000,000 이하입니다.
*   K는 0 이상 1,000,000,000 이하입니다.
*   scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
*   모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.
*
*   입출력 예
*   scoville	            K	return
*   [1, 2, 3, 9, 10, 12]	7	2
*   설명
*   스코빌 지수가 1인 음식과 2인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
*   새로운 음식의 스코빌 지수 = 1 + (2 * 2) = 5
*   가진 음식의 스코빌 지수 = [5, 3, 9, 10, 12]
*
*   스코빌 지수가 3인 음식과 5인 음식을 섞으면 음식의 스코빌 지수가 아래와 같이 됩니다.
*   새로운 음식의 스코빌 지수 = 3 + (5 * 2) = 13
*   가진 음식의 스코빌 지수 = [13, 9, 10, 12]
*
*   모든 음식의 스코빌 지수가 7 이상이 되었고 이때 섞은 횟수는 2회입니다.
* */

package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MoreSpicy {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        Arrays.sort(scoville);
        if(scoville[0] > K) return 0;
        for(int element : scoville) {
            if(element < K)
                priorityQueue.add(element);
            else
                break;
        }
        //0이거나, -1이거나, 섞은횟수거나
        //0 : 전부 타겟지수 이상
        //-1 : 전부 타겟지수 이상으로 조합 불가
        //섞은횟수 :전부 타겟지수 이상으로 조합 가능
        //적은 지수 갯수 1개
        //적은 지수 갯수 2개
        //적은 지수 갯수

        while(priorityQueue.size() > 1) {
            int target1 = priorityQueue.remove();
            int target2 = priorityQueue.remove();

            if( (target1 + (target2 * 2) < K)) {
                priorityQueue.add(target1 + (target2 * 2));
            }
           /* else if(priorityQueue.size() == 1)
                priorityQueue.add(target1 + (target2 * 2)); 이걸 추가하면 다른게 틀리는 대신 이전에 틀리던
                케이스 6, 7, 10, 13 이 모두 맞음. -> 3개남았을 때 두개를 검사한게 넘을 경우, 마지막하나 검사가 안되는 상태였음
                아무래도 무조건 추가는 되면서 비교가 되어야 하는 것 같다.*/
                answer++;
        }
        if(priorityQueue.size() == 1 ) {
            return -1;
        }
        return answer;
    }
}
