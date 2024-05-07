package com.example.cardiolink.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.cardiolink.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthRepository(application)
    val userData: MutableLiveData<FirebaseUser> = repository.firebaseUserMutableLiveData
    val loggedStatus: MutableLiveData<Boolean> = repository.userLoggedMutableLiveData

    fun register(email: String, pass: String, param: (Any) -> Unit) {
        repository.register(email, pass)
    }

    fun signIn(email: String, pass: String, param: (Any) -> Unit) {
        repository.login(email, pass)
    }

    fun signOut() {
        repository.signOut()
    }
}
