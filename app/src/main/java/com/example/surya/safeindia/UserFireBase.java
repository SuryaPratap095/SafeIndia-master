package com.example.surya.safeindia;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserFireBase extends AppCompatActivity {

    private TextView txtData,txtDOB;
    private EditText editFirstName,editLastName,editGender;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private FirebaseDatabase frefDatabase;
    private DatabaseReference drefDatabase;
    private String phoneNumber;

    private Button btnSave;
    private String userId;

    private RadioGroup genderRadioGroup;
    private RadioButton genderRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_fire_base);

        FirebaseApp.getApps(this);
        phoneNumber=getIntent().getStringExtra("phoneNumber");
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }

        //Firebase.setAndroidContext(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        txtData=(TextView) findViewById(R.id.txt_user);
        editFirstName=(EditText) findViewById(R.id.firstName);
        editLastName=(EditText)findViewById(R.id.lastName);
        //editGender=(EditText) findViewById(R.id.Address);
        btnSave=(Button)findViewById(R.id.btn_save);
        genderRadioGroup=(RadioGroup) findViewById(R.id.genderRadioGroup);
        txtDOB=(TextView)findViewById(R.id.dateDob);

        txtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

       // firebaseDatabase=FirebaseDatabase.getInstance();
        //databaseReference=firebaseDatabase.getReference("users");

       frefDatabase=FirebaseDatabase.getInstance();
        drefDatabase=frefDatabase.getReference("users");
        frefDatabase.getReference("app_title").setValue("Safe India");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radiobtnId=genderRadioGroup.getCheckedRadioButtonId();
                genderRadioButton=(RadioButton) findViewById(radiobtnId);
                String Gender=genderRadioButton.getText().toString();

                String FirstName=editFirstName.getText().toString();
                String  LastName=editLastName.getText().toString();
//                String Gender=editGender.getText().toString();
                String DOB=txtDOB.getText().toString();

                if(TextUtils.isEmpty(userId)){
                    createUser(FirstName,LastName,Gender,DOB,phoneNumber);
                }
                else{
                    updateUser(FirstName,LastName,Gender,DOB);
                }

                Intent intent=new Intent(getApplicationContext(),MapDrawer.class);
                startActivity(intent);
                UserFireBase.this.finish();

            }
        });


    }


    private void createUser(String FirstName,String LastName,String Gender,String DOB,String phoneNumber){
        if(TextUtils.isEmpty(userId)){
            userId=drefDatabase.push().getKey();
        }

        UserPOJO userPOJO=new UserPOJO(FirstName,LastName,Gender,DOB,phoneNumber);

        drefDatabase.child(userId).setValue(userPOJO);

        addUserChangeListner();
    }

    private void addUserChangeListner(){
        drefDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserPOJO userPOJO=dataSnapshot.getValue(UserPOJO.class);

                if(userPOJO==null){
                    Log.e(config.TAG,"User is NULL");
                    return;
                }
                Log.e(config.TAG,"User Data Changed"+userPOJO.getFirstName()+" ,"+userPOJO.getLastName()+
                        ", "+userPOJO.getGender()+", "+ userPOJO.getDOB());

                txtData.setText(userPOJO.getFirstName()+" ,"+userPOJO.getLastName()+
                        ", "+userPOJO.getGender()+", "+ userPOJO.getDOB()+", "+userPOJO.getPhoneNumber());

                editFirstName.setText("");
                editLastName.setText("");
              //  editGender.setText("");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(config.TAG, "Failed to read user", databaseError.toException());

                    }
        });
    }

    private void updateUser(String FirstName,String LastName,String Gender,String DOB){

    }


    public void showDatePickerDialog(){
        DialogFragment dialogFragment=new DatePickerFragment(txtDOB);
        dialogFragment.show(getSupportFragmentManager(),"datePicker");
    }

}
