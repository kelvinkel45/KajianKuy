package com.vcode.kajiankuy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText Email;

    private EditText Pw;

    private Button buttonlogin;

    private TextView buttondaftar;

    //private CheckBox ShowPass;


    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //ShowPass = findViewById(R.id.showPass);
        getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        buttondaftar = findViewById(R.id.btn_daftar);
        ((TextView) buttondaftar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });

        Email = (EditText) findViewById(R.id.email);

        Pw = (EditText) findViewById(R.id.password);

        buttonlogin = (Button) findViewById(R.id.btn_login);

        mAuth = FirebaseAuth.getInstance();



        mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {

                    startActivity(new Intent(Login.this, MainActivity.class) );
                }

            }

        };



        buttonlogin.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                startSignIn();

            }

        });

    }


    @Override
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

    protected void onStart() {

        super.onStart();



        mAuth.addAuthStateListener(mAuthListener);

    }



    private void startSignIn() {

        String email = Email.getText().toString();

        String password = Pw.getText().toString();



        if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {

            Toast.makeText(Login.this, "Silahkan isi email atau password dahulu", Toast.LENGTH_SHORT).show();

        } else {



            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {

                @Override

                public void onComplete(@NonNull Task task) {



                    if (!task.isSuccessful()) {

                        Toast.makeText(Login.this, "Email atau password anda salah", Toast.LENGTH_SHORT).show();

                    }



                }

            });

        }

    }



}