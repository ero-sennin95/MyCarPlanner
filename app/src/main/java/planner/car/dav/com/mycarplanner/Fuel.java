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
    private final String m_price,m_date,m_mileage,m_price_per_liter,m_fuel_station,m_fuel,m_city,m_picture_path,m_note;

    public Fuel(long id,long carId,String price,String date,String mileage,String price_per_liter,String fuel_station,String fuel,String city,String picture_path,String note){
        super();
        this.id  = id;
        m_carId =carId;
        m_price =price;
        m_date =date;
        m_mileage = mileage;
        m_price_per_liter =price_per_liter;
        m_fuel_station =fuel_station;
        m_fuel = fuel;
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

    public String getM_price() {
        return m_price;
    }

    public String getM_date() {
        return m_date;
    }

    public String getM_mileage() {
        return m_mileage;
    }

    public String getM_price_per_liter() {
        return m_price_per_liter;
    }

    public String getM_fuel_station() {
        return m_fuel_station;
    }

    public String getM_fuel() {
        return m_fuel;
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
