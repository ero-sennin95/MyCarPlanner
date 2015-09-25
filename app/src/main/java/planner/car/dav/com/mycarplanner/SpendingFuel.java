package planner.car.dav.com.mycarplanner;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static planner.car.dav.com.mycarplanner.R.id.date_spend_editText;

public class SpendingFuel extends FragmentActivity implements DatePickerDialog.OnDateSetListener,CarDialogFragment.CarDialogListener {
    public static final String DATE_TAG = "spendFragId";
    public static final int INVOICE_PHOTO_TAKEN = 1;
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
    ImageButton Add_Invoice_Picture_bt = null;
    private File imageFile = null;
    long id;
    Context ctx = null;

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Uri fileUri;


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
        Add_Invoice_Picture_bt = (ImageButton) this.findViewById(R.id.addPicture_invoice_imageButton);
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

        Add_Invoice_Picture_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(Acceuil.APP_TAG, "Add Picture Invoice");
                Intent invoicePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                imageFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                invoicePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, getOutputMediaFileUri(MEDIA_TYPE_IMAGE));
                startActivityForResult(invoicePicIntent, INVOICE_PHOTO_TAKEN);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.i(Acceuil.APP_TAG, "carName : onActivityResult");
        switch(requestCode){
            case INVOICE_PHOTO_TAKEN:{
                if(resultCode == RESULT_OK){
                    Log.i(Acceuil.APP_TAG, "Spending : onActivityResult RESULT_OK");
                //    Bundle b = data.getExtras();
                   Toast.makeText(this, "Image saved to:\n" +imageFile.getPath(), Toast.LENGTH_LONG).show();

                    // Bundle extraFromPic = data.getExtras();
                  /*  this.thePhotoTaken = (Bitmap) extraFromPic.get("data");
                    addPicture_btn.setImageBitmap(thePhotoTaken);
                    infoCarPicture_btn.setText("Picture successfull taken");*/
                }
            }
        }
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
        String picture_path = null;
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
        if(imageFile != null){
            picture_path =imageFile.getPath();

        }
        String note =  Note_spend_ET.getText().toString();



        new DbManager(this).insertFuel(new Fuel(-1, id, priceTotal, date, mileage, price_per_liter, fuel_station, city,picture_path,note));

        Toast.makeText(this, "Submit successfull...", Toast.LENGTH_LONG).show();

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

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /** Create a file Uri for saving an image or video */
    private  Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraCarPlanner");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }


}
