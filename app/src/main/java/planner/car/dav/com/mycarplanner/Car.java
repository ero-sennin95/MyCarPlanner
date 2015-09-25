package planner.car.dav.com.mycarplanner;

import java.util.List;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Car extends Activity {

    public static final int CREATION_CAR_REQUEST = 1;
    //DbManager dbMan = new DbManager(this);
    //@SuppressWarnings("unused")
    ArrayAdapter<Vehicule> myadapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_layout);
        final ListView car_ListView = (ListView)this.findViewById(R.id.car_listView);

        List<Vehicule> list = new DbManager(this).listCar();
        Log.i(Acceuil.APP_TAG,"Car onCreate() : List<Vehicule>" + list.toString());
        myadapter = new ArrayAdapter<Vehicule>(this, android.R.layout.simple_list_item_1,list);
        car_ListView.setAdapter(myadapter);
        car_ListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int arg2,
                                    long arg3) {
                Log.i(Acceuil.APP_TAG,"Car onCreate() : onItemClick");
                Intent carIntent = new Intent(Car.this,CarStat.class);
                startActivity(carIntent);
            }

        } );
    }

    public void addAction(View v){
        switch(v.getId())
        {
            case  R.id.add_button:
                Log.i(Acceuil.APP_TAG,"Add car button clicked");
                Intent carIntent = new Intent(Car.this,AddCar.class);
                this.startActivityForResult(carIntent, CREATION_CAR_REQUEST);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(CREATION_CAR_REQUEST == requestCode){
            if(RESULT_OK == resultCode){
                Log.i(Acceuil.APP_TAG,"result add car ok");
                final Bundle b = data.getExtras();
                long id = new DbManager(this).insertCar(new Vehicule(-1, b.getString(Vehicule.NAME_KEY)
                        , b.getString(Vehicule.REGISTRATION_KEY)
                        , b.getString(Vehicule.FIRST_REGISTRATION_KEY)
                        , b.getString(Vehicule.CONTROL_TECH_KEY)
                        , b.getString(Vehicule.BRAND_KEY)
                        , b.getString(Vehicule.MODEL_KEY)
                        , b.getString(Vehicule.FUEL_KEY)
                        , b.getString(Vehicule.MILEAGE_KEY)
                        , b.getString(Vehicule.AVERAGE_MILEAGE_KEY)
                        , b.getString(Vehicule.PICTURE_PATH)));

                myadapter.add(new Vehicule(id, b.getString(Vehicule.NAME_KEY)
                        , b.getString(Vehicule.REGISTRATION_KEY)
                        , b.getString(Vehicule.FIRST_REGISTRATION_KEY)
                        , b.getString(Vehicule.CONTROL_TECH_KEY)
                        , b.getString(Vehicule.BRAND_KEY)
                        , b.getString(Vehicule.MODEL_KEY)
                        , b.getString(Vehicule.FUEL_KEY)
                        , b.getString(Vehicule.MILEAGE_KEY)
                        , b.getString(Vehicule.AVERAGE_MILEAGE_KEY)
                        , b.getString(Vehicule.PICTURE_PATH)));
                myadapter.notifyDataSetChanged();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
