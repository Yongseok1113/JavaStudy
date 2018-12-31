package Execute;

import Hash.Camouflage;
import Hash.Marathon;
import Hash.Phonebook;
import Stack.Ironbar;

import Queue.Printer;

public class main {
    public static void main(String[] args) {
/*
        int answer;
        System.out.println("=========================  Ironbar  =============================");
        Ironbar ironbar = new Ironbar();

        answer = ironbar.Solution("(((()())(())()))(())"); // Expected value: 17
        System.out.println("Expected value : 17 , Ironbar result1 : " + answer);
        answer = ironbar.Solution2("(((()())(())()))"); // Expected value : 15
        System.out.println("Expected value : 15 , Ironbar result2 : " + answer);

        System.out.println("==========================  Printer  =============================");
        Printer printer = new Printer();
        int[] testCase = {2, 1, 3, 2};
        int[] testCase2 = {1, 1, 9, 1, 1, 1};
        int[] testCase3 = {1, 1, 9, 1, 2, 1};
        int[] testCase4 = {3, 1, 9, 1, 2, 1};
        //[2, 1, 3, 2] , 2  -> Expected value : 1
        answer = printer.Solution6(testCase, 2);
        System.out.println("Expected value : 1 , Printer result1 : " + answer);
        //[1, 1, 9, 1, 1, 1] , 0  -> Expected value : 5
        answer = printer.Solution6(testCase2, 0);
        System.out.println("Expected value : 5 , Printer result2 : " + answer);
        //[1, 1, 9, 1, 2, 1] , 0  -> Expected value : 4
        answer = printer.Solution6(testCase3, 0);
        System.out.println("Expected value : 4 , Printer result3 : " + answer);
        //[3, 1, 9, 1, 2, 1] , 1  -> Expected value : 5
        answer = printer.Solution6(testCase4, 1);
        System.out.println("Expected value : 5 , Printer result4 : " + answer);

        System.out.println("===========================  Marathon  ==========================");
        Marathon marathon = new Marathon();
        String result;
        String [] participate1 = {"leo", "kiki", "eden"};
        String [] completion1 = {"eden", "kiki"};	// Expected value : "leo"
        String [] participate2 = { "marina", "josipa", "nikola", "vinko", "filipa"};
        String [] completion2 = {"josipa", "filipa", "marina", "nikola"};    //Expected value : "vinko"
        String [] participate3 = {"mislav", "stanko", "mislav", "ana"};
        String [] completion3 = {"stanko", "ana", "mislav"}; //Expected value : "mislav"

        result = marathon.Solution4(participate1, completion1);
        System.out.println("Expected value : leo , Marathon result1 : " + result);
        result = marathon.Solution4(participate2, completion2);
        System.out.println("Expected value : vinko , Marathon result2 : " + result);
        result = marathon.Solution4(participate3, completion3);
        System.out.println("Expected value : mislav , Marathon result3 : " + result);

        System.out.println("===========================  Phonebook  ==========================");
        Phonebook phonebook = new Phonebook();
        boolean phoneAnswer = true;

        String[] testcase1 ={"119", "97674", "11955", "119", "11911", "11922"};      //Expected Value : false
        String[] testcase2 ={"123", "456", "789"};                  //Expected Value : true
        String[] testcase3 ={"12", "123", "1235", "567", "88"};     //Expected Value : false
        String[] testcase4 = {"113", "12340", "123440", "12345", "98346"};
        phoneAnswer = phonebook.Solution3(testcase1);
        System.out.println("Expected value : false , Phonebook result1 : " + phoneAnswer);
        phoneAnswer = phonebook.Solution3(testcase2);
        System.out.println("Expected value : true , Phonebook result2 : " + phoneAnswer);
        phoneAnswer = phonebook.Solution3(testcase3);
        System.out.println("Expected value : false , Phonebook result3 : " + phoneAnswer);
        phoneAnswer = phonebook.Solution3(testcase4);
        System.out.println("Expected value : true , Phonebook result4 : " + phoneAnswer);
*/
        System.out.println("===========================  Camouflage  ==========================");
        int camouflageAnswer = 0;
        Camouflage camouflage = new Camouflage();
        String [][] camouflageTestcase1 = { {"yellow_hat",      "headgear"},
                                            {"blue_sunglasses", "eyewear"},
                                            {"green_turban",    "headgear"} }; //Expected value : 5
        String [][] camouflageTestcase2 = { {"crow_mask",       "face"},
                                            {"blue_sunglasses", "face"},
                                            {"smoky_makeup",    "face"} }; //Expected value : 3
        camouflageAnswer = camouflage.solution(camouflageTestcase1);
        System.out.println("Expected value : 5 , Camouflage result1 : " + camouflageAnswer);
        camouflageAnswer = camouflage.solution(camouflageTestcase2);
        System.out.println("Expected value : 5 , Camouflage result2 : " + camouflageAnswer);

    }
}