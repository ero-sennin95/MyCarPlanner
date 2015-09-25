package planner.car.dav.com.mycarplanner;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarStat extends Activity {
    ExpandableListView eLv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_stat_activity);

        List<Map<String, String>> groupData = null;
        groupData = new ArrayList<Map<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("ROOT_NAME", "Group 1");
            }});
            add(new HashMap<String, String>() {{
                put("ROOT_NAME", "Group 2");
            }});
        }};

        List<List<Map<String, String>>> listOfChildGroups = new ArrayList<List<Map<String, String>>>();

        List<Map<String, String>> childGroupForFirstGroupRow = new ArrayList<Map<String, String>>(){{
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 1");
            }});
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 1");
            }});
        }};
        listOfChildGroups.add(childGroupForFirstGroupRow);

        List<Map<String, String>> childGroupForSecondGroupRow = new ArrayList<Map<String, String>>(){{
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 2");
            }});
            add(new HashMap<String, String>() {{
                put("CHILD_NAME", "child in group 2");
            }});
        }};

        listOfChildGroups.add(childGroupForSecondGroupRow);
        SimpleExpandableListAdapter SAdapt= new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "ROOT_NAME" },
                new int[] { android.R.id.text1 },

                listOfChildGroups,
                android.R.layout.simple_expandable_list_item_2,
                new String[] { "CHILD_NAME", "CHILD_NAME" },
                new int[] { android.R.id.text1, android.R.id.text2 }
        );
        eLv = (ExpandableListView)this.findViewById(R.id.expandableListView);
        eLv.setAdapter(SAdapt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_car_stat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
