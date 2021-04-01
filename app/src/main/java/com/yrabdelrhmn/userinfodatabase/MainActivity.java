package com.yrabdelrhmn.userinfodatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText userName, userPhone , userAddress; // creating variables for EditTexts and Button
    private Button saveBtn;
    FirebaseDatabase db; //var for the firebase database
    DatabaseReference dbReference; // var for the reference for Firebase
    UserInfo userInfo; //creating var for the object class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        userPhone = findViewById(R.id.phone);
        userAddress = findViewById(R.id.address);

        db = FirebaseDatabase.getInstance();
        dbReference = db.getReference("UserInfo"); //to get the reference for the database
        userInfo = new UserInfo(); // initializing the object class var
        saveBtn = findViewById(R.id.saveBtn);


        saveBtn.setOnClickListener(
                v -> {
                    String name = userName.getText().toString();
                    String phone = userPhone.getText().toString();
                    String address = userAddress.getText().toString();
                    if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(address)) {
                        Toast.makeText(MainActivity.this, "Please add your data", Toast.LENGTH_SHORT).show();
                    } else {
                        addDataToFirebase(name, phone, address); // call the method to add data to database
                    }
                }
        );
        getData();

        }


    private void addDataToFirebase(String name, String phone, String address) {
    userInfo.setUserName(name);
    userInfo.setUserPhone(phone);
    userInfo.setUserAddress(address);
    dbReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) { // set object to database reference
            dbReference.setValue(userInfo);
            Toast.makeText(MainActivity.this,"data added successfully",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) { // if the data is not added successfully and cancelled
        Toast.makeText(MainActivity.this,"Failed to add data"+ error,Toast.LENGTH_SHORT).show();
        }
    });

    }


private void getData(){
        dbReference.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                userInfo.setUserName(value);
                userInfo.setUserPhone(value);
                userInfo.setUserAddress(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error fetching data", Toast.LENGTH_LONG).show();
            }
        });
}

}