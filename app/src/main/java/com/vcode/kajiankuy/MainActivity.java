package com.vcode.kajiankuy;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

//    private TextView signOut;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Mau Keluar yah ?")
                .setCancelable(false)
                .setPositiveButton("Iyah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);

                    }
                })
                .setNegativeButton("Tidak", null)
                .show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setIcon(R.drawable.tulisan);

        showWidget();

        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, Login.class));
                    finish();
                }
            }
        };
//logout
//        signOut = (TextView) findViewById(R.id.btn_logout);
//        signOut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Anda Telah Keluar ...", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, Login.class));
//                signOut();
//            }
//        });

//


    }

    private void showWidget() {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);
        RemoteViews remoteViews = new RemoteViews(MainActivity.this.getPackageName(), R.layout.widget);
        ComponentName thisWidget = new ComponentName(MainActivity.this, AppWidget.class);
        appWidgetManager.updateAppWidget(thisWidget, remoteViews);
    }

    //sign out method
    public void signOut() {
        auth.signOut();
    }



    public void btnDaftar(View view) {
        Intent daftar = new Intent(MainActivity.this, KajianActivity.class);
        startActivity(daftar);
    }

    public void btnFavorite(View view) {
        Intent favorite = new Intent(MainActivity.this, FavoriteActivity.class);
        startActivity(favorite);
    }

    public void btnBantuan(View view) {
        Intent bantuan = new Intent(MainActivity.this, BantuanActivity.class);
        startActivity(bantuan);
    }

    public void btnTentang(View view) {
        Intent tentang = new Intent(MainActivity.this, TentangActivity.class);
        startActivity(tentang);
    }
}
