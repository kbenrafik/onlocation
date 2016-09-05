package ma.onlocation.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;

import ma.onlocation.adapter.LocationDetailAdapter;

import com.example.kbenrafik.myapplication.R;

import java.util.ArrayList;

import ma.onlocation.model.Location;
import ma.onlocation.util.listeners.itemLocationClickListener;

public class LocationsListActivity extends ActionBarActivity {

    ArrayList<Location> jsonItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.locations_list_activity);


        if (getIntent().getParcelableArrayListExtra("listLocations") == null) {
            jsonItems = new ArrayList<Location>();
        } else {
            Log.e("tro", String.valueOf(getIntent().getParcelableArrayListExtra("listLocations").size()));
            jsonItems = getIntent().getParcelableArrayListExtra("listLocations");
        }

        final ListView listview = (ListView) findViewById(R.id.listview);

        if (listview.getAdapter() != null) {
            Log.e("image dim", listview.getAdapter().getItem(0).toString());
        }

        LocationDetailAdapter adapter = new LocationDetailAdapter(this, jsonItems, R.layout.item_list_view);

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new itemLocationClickListener(jsonItems, this, listview));
    }
/*
    public void getLocations(LocationDetailAdapter adapter) {

        RequestQueue requestQueue = VolleySingleton.getInstance(this).getRequestQueue();

        //requestQueue = Volley.newRequestQueue(this);
        LocationJSONResponseListener t=new LocationJSONResponseListener(this, adapter, jsonItems);

        // Read JSON data
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "http://demo6340231.mockable.io/pfe",
                null,
                t,
                new LocationJSONResponseErrorListener(this));

        int socketTimeout = 100000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);

        Log.e("siiize", String.valueOf(t.getItems().size()));
    }
    */
}