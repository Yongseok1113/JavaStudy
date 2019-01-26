package CodingTest;

import java.util.Arrays;

public class CodingTest1 {
    public boolean solution(int[] arrA, int[] arrB) {
        boolean answer = true;
        int target = arrA[arrA.length -1];
        int indexA = arrA.length-1;
        int indexB = arrB.length-1;
        //7, 8, 10   ,   10, 7, 8
        //중복된 값이 아니면서 arrA와 arrB의 값이 같은 인덱스위치를 찾아야함
        for(int i=0; i<arrA.length; i++) {
            if(target == arrA[i] && i != arrA.length -1) {
                target = arrA[i+1];
                indexA = i;
            }
        }
        for(int i=0; i<arrB.length; i++) {
            if(target == arrB[i]){
                indexB = i;
            }
        }


        int temp = arrA.length;
        while(temp != 0) {
            indexA++;
            indexB++;
            if(indexA == arrA.length) indexA = 0;
            if(indexB == arrB.length) indexB = 0;

            if(arrA[indexA] != arrB[indexB]) {
                answer = false;
                break;
            }

            temp--;
        }

        return answer;
    }


    public int solution2(int l, int[] v) {
        int answer = 0;

        Arrays.sort(v);

        for(int i=0; i<v.length-1; i++) {
            if(answer < (v[i+1] - v[i])) {
                answer = v[i+1] - v[i];
            }
        }
        if(answer % 2 == 1)
            answer = answer/2 + 1;
        else
            answer = answer /2;
        if(answer < (l - v[v.length-1]) )
            answer = l - v[v.length-1];
        if(answer < v[0] - 0)
            answer = v[0] - 0;

        return answer;
    }

    public int solution3(int[][] board, int[] nums) {
        int answer = 0;

        int temp =0;
        while(temp< nums.length) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                   if(board[i][j] == nums[temp])
                       board[i][j] = 0;
                }
            }
            temp++;
        }
        return answer;
    }

}
