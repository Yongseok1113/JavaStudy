package Stack;



//'properties'는 작업리스트, 'location'은 요청한 작업 위치(properties 배열의 인덱스와 같음. 0이면 배열 첫 번째)
//작업리스트의 우선순위 값이 클 수록 우선도가 높음.
//왼쪽에서 오른쪽으로 진행하며 인쇄 여부를 확인
//자기보다 인쇄 우선순위가 높은 작업이 있으면 가장 나중으로 미룸
//location의 작업이 몇번째에 진행 되는지 찾아 값을 리턴.

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//원형 큐
public class Printer {

    public int Solution(int[] properties, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<properties.length;i++) {
            queue.add(properties[i]);
        }
        PriorityQueue<Integer> integerPriorityQueue = new LinkedList<>();
    //    int [] CircularQue = new int [properties.length + 1];
        int front = 0; // 맨 앞
        int end = properties.length -1; // 순환자
        int printCount = 0;



        for(int i=0; i < properties.length; i++) {
            for (int j = front; j < properties.length; j++) {
                if(properties[j] > properties[location]) {

                    break;
                }
            }

        }


        return answer;
    }

}
