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
*
*
* */

package Heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MoreSpicy {

    //90.5점
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        boolean flag = false;
        Arrays.sort(scoville);

        for(int element : scoville) {
            if(element < K)
                priorityQueue.add(element);
            else {
                flag = true;
                break;
            }
        }
        while(priorityQueue.size() > 1) {
            int target1 = priorityQueue.remove();
            int target2 = priorityQueue.remove();
            int mixedTarget = target1 + target2 * 2;

            if(mixedTarget > K) {
                flag = true;
            }
            else if(!priorityQueue.isEmpty())
                priorityQueue.add(mixedTarget);
            answer++;
        }
        if(!priorityQueue.isEmpty() && !flag)
            return -1;
        return answer;
    }

    //100점
    //조건문 낭비 매우 심함.
    public int solution2(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        boolean flag = false;
        Arrays.sort(scoville);

        for(int element : scoville) {
            if(element < K)
                priorityQueue.add(element);
            else {
                priorityQueue.add(element);
                break;
            }
        }

        if(priorityQueue.size() == 1) {
            return 0;
        }

        while(priorityQueue.size() > 1) {
            int target1 = priorityQueue.remove();
            int target2 = priorityQueue.remove();

            if(priorityQueue.size() == 0) {
                if( target1 + (target2 * 2) < K) {
                    if(!flag)
                        return -1;
                }
                else {
                    answer++;
                    return answer;
                }
            }
            else if(target1 + (target2 * 2) < K)
                priorityQueue.add(target1 + (target2 *2));
            else if(priorityQueue.size() == 1){
                if(priorityQueue.element() > K) {
                    return answer + 1;
                }
                else
                    return answer + 2;
            }
            else
                flag = true;
            answer++;
        }
        return answer;
    }
}
