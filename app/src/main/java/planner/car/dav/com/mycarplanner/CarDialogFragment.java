package planner.car.dav.com.mycarplanner;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
/**
 * Created by Bascule on 09/09/2015.
 */
public class CarDialogFragment extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        String[] listitems = { "item01", "item02", "item03", "item04" };
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Choose car").setItems(listitems,new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                // The 'which' argument contains the index position
                // of the selected item
            }
        });




        return builder.create();
    }
}
