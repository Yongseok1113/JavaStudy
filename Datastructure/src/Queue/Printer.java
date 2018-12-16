package Queue;



//'properties'는 작업리스트, 'location'은 요청한 작업 위치(properties 배열의 인덱스와 같음. 0이면 배열 첫 번째)
//작업리스트의 우선순위 값이 클 수록 우선도가 높음.
//왼쪽에서 오른쪽으로 진행하며 인쇄 여부를 확인
//자기보다 인쇄 우선순위가 높은 작업이 있으면 가장 나중으로 미룸
//location의 작업이 몇번째에 진행 되는지 찾아 값을 리턴.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//나중에 요청된 작업 리스트에 우선순위가 더 높은 작업이 있으면 현재 작업을 가장 마지막 작업으로 미룸.
//요청한 작업이 처리되는 순번 반환
//priorities : 우선순위 값이 저장된 배열, location : 요청한 작업의 배열 인덱스(0부터 마지막)
public class Printer {

    //정확도 점수 : 65점
    public int Solution(int[] priorities, int location) {
        int answer = 0;

        //요청 작업보다 높은 작업 중 우선 순위가 가장 낮은 작업
        int minNum = 10;
        //minNum의 위치값
        int minLocation = 0;
        boolean sameNumFlag = false;
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<priorities.length;i++) {
            if(priorities[i] > priorities[location]) {
                queue.add(priorities[i]);
                if(priorities[i] < minNum) {
                    minNum = priorities[i];
                    minLocation = i;
                }
            }
            else if(priorities[i] == priorities[location])
                sameNumFlag = true;
        }

        //minlocation의 위치에 따라 우선순위가 같은 작업 추가
        if(queue.isEmpty()) {
            if(sameNumFlag) {
                for (int i = 0; i <= location; i++) {
                    if (priorities[i] == priorities[location])
                        queue.add(priorities[i]);
                }
            }
            answer = queue.size();
            return answer;
        }
        else if(location > minLocation) {
            if(sameNumFlag) {
                for (int i = minLocation; i <= location; i++) {
                    if (priorities[i] == priorities[location])
                        queue.add(priorities[i]);
                }
            }
            answer = queue.size();
            return answer;
        }
        else if(location < minLocation) {
            if(sameNumFlag) {
                for (int i = minLocation; i < priorities.length; i++) {
                    if (priorities[i] == priorities[location])
                        queue.add(priorities[i]);
                }
            }
            answer = queue.size() + 1;
            return answer;
        }

        return 9999;
    }//Solution

    /*  풀이 전략 2
    *   1. 요청작업보다 우선순위가 낮은것은 모두 삭제하면서 순차 저장.
    *       이 후 처리하는 작업량을 낮추기위해서
    *
    */

    //정확도 점수 : 45점
    public int Solution2(int[] priorities, int location) {
        int answer = 0;
        int [] deleteLowPriority = new int[priorities.length];
        int deletedLowPriorityIndex = 0;
        int locationIndex = 0;
        int minNum = 10;
        int minLocation = 0;
        boolean sameNumFlag = false;

        //결과 : 우선순위가 높거나 같은 작업만 남는다.
        //location보다 앞에 있는
        for(int i=0; i<priorities.length; i++) {
            if(priorities[i] >= priorities[location]) {
                if(i == location) {
                    locationIndex = deletedLowPriorityIndex;
                }
                deleteLowPriority[deletedLowPriorityIndex++] = priorities[i];
            }
        }
        //locationIndex = 새로 걸러낸 리스트의 요청작업 위치
        //deletedLowPriorityIndex = 새로 걸러낸 리스트의 마지막 다음 위치 = 리스트 크기 값
        int cnt = 0;
        for(int i=0; i<deletedLowPriorityIndex;i++) {
            //리스트 값이 요청작업 값보다 크면
            if(deleteLowPriority[i] > deleteLowPriority[locationIndex]) {
                cnt++;
                //요청작업보다 큰 최소우선순위 값보다 작으면
                if(deleteLowPriority[i] < minNum) {
                    minNum = deleteLowPriority[i];
                    minLocation = i;
                }
            }
            else if(deleteLowPriority[i] == deleteLowPriority[locationIndex] && i != locationIndex)
                sameNumFlag = true;
        }

        //minlocation의 위치에 따라 우선순위가 같은 작업 추가


        if(minNum == 10) {
            if(sameNumFlag) {
                for (int i = 0; i <= locationIndex; i++) {
                    cnt++;
                }
            }
            else
                cnt++;
            answer = cnt;
            return answer;
        }
        else if(locationIndex > minLocation) {
            if(sameNumFlag) {
                for (int i = minLocation; i <= location; i++) {
                    if (deleteLowPriority[i] == deleteLowPriority[location])
                        cnt++;
                }
            }
            answer = cnt;
            return answer;
        }
        else if(location < minLocation) {
            if(sameNumFlag) {
                for (int i = minLocation; i < deletedLowPriorityIndex; i++) {
                    if (deleteLowPriority[i] == deleteLowPriority[locationIndex])
                        cnt++;
                }
            }
            answer = cnt + 1;
            return answer;
        }
        return 0;
    }//Solution2

    /*  풀이 전략 3 : 원형 배열 큐
    *   1. front확인.
    *   2. 우선순위가 더 높은 작업이 있는지 검사. (요청작업보다 우선순위가 높고 더 높은 우선순위가 없다면. 카운트 + 이탈
    *   3. 우선순위가 요청작업보다 낮다면 큐에서 이탈
    *   4. 요청작업의 위치가 계속 바뀜 (위치 관리해줘야 함)
    *   5. 요청작업일 때 우선순위가 더 높은 작업이 없다면, 카운트 + 루프 이탈 + 반환
    */
    //정확도 점수 : 100점
    public int Solution3(int[] priorities, int location) {

        int queueSize = priorities.length + 1;
        int front = 1;
        int rear = 0;
        int frontIndex = 1;
        int rearIndex =0;
        int [] circularQueue = new int [queueSize];
        int target = 0;
        boolean isHighst = true;
        int printCount = 1;

        //타겟보다 우선 순위가 낮은 작업 필터링
        for(int i=0; i<priorities.length; i++) {
            if(priorities[i] >= priorities[location]) {
                rearIndex = ++rear % queueSize;
                if(i == location) {
                    target = rearIndex;
                }
                circularQueue[rearIndex] = priorities[i];
            }
        }

        int i = 1;
        boolean flag = false;
        while(flag == false) {
            isHighst = true;

            if(frontIndex < rearIndex) {
                for(int j=frontIndex; j<=rearIndex; j++) {
                    //리스트에서 확인중인 작업이 현재작업보다 큰 경우(우선순위가 높은 경우)
                    if(circularQueue[j] > circularQueue[frontIndex]) {
                        isHighst = false;
                        break;
                    }
                }
            }// if(frontIndex < rearIndex) end

            else if(rearIndex < frontIndex) {
                for(int j=frontIndex; j<queueSize; j++) {
                    if(circularQueue[j] > circularQueue[frontIndex]) {
                        isHighst = false;
                        break;
                    }
                }
                for(int j=0; j<=rearIndex; j++) {
                    if(circularQueue[j] > circularQueue[frontIndex]) {
                        isHighst = false;
                        break;
                    }
                }
            } //else if(rearIndex < frontIndex) end

            if(isHighst) {
                if(i == target) {
                    //끝

                    return printCount;
                }
                printCount++;
                circularQueue[i] = 0;
            }
            else if(isHighst == false) {
                rearIndex = ++rear % queueSize;
                if(i == target) {
                    target = rearIndex;
                }
                circularQueue[rearIndex] = circularQueue[i];
                circularQueue[i] = 0;
            }
            frontIndex = ++front % queueSize;
            i++;
            i = i % queueSize;

        }


        return printCount;
    }
}
