package Queue;



//'properties'는 작업리스트, 'location'은 요청한 작업 위치(properties 배열의 인덱스와 같음. 0이면 배열 첫 번째)
//작업리스트의 우선순위 값이 클 수록 우선도가 높음.
//왼쪽에서 오른쪽으로 진행하며 인쇄 여부를 확인
//자기보다 인쇄 우선순위가 높은 작업이 있으면 가장 나중으로 미룸
//location의 작업이 몇번째에 진행 되는지 찾아 값을 리턴.

import java.util.LinkedList;
import java.util.Queue;

//나중에 요청된 작업 리스트에 우선순위가 더 높은 작업이 있으면 현재 작업을 가장 마지막 작업으로 미룸.
//요청한 작업이 처리되는 순번 반환
//priorities : 우선순위 값이 저장된 배열, location : 요청한 작업의 배열 인덱스(0부터 마지막)
public class Printer {

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


}
