package planner.car.dav.com.mycarplanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle.Control;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddCar extends FragmentActivity implements OnItemSelectedListener,OnDateSetListener{

    public static final int CAR_PHOTO_TAKEN = 1;
    private Bitmap thePhotoTaken =null;
    private String bitmapPath =null;
    Spinner model_spinner =null;
    Spinner brand_spinner = null;
    Spinner fuel_spinner = null;
    HashMap<Integer,Integer> mapper = null;
    EditText registration_ET=null;
    EditText firstRegistration_ET=null;
    EditText control_ET =null;
    EditText carNameET = null;
    DialogFragment newFrag= null;
    Button addCar_btn =null;
    ImageButton addPicture_btn = null;
    TextView infoCarPicture_btn = null;
    EditText averageMileAge_ET = null;
    EditText mileAge_ET = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcar_layout);
        initFindViewById();
        initView();
        setListener();
        Calendar rightNow = Calendar.getInstance();
        final int month = rightNow.get(Calendar.MONTH);
        final int year = rightNow.get(Calendar.YEAR);
        final int day = rightNow.get(Calendar.DAY_OF_MONTH);
        final int date = rightNow.get(Calendar.DATE);
        Log.i(Acceuil.APP_TAG, "onCreate current date : " + "day : " + day + "month : " + month + "yaer : " + year);
        Log.i(Acceuil.APP_TAG, "onCreate current date 2 : " + date);

       // checkValid();
    }

    private void setListener() {
        firstRegistration_ET.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i(Acceuil.APP_TAG, "Registration ET clicked!");
                newFrag = new DatePickerFragment();
                newFrag.show(getSupportFragmentManager(), "DateRegisterId");
            }
        });

        control_ET.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i(Acceuil.APP_TAG, "control ET clicked!");
                newFrag = new DatePickerFragment();
                newFrag.show(getSupportFragmentManager(), "ControlId");
            }
        });
        addPicture_btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(Acceuil.APP_TAG, "add picture btn clicked!");
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAR_PHOTO_TAKEN);
            }
        });
    }

    private void initView() {
        ArrayAdapter<CharSequence> brand_adapter = ArrayAdapter.createFromResource(this,R.array.brand,android.R.layout.simple_spinner_item);
        brand_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        brand_spinner.setAdapter(brand_adapter);
        brand_spinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> fuel_adapter = ArrayAdapter.createFromResource(this,R.array.fuel,android.R.layout.simple_spinner_item);
        fuel_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        fuel_spinner.setAdapter(fuel_adapter);

    }

    private void initFindViewById() {
        carNameET = (EditText)this.findViewById(R.id.car_Name_editText);
        registration_ET = (EditText) this.findViewById(R.id.registration_editText);
        firstRegistration_ET = (EditText) this.findViewById(R.id.firstRegistration_editText);
        control_ET = (EditText) this.findViewById(R.id.techniqueControl_editText);
        brand_spinner = (Spinner) this.findViewById(R.id.brand_spinner);
        model_spinner = (Spinner) this.findViewById(R.id.modele_spinner);
        fuel_spinner = (Spinner) this.findViewById(R.id.fuel_spinner);
        averageMileAge_ET =(EditText)this.findViewById(R.id.Average_mileAge_editText);
        mileAge_ET = (EditText)this.findViewById(R.id.mileAge_editText);
        addCar_btn = (Button) this.findViewById(R.id.add_car_db_button);
        addPicture_btn = (ImageButton) this.findViewById(R.id.addPicture_imageButton);
        infoCarPicture_btn =  (TextView) this.findViewById(R.id.add_picture_textView);


    }

    public void updateModel(int id){

        ArrayAdapter<CharSequence> model_adapter = ArrayAdapter.createFromResource(this,id,android.R.layout.simple_spinner_item);
        model_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        model_spinner.setAdapter(model_adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
        // TODO Auto-generated method stub
        //mapper.clear();

        Log.i(Acceuil.APP_TAG, "onItemSelected");
        Log.i(Acceuil.APP_TAG, "Item at pos : " +parent.getItemAtPosition(pos));
        Log.i(Acceuil.APP_TAG, "Id : " + id);
        Log.i(Acceuil.APP_TAG, "P : " + pos);
        switch(pos){
            case 0:
                updateModel(R.array.model_ford);
                break;

            case 2:
                updateModel(R.array.model_peugot);
                break;
            case 3:
                updateModel(R.array.model_renault);
                break;
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
       // Log.i(Acceuil.APP_TAG, "onNothingSelected");
    }



    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
        Log.i(Acceuil.APP_TAG, "onDateSet AddCar method");
        //switch (view.getId())
        Log.i(Acceuil.APP_TAG,"view ID" + view.getId());
        // Log.i(Acceuil.APP_TAG,"regedittext ID" + R.id.firstRegistration_editText);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        Calendar c = Calendar.getInstance();
        c.set(year, monthOfYear, dayOfMonth);
        String date = sdf.format(c.getTime());

        //Log.i(Acceuil.APP_TAG, "AddCar method Date: " +date);


        Log.i(Acceuil.APP_TAG, "AddCar method Tag: "+ view.getTag());
        if (view.getTag().equals("DateRegisterId")){
            Log.i(Acceuil.APP_TAG,"DateReisterId selected");
            firstRegistration_ET.setText(date);
        }else if (view.getTag().equals("ControlId")){
            Log.i(Acceuil.APP_TAG,"ControlId selected");
            control_ET.setText(date);
        }
        // TODO Auto-generated method stub

    }

    public void add_btn(View v){

        Log.i(Acceuil.APP_TAG, "add car clicked");
        if (checkValid()){
            submitForm();
        }else{
            Toast.makeText(AddCar.this,"Erreur dans le formulaire",Toast.LENGTH_LONG).show();
        }


    }

    private void submitForm() {
        Toast.makeText(this, "Submitting form...", Toast.LENGTH_LONG).show();
        String carName = carNameET.getText().toString();
        // String picturePath = "A implenter";
        String registration = registration_ET.getText().toString();
        String firstRegistration = firstRegistration_ET.getText().toString();
        String brand = brand_spinner.getSelectedItem().toString();
        String model = model_spinner.getSelectedItem().toString();
        String fuel = fuel_spinner.getSelectedItem().toString();
        String mileAge = mileAge_ET.getText().toString();
        String averageMile = averageMileAge_ET.getText().toString();
        String control = control_ET.getText().toString();

       // Log.i(Acceuil.APP_TAG,"carName : " + carName + firstRegistration);
        //Log.i(Acceuil.APP_TAG,"add btn brand : " + brand);
        Intent i = new Intent();
        i.putExtra(Vehicule.NAME_KEY, carName);
       // i.putExtra(Vehicule.PICTURE_PATH, picturePath);
        i.putExtra(Vehicule.REGISTRATION_KEY, registration);
        i.putExtra(Vehicule.FIRST_REGISTRATION_KEY, firstRegistration);
        i.putExtra(Vehicule.BRAND_KEY, brand);
        i.putExtra(Vehicule.MODEL_KEY, model);
        i.putExtra(Vehicule.FUEL_KEY, fuel);
        i.putExtra(Vehicule.MILEAGE_KEY, mileAge);
        i.putExtra(Vehicule.AVERAGE_MILEAGE_KEY, averageMile);
        i.putExtra(Vehicule.CONTROL_TECH_KEY, control);

        if(savingBitmap(thePhotoTaken) && thePhotoTaken != null ){
             i.putExtra(Vehicule.PICTURE_PATH, bitmapPath);
        }

        this.setResult(RESULT_OK,i);
        Log.i(Acceuil.APP_TAG,"add btn finish");
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i(Acceuil.APP_TAG,"carName : onActivityResult" );
        switch(requestCode){
            case CAR_PHOTO_TAKEN:{
                if(resultCode == RESULT_OK){
                    Log.i(Acceuil.APP_TAG,"carName : onActivityResult RESULT_OK" );
                    Bundle extraFromPic = data.getExtras();
                    this.thePhotoTaken = (Bitmap) extraFromPic.get("data");
                    addPicture_btn.setImageBitmap(thePhotoTaken);
                    infoCarPicture_btn.setText("Picture successfull taken");
                }
            }
        }
    }

    private boolean savingBitmap(Bitmap bmp){

       // String fileName = "testFile008";
        Random generator = new Random();
        int n = 10000;
        Calendar rightNow = Calendar.getInstance();
        final int m = (rightNow.get(Calendar.MONTH) + 1);
        final int d = rightNow.get(Calendar.DAY_OF_MONTH);
        final int y = rightNow.get(Calendar.YEAR);

        n = generator.nextInt(n);
        String fileName = String.format("thumbnail-%d%d%d-%d.jpg", d, m, y,n);
        Log.i(Acceuil.APP_TAG,"carName : fileName : " +fileName);
        File storageDir = new File( this. getDir("Thumbnails", Context.MODE_PRIVATE) ,fileName);
        FileOutputStream storageStreamOut = null;
        //   Log.i(Acceuil.APP_TAG,"carName : storage Dir : " +storageDir.toString() );

        try {
            //File newDir =;
            storageStreamOut = new FileOutputStream(storageDir);
            thePhotoTaken.compress(Bitmap.CompressFormat.JPEG,100,storageStreamOut);
            bitmapPath =  storageDir.getPath();
            Log.i(Acceuil.APP_TAG,"carName : get bitmapPath : " + bitmapPath);

            if(storageStreamOut != null){
                storageStreamOut.flush();
                storageStreamOut.close();
            return true;}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkValid(){
        boolean ret = true;

        if (!Validation.hasText(carNameET)) ret = false;
        if( !Validation.hasText(firstRegistration_ET))ret =false;
        if(!Validation.hasText(averageMileAge_ET))ret =false;
        if(!Validation.hasText(control_ET))ret =false;
        if(!Validation.hasText(mileAge_ET))ret =false;

        if(!Validation.isRegistrationOld(registration_ET,true) && !Validation.isRegistrationNew(registration_ET, true)) {
            ret = false;
        }
        return ret;
    }
}
