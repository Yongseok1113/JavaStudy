package Queue;



//'properties'는 작업리스트, 'location'은 요청한 작업 위치(properties 배열의 인덱스와 같음. 0이면 배열 첫 번째)
//작업리스트의 우선순위 값이 클 수록 우선도가 높음.
//왼쪽에서 오른쪽으로 진행하며 인쇄 여부를 확인
//자기보다 인쇄 우선순위가 높은 작업이 있으면 가장 나중으로 미룸
//location의 작업이 몇번째에 진행 되는지 찾아 값을 리턴.
import java.util.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    }//Solution 3 end
    /*  풀이 3 리뷰 : 자료구조를 있는 그대로 하드코딩.
        풀이 4는 리스트를 이용하여 더 깔끔하게 개선할 것.
     */
    //정확도 점수 75점.
    public int Solution4(int[] priorities, int location) {
        int answer = 1;
        boolean flag = true;
        LinkedList<Integer> waitProcessList = new LinkedList<>();
        boolean isTopPriority = true;   //최우선순위 여부 확인

        //리스트의 노드중 타겟을 어떻게 식별해야 하지?
        //Linkedlist.add(int index, int elements);
        //index는 명시하지 않아도 됨. 자동으로 관리된다. 즉 Linckedlist.add(elements) 로 사용

        for(int i=0; i<priorities.length; i++) {
            if(priorities[i] >= priorities[location]) {
                waitProcessList.add(priorities[i]);
                if(i == location) {
                    location = waitProcessList.size() - 1;
                }
            }
        }

        while(flag) {

            isTopPriority = true;
            for(int i=0; i<waitProcessList.size(); i++) {
                if (waitProcessList.peek() < waitProcessList.get(i)) {
                    isTopPriority = false;
                    break;
                }
            }
            if(isTopPriority) {
                if( location == 0) {
                    return answer;
                }
                waitProcessList.poll();
                location--;
                answer++;
            }
            //최우선순위가 아님. 맨 앞노드 맨 뒤 추가 ->
            else if(!isTopPriority) {
                waitProcessList.add(waitProcessList.element());

                if (location == 0) {
                    location = waitProcessList.size() - 1;
                }
                waitProcessList.poll();
                location--;
            }
        }
        return answer;
    }//solution4 end

    //정확도 점수 40점

    public int Solution5(int[] priorities, int location) {
        int answer = 1;
        ArrayList<Integer> waitProcessList = new ArrayList<>();

        for(int i=0; i<priorities.length; i++) {
            if(priorities[i] >= priorities[location]) {
                waitProcessList.add(priorities[i]);
                if(i == location)
                    location = waitProcessList.size() - 1;
            }
        }

        while(!waitProcessList.isEmpty()) {
            int listHead = waitProcessList.get(0);
            for(int i=0; i<waitProcessList.size(); i++) {
                if (listHead < waitProcessList.get(i)) {
                    waitProcessList.add(listHead);
                    waitProcessList.remove(0);
                    if(location > 0)
                        location--;
                    else if(location == 0)
                        location = waitProcessList.size() - 1;
                    i = 0;
                }
            }
            if( location == 0) {
                return answer;
            }
            waitProcessList.remove(0);
            location--;
            answer++;
        }

        return answer;
    }//solution5 end

    /*  다른 사람 풀이 참고(효율적인 발상)
    *   전략 : 큐에 원본 작업을 저장하고 원본 배열을 오름차순 정렬한다.
    *   정렬된 배열의 마지막 값은 최우선순위 작업이다.
    *
    *   배열의 마지막 색인(priorities.length-1)에서 출력 수(answer)를 뺀 색인 값은
    *   현재 남아있는 최우선순위 작업의 위치와 같다. (핵심 생각)
    *
    *   맨 앞에 있는 현재작업을 확인 하면서 남아있는 최우선 순위 작업이면 출력 수 증가 후 요청작업이면 프로그램을 종료하고
    *   최우선순위 작업이 아니라면 가장 뒤에 작업 추가하고 요정작업 위치도 가장 뒤로 조정하고 반복한다.
    *
    *   이 풀이의 장점은
    *   이미 정렬이 되어있는 상태에서 순차 순환하기 때문에 항상 최우선 작업을 알 수 있다
    *   따라서 출력할 때마다 리스트 전체를 확인할 필요 없고
     *  최우선순위 위치인지, 요청작업위치인지 한번씩만 확인하면 되기 때문에
    *   정렬 1회 +  1차 반복으로 끝낼 수 있다
    *   시간복잡도가 제곱 수가 아니다.
    *
    *   느낀 점
    *   나열된 데이터들의 중간 위치 수정(삽입 작업)이 없는 경우에 정렬은 좋은 효율을 낼 수 있는 것 같다.
    */
    public int Solution6(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> processList = new LinkedList<>();

        //큐에 원본 작업을 저장 (요청작업보다 우선순위가 낮은값을 없애는 것이 효율적이라 생각했으나
        //그럴 경우 같은 순위의 다른작업과 요청작업을 구분하기 어려워짐)
        for(int tempProcess : priorities){
            processList.add(tempProcess);
        }
        //원본 배열 정렬
        Arrays.sort(priorities);
        int size = priorities.length-1;

        while(!processList.isEmpty()){
            //현재 작업
            int processListHead = processList.poll();
            location--;
            //현재 작업이 남아있는 최우선순위 작업이면 출력 수 증가, 현재 작업이 요청작업이면 루프 종료. 메소드 종료.
            //최우선순위 작업이 아니라면 현재 작업을 가장 뒤에 새로 추가, 요청작업 위치 가장 뒤로 조정.
            if(processListHead == priorities[size - answer]){
                answer++;
                if(location <0)
                    break;
            }else{
                processList.add(processListHead);
                if(location < 0)
                    location=processList.size()-1;
            }
        }

        return answer;
    }//solution6 end


}
