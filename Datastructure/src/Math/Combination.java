/*
*   조합 : 중복없이 n개 중에서 r개를 순서 상관없이 뽑는 경우의 수
*   공식 : nCr = P(n,k) / k! = n!/ (k! * (n-k)!) = n-1Cr-1 + n-1Cr  , nC0 = 1
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
        if(n<r) return 0;

        int result = 0;


        if(n == r) return result;
        else return run(n-1, r-1);
    }

    public int run(int[]list, int n, int r) {
        if(n<r) return 0;

        int result = 0;
        run(list,n-1, r-1);

        if(n == r) return result;
        else       return 0;
    }
}
