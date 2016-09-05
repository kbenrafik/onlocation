package ma.onlocation.util.listeners;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.android.volley.Response.Listener;

import org.json.JSONObject;

import java.util.List;

import ma.onlocation.model.Location;
import ma.onlocation.util.LocationJSONUtils;

/**
 * JSON Response listener
 *
 * @author itcuties
 */
public class LocationJSONResponseListener implements Listener<JSONObject> {
    // Main activity context reference
    private Context context;

    // JSON feed items
    public List<Location> items;

    // List adapter reference
    public BaseAdapter adapter;

    // Initialize all the attributes
    public LocationJSONResponseListener(Context context, BaseAdapter adapter, List<Location> items) {
        this.context = context;
        this.items = items;
        this.adapter = adapter;
    }

    // Called when JSON data is ready to be parsed
    public void onResponse(JSONObject response) {
        // Parse JSON results
        LocationJSONUtils.fillList(response, items);
        // Notify the list adapter that the item list is ready
        adapter.notifyDataSetChanged();
        // Display quick info to the user about the success
        Toast.makeText(context, "Loading done !", Toast.LENGTH_LONG).show();
    }

    public List<Location> getItems() {
        return items;
    }
}
