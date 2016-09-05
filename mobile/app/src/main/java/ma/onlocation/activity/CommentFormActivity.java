package ma.onlocation.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kbenrafik.myapplication.R;

import java.util.ArrayList;

import ma.onlocation.model.Location;

public class CommentFormActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.comment_form_activity);

    }


    @Override
    public void supportNavigateUpTo(android.content.Intent upIntent) {
        ArrayList<Location> locationItems = getIntent().getParcelableArrayListExtra("listLocations");
        upIntent.putParcelableArrayListExtra("listLocations", locationItems);

        Location location = (Location) getIntent().getParcelableExtra("currentPlace");
        upIntent.putExtra("currentPlace", location);

        upIntent.setClass(this, LocationDetailActivity.class);

        this.startActivity(upIntent);
        finish();
        //Toast.makeText(this, "go to main ", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commenter, menu);
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