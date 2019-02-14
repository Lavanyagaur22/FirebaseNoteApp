package com.lavanya.firebase_kotlin.Activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.lavanya.firebase_kotlin.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val REQUEST_CODE_GOOGLE_SIGN_IN = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        FirebaseAuth.getInstance().addAuthStateListener {
            it.currentUser?.let {
                val intent = Intent(baseContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        //Button Login if the user already has an account

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
        }

        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
        }

        btnGoogle.setOnClickListener {
            val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(getString(R.string.oauth_id))
                .build()

            val googleSignInClient = GoogleSignIn.getClient(baseContext, googleSignInOptions)

            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, REQUEST_CODE_GOOGLE_SIGN_IN)
        }


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQUEST_CODE_GOOGLE_SIGN_IN){
            val accountTask = GoogleSignIn.getSignedInAccountFromIntent(data)

            val account = accountTask.result
            val credentials =GoogleAuthProvider.getCredential(account?.idToken,null)

            FirebaseAuth.getInstance().signInWithCredential(credentials)
        }
    }

}