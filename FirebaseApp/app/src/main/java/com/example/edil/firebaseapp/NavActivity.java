package com.example.edil.firebaseapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.edil.firebaseapp.CaptureActivityPortrait;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class NavActivity extends AppCompatActivity {


    private BottomNavigationView mainbottomNav;
    private FirebaseAuth mAuth;


    private NewsFragment newsFragment;
    private RoomsFragment roomsFragment;
    private ProfileFragment profileFragment;
    private Toolbar navToolbar;
    private ImageView mapImage;
    private ImageView searchImage;
    private Button btnBussiness;
    private  Button btnFriends;
    private ListView listView;
    private BottomNavigationView bottomNavigationView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        mapImage =findViewById(R.id.mapIcon);
        searchImage = findViewById(R.id.searchIcon);
        btnBussiness = findViewById(R.id.btnForBussiness);
        btnFriends = findViewById(R.id.bntForFriends);
        listView = findViewById(R.id.listViewMain);

        mainbottomNav = (BottomNavigationView) findViewById(R.id.mainBottomNav);
        navToolbar = (Toolbar) findViewById(R.id.nav_toolbar);
        mAuth = FirebaseAuth.getInstance();

        setSupportActionBar(navToolbar);

        getSupportActionBar().setTitle("Reservation");

        newsFragment = new NewsFragment();
        roomsFragment = new RoomsFragment();
        profileFragment = new ProfileFragment();

        initializeFragment();

        mainbottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.main_container);


                switch (item.getItemId()) {

                    case R.id.bottom_action_news:

                        replaceFragment(newsFragment, currentFragment);
                        return true;

                    case R.id.bottom_action_roomlist:

                        replaceFragment(roomsFragment, currentFragment);
                        return true;

                    case R.id.bottom_action_scan:

                        scan();

                    case R.id.bottom_action_Mine:

                        replaceFragment(profileFragment, currentFragment);
                        return true;

                    default:
                        return false;


                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser==null){

            sendToLogin();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action_logoutBtn:

                logout();
                return true;

            case R.id.action_scan:
                scan();
                return true;

        }
        return false;
    }

    private void logout() {

        mAuth.signOut();
        sendToLogin();

    }


    private void initializeFragment(){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.main_container, newsFragment);
        fragmentTransaction.add(R.id.main_container, roomsFragment);
        fragmentTransaction.add(R.id.main_container, profileFragment);

        fragmentTransaction.hide(roomsFragment);
        fragmentTransaction.hide(profileFragment);

        fragmentTransaction.commit();

    }




    private void replaceFragment(Fragment fragment, Fragment currentFragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if(fragment == newsFragment){

            getSupportActionBar().setTitle("Reservation");

            fragmentTransaction.hide(roomsFragment);
            fragmentTransaction.hide(profileFragment);

        }

        if(fragment == roomsFragment){

            getSupportActionBar().setTitle("Room List");

            fragmentTransaction.hide(newsFragment);
            fragmentTransaction.hide(profileFragment);

        }

        if(fragment == profileFragment){

            getSupportActionBar().setTitle("My Profile");

            fragmentTransaction.hide(newsFragment);
            fragmentTransaction.hide(roomsFragment);

        }
        fragmentTransaction.show(fragment);

        //fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();

    }


    private void scan() {

        IntentIntegrator integrator = new IntentIntegrator(NavActivity.this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setOrientationLocked(true);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.initiateScan();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){

            if(result.getContents() == null) {

                Toast.makeText(NavActivity.this,"You cancelled the scanning.",Toast.LENGTH_LONG).show();

            }else{

                Toast.makeText(NavActivity.this,result.getContents(),Toast.LENGTH_LONG).show();

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void sendToLogin() {

        startActivity(new Intent(NavActivity.this,LoginActivity.class));
        finish();

    }
}

