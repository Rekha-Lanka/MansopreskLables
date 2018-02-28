package com.training.mansopresk.mansopresklables;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {
  EditText etname,etpwd,etcalender;
  RadioButton rb;
  RadioGroup rg;
  RatingBar rating;
  Spinner sp;
  ToggleButton tb;
  Switch switchbtn;
  Button navigatebtn;
  //Calendar c;
  CheckBox cb;
    Calendar myCalendar = Calendar.getInstance();
    private int day;
    private int month;
    private int year;
    static final int DATE_PICKER_ID = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         etname=(EditText)findViewById(R.id.etname);
         etpwd=(EditText)findViewById(R.id.etpwd);
        cb=(CheckBox)findViewById(R.id.cb);
        rg=(RadioGroup)findViewById(R.id.rg);
        rating=(RatingBar)findViewById(R.id.rating);
        sp=(Spinner)findViewById(R.id.sp);
        etcalender=(EditText)findViewById(R.id.etcalender);
        tb=(ToggleButton)findViewById(R.id.togglebtn);
        switchbtn=(Switch)findViewById(R.id.switchbtn);

        //c=Calendar.getInstance();
//        String date=c.get(Calendar.DAY_OF_MONTH)+"/"+c.get((Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
//         etcalender.setText(date);

        etcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                new DatePickerDialog(UrgencyActivity.this,date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

                myCalendar = Calendar.getInstance();
                day = myCalendar.get(Calendar.DAY_OF_MONTH);
                month = myCalendar.get(Calendar.MONTH);
                year = myCalendar.get(Calendar.YEAR);
                showDialog(DATE_PICKER_ID);
                String s= etcalender.getText().toString();


            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                // create a new DatePickerDialog with values you want to show

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, year, month, day);
                myCalendar = Calendar.getInstance();
                myCalendar.add(Calendar.DATE, +1); // Add 0 days to Calendar
                Date newDate = myCalendar.getTime();
                datePickerDialog.getDatePicker().setMinDate(newDate.getTime()-(newDate.getTime()%(24*60*60*1000)));
                return datePickerDialog;
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the
        // DatePickerDialog
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {

            etcalender.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear);
        }
    };

    public  void cleardata(View v){
        etname.setText("");
        etpwd.setText("");
        if(cb.isChecked())
            cb.performClick();

    }
    public  void validate(View v){
        String s1=etname.getText().toString();
        String s2=etpwd.getText().toString();
        if(cb.isChecked()){
            if(s1.equals(""))
                etname.setError("please fill Name");
            if(s2.equals(""))
                etpwd.setError("please fill Password");
            else if(s2.length()<4){
                etpwd.setError("Enter min 4 chars");
            }
            else{
                Toast.makeText(getApplicationContext(),"Validation Completed",Toast.LENGTH_LONG).show();
                //startActivity(new Intent(this,NavDrawerActivity.class));
                Intent i=new Intent(this,NavDrawerActivity.class);
                SharedPreferences.Editor editor=getSharedPreferences("UserDetails",MODE_PRIVATE).edit();
                editor.putString("username",s1);
                editor.commit();
                startActivity(i);


            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Please check T and C",Toast.LENGTH_LONG).show();
        }
    }
    public void valid(View v){
       rb=(RadioButton)findViewById(rg.getCheckedRadioButtonId());
       String value=rb.getText().toString();
       float rate=rating.getRating();
       String role=sp.getSelectedItem().toString();
       Toast.makeText(getApplicationContext(),value+""+rate+""+role,Toast.LENGTH_LONG).show();
    }
    public void testSwitch(View v){
       if(tb.isChecked()&&switchbtn.isChecked()){
           //On and on ---o/p=on
           //off and on===o/p= off
           Toast.makeText(getApplicationContext(),"ON",Toast.LENGTH_LONG).show();
       }else{
           Toast.makeText(getApplicationContext(),"OFF",Toast.LENGTH_LONG).show();
       }
     }

}
