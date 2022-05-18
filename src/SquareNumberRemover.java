//What I've Learned:
// Identify broken block.
    //Focus on predicting what should happen next vs. what happens next.
    //Debug various blocks of your code where you suspect problem to be.
    //Expand if necessary
    //Narrow it down
// Debug broken block with utmost scrutiny.
    //conditional breakpoints to increase debugging speed.
//import com.sun.org.apache.xerces.internal.dom.DOMStringListImpl;

//BROKEN: counter varible is preventing the variation that starts with 8 from fully executing, but if I increase counter variable, even by 1, it loops infinitely.
import java.util.*;
import java.lang.Math;


public class SquareNumberRemover {
    private static Integer currentValue;
    static HashMap<Integer, ArrayList<Integer>> squarePairs = new HashMap<>();


    static ArrayList<ArrayList<Integer>> deadEnds = new ArrayList<>();
//new idea: input highest and lowest numbers, calculate what the possible squares could be within that. test against those squares.
//One of the issues is that the program is currently just matching the first square and adding it to the array, which prevents other options from being checked.


    //The problem with this approach is that it's potentially possible that the I cannot start
    public static void main(String[] args) {
        ArrayList<Integer> newArrayList = new ArrayList<Integer>();

        ArrayList<Integer> myArrayList = new ArrayList<Integer>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        ArrayList<Integer> myArrayList2;
        ArrayList<Integer> neverChangingArrayList = new ArrayList<Integer>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        ArrayList<Integer> possibleSquareNumbers = new ArrayList<>();
//        new ArrayList<Integer>(Arrays.asList(
//                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));

        ArrayList<ArrayList<Integer>> allValidResults = new ArrayList<ArrayList<Integer>>();



        myArrayList2 = neverChangingArrayList;

        for (Integer num : neverChangingArrayList) {
            for (Integer compNum: neverChangingArrayList) {
                Integer sum = num + compNum;
                if(squarePairs.get(num) == null) {
                    squarePairs.put(num, new ArrayList<Integer>());
                }
                if (Math.sqrt(sum) % 1 == 0) {
                    squarePairs.get(num).add(compNum);
                    if(!possibleSquareNumbers.contains(sum)){
                        possibleSquareNumbers.add(sum);
                    }
                }
            }
        }
        System.out.println(possibleSquareNumbers);

        System.out.println(squarePairs);
        Boolean test = false;


            for(int i = 0; i < neverChangingArrayList.size(); i++){
                //Clears out newArraysList to start fresh.
                newArrayList.clear();
                //Selects first number as starting number
                newArrayList.add(neverChangingArrayList.get(i));
                //removes int from myArrayList as to not reuse it.
                myArrayList.remove(neverChangingArrayList.get(i));

                //counter is to exit loop if newArrayList has tried every variation? NOT SURE IF THIS WORKS
                int counter = 0;

                //loop terminates if newArrayList.size() == 15
                while(!test){
                    //gets current value (last value in newArrayList)
                    currentValue = (newArrayList.get(newArrayList.size()-1));
                    //prevents infinite loop.
                    //BROKEN: the counter increment aka counter++ is in the wrong place
                    if(counter > 34){
                        break;
                    }
                    //exit loop condition.
                    if(newArrayList.size() == 15){
                        System.out.println("Done, the new list is: " + newArrayList);
                        test = true;
                        continue;
                    }

                    //stores all matching sets EX: currentvalue = 1 would produce [3,8,15] assuming none of those are in newArrayList
                    ArrayList<Integer> currentValueMatchingsets = findAllMatchingSets(currentValue, newArrayList);
//                    System.out.println(currentValueMatchingsets);
                    //Supposed to give a fresh start.
                        //MIGHT NEED TO CHECK IF VALUE BEING ADDED/ REMOVED HERE HAS ALREADY BEEN TRIED VIA deadEnds;
                    if(currentValueMatchingsets.size() > 0){
                        newArrayList.add(currentValueMatchingsets.get(0));
                        currentValueMatchingsets.remove(currentValueMatchingsets.get(0));
                        //supposed to add shortened arrayList to deadEnds because currentValueMatchingsets.size() == 0 means there is no viable options on this path.
                    } else{
                        ArrayList<Integer> copyOfNewArrayList = new ArrayList<>();
                        copyOfNewArrayList = (ArrayList<Integer>) newArrayList.clone();
                        deadEnds.add(copyOfNewArrayList);

                        //Check that function isn't being called twice.
//                        ArrayList<Integer> newTry = findWhereItAllWentWrong(deadEnds, newArrayList);

                        //MIGHT NEED TO REMOVE ONE MORE HERE. WHY WOULD THIS execute here and not on line 87?
                            //BROKEN: Needs different condition or currentValueMatchingsets needs to be changed before this do/while.
                        do{
                            if(newArrayList.size() > 1 ){
                                newArrayList.remove(newArrayList.size()-1);
                                currentValue = (newArrayList.get(newArrayList.size()-1));
                                currentValueMatchingsets = findAllMatchingSets(currentValue, newArrayList);
                                if(currentValueMatchingsets.size() > 0){
//                                    currentValueMatchingsets.indexOf(newArrayList.get(newArrayList.size()-1)) == -1
//                                    currentValueMatchingsets.remove(currentValueMatchingsets.indexOf(newArrayList.get(newArrayList.size()-1)));
                                    System.out.println(currentValueMatchingsets);
//                                    newArrayList = findWhereItAllWentWrong(deadEnds, newArrayList);

                                    newArrayList.add(currentValueMatchingsets.get(0));
                                }

                            } else {
                                deadEnds.add(newArrayList);
                                break;
                            }
                            //BROKEN: do While is exiting before all variations are found Example: [9, 7, 2, 14, 11, 5, 4, 12, 13, 3, 1, 15] meets exit criteria
                            //Solution: Add in additional while loop.
                        }while(deadEnds.contains(newArrayList));





                    }


                    counter++;
                    System.out.println(newArrayList + " counter is" + counter);
            }

        }
    }























    //finds all sets which have a valid key. (aka, adds up with currentValue to equal square num and not in newArrayList already.
    public static ArrayList<Integer> findAllMatchingSets(int currentValue, ArrayList<Integer> newArrayList) {
        ArrayList<Integer> potentialNextCurrentValue = new ArrayList<>();
        ArrayList<Integer> testArrayListForDeadEnd = (ArrayList<Integer>) newArrayList.clone();

    //ADDED a check to see if adding the element would result in an array that would be a dead end.
        for(Map.Entry element : squarePairs.entrySet()) {
            testArrayListForDeadEnd.add((int) element.getKey());
            if(!newArrayList.contains((int) element.getKey()) && !deadEnds.contains(testArrayListForDeadEnd)){
                ArrayList<Integer> copy = squarePairs.get(element.getKey());
                if(copy.contains(currentValue)){
                    potentialNextCurrentValue.add((int) element.getKey());
                }
            }
            testArrayListForDeadEnd.remove(testArrayListForDeadEnd.size() - 1);
        }
        return potentialNextCurrentValue;
    }
    public static ArrayList<Integer> findWhereItAllWentWrong(ArrayList<ArrayList<Integer>> deadEnds, ArrayList<Integer> newArrayList) {

//        newArrayList.remove(newArrayList.size() - 1);
        ArrayList<Integer> valuesBeingChecked = findAllMatchingSets(newArrayList.get(newArrayList.size() - 1), newArrayList);
        ArrayList<Integer> copyOfNewArrayList = (ArrayList<Integer>) newArrayList.clone();

        for(Integer el : valuesBeingChecked){
            copyOfNewArrayList.add(el);
            if(valuesBeingChecked.size() == 0 && !deadEnds.contains(copyOfNewArrayList)){
                copyOfNewArrayList.remove(copyOfNewArrayList.size() - 1);
                deadEnds.add(copyOfNewArrayList);
            } else if(deadEnds.contains(copyOfNewArrayList)){
                copyOfNewArrayList.remove(copyOfNewArrayList.size()-1);

            }

        }
        return copyOfNewArrayList;
    }




}



//
////Loops if got incorrect
//            //starterNum is the first entry in the newArrayList
//            //currentNum is the last number placed in the newArrayList that is being compared to iterations.
//            int currentNum = myArrayList.get(0);
//            int starterNum = myArrayList.get(0);
//            int starterNumIndex = 0;
//            int currentNumIndex = 0;
//            int sum = 0;
//            int count = 0;
//
//
//            do {
//
//
//
//                myArrayList2 = (ArrayList<Integer>)neverChangingArrayList.clone();
//                myArrayList = (ArrayList<Integer>)neverChangingArrayList.clone();
//                newArrayList.clear();
//                starterNum = neverChangingArrayList.get(starterNumIndex);
//
//                newArrayList.add(starterNum);
//                currentNum = starterNum;
//                System.out.println(starterNum);
//                System.out.println(myArrayList.size());
//                if(myArrayList.size() < myArrayList.indexOf(starterNum))  {
//                    currentNum = myArrayList.get(starterNum);
//                }
//
//
//                if(myArrayList.size() > myArrayList2.indexOf(currentNum)){
//                    myArrayList.remove(myArrayList2.indexOf(currentNum));
//                }
//
//                Iterator itr = myArrayList.iterator();
//                while(itr.hasNext()) {
//
//
//                    for(Integer i : myArrayList){
//                        sum = currentNum + i;
//                        if (Math.sqrt(sum) % 1 == 0) {
//                            if(newArrayList.contains(i)){
//                                continue;
//                            }
//                            newArrayList.add(myArrayList2.get(myArrayList2.indexOf(i)));
//                            currentNum = myArrayList2.get(myArrayList2.indexOf(i));
//                            break;
//                        }
//
//                    }
//                    itr.next();
//                    if(myArrayList.size() == 1){
//                        continue;
//                    }
//                    itr.remove();
//
//                    count++;
//                    System.out.println(count + " " + newArrayList);
//
//                    if(myArrayList.size() == 1){
//                        break;
//                    }
//
//                }
//                starterNumIndex++;
//                if(myArrayList.size()>0 && myArrayList.size() < 13) {
//                    int counter = 0;
////        currentNumIndex = myArrayList2.get(myArrayList2.indexOf(currentNum) + 1) ;
//                    System.out.println(counter + " " +newArrayList);
//                    System.out.println("hi");
//                    continue;
//                }
//
//
//
//
//
//
////            num
//
//
//            }while (newArrayList.size() < 15);
//
//        }
//    }
//
//}
