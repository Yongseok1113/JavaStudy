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

    //중복없이 순서에 상관없이 각기 다른 주머니를 뽑고 주머니 안에 각각 다른 구슬을 뽑는 모든 경우의 수
    //list 색인 하나는 주머니 하나를 의미 원소는 구슬이 들어있는 수
    //
    //n = list.length, r = 뽑을 경우의 수
    public int marbleInPoket(int[] array, int[]list, int n, int r, int result) {
        if(n < r) return 0;
        if(r == 0 ) {
            return 1;
        }
        else if(r == n) {
            for()
        }
        else {
            //return marbleInPoket(list, n-1,r-1, result);
        }

    }
}
