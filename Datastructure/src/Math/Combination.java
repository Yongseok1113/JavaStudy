/*
*   조합 : 중복없이 n개 중에서 r개를 순서 상관없이 뽑는 경우의 수
*   공식 : nCr = P(n,r) / r! = n!/ (r! * (n-r)!) = n-1Cr-1 + n-1Cr  , nC0 = 1
*   예제 : 10C3 = P(10,3) / 3! = 10! / 3! * 7! = (10*9*8) / (3*2*1) = 120
*   참고 : 서로 다른 n개를 p, q, r 3개 그룹으로 나누는 방법 = nCp * n-pCq * rCr(= n-p-qCr)
*
*   중복조합 : n개 중에서 r개를 순서 상관없이 중복을 허용하여 뽑는 경우의 수
*   공식 : nHr = n+r-1Cr = nHr-1 + n-1Hr
*   예제 : 10H3 = 12C3 = (12*11*10) / (3*2*1) = 220
*
* */

package Math;

import java.util.ArrayList;

public class Combination {

    public int run(int n, int r) {
        int check;
        if(n < r) {
            System.out.println("n < r , Invalid argument used in run(n, r)");
            return 0;
        }
        if(r == 0 || n == r) return 1;
        else return run(n-1, r-1) + run(n-1, r);
    }

    /*
    *   각 옷장별로 같은 종류의 다른 옷들이 보관되어 있는 상태에서 최소한 하나의 옷은 입는 모든 경우의 수 (종류와 이름이 모두 같은 옷은 없음)
    *   @selectedCloset      : 옷장을 선택할 때 임시로 보관되는 곳
    *   @closetList          : 모든 옷장정보가 보관된 곳, 색인 하나 = 옷장 하나 , 색인별로 저장된 값 = 옷장에 보관된 옷의 수
    *   @n                   : 옷장의 총 개수
    *   @r                   : 옷장을 선택할 개수
    *   @closetListIndex     : closetList의 색인( 현재 주목하고 있는 옷장, 선택할지 말지 결정해야 할 옷장)
    *   @selectedClosetIndex : selectedCloset의 색인 (선택된 옷장을 임시로 보관할 색인)
    *
    * */
    public int sumOfCombination(int[] selectedCloset, ArrayList closetList, int n, int r, int closetListIndex, int selectedClosetIndex) {
        //옷장의 선택이 끝났다면, 현재까지 선택된 옷장에서 옷을 하나씩 고르는 경우의 수 반환
        if(r == 0) {
            int result = 1;
            for(int i=0; i<selectedClosetIndex; i++) {
                result *= selectedCloset[i];
            }
            return result;
        }
        //모든 옷장을 선택하는 경우, 모든 옷장에서 하나씩 고르는 경우의 수 반환
        else if(r == n) {
            int result = 1;
            for(int i=0; i<r; i++) {
                result *= (int)closetList.get(i);
            }
            return result;
        }
        //마지막 선택이 완료된 시점에서 마지막 선택을 하지 않았다고 가정된 경우 접근됨
        //모든 옷장을 선택하는 경우(r==n)가 처리되고 있기 때문에 마지막 옷장이 주목되어선 안됨. 0반환
        else if(closetListIndex == n) return 0;
        else {
            selectedCloset[selectedClosetIndex] = (int)closetList.get(closetListIndex);
            return
                    //현재 주목하는 옷장을 선택한 경우
                    sumOfCombination(selectedCloset, closetList, n, r-1, closetListIndex + 1, selectedClosetIndex + 1) +
                            //선택 하지 않은 경우
                            sumOfCombination(selectedCloset, closetList, n, r, closetListIndex + 1, selectedClosetIndex);
        }
    }
}
