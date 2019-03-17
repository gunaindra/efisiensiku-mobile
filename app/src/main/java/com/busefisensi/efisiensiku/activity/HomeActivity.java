package com.busefisensi.efisiensiku.activity;

import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBarTopEdgeTreatment;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.busefisensi.efisiensiku.Fragment.AkunFragment;
import com.busefisensi.efisiensiku.Fragment.BantuanFragment;
import com.busefisensi.efisiensiku.Fragment.BookingFragment;
import com.busefisensi.efisiensiku.Fragment.HistoryFragment;
import com.busefisensi.efisiensiku.R;

public class HomeActivity extends AppCompatActivity {

    public TextView tvToolbar;
//    public static final String FRAGMENT_HOME;
//    public static final String FRAGMENT_OTHER;

    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        tvToolbar = (TextView)findViewById(R.id.tvToolbar);

        tvToolbar.setText("Akun");
        navigation.setSelectedItemId(R.id.nav_akun);
        loadFragment(new AkunFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()


    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.nav_home:
                    tvToolbar.setText("Home");
//                    fragment = new BookingFragment();
                    loadFragment(new BookingFragment());
                    return true;
                case R.id.nav_akun:
                    tvToolbar.setText("Akun");
//                    fragment = new AkunFragment();
                    loadFragment(new AkunFragment());
                    return true;
                case R.id.nav_history:
                    tvToolbar.setText("History");
//                    fragment = new HistoryFragment();
                    loadFragment(new HistoryFragment());
                    return true;
                case R.id.nav_bantuan:
                    tvToolbar.setText("Bantuan");
//                    fragment = new BantuanFragment();
                    loadFragment(new BantuanFragment());
                    return true;
            }
            return false;
        }
    };


    private void loadFragment(Fragment fragment){
        final FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
//       transaction.addToBackStack("FRAGMENT_HOME");
        transaction.commit();
    }

    public void onBackPressed(){
        if (navigation.getSelectedItemId() == R.id.nav_home){
            super.onBackPressed();
        }else {
            navigation.setSelectedItemId(R.id.nav_home);
            tvToolbar.setText("Home");
        }
    }
}