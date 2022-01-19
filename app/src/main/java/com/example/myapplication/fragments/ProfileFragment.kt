package com.example.myapplication.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.inflate
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.ChangePasswordActivity
import com.example.myapplication.LoginActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import org.w3c.dom.Text

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var buttonChangePassword: Button
    private lateinit var buttonLogOut: Button
    private lateinit var textViewEmail : TextView
    private lateinit var textViewUid : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        changeListeners()
        logOutListeners()

        textViewEmail.text = FirebaseAuth.getInstance().currentUser?.email
        textViewUid.text = FirebaseAuth.getInstance().currentUser?.uid


    }

    private fun init() {
        buttonChangePassword = requireView().findViewById(R.id.buttonChangePassword)
        buttonLogOut = requireView().findViewById(R.id.buttonLogOut)
        textViewEmail = requireView().findViewById(R.id.textViewEmail)
        textViewUid = requireView().findViewById(R.id.textViewUid)


    }

    private fun changeListeners() {
        buttonChangePassword.setOnClickListener {
            requireActivity().run { startActivity(Intent(this, ChangePasswordActivity::class.java)) }

        }
    }

    private fun logOutListeners() {
        buttonLogOut.setOnClickListener() {
            view?.let { it1 ->
                AlertDialog.Builder(it1.context)
                    .setTitle("გასვლა")
                    .setMessage("ნამდვილად გსურთ გასვლა?")
                    .setPositiveButton("დიახ") { dialog, i ->
                        FirebaseAuth.getInstance().signOut()
                        requireActivity().run { startActivity(Intent(this, LoginActivity::class.java)) }

                    }
                    .setNegativeButton("არა") { dialog, i ->
                        dialog.dismiss()

                    }
                    .show()
            }
        }

    }
}
