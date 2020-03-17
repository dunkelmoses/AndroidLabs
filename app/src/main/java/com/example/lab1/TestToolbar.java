package com.example.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class TestToolbar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar tBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);

        tBar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(tBar);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout, tBar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);


	    /* slide 15 material:
	    MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchView sView = (SearchView)searchItem.getActionView();
        sView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }  });
	    */

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String message = null;
        //Look at your menu XML file. Put a case for every id in that file:
        switch (item.getItemId()) {
            //what to do when the menu item is selected:
            case R.id.facebook:
                message = "You clicked on facebook";
                break;
            case R.id.instagram:
                message = "You clicked on instagram";
                break;
            case R.id.twitter:
                message = "You clicked on twitter";
                break;
            case R.id.Chat_Page:
                message = "You clicked on Chat Page";
                break;
            case R.id.Weather_Forecast:
                message = "You clicked on Weather Forecast";
                break;
            case R.id.Go_back_to_login:
                message = "You clicked on Login";
                break;
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        return true;
    }


    // Needed for the OnNavigationItemSelected interface:
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        String message = null;

        switch (item.getItemId()) {
            case R.id.Chat_Page:
                Intent chatPage = new Intent(TestToolbar.this, ChatRoomActivity.class);
                startActivity(chatPage);
                break;
            case R.id.Weather_Forecast:
                Intent weatherForecast = new Intent(TestToolbar.this, WeatherForecast.class);
                startActivity(weatherForecast);
                break;
            case R.id.Go_back_to_login:
                Intent  backToLogin = new Intent(TestToolbar.this, MainActivity.class);
                startActivity(backToLogin);
                break;
            case R.id.search_item:
                message = "You clicked on the search";
                break;
            case R.id.facebook:
                message = "You clicked on facebook";
                break;
            case R.id.instagram:
                message = "You clicked on instagram";
                break;
            case R.id.twitter:
                message = "You clicked on twitter";
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

        Toast.makeText(this, "NavigationDrawer: " + message, Toast.LENGTH_LONG).show();
        return false;
    }
}