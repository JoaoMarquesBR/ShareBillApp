package john.is.hot.backend;

import java.util.ArrayList;
import java.util.Arrays;

public class Item {
     public String name;
     public Double totalPrice;
     public ArrayList<String>payers;

    public Item(String name, Double totalPrice,ArrayList list) {
        this.name = name;
        this.totalPrice = totalPrice;
        //make a chake of the REAL payers to make a real price for each person
        payers=Check.checkPayersList(list);
        addPriceToPayer();
    }




    void printItemInfo(){
        System.out.println("** "+ name+" - "+totalPrice+" - "+ payers);
    }



    void addPriceToPayer(){
        double price =totalPrice/payers.size();
        System.out.println("Size os "+ payers.size());
        for(int i=0;i<payers.size();i++){
            int friendIndex = Check.checkIfFriendExists(payers.get(i));
            Management.friends.get(friendIndex).addBill(price);

        }
    }


}
