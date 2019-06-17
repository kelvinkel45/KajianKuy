package com.vcode.kajiankuy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {

    private Button btSignUp;
    private TextView btMasuk;
    private EditText etEmail;
    private EditText etPassword;

    private FirebaseAuth fAuth;
    private FirebaseAuth.AuthStateListener fStateListener;

    private static final String TAG = SignUp.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();
        /**
         * Inisialisasi Firebase Auth
         */
        fAuth = FirebaseAuth.getInstance();

        /**
         * Cek apakah ada user yang sudah login
         */
        fStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User sedang login
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User sedang logout
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        btSignUp = (Button) findViewById(R.id.btn_signup);

        btMasuk = findViewById(R.id.btn_masuk);
        btMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this, Login.class);
                startActivity(i);
            }
        });

        etEmail = (EditText) findViewById(R.id.email);
        etPassword = (EditText) findViewById(R.id.password);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Lempar email dan password ketika tombol signup diklik
                 */
                signUp(etEmail.getText().toString(), etPassword.getText().toString());
            }
        });

    }

    private void signUp(final String email, String password){
        if (TextUtils.isEmpty(email) | TextUtils.isEmpty(password)) {

            Toast.makeText(SignUp.this, "Silahkan isi email atau password dahulu", Toast.LENGTH_SHORT).show();

        } else {

            fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                            /**
                             * Jika sign in gagal, tampilkan pesan ke user. Jika sign in sukses
                             * maka auth state listener akan dipanggil dan logic untuk menghandle
                             * signed in user bisa dihandle di listener.
                             */
                            if (!task.isSuccessful()) {
                                task.getException().printStackTrace();
                                Toast.makeText(SignUp.this, "Proses Pendaftaran Gagal",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUp.this, "Proses Pendaftaran Berhasil\n" +
                                                "Email "+email,
                                        Toast.LENGTH_SHORT).show();
                            }

                            // rest of code
                        }
                    });

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(fStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (fStateListener != null) {
            fAuth.removeAuthStateListener(fStateListener);
        }
    }
}

