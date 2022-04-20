import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class SquareNumberRemover {
//new idea: input highest and lowest numbers, calculate what the possible squares could be within that. test against those squares.
//One of the issues is that the program is currently just matching the first square and adding it to the array, which prevents other options from being checked.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.lang.Math;
import java.util.Iterator;
    public class SquareNumberRemover {

        //The problem with this approach is that it's potentially possible that the I cannot start
        public static void main(String[] args) {


            ArrayList<Integer> myArrayList = new ArrayList<Integer>(Arrays.asList(
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
            ArrayList<Integer> myArrayList2;
            ArrayList<Integer> neverChangingArrayList = new ArrayList<Integer>(Arrays.asList(
                    1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));;
//        new ArrayList<Integer>(Arrays.asList(
//                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
            ArrayList<Integer> newArrayList = new ArrayList<Integer>();
//
//        What we need to keep track of:
//              Current Value, comparison value
//        What we need to do:
//              Compare two numbers, establish if their sum is a Square number, if it is
            //      push both number to new list, remove from old.
            //      Compare last element's value of new array to items in old array
            //      find one that adds to it to be a square number.
//        for(Integer i : myArrayList)
//                int firstNum = myArrayList.get(0);
//
//
//
//            }


//Loops if got incorrect
            //starterNum is the first entry in the newArrayList
            //currentNum is the last number placed in the newArrayList that is being compared to iterations.
            int currentNum = myArrayList.get(0);
            int starterNum = myArrayList.get(0);
            int starterNumIndex = 0;
            int currentNumIndex = 0;
            int sum = 0;
            int count = 0;


            do {



                myArrayList2 = (ArrayList<Integer>)neverChangingArrayList.clone();
                myArrayList = (ArrayList<Integer>)neverChangingArrayList.clone();
                newArrayList.clear();
                starterNum = neverChangingArrayList.get(starterNumIndex);

                newArrayList.add(starterNum);
                currentNum = starterNum;
                System.out.println(starterNum);
                System.out.println(myArrayList.size());
                if(myArrayList.size() < myArrayList.indexOf(starterNum))  {
                    currentNum = myArrayList.get(starterNum);
                }


                if(myArrayList.size() > myArrayList2.indexOf(currentNum)){
                    myArrayList.remove(myArrayList2.indexOf(currentNum));
                }

                Iterator itr = myArrayList.iterator();
                while(itr.hasNext()) {


                    for(Integer i : myArrayList){
                        sum = currentNum + i;
                        if (Math.sqrt(sum) % 1 == 0) {
                            if(newArrayList.contains(i)){
                                continue;
                            }
                            newArrayList.add(myArrayList2.get(myArrayList2.indexOf(i)));
                            currentNum = myArrayList2.get(myArrayList2.indexOf(i));
                            break;
                        }

                    }
                    itr.next();
                    if(myArrayList.size() == 1){
                        continue;
                    }
                    itr.remove();

                    count++;
                    System.out.println(count + " " + newArrayList);

                    if(myArrayList.size() == 1){
                        break;
                    }

                }
                starterNumIndex++;
                if(myArrayList.size()>0 && myArrayList.size() < 13) {
                    int counter = 0;
//        currentNumIndex = myArrayList2.get(myArrayList2.indexOf(currentNum) + 1) ;
                    System.out.println(counter + " " +newArrayList);
                    System.out.println("hi");
                    continue;
                }






//            num


            }while (newArrayList.size() < 15);

        }
    }

}
