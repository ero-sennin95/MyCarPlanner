package planner.car.dav.com.mycarplanner;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Spending extends FragmentActivity implements DatePickerDialog.OnDateSetListener {
public static final String DATE_TAG = "spendFragId";
    EditText spendDateET =null;
    DialogFragment newFrag= null;
    EditText Car_spend_ET = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addspending_layout);
        initFindViewById();
        setListener();
    }

    private void initFindViewById(){
        spendDateET = (EditText) this.findViewById(R.id.date_spend_editText);
        Car_spend_ET= (EditText) this.findViewById(R.id.Car_spend_editText);
    }

    private void setListener(){
        spendDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Acceuil.APP_TAG, "Spend ET clicked!");
                newFrag = new DatePickerFragment();
                newFrag.show(getSupportFragmentManager(),DATE_TAG);

            }
        });
        Car_spend_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Acceuil.APP_TAG, "car spend ET clicked!");
                CarDialogFragment listCarFrag = new CarDialogFragment();
                listCarFrag.show(getSupportFragmentManager(),"TAG");
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_spending, menu);
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
    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
        Log.i(Acceuil.APP_TAG, "onDateSet Spend method");
        Log.i(Acceuil.APP_TAG,"view ID" + view.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        String date = sdf.format(c.getTime());
        Log.i(Acceuil.APP_TAG, "Spend method Date: " +date);


        Log.i(Acceuil.APP_TAG, "Spend method Tag: "+ view.getTag());
        if (view.getTag().equals(DATE_TAG)){
            Log.i(Acceuil.APP_TAG,"DateReisterId selected");
            spendDateET.setText(date);
        }
    }
}
