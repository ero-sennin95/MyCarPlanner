package planner.car.dav.com.mycarplanner;

public class Vehicule {
    public static final String ID_KEY = "_id";
    public static final String NAME_KEY = "name";
    public static final String REGISTRATION_KEY = "registartion";
    public static final String FIRST_REGISTRATION_KEY = "first_registration";
    public static final String CONTROL_TECH_KEY = "control_tech";
    public static final String BRAND_KEY = "brand";
    public static final String MODEL_KEY = "model";
    public static final String FUEL_KEY = "fuel";
    public static final String MILEAGE_KEY = "mileage";
    public static final String AVERAGE_MILEAGE_KEY ="average_mileage";
    public static final String PICTURE_PATH= "picture_path";

    private final long id ;
    private final String name,registration,first_registration,control_tech,brand,model,fuel,mileage,average_mileage,picture_path;


    public Vehicule(long id,String name,String registration,String first_registration,String control_tech,String brand,String model,String fuel,String mileAge,String average_mileAge,String picture_path ){
        super();
        this.id=id;
        this.name=name;
        this.registration=registration;
        this.first_registration=first_registration;
        this.control_tech=control_tech;
        this.brand=brand;
        this.model=model;
        this.fuel=fuel;
        this.mileage=mileAge;
        this.picture_path=picture_path;
        this.average_mileage=average_mileAge;

    }


    @Override
    public String toString() {
        return "" + this.name + "\nBrand :" + this.brand+ "\nmodel :" + this.model  + "\nRegistration : " + this.registration + "\nMileage : " + this.mileage;

    }

    public String toList() {
        return "Vehicule [id=" + id + ", name=" + name + ", registration="
                + registration + ", brand=" + brand+ ", model=" + model ;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public String getRegistration() {
        return registration;
    }


    public String getFirst_registration() {
        return first_registration;
    }


    public String getControl_tech() {
        return control_tech;
    }


    public String getBrand() {
        return brand;
    }


    public String getModel() {
        return model;
    }


    public String getFuel() {
        return fuel;
    }


    public String getMileage() {
        return mileage;
    }


    public String getAverage_mileage() {
        return average_mileage;
    }


    public String getPicture_path() {
        return picture_path;
    }


}

