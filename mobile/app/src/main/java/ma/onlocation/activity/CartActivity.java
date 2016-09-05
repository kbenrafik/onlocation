package ma.onlocation.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.kbenrafik.myapplication.R;
import com.google.android.gms.wallet.Cart;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import ma.onlocation.model.Location;
import ma.onlocation.util.VolleySingleton;

public class CartActivity extends ActionBarActivity {

    ProgressDialog dialog;
    String url;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView name = (TextView) findViewById(R.id.nameProduct);
        TextView price = (TextView) findViewById(R.id.priceProduct);
        TextView tTotalPrice = (TextView) findViewById(R.id.totalPrice);

        String listName = "";
        String listPrice = "";
        double totalPrice = 0;

        url = getIntent().getStringExtra("url");
        data = getIntent().getStringExtra("data").split("#");
        for (String item : data) {
            String[] product = item.split(";");
            listName = listName + product[0] + " x " + product[1] + "\n";
            listPrice = listPrice + (Integer.parseInt(product[0]) * Double.parseDouble(product[2])) + " DH" + "\n";
            totalPrice = totalPrice + (Integer.parseInt(product[0]) * Double.parseDouble(product[2]));
        }
        name.setText(listName);
        price.setText(listPrice);
        tTotalPrice.setText(String.valueOf(totalPrice) + " DH");
    }

    public void doSubmitCommand(View view) {
        Log.e("Cart url", url);
        new PrefetchData(this).execute();
    }

    private class PrefetchData extends AsyncTask<Void, Void, Void> {
        private Context context;

        public PrefetchData(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            JsonObjectRequest request = new JsonObjectRequest(
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.e("cart", "finished");
                            String status = "";
                            try {
                                status = response.getString("status");
                                Log.e("cart status", "error" + status);
                            } catch (JSONException e) {
                                Log.e("cart status", "error" + e.getMessage());
                            }
                            try {
                                Thread.sleep(3000);
                                // Do some stuff
                            } catch (Exception e) {
                                e.getLocalizedMessage();
                            }
                            dialog.dismiss();
                            if (status.contains("OK")) {
                                Intent intent = new Intent(context, ConfirmationCommandActivity.class);
                                context.startActivity(intent);
                                ((Activity) context).finish();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Log.e("cart", "error" + volleyError.getMessage());
                        }
                    });

            VolleySingleton.getInstance(CartActivity.this).getRequestQueue().add(request);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            dialog = new ProgressDialog(CartActivity.this);
            dialog.setMessage("Chargement...");
            dialog.show();
        }
    }

}