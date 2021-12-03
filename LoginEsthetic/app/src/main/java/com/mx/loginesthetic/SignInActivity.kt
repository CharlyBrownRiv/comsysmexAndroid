package com.mx.loginesthetic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mx.loginesthetic.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.signInAppCompatButton.setOnClickListener {
            val vmail=binding.emailEditText.text.toString()
            val vPassword=binding.passwordEditText.text.toString()

            when{
                vmail.isEmpty()||vPassword.isEmpty()->{
                    Toast.makeText(baseContext, "Correo o contraseña incorrecto.",
                        Toast.LENGTH_SHORT).show()
                }else ->{
                   SingIn(vmail,vPassword)
                }
            }

        }
    }

    private fun SingIn(email:String, password:String){

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("TAG", "signInWithEmail:success")
                    reload()
                } else {
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Correo o contraseña incorrecto.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun reload(){

        val intent=Intent(this, MainActivity::class.java)
        this.startActivity(intent)

    }

}