package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.security.AuthProvider

class Signup : AppCompatActivity() {

    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val btnSignup = findViewById<Button>(R.id.btnSignup)
        firebaseAuth = FirebaseAuth.getInstance()
        btnSignup.setOnClickListener{
            signupuser()
        }
        val signup = findViewById<TextView>(R.id.Signup)
        signup.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    private fun signupuser() {
        val emailSignup = findViewById<EditText>(R.id.etEmailAdress)
        val passSignup = findViewById<EditText>(R.id.etPassword)
        val confirmpassSignup = findViewById<EditText>(R.id.etconfirmpassword)

        val email : String =emailSignup.text.toString()
        val password :String =passSignup.text.toString()
        val confirmpassword = confirmpassSignup.text.toString()
        if (email.isBlank() || password.isBlank() || confirmpassword.isBlank()){
            Toast.makeText(this,"Email and password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }
        if (password != confirmpassword){
            Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if(it.isSuccessful){
                    Toast.makeText(this, "Login Succesful", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this, "Eroor creating user.",Toast.LENGTH_SHORT).show()
                }
            }
    }
}