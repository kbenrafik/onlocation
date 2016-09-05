package ma.onlocation.activity;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.NetworkImageView;
import com.example.kbenrafik.myapplication.R;

import java.util.ArrayList;

import ma.onlocation.adapter.CommentListAdapter;
import ma.onlocation.controller.DispatcherController;
import ma.onlocation.model.Location;
import ma.onlocation.model.Product;
import ma.onlocation.util.VolleySingleton;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocationDetailActivity extends ActionBarActivity {

    Location location;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_location_activity);

        location = (Location) getIntent().getParcelableExtra("currentPlace");

        //location.setComments(getComments());
        TextView t = (TextView) findViewById(R.id.category);
        t.setText(location.getCategoryLocation().getName());

        t = (TextView) findViewById(R.id.description);
        t.setText(location.getName());

        t = (TextView) findViewById(R.id.phone);
        if (TextUtils.equals(location.getPhone(), "null")) {
            t.setText("");
        } else {
            t.setText(location.getPhone());
        }
        t = (TextView) findViewById(R.id.adresse);
        if (TextUtils.equals(location.getAdresse(), "null")) {
            t.setText("");
        } else {
            t.setText(location.getAdresse());
        }

        if (location.getComments() != null) {
            t = (TextView) findViewById(R.id.commentDetailLocation);
            t.setText(String.valueOf(location.getComments().size()));
        }

        if (location.getLikes() != null) {
            t = (TextView) findViewById(R.id.likePageDetail);
            if (location.getComments().size() == 4)
                t.setText(String.valueOf(3));
            else
                t.setText(String.valueOf(location.getLikes().size()));
        }

        /*---------Photo----------*/
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        String urlImage = "";
        if (!location.getPhotos().isEmpty()) {
            urlImage = location.getPhotos().get(0).getPrefix() + width + "x" + getString(R.string.image_detail_location) + location.getPhotos().get(0).getSuffix();
        }
        Log.e("urlImage", urlImage);
        if (urlImage != "") {
            NetworkImageView imgV = (NetworkImageView) findViewById(R.id.photoLocationDetail);
            imgV.setImageUrl(urlImage, VolleySingleton.getInstance(this).getImageLoader());
        }

        setTitle(location.getName());

        /*---Commentaires---*/
        final ListView listview = (ListView) findViewById(R.id.commentsList);
        listview.setClickable(false);
        CommentListAdapter adapter = new CommentListAdapter(this, location.getComments(), R.layout.comment_item_view);

        listview.setAdapter(adapter);

    }


    @Override
    public void supportNavigateUpTo(android.content.Intent upIntent) {
        ArrayList<Location> locationItems = getIntent().getParcelableArrayListExtra("listLocations");
        upIntent.putParcelableArrayListExtra("listLocations", locationItems);

        upIntent.setClass(this, LocationsListActivity.class);

        this.startActivity(upIntent);
        finish();
        //Toast.makeText(this, "go to main ", Toast.LENGTH_LONG).show();
    }

    public void goToCommentForm(View view) {
        Intent intent = new Intent(view.getContext(), CommentFormActivity.class);
        ArrayList<Location> locationItems = getIntent().getParcelableArrayListExtra("listLocations");
        intent.putParcelableArrayListExtra("listLocations", locationItems);
        intent.putExtra("goToActivity", "LocationDetailActivity");
        intent.putExtra("currentPlace", location);

        DispatcherController dispatcherController = new DispatcherController(this, intent, view.getContext());
        dispatcherController.doDispatcher();


        //this.startActivity(intent);
        //finish();
    }

    public void goToProductsList(View view) {
        Intent intent = new Intent(view.getContext(), ProductsActivity.class);
        intent.putExtra("currentPlace", location);
        this.startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (!location.getProducts().isEmpty())
            getMenuInflater().inflate(R.menu.menu_detail_location, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_produits) {
            goToProductsList(findViewById(R.id.action_produits));
        }

        return super.onOptionsItemSelected(item);
    }

}