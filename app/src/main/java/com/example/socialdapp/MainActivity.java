package com.example.socialdapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.socialdapp.fragment.FeedFragment;
import com.example.socialdapp.fragment.ProfileFragment;
import com.example.socialdapp.fragment.OrdersFragment;
import com.example.socialdapp.fragment.ViewGigFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        activity = this;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FeedFragment(activity)).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    FragmentManager manager = getSupportFragmentManager();
                    switch (menuItem.getItemId()){
                        case R.id.nav_home:
                            FeedFragment ff = new FeedFragment(activity);
                            manager.beginTransaction().replace(R.id.fragment_container, ff).commit();
                            break;
                        case R.id.nav_profile:
                            manager.beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                            break;
                        case R.id.nav_search:
                            manager.beginTransaction().replace(R.id.fragment_container, new OrdersFragment()).commit();
                            break;
                        default:
                    }
                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0)
            manager.popBackStackImmediate();
    }

    public void showViewFragment(int gigID, String title, String desc, int merchantID, int price){
        ViewGigFragment fragment = new ViewGigFragment();
        Bundle bundle = new Bundle();
        bundle.putString("gigTitle", title);
        bundle.putString("gigDesc", desc);
        bundle.putInt("merchantID", merchantID);
        bundle.putInt("price", price);
        bundle.putInt("gigID", gigID);
        fragment.setArguments(bundle);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}