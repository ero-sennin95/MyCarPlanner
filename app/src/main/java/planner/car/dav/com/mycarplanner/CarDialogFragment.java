package planner.car.dav.com.mycarplanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Bascule on 09/09/2015.
 */
public class CarDialogFragment extends DialogFragment {
    //methode que la classe doit implementer

    public interface CarDialogListener{
            public void onChoiceSet(String i);
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
       // String[] listitems = { "item01", "item02", "item03", "item04" };
        final String[] listitems = new DbManager(this.getActivity().getBaseContext()).listCarName();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        builder.setTitle("Choose car").setItems(listitems,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                Log.i(Acceuil.APP_TAG, "onCreateDialog onClick listItems[whitch] : " + listitems[which]);
                mListener.onChoiceSet(listitems[which]);
                // The 'which' argument contains the index position
                // of the selected item
            }
        });

        return builder.create();
    }
}
