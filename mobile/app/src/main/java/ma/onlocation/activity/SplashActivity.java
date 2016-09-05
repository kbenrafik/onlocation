package ma.onlocation.activity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.kbenrafik.myapplication.R;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONObject;

import java.util.ArrayList;

import ma.onlocation.model.Location;
import ma.onlocation.util.LocationJSONUtils;
import ma.onlocation.util.VolleySingleton;

public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //LocationDetailAdapter adapter = new LocationDetailAdapter(this, itemsLocation, R.layout.item_list_view);

        setContentView(R.layout.splash_activity);

        new PrefetchData().execute();
        getSupportActionBar().hide();
        //getLocations(null);
        //Log.e("dsq", String.valueOf(itemsLocation.size()));


    }

    public SplashActivity getThis() {
        return this;
    }

    //After click to activity home
    public void goToListLocations(View view) {
        Intent intent = new Intent(view.getContext(), LocationsListActivity.class);
        this.startActivity(intent);
    }


    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        ArrayList<Location> items;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            JsonObjectRequest request = new JsonObjectRequest(
                    "http://demo9012237.mockable.io/pfe",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            items = new ArrayList<>();
                            LocationJSONUtils.fillList(response, items);
                            Intent intent = new Intent(SplashActivity.this, LocationsListActivity.class);
                            intent.putParcelableArrayListExtra("listLocations", items);
                            try {
                                Thread.sleep(3000);
                                // Do some stuff
                            } catch (Exception e) {
                                e.getLocalizedMessage();
                            }
                            startActivity(intent);
                            finish();
                            Log.e("splash", "finished");
                            // TODO: Parse the JSON
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("Unable to fetch data", "error" + volleyError.getMessage());
                        }
                    });

            VolleySingleton.getInstance(getThis()).getRequestQueue().add(request);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.e("splash", "postExecute");
        }
    }
}