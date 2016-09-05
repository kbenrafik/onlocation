package ma.onlocation.util.listeners;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import ma.onlocation.activity.LocationDetailActivity;

import java.util.ArrayList;

import ma.onlocation.model.Location;

/**
 * Class implements a list listener
 *
 * @author itcuties
 */
public class itemLocationClickListener implements OnItemClickListener {

    // List item's reference
    ArrayList<Location> listItems;
    // Calling activity reference
    Activity activity;
    ListView listview;

    public itemLocationClickListener(ArrayList<Location> aListItems, Activity anActivity, ListView _listview) {
        listItems = aListItems;
        activity = anActivity;
        this.listview = _listview;
    }

    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        Intent intent = new Intent(view.getContext(), LocationDetailActivity.class);
        Location location = (Location) listview.getAdapter().getItem(pos);
        //intent.putExtra("placeName",place);
        //Bundle mBundle = new Bundle();
        //mBundle.putParcelable("currentPlace", location);
        intent.putExtra("currentPlace", location);

        intent.putParcelableArrayListExtra("listLocations", listItems);

        //intent.putExtras(mBundle);
        activity.startActivity(intent);
    }

}