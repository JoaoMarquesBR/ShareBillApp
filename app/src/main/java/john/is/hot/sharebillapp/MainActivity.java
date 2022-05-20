package john.is.hot.sharebillapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import john.is.hot.backend.*;

public class MainActivity extends AppCompatActivity {
    Management management;
    ListView friendListView;
    ListView itemListView;
//    ListView listView;

    ArrayList<String>myFriends;  //The Man.friendList are in ArrayList<Friends>, so we pass that to String arrayListReset();
    ArrayList<String>myItem;   //Same thing here

    ArrayList<String>friendPlusInfo;

    TextView totalTextView;//Total price of the bill

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        management = new Management();
        super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    totalTextView = findViewById(R.id.totalTextView);
    totalTextView.setText("Total = $"+ management.totalBil());
    //.
    friendListView = findViewById(R.id.friendListView);
    itemListView  = findViewById(R.id.itemListView);

    myFriends  = new ArrayList<String>();
    myItem  = new ArrayList<String>();
    friendPlusInfo = new ArrayList<>();

    }
    public void arrayListReset(){
    myFriends.clear();
    myItem.clear();
    friendPlusInfo.clear();
        for(int i=0;i<Management.friends.size();i++){
            String name = Management.friends.get(i).name;
            myFriends.add(name);
            friendPlusInfo.add(name+" $"+Management.friends.get(i).bill );
        }
        for(int i=0;i<Management.itemArrayList.size();i++){
            myItem.add(Management.itemArrayList.get(i).name+" , $"+ Management.itemArrayList.get(i).totalPrice + ","+Management.itemArrayList.get(i).payers);
        }

        ArrayAdapter friendPlusInfoAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,friendPlusInfo);
        ArrayAdapter itemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,myItem);

        friendListView.setAdapter(friendPlusInfoAdapter);
        itemListView.setAdapter(itemAdapter);
        totalTextView.setText("Total = "+ management.totalBil());

        itemListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Management.itemArrayList.remove(position);
                Management.reloadNumbers();
                arrayListReset();
                return false;
            }
        });
        friendListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Management.friends.remove(position);
                Management.reloadNumbers();
                arrayListReset();
                return false;
            }
        });

    }

    public void addFriend(View view){
        addFriendWindow();
    }
    public void addItem(View view){
        addItemWindow();
    }

    private void addItemWindow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Item");

        final EditText text = new EditText(this);
        final EditText numberText = new EditText(this);
//        numberText.setInputType(InputType.TYPE_CLASS_NUMBER);;
        //android.inputType="numberSigned|numberDecimal"
        numberText.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
//        numberText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
        text.setHint("Name");
        numberText.setHint("Price");


        ListView listView = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, myFriends);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setAdapter(adapter);

//        final HashMap<Boolean,String>friendsSelected = new HashMap<>();

        final ArrayList<String>friendsSelected = new ArrayList<>();
        friendsSelected.clear();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(friendsSelected.contains(myFriends.get(position))){
                    friendsSelected.remove(myFriends.get(position));
                }else{
                    Log.i("INFORMATION"," We are adding "+ myFriends.get(position));
                    friendsSelected.add(myFriends.get(position));
                }
            }
        });


        LinearLayout ll = new LinearLayout(this);

        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(text);
        ll.addView(numberText);

        ll.addView(listView);
        builder.setView(ll);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("INFO","Sending Array = "+ friendsSelected.toString());
                try{
                    management.addItem(text.getText().toString(), Double.parseDouble(numberText.getText().toString()),friendsSelected);
                    arrayListReset();
                }catch (Exception e){
                    //nothing
                }
            }
        }).show();
        }

        private void addFriendWindow(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AddFriend");
        //input
        final EditText text = new EditText(this);
        builder.setView(text);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                management.addFriend(text.getText().toString());
                arrayListReset();
            }
        });
        builder.show();
    }
}