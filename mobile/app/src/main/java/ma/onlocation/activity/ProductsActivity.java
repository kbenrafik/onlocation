package ma.onlocation.activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.kbenrafik.myapplication.R;

import java.util.Map;

import ma.onlocation.adapter.ProductListAdapter;
import ma.onlocation.model.Location;

public class ProductsActivity extends ActionBarActivity {

    private Location location;
    ListView listView;
    ProductListAdapter adapter;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        location = (Location) getIntent().getParcelableExtra("currentPlace");

        listView = (ListView) findViewById(R.id.listProducts);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        adapter = new ProductListAdapter(this, location.getProducts(), R.layout.product_item_view);

        listView.setAdapter(adapter);

    }

    public void doCommander(View view) {
        url = "http://192.168.1.2:5555/onLocation_V5/orders/create?locationId=1236&userId=4";
        Log.e("location", String.valueOf(location.getLocationID()));
        String data = "";
        for (Map.Entry<Integer, String> entry : adapter.getProductsVsQuantity().entrySet()) {
            Integer key = entry.getKey();
            String[] value = entry.getValue().split(";");
            url = url + "&productId=" + key + "&quantity=" + value[0];
            data = data + entry.getValue() + "#";

            Log.e("key", String.valueOf(entry.getKey()));
            Log.e("value", String.valueOf(entry.getValue()));
        }

        Intent intent = new Intent(view.getContext(), CartActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("data", data);


        Log.e("products url", url);
        Log.e("products data", data);
        this.startActivity(intent);
        finish();

    }

}


