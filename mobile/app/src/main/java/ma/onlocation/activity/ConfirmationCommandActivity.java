package ma.onlocation.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.kbenrafik.myapplication.R;

public class ConfirmationCommandActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_command);
    }

    public void goToDetailLocation(View v) {
        Intent intent = new Intent(v.getContext(), LocationDetailActivity.class);
        this.startActivity(intent);
        finish();
    }
}
