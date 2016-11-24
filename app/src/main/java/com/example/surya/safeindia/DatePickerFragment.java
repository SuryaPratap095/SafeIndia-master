package com.example.surya.safeindia;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.Calendar;

/**
 * Created by surya on 27/8/16.
 */
public class DatePickerFragment extends android.support.v4.app.DialogFragment implements DatePickerDialog.OnDateSetListener{

    public DatePickerFragment(){

    }
    private EditText activity_edittext;
    private TextView mtextView;
    @SuppressLint("ValidFragment")
    public DatePickerFragment(TextView editText){
        mtextView = editText;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mtextView.setText(String.valueOf(dayOfMonth ) + "/" +   String.valueOf(monthOfYear + 1) + "/" + String.valueOf(year));
    }

}
