import java.util.*;
import java.lang.Math;


public class SquareNumberRemover {
    private static Integer currentValue;
    static HashMap<Integer, ArrayList<Integer>> squarePairs = new HashMap<>();
    static ArrayList<Integer> newArrayList = new ArrayList<Integer>();
//new idea: input highest and lowest numbers, calculate what the possible squares could be within that. test against those squares.
//One of the issues is that the program is currently just matching the first square and adding it to the array, which prevents other options from being checked.


    //The problem with this approach is that it's potentially possible that the I cannot start
    public static void main(String[] args) {


        ArrayList<Integer> myArrayList = new ArrayList<Integer>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        ArrayList<Integer> myArrayList2;
        ArrayList<Integer> neverChangingArrayList = new ArrayList<Integer>(Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        ArrayList<Integer> possibleSquareNumbers = new ArrayList<>()
        ;
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
                //Selects first number as starting number
                newArrayList.clear();
                newArrayList.add(neverChangingArrayList.get(i));
                myArrayList.remove(neverChangingArrayList.get(i));

                int counter = 0;
                while(!test){
                    currentValue = (newArrayList.get(newArrayList.size()-1));
                    //prevents infinite loop.
                    if(counter > 15){
                        break;
                    }

                    if(newArrayList.size() == 15){
                        System.out.println("Done, the new list is: " + newArrayList);
                        test = true;
                        continue;
                    }

                    ArrayList<Integer> currentValueMatchingsets = findAllMatchingSets(currentValue);
//                    System.out.println(currentValueMatchingsets);
                    if(currentValueMatchingsets.size() > 0){
                        newArrayList.add(currentValueMatchingsets.get(0));
                        currentValueMatchingsets.remove(currentValueMatchingsets.get(0));
                    } else{
                        break;
                    }



                    System.out.println(newArrayList);
            }

        }
    }























    //finds all sets which have a valid key. (aka, adds up with currentValue to equal square num and not in newArrayList already.
    public static ArrayList<Integer> findAllMatchingSets(int currentValue) {
        ArrayList<Integer> potentialNextCurrentValue = new ArrayList<>();

        for(Map.Entry element : squarePairs.entrySet()) {
            if(!newArrayList.contains((int) element.getKey())){
                ArrayList<Integer> copy = squarePairs.get(element.getKey());
                if(copy.contains(currentValue)){
                    potentialNextCurrentValue.add((int) element.getKey());
                }
            }
        }
        return potentialNextCurrentValue;
    }
    public static void findWhereItAllWentWrong() {

        newArrayList.remove(newArrayList.size() - 1);
        ArrayList<Integer> valuesBeingChecked = findAllMatchingSets(newArrayList.size() - 1);
        for(Integer el : valuesBeingChecked){
            if(!newArrayList.contains(el)){
                newArrayList.add(el);
            }
        }
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
