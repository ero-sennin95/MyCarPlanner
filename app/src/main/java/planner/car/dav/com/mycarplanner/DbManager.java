package planner.car.dav.com.mycarplanner;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbManager extends SQLiteOpenHelper{

    public static final String DBNAME = "baseCarPlanner";
    public static final int DBVERSION = 2;
    public static final String TABLE_CAR = "car";
    public static final String TABLE_FUEL = "fuel";
    public static final String TABLE_CATEGORIE = "categorie";

    //SQLiteDatabase dbb =null;


    public DbManager(Context context) {
        super(context, DBNAME, null, DBVERSION);
        Log.i(Acceuil.APP_TAG,"dbManager() : constructeur normal");

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(Acceuil.APP_TAG,"onCreate() : implantation du schema de la base");

        String strCar = "CREATE TABLE "+ TABLE_CAR + " (" +Vehicule.ID_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Vehicule.NAME_KEY +" TEXT,"
                +Vehicule.REGISTRATION_KEY +" TEXT,"
                +Vehicule.FIRST_REGISTRATION_KEY + " TEXT,"
                +Vehicule.BRAND_KEY + " TEXT,"
                +Vehicule.MODEL_KEY + " TEXT,"
                +Vehicule.FUEL_KEY +" TEXT,"
                +Vehicule.MILEAGE_KEY +" TEXT,"
                +Vehicule.AVERAGE_MILEAGE_KEY +" TEXT,"
                +Vehicule.CONTROL_TECH_KEY + " TEXT,"
                +Vehicule.PICTURE_PATH + " TEXT);";

        String strfuel = "CREATE TABLE "+ TABLE_FUEL + " (" +Fuel.ID_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Fuel.ID_CAR_KEY +" INTEGER,"
                +Fuel.PRICE_KEY +" TEXT,"
                +Fuel.DATE_KEY + " TEXT,"
                +Fuel.MILEAGE_KEY + " TEXT,"
                +Fuel.PICEPERLITER_KEY + " TEXT,"
                +Fuel.FUELSTATION_KEY +" TEXT,"
                +Fuel.CITY_KEY +" TEXT,"
                +Fuel.PICTURE_BILL_PATH_KEY + " TEXT,"
                +Fuel.NOTE_PATH + " TEXT,"
                +"FOREIGN KEY(" + Fuel.ID_CAR_KEY + ")REFERENCES " + TABLE_CAR +"("+Vehicule.ID_KEY+"));";

        String strCategorie = "CREATE TABLE " + TABLE_CATEGORIE + " (" +Categorie.ID_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +Categorie.TYPE_KEY + " TEXT);";

        Log.i(Acceuil.APP_TAG, "on Create DB1 : " +strCar);
        Log.i(Acceuil.APP_TAG, "on Create DB2 : " +strfuel);
        Log.i(Acceuil.APP_TAG, "on Create DB3 : " + strCategorie);
        db.execSQL(strCar);
        db.execSQL(strfuel);
        db.execSQL(strCategorie);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(Acceuil.APP_TAG, "Database Upgrade : delete the old "
                +"database");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FUEL);

        this.onCreate(db);

    }

    @Override
    public void onConfigure(SQLiteDatabase db){
        Log.i(Acceuil.APP_TAG, "Database configure the database");
        db.setForeignKeyConstraintsEnabled(true);
    }

    public void deletteAllDBentry(){
        Log.i(Acceuil.APP_TAG, "Database delette : delete the old "
                +"database");
        final SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CAR);

    }
    /*Methode pour inserer un vehicule dans la BDD
     * */
    public long insertCar(final Vehicule car){
        if(-1 != car.getId()){
            Log.i(Acceuil.APP_TAG, "vehicule déja enregistrée !");
            Log.i(Acceuil.APP_TAG, "vehicule déja enregistrée = " + car.toString());
            return -1;
        }
        Log.i(Acceuil.APP_TAG, "debut vehicule inseré " );
        ContentValues content = new ContentValues();
        content.put(Vehicule.NAME_KEY, car.getName());
        content.put(Vehicule.REGISTRATION_KEY, car.getRegistration());
        content.put(Vehicule.FIRST_REGISTRATION_KEY, car.getFirst_registration());
        content.put(Vehicule.BRAND_KEY, car.getBrand());
        content.put(Vehicule.MODEL_KEY, car.getModel());
        content.put(Vehicule.FUEL_KEY, car.getFuel());
        content.put(Vehicule.MILEAGE_KEY, car.getMileage());
        content.put(Vehicule.AVERAGE_MILEAGE_KEY, car.getAverage_mileage());
        content.put(Vehicule.CONTROL_TECH_KEY, car.getControl_tech());
        content.put(Vehicule.PICTURE_PATH, car.getPicture_path());

        long id = this.getWritableDatabase().insertOrThrow(TABLE_CAR, null, content);
        return id;
    }

    public long insertCategorie(final Categorie cat){
        if(-1 != cat.getId()){
            Log.i(Acceuil.APP_TAG, "Categorie déja enregistrée !");
            Log.i(Acceuil.APP_TAG, "Categorie déja enregistrée = " + cat.toString());
            return -1;
        }
        Log.i(Acceuil.APP_TAG, "debut Categorie inseré " );
        ContentValues content = new ContentValues();
      //  content.put(Categorie.ID_KEY,cat.getId());
        content.put(Categorie.TYPE_KEY,cat.getCategorie());
        long id = this.getWritableDatabase().insertOrThrow(TABLE_CATEGORIE, null, content);
        Log.i(Acceuil.APP_TAG, "Fin Categorie inseré " );
        return id;
    }

    public long insertFuel(final Fuel fuel){
        if(-1 != fuel.getId()){
            Log.i(Acceuil.APP_TAG, "fuel déja enregistrée !");
            Log.i(Acceuil.APP_TAG, "fuel déja enregistrée = " + fuel.toString());
            return -1;
        }
        Log.i(Acceuil.APP_TAG, "debut fuel inseré ");
        ContentValues content = new ContentValues();
        content.put(Fuel.ID_CAR_KEY, fuel.getM_carId());
        content.put(Fuel.PRICE_KEY , fuel.getM_price());
        content.put(Fuel.DATE_KEY, fuel.getM_date());
        content.put(Fuel.MILEAGE_KEY, fuel.getM_mileage());
        content.put(Fuel.PICEPERLITER_KEY, fuel.getM_price_per_liter());
        content.put(Fuel.FUELSTATION_KEY, fuel.getM_fuel_station());

        content.put(Fuel.CITY_KEY,fuel.getM_city());
        content.put(Fuel.PICTURE_BILL_PATH_KEY,fuel.getM_picture_path());
        content.put(Fuel.NOTE_PATH,fuel.getM_note());

        long idFuel = this.getWritableDatabase().insertOrThrow(TABLE_FUEL, null, content);
        return idFuel;

    }


    public String getPath(){
        Log.i(Acceuil.APP_TAG,"Appel getpath() " );
        return "ras";
        //return this.getPath();
    }


    public List<Vehicule> listCar(){
        Log.i(Acceuil.APP_TAG, "listCar() " );

        ArrayList<Vehicule> carsResult = new ArrayList<Vehicule>();
        final SQLiteDatabase dbb = this.getReadableDatabase();
        dbb.beginTransaction();
        try{
            final String requete = "SELECT * FROM " + TABLE_CAR;
            Log.i(Acceuil.APP_TAG, "requete : " + requete);
            Cursor cursor = getReadableDatabase().rawQuery(requete, new String[0]);
            //Log.i(Acceuil.APP_TAG, "cursor : " + cursor.toString());

            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                final int id = cursor.getInt(0);
                final String name = cursor.getString(1);
                final String registration = cursor.getString(2);
                final String first_registration = cursor.getString(3);
                final String brand = cursor.getString(4);
                final String model = cursor.getString(5);
                final String fuel = cursor.getString(6);
                final String mileAge = cursor.getString(7);
                final String average_mileAge = cursor.getString(8);
                final String control_tech  = cursor.getString(9);
                final String picture_path  = cursor.getString(10);

                Vehicule car = new Vehicule(id, name, registration, first_registration, control_tech, brand, model, fuel, mileAge, average_mileAge, picture_path);
                Log.i(Acceuil.APP_TAG, "Car : " + car.toString() );

                carsResult.add(car);
                cursor.moveToNext();

            }
            cursor.close();
            dbb.setTransactionSuccessful();
        }finally{
            dbb.endTransaction();
        }
        Log.i(Acceuil.APP_TAG, "carsResult : " + carsResult.toString() );

        return carsResult;
    }

    public ArrayList<Cursor> getData(String Query){

        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "mesage" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }

    }
}
