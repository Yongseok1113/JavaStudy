package Stack;

import java.util.*;


public class Ironbar {
    public int Solution(String arrangement) {
        int answer = 0;
        Stack<Integer> integerStack = new Stack<>();

        for(int i=0; i<arrangement.length(); i++) {
            if(arrangement.charAt(i) == '(')
                integerStack.push(i);
            else if(arrangement.charAt(i) == ')') {
                //When the top iron bar ends
                //if(arrangement.charAt(i-1) == ')') 를 사용하면 'pop'을 'else if'문 첫 번째로 처리 가능
                if(integerStack.peek()+1 == i) {
                    integerStack.pop();//'pop'이 선행되어야 하지만 중복 제거를 위해 'pop'을 'else if'문 첫 번째로 처리 불가능.('pop'을 한 시점에서 'peek'를 사용할 수 없게 됨)
                    answer += integerStack.size();
                }
                //When cutting a bar with a laser
                else if(integerStack.size() > 0) {
                    integerStack.pop();
                    answer++;
                }
            }
        }

        return answer;
    }
    //Don't use Stack Class
    public int Solution2(String arrangement) {
        int answer = 0;
        char [] stack = arrangement.toCharArray();
        short top = -1;

        for(int i=0; i<stack.length; i++) {
            if(stack[i] == '(')
                top++;
            else if(stack[i] == ')') {
                if(stack[i-1] == ')')
                    answer++;
                else if(top > 0)
                    answer += top;
                top--;
            }
        }
        return answer;
    }
}
