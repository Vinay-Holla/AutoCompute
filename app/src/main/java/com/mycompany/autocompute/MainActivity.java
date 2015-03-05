package com.mycompany.autocompute;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private EditText custname;
    private EditText custaddress;
    private EditText fromDateEtxt;
    private TextView toDateEtxt;
    private String passtype;
    private String passduration;

    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;

    Calendar newDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        custname = (EditText)findViewById(R.id.e_custname);
        custaddress = (EditText)findViewById(R.id.e_custaddress);
        passtype = ((Spinner)findViewById(R.id.s_passtype)).getSelectedItem().toString();
        passduration = ((Spinner)findViewById(R.id.s_passduration)).getSelectedItem().toString();

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        findViewsById();

        setDateTimeField();


    }

    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.e_fromdate);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);
        fromDateEtxt.requestFocus();

        toDateEtxt = (TextView) findViewById(R.id.t_todatedisp);
        toDateEtxt.setInputType(InputType.TYPE_NULL);
    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

                toDateSet();
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /*public void onDateSet(DatePicker this, int year, int month, int day) {
            // Do something with the date chosen by the user

            Toast.makeText(getApplicationContext(), (String)"I am here",
                    Toast.LENGTH_LONG).show();*/
        /*}*/
    }

    private void toDateSet(){

        Calendar newCalendar1 = Calendar.getInstance();
        /*newCalendar1 = newDate;*//**/
        /*newCalendar1.setTime(newDate);*/
        /*newCalendar1.add(Calendar.DAY_OF_MONTH, 12);*/
        int add_days = 30;

        switch (passduration){
            case "Daily"   :    add_days = 0;
                                break;
            case "Monthly" :    add_days = newCalendar1.getActualMaximum(Calendar.DAY_OF_MONTH);
                                break;
        }
        newCalendar1.add(Calendar.DAY_OF_MONTH, add_days);
        /*toDateEtxt = fromDateEtxt + 1;*/
        /*toDateEtxt.setText(dateFormatter.format(newCalendar1.getTime()));*/
        toDateEtxt.setText(dateFormatter.format(newCalendar1.getTime()));

    }
/*    DatePickerDialog.OnDateSetListener fromDatePickerDialog = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            Toast.makeText(
                    MainActivity.this,
                    String.valueOf(year) + "-" + String.valueOf(monthOfYear)
                            + "-" + String.valueOf(dayOfMonth),
                    Toast.LENGTH_LONG).show();

            Toast.makeText(getApplicationContext(), (String)"I am here",
                    Toast.LENGTH_LONG).show();
        }
    };*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        }
    }
}
