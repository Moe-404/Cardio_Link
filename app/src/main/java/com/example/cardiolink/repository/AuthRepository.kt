package com.example.cardiolink.repository

import android.app.Application
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository(private val application: Application) {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser> = MutableLiveData()
    val userLoggedMutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    init {
        if (auth.currentUser != null) {
            firebaseUserMutableLiveData.postValue(auth.currentUser)
        }
    }


    fun register(email: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            } else {
                //make alert dialog to show error message
                Toast.makeText(application, task.exception?.message, Toast.LENGTH_SHORT).show()

            }
        }
    }

    fun login(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                firebaseUserMutableLiveData.postValue(auth.currentUser)
            } else {
                Toast.makeText(application, task.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData.postValue(true)
    }
}