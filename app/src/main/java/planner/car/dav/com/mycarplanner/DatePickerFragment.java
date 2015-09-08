package planner.car.dav.com.mycarplanner;


import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;





public class DatePickerFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        Calendar c = Calendar.getInstance();
        int year= c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day_of_month = c.get(Calendar.DAY_OF_MONTH);
        //	Log.(Acceuil.APP_TAG, "DialogFrag method Id: " +this.getId());

        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), STYLE_NORMAL, (OnDateSetListener)getActivity(), year,month,day_of_month);
        datePicker.getDatePicker().setTag(this.getTag());
        //datePicker.setTag("xx");
        Log.i(Acceuil.APP_TAG,"DatePickerFragment tag: " +this.getTag());
        Log.i(Acceuil.APP_TAG,"DatePickerFragment id: " +this.getId());
        //this.getFragmentManager().
        return datePicker;

    }


}

