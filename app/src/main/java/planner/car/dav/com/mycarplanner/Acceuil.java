package planner.car.dav.com.mycarplanner;

import android.app.Activity;
        import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Path;
import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;

import java.io.File;

public class Acceuil extends Activity {
    public static final String APP_TAG = "CAR_PLANNER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_acceuil);
       //Si la base de données n'existe pas, on la creer et on insere les categories

       if (!checkDataBase()){
            Log.i(APP_TAG, "Acceuil la bdd n'existe pas" );

         // Categorie c1 =  new Categorie(-1, "Administratif");
           //Log.i(APP_TAG, "categorie c1 : " + c1.toString());
           //final long l = new DbManager(this).insertCategorie(c1);

           new DbManager(this).insertCategorie(new Categorie(-1, "Administratif"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Carburant"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Divers"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Equipement"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Parking"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Péage"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Réparation"));
           new DbManager(this).insertCategorie(new Categorie(-1,"Service"));
           Log.i(APP_TAG, "Fin ajout categorie");

            //long id = new DbManager(this).insertCar();
        }else Log.i(APP_TAG, "Acceuil la bdd existe " );

        Log.i(APP_TAG, "Acceuil On continu" );
    }

    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        File dbPath = this.getDatabasePath("baseCarPlanner");
        try {
             checkDB = SQLiteDatabase.openDatabase(dbPath.getPath(), null, SQLiteDatabase.OPEN_READONLY);
            Log.i(APP_TAG, "Acceuil try db" );
            checkDB.close();
        } catch (SQLiteException e) {
            Log.i(APP_TAG, "Acceuil  db n'existe pas" );
            // base de données n'existe pas.
        }
        return checkDB != null ? true : false;
    }

    private void initCategorie(){

    }

    public void action(View v){
        switch(v.getId())
        {
            case  R.id.car_button:
                Log.i(APP_TAG,"car button clicked");
                Intent carIntent = new Intent(Acceuil.this,Car.class);
                this.startActivity(carIntent);
                break;

            case R.id.register_button:
                Log.i(APP_TAG,"register button clicked");

                Intent dbmanager = new Intent(Acceuil.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
                break;
            case R.id.delette_button:
                new DbManager(this).deletteAllDBentry();
                break;
            case R.id.spending_button:
                Intent spendIntent = new Intent(Acceuil.this,Spending.class);
                startActivity(spendIntent);
                //public Fuel(long id,String carId,String price,String date,String mileage,String price_per_liter,String fuel_station,String fuel,String city,String picture_path,String note)
                //new DbManager(this).insertFuel(new Fuel(-1,1,"40","01/08/15","750","3.15","BP","diesel","Magny","ToDo","Ras"));
                break;
        }

    }
}
