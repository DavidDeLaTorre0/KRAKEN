package com.example.kraken.data.repository

import com.example.kraken.data.remote.FirebaseAuthService
import com.google.firebase.auth.FirebaseUser

class AuthRepository(private val authService: FirebaseAuthService) {

    suspend fun login(email: String, password: String): FirebaseUser? {
        return authService.login(email, password)
    }

    fun logout() {
        authService.logout()
    }
}
