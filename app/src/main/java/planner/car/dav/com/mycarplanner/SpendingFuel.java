package planner.car.dav.com.mycarplanner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static planner.car.dav.com.mycarplanner.R.id.date_spend_editText;

public class SpendingFuel extends FragmentActivity implements DatePickerDialog.OnDateSetListener,CarDialogFragment.CarDialogListener {
    public static final String DATE_TAG = "spendFragId";
    EditText spendDateET =null;
    DialogFragment newFrag= null;
    EditText Car_spend_ET = null;
    EditText Price_spend_ET = null;
    EditText Price_per_liter_ET = null;
    EditText Date_spend_ET = null;
    EditText MileAge_spend_ET = null;
    EditText Station_spend_ET = null;
    EditText City_spend_ET = null;
    EditText Note_spend_ET = null;
    Button Add_Spend_bt = null;
    long id;
    Context ctx = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addspending_layout);
        initFindViewById();
        setListener();
        id=-1;

    }

    private void initFindViewById(){
        spendDateET = (EditText) this.findViewById(date_spend_editText);
        Car_spend_ET= (EditText) this.findViewById(R.id.Car_spend_editText);
        Add_Spend_bt =(Button) this.findViewById(R.id.Add_Spend_Btn);
        Price_spend_ET = (EditText) this.findViewById(R.id.price_spend_editText);
        Price_per_liter_ET = (EditText) this.findViewById(R.id.price_liter_spend_editText);
        Date_spend_ET = (EditText) this.findViewById(R.id.date_spend_editText);
        MileAge_spend_ET =  (EditText) this.findViewById(R.id.mileage_spend_editText);
        Station_spend_ET = (EditText) this.findViewById(R.id.station_spend_editText);
        City_spend_ET = (EditText) this.findViewById(R.id.city_spend_editText);
        Note_spend_ET = (EditText) this.findViewById(R.id.note_spend_editText);
    }

    private void setListener(){
        spendDateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Acceuil.APP_TAG, "Spend ET clicked!");
                newFrag = new DatePickerFragment();
                newFrag.show(getSupportFragmentManager(), DATE_TAG);

            }
        });
        Car_spend_ET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Acceuil.APP_TAG, "car spend ET clicked!");
               // final ArrayList<String> reqResult = new DbManager(view.getContext()).listCarName();
              //  Log.i(Acceuil.APP_TAG, "reqResult : " + reqResult.toString());
                CarDialogFragment listCarFrag = new CarDialogFragment();
                listCarFrag.show(getSupportFragmentManager(),"TAG");
            }
        });
        Add_Spend_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Acceuil.APP_TAG, "car spend button clicked!");
             //   testInsertDb();
                if (checkValid()){
                    submitForm();
                    SpendingFuel.this.finish();


                }else{
                    Toast.makeText(SpendingFuel.this, "Erreur dans le formulaire", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private boolean checkValid() {
        Log.i(Acceuil.APP_TAG, "check valid!");
        boolean ret = true;
        if (!Validation.hasText(Car_spend_ET)) ret = false;
        if(!Validation.hasText(Price_spend_ET)) ret = false;
        if(!Validation.hasText(Date_spend_ET)) ret = false;
        if (id<0) ret = false;
        return ret;

    }
    private void submitForm(){
        Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
        //public Fuel(long id,long carId,String price,String date,String mileage,Float price_per_liter,String fuel_station,String city,String picture_path,String note)
        String date =Date_spend_ET.getText().toString();
        int mileage=-1;
        float priceTotal=-1;
        float price_per_liter=-1;
        if (Validation.hasText(MileAge_spend_ET)) mileage = Integer.parseInt(MileAge_spend_ET.getText().toString());
        if (Validation.hasText(Price_spend_ET)) priceTotal = Float.parseFloat(Price_spend_ET.getText().toString());
        if (Validation.hasText(Price_per_liter_ET)) price_per_liter = Float.parseFloat(Price_per_liter_ET.getText().toString());

        String fuel_station =Station_spend_ET.getText().toString();
        String city =City_spend_ET.getText().toString();
        String picture_path ="Todo";
        String note =  Note_spend_ET.getText().toString();



        new DbManager(this).insertFuel(new Fuel(-1, id, priceTotal, date, mileage, price_per_liter, fuel_station, city,picture_path,note));



    }
    private void testInsertDb(){
        Toast.makeText(this, "test insertion...", Toast.LENGTH_LONG).show();
        //public Fuel(long id,long carId,String price,String date,String mileage,Float price_per_liter,String fuel_station,String city,String picture_path,String note)
        float priceTotal = 45.23f;
        float price_per_liter =1.08f;
        String date ="01/09/2015";
        int mileage =154265;
        String fuel_station ="Esso";
        String city ="cergy";
        String picture_path ="Todo";
        String note ="In progress...";
        new DbManager(this).insertFuel( new Fuel(-1,id,priceTotal,date,mileage,price_per_liter,fuel_station,city,picture_path,note));
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

    @Override
    public void onChoiceSet(long id,String i) {
        Log.i(Acceuil.APP_TAG,"onChoiceSet : " + i);
        Log.i(Acceuil.APP_TAG,"onChoiceSet id : " + id);
        Car_spend_ET.setText(i);
        this.id=id;
    }
}
