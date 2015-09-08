package planner.car.dav.com.mycarplanner;

/**
 * Created by Bascule on 08/09/2015.
 */
public class Categorie {
    public static final String ID_KEY = "_id";
    public static final String TYPE_KEY = "type";

    private final long m_id ;
    private final String m_categorie;

    public Categorie(long id,String categorie){
        super();
        m_id=id;
        this.m_categorie=categorie;
    }

    public long getId() {
        return m_id;
    }

    public String getCategorie() {
        return m_categorie;
    }

    public String toString(){
        return "id : " + m_id + " categorie : " + m_categorie;
    }
}

