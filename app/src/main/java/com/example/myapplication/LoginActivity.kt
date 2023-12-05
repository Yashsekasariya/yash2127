package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.provider.Telephony.Mms.Intents
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnLoginActivity = findViewById<Button>(R.id.Login)
        firebaseAuth = FirebaseAuth.getInstance()
        btnLoginActivity.setOnClickListener{
            login()
        }
        val signup = findViewById<TextView>(R.id.Signup)
        signup.setOnClickListener{
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }


    }
    private fun login(){
        val emailLoginActivity = findViewById<EditText>(R.id.etEmailadress)
        val passLoginActivity = findViewById<EditText>(R.id.etPassword)

        val email : String = emailLoginActivity.text.toString()
        val password : String = passLoginActivity.text.toString()

        if (email.isBlank() || password.isBlank() ){
            Toast.makeText(this,"Email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Authentication Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}