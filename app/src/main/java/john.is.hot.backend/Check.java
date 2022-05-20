package john.is.hot.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class Check {

    static int findItemIndex(String itemName){
        for(int i=0;i< Management.itemArrayList.size();i++){
            if(itemName.equals(Management.itemArrayList.get(i).name)){
                return i;
            }
        }
        return -1;
    }

    static int checkIfFriendExists(String name, ArrayList<Friends> list){
        return check(name);
    }
    static int checkIfFriendExists(String name){
       return check(name);
    }


    private static int check(String name){
        for(int i = 0 ; i < Management.friends.size();i++){
            if(name.equals(Management.friends.get(i).name)){
                //user exists already
                return i;
            }
        }
        return -1;//user does NOT exist.
    }



    static ArrayList checkPayersList(ArrayList<String> list){

        ArrayList<String>realPayersList = new ArrayList<>();

          for(int j=0;j<list.size();j++){
              for(int i=0;i<Management.friends.size();i++){
                  System.out.println("Comparing "+ Management.friends.get(i).name+" to "+ list.get(j));
                  if(list.get(j).equals(Management.friends.get(i).name)){
                      realPayersList.add(list.get(j));
                      System.out.println("Adding "+ list.get(j));
                  }
              }
          }
        return realPayersList;
    }


    static ArrayList<String> argToArrayList(String... list){
        ArrayList<String>argToArray = new ArrayList<>(Arrays.asList(list));
        return argToArray;
    }

}
