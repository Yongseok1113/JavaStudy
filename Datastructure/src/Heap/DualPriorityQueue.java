/*
*   시작일 : 2019. 1. 10
*   프로그래머스 코딩테스트 연습문제 이중우선순위큐
*   이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.
*
*   명령어  수신 탑(높이)
*   I 숫자	큐에 주어진 숫자를 삽입합니다.
*   D 1	    큐에서 최댓값을 삭제합니다.
*   D -1	큐에서 최솟값을 삭제합니다.
*   이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때,
*   모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.
*
*   제한사항
*   operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
*   operations의 원소는 큐가 수행할 연산을 나타냅니다.
*   원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
*   빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다
*
*   입출력 예
*   operations	            return
*   [I 16,D 1]	            [0,0]
*   [I 7,I 5,I -5,D -1]	    [7,5]
* */

package Heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class DualPriorityQueue {
    //100점
    public void solution(String[] operations) {
        int[] answer = new int[2];
        PriorityQueue<Integer> minPQ = new PriorityQueue<>(1000000);
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(1000000, Collections.reverseOrder());

        for (String element : operations) {
            String[] command = element.split(" ");
            if (command[0].equals("I")) {
                minPQ.add(Integer.parseInt(command[1]));
                maxPQ.add(Integer.parseInt(command[1]));
            } else if (command[0].equals("D") && !minPQ.isEmpty() && !maxPQ.isEmpty()) {
                if (command[1].equals("1")) {
                    int removeTail = maxPQ.remove();
                    minPQ.remove(removeTail);
                } else if (command[1].equals("-1")) {
                    int removeTail = minPQ.remove();
                    maxPQ.remove(removeTail);
                }
            }
        }
        if (!maxPQ.isEmpty() && !minPQ.isEmpty()) {
            int max = maxPQ.element();
            int min = minPQ.element();
            answer[0] = max;
            answer[1] = min;
        }

        System.out.println(answer[0] + ", " + answer[1]);
    }


}
