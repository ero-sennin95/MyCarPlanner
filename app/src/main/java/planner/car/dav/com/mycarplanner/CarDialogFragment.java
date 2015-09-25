package planner.car.dav.com.mycarplanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bascule on 09/09/2015.
 */
public class CarDialogFragment extends DialogFragment {
    //methode que la classe doit implementer

    public interface CarDialogListener{
            public void onChoiceSet(long id,String i);
            //public void onChoiceSet(CarMapping carMap);
        }

    // Use this instance of the interface to deliver action events
    CarDialogListener mListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(Acceuil.APP_TAG, "CarDialogFragment onAttach() : Debut");
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (CarDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()+ " must implement CarDialogListener");
        }
        Log.i(Acceuil.APP_TAG, "CarDialogFragment onAttach() : Fin");

    }
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
      //  String[] listitems = null;//{ "item01", "item02", "item03", "item04" };
        //final String[] listitems = new DbManager(this.getActivity().getBaseContext()).listCarName();
        //On request la DB pour un vehicule contenant le nom + id
        final List<Vehicule> listNameId = new DbManager(this.getActivity().getBaseContext()).listCarNameId();
        int taille = listNameId.size();
        //Creation d'un String[] ds lequel on met les nom des vehicules
        final String[] cs = new String[taille];
        //Idem id
        final long[] id = new long[taille];
        for (int i= 0;i<listNameId.size();i++){
            cs[i] = listNameId.get(i).getName();
            id[i]= listNameId.get(i).getId();
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose car").setItems(cs,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                mListener.onChoiceSet(id[which],cs[which]);
                // The 'which' argument contains the index position
                // of the selected item
            }
        });

        return builder.create();
    }
}
