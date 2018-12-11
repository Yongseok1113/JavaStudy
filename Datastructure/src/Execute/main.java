package Execute;

import Stack.Ironbar;

public class main {
    public static void main(String[] args) {
        Ironbar ironbar = new Ironbar();
        int answer;
        //ironbar input : "(((()())(())()))(())" Expected value: 17 , "(((()())(())()))" Expected value : 15
        answer = ironbar.Solution("(((()())(())()))(())");
        System.out.println(answer);
        answer = ironbar.Solution2("(((()())(())()))");
        System.out.println(answer);
    }
}
