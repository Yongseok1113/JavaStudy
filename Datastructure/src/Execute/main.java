package Execute;

import Heap.DualPriorityQueue;
import Heap.MoreSpicy;
import Math.Combination;
import Hash.Camouflage;
import Hash.Marathon;
import Hash.Phonebook;
import Sort.KthNumber;
import Sort.LargestNumber;
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
        String [] completion1 = {"eden", "kiki"};	                                // Expected value : "leo"
        String [] participate2 = { "marina", "josipa", "nikola", "vinko", "filipa"};
        String [] completion2 = {"josipa", "filipa", "marina", "nikola"};           //Expected value : "vinko"
        String [] participate3 = {"mislav", "stanko", "mislav", "ana"};
        String [] completion3 = {"stanko", "ana", "mislav"};                        //Expected value : "mislav"

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
        String[] testcase2 ={"123", "456", "789"};                                   //Expected Value : true
        String[] testcase3 ={"12", "123", "1235", "567", "88"};                      //Expected Value : false
        String[] testcase4 = {"113", "12340", "123440", "12345", "98346"};
        phoneAnswer = phonebook.Solution3(testcase1);
        System.out.println("Expected value : false , Phonebook result1 : " + phoneAnswer);
        phoneAnswer = phonebook.Solution3(testcase2);
        System.out.println("Expected value : true , Phonebook result2 : " + phoneAnswer);
        phoneAnswer = phonebook.Solution3(testcase3);
        System.out.println("Expected value : false , Phonebook result3 : " + phoneAnswer);
        phoneAnswer = phonebook.Solution3(testcase4);
        System.out.println("Expected value : true , Phonebook result4 : " + phoneAnswer);

        System.out.println("===========================  Camouflage  ==========================");
        int camouflageAnswer = 0;
        Camouflage camouflage = new Camouflage();
        String [][] camouflageTestcase1 = { {"yellow_hat",      "headgear"},
                                            {"blue_sunglasses", "eyewear"},
                                            {"green_turban",    "headgear"} };  //Expected value : 5
        String [][] camouflageTestcase2 = { {"crow_mask",       "face"},
                                            {"blue_sunglasses", "face"},
                                            {"smoky_makeup",    "face"} };      //Expected value : 3
        String [][] camouflageTestcase3 = { {"yellow_hat",      "headgear"},
                                            {"blue_sunglasses", "eyewear"},
                                            {"green_turban",    "headgear"},
                                            {"yellow_shirt", "shirts"},
                                            {"blue_shirt", "shirts"},
                                            {"green_shirt", "shirts"} };          //Expected value : 23
        camouflageAnswer = camouflage.solution(camouflageTestcase1);
        System.out.println("Expected value : 5 , Camouflage result1 : " + camouflageAnswer);
        camouflageAnswer = camouflage.solution(camouflageTestcase2);
        System.out.println("Expected value : 3 , Camouflage result2 : " + camouflageAnswer);
        camouflageAnswer = camouflage.solution(camouflageTestcase3);
        System.out.println("Expected value : 23 , Camouflage result3 : " + camouflageAnswer);

        System.out.println("===========================  Math  ==========================");
        Combination combination = new Combination();
        int result = 1;
        result = combination.run(10, 3);
        System.out.println(result);

        System.out.println("===========================  MoreSpicy  ==========================");
        MoreSpicy moreSpicy = new MoreSpicy();
        int answer = 0;
        int[] testcase1 = {1, 2, 6, 9, 10, 12};  int k = 7;    //Expected value : 2
        int[] testcase2 = {1, 1, 1};  int k2 = 20;    //Expected value : -1
        answer = moreSpicy.solution(testcase1, k);
        System.out.println("Expected value : 2 , moreSpicy result1 : " + answer);
        answer = moreSpicy.solution(testcase2, k2);
        System.out.println("Expected value : -1 , moreSpicy result2 : " + answer);

        System.out.println("=====================  DualPriorityQueue  ===================");
        DualPriorityQueue dualPriorityQueue = new DualPriorityQueue();
        //Expected value : [0,0]
        String[] DualPriorityQueue_testcase1 = {"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        dualPriorityQueue.solution(DualPriorityQueue_testcase1);
        //Expected value : [333, -45]
        String[] DualPriorityQueue_testcase2 = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        dualPriorityQueue.solution((DualPriorityQueue_testcase2));
        //Expected value : [-9999, -9999]
        String[] DualPriorityQueue_testcase3 = {"I 16", "I -5643", "D -1", "I 123", "I -9999", "D 1", "D 1"};
        dualPriorityQueue.solution((DualPriorityQueue_testcase3));

        System.out.println("=========================  KthNumber  ======================");
        KthNumber kthNumber = new KthNumber();
        int[] kthNumResult;
        //Expected value : [5, 6, 3]
        int[] testcase1 = { 1, 5, 2, 6, 3, 7, 4};
        int[][] commands1 = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        kthNumResult = kthNumber.solution(testcase1, commands1);
        System.out.println("Expected value : [5, 6, 3], testcase1 result : " + kthNumResult);
*/
        System.out.println("=======================  LargestNumber  ====================");
        LargestNumber largestNumber = new LargestNumber();
        int[] testcase1 = {6, 10, 2};	                     //Expected value : 6210
        int[] testcase2 = {7, 3, 30, 34, 9, 91, 970, 972};	 //Expected value : 997297091734330
        int[] testcase3 = {12, 121};	                     //Expected value : 12121
        int[] testcase4 = {21, 212};	                     //Expected value : 21221
        int[] testcase5 = {0, 0, 0, 0};	                     //Expected value : 0
        StringBuffer largestNumberAnswer;
        largestNumberAnswer = largestNumber.solution2(testcase1);
        System.out.println("Expected value : 6210, result : " + largestNumberAnswer);
        largestNumberAnswer = largestNumber.solution2(testcase2);
        System.out.println("Expected value : 997297091734330, result : " + largestNumberAnswer);
        largestNumberAnswer = largestNumber.solution2(testcase3);
        System.out.println("Expected value : 12121, result : " + largestNumberAnswer);
        largestNumberAnswer = largestNumber.solution2(testcase4);
        System.out.println("Expected value : 21221, result : " + largestNumberAnswer);
        largestNumberAnswer = largestNumber.solution2(testcase5);
        System.out.println("Expected value : 0, result : " + largestNumberAnswer);

    }
}
