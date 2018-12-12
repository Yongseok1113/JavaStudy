package Execute;

import Stack.Ironbar;
import Stack.Printer;

public class main {
    public static void main(String[] args) {
        int answer;
        System.out.println("=========================  iron bar  =============================");
        Ironbar ironbar = new Ironbar();

        //ironbar input : "(((()())(())()))(())" Expected value: 17 , "(((()())(())()))" Expected value : 15
        answer = ironbar.Solution("(((()())(())()))(())");
        System.out.println(answer);
        answer = ironbar.Solution2("(((()())(())()))");
        System.out.println(answer);

        System.out.println("==========================  printer  =============================");
        Printer printer = new Printer();
        int[] properties1 = {2, 1, 3, 2};
        int[] properties2 = {1, 1, 9, 1, 1, 1};
        //[2, 1, 3, 2] , 2  -> Expected value : 1
        answer = printer.Solution(properties1, 2);
        System.out.println(answer);
        //[1, 1, 9, 1, 1, 1] , 0  -> Expected value : 5
        answer = printer.Solution(properties2, 0);
        System.out.println(answer);

    }
}
