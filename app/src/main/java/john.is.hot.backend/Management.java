package john.is.hot.backend;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import john.is.hot.sharebillapp.MainActivity;

public class Management  {
     public static ArrayList<Item>itemArrayList = new ArrayList<>();
     public static ArrayList<Friends>friends = new ArrayList<>();

    public void addFriend(String name){
        if(Check.checkIfFriendExists(name,friends)==-1){
//            MainActivity.printLog("oiii tudo bem");
            friends.add(new Friends(name));
        }else{
//            MainActivity.printLog("Error: name is already being used.");
        }
    }

    public void addItem(String itemName,double price,ArrayList whoPays){
            System.out.println("Receiving "+ whoPays);
            Item newItem = new Item(itemName,price,whoPays);
            itemArrayList.add(newItem);
    }
    public static void removeItem(String name){
        int itemIndex = Check.findItemIndex(name);
        if(itemIndex>=0){
            itemArrayList.remove(itemIndex);
        }
    }

    public void printItemList(){
        for(int i=0;i<itemArrayList.size();i++){
            itemArrayList.get(i).printItemInfo();
        }
    }

    public static void reloadNumbers(){
        for(Friends fr: friends){
            fr.bill=00.00;
        }
        ArrayList<Item> newItemList = (ArrayList<Item>)itemArrayList.clone();
        itemArrayList.clear();
        for(int i=0;i<newItemList.size();i++){
            Item item = newItemList.get(i);
            System.out.println("Adding "+ item.name +item.totalPrice+ String.valueOf(item.payers));
            Item newItem = new Item(item.name,item.totalPrice,item.payers);
            itemArrayList.add(newItem);
        }
        newItemList.clear();

    }

    public void getBillOfFriends(){
        double wholeBill=0;
        for(Friends fr : friends){
            double bill = fr.bill;
            System.out.println("-- Bill Of "+ fr.name+" = "+ bill);
            wholeBill+=bill;
        }
        System.out.println("* * * Total Bill Of " + wholeBill+" * * * ");
    }
    public double totalBil(){
        double wholeBill=0;
        for(int i=0;i<itemArrayList.size();i++){
            wholeBill+=itemArrayList.get(i).totalPrice;
        }
            return wholeBill;
    }


}
