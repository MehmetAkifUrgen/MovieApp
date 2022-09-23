package com.example.movieapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityLoginBinding
import com.example.movieapp.databinding.ActivityMovieDetailsBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private var email:String? = null
    private var password:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance();
        // Initialize Firebase Auth
        email= binding.editTextTextEmailAddress2.text.toString()
        password=binding.editTextTextPassword2.text.toString()

        binding.button2.setOnClickListener(View.OnClickListener {
            signUp()
        })
    }

    /*override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        //        val currentUser: FirebaseUser = auth.getCurrentUser()!!

    }*/
    fun signUp(){
        auth.createUserWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("message", "createUserWithEmail:success")
                        val user: FirebaseUser = auth.getCurrentUser()!!
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("message", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }

                    // ...
                })
    }
    fun signIn(){
        auth.signInWithEmailAndPassword(email!!, password!!)
            .addOnCompleteListener(this,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("message", "signInWithEmail:success")
                        val user: FirebaseUser = auth.getCurrentUser()!!
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("message", "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@LoginActivity, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        //updateUI(null)
                    }

                    // ...
                })
    }

}