package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var editTextPasswordChange : EditText
    private lateinit var editTextPasswordChangeRepeat : EditText
    private lateinit var buttonChange : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        init()
        registerListeners()
    }


    private fun init() {
        editTextPasswordChange = findViewById(R.id.editTextPasswordChange)
        editTextPasswordChangeRepeat = findViewById(R.id.editTextPasswordChangeRepeat)
        buttonChange = findViewById(R.id.buttonChange)
    }
    private fun registerListeners(){
        buttonChange.setOnClickListener(){
            val newPassword = editTextPasswordChange.text.toString()
            val repeatPass = editTextPasswordChangeRepeat.text.toString()

            if (newPassword.isEmpty() || repeatPass.isEmpty() || newPassword != repeatPass || newPassword.length < 8){
                Toast.makeText(this, "შეიყვანეთ პაროლი სწორად", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "პაროლი წარმატებით შეიცვალა", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()

                }else{
                    Toast.makeText(this, "შეიყვანეთ პაროლი სწორად", Toast.LENGTH_SHORT).show()

                }
            }

        }

    }
}

