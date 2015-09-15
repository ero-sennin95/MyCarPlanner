package planner.car.dav.com.mycarplanner;


public class Fuel {
    public static final String ID_KEY = "_id";
    public static final String ID_CAR_KEY = "carId";
    public static final String PRICE_KEY = "price";
    public static final String DATE_KEY = "date";
    public static final String MILEAGE_KEY = "mileage";
    public static final String PICEPERLITER_KEY = "price_per_liter";
    public static final String FUELSTATION_KEY = "fuel_station";
    public static final String CITY_KEY = "city";
    public static final String PICTURE_BILL_PATH_KEY ="picture_path";
    public static final String NOTE_PATH= "note";

    private final long id,m_carId ;
    private final float m_price;
    private final String m_date;
    private final int m_mileage;
    private final float m_price_per_liter;
    private final String m_fuel_station;
    private final String m_city;
    private final String m_picture_path;
    private final String m_note;

    public Fuel(long id,long carId,float price,String date,int mileage,float price_per_liter,String fuel_station,String city,String picture_path,String note){
        super();
        this.id  = id;
        m_carId =carId;
        m_price =price;
        m_date =date;
        m_mileage = mileage;
        m_price_per_liter =price_per_liter;
        m_fuel_station =fuel_station;
        //m_fuel = fuel;
        m_city= city;
        m_picture_path= picture_path;
        m_note = note;
    }

    public long getId() {
        return id;
    }

    public long getM_carId() {
        return m_carId;
    }

    public float getM_price() {
        return m_price;
    }

    public String getM_date() {
        return m_date;
    }

    public int getM_mileage() {
        return m_mileage;
    }

    public float getM_price_per_liter() {
        return m_price_per_liter;
    }

    public String getM_fuel_station() {
        return m_fuel_station;
    }


    public String getM_city() {
        return m_city;
    }

    public String getM_picture_path() {
        return m_picture_path;
    }

    public String getM_note() {
        return m_note;
    }


}
