package com.example.myapplication

import android.content.Intent
import android.icu.lang.UProperty.NAME
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myapplication.fragments.ProfileFragment
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail : EditText
    private lateinit var editTextPassword : EditText
    private lateinit var editTextPasswordRepeat : EditText
    private lateinit var buttonRegistration : Button




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()
        registerListeners()
    }






    private fun init() {
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextPasswordRepeat = findViewById(R.id.editTextPasswordRepeat)
        buttonRegistration = findViewById(R.id.buttonRegistration)



    }
    private fun registerListeners(){
        buttonRegistration.setOnClickListener{
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()
            val repeatPass = editTextPasswordRepeat.text.toString()



            if(email.isEmpty() || password.isEmpty() || repeatPass.isEmpty() || password != repeatPass || password.length < 8 || !(email.contains("@"))){
                Toast.makeText(this, "შეიყვანეთ პარამეტრები სწორად", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "თქვენ წარმატებით დარეგისტრირდით!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "შეიყვანეთ პარამეტრები სწორად", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }


}



