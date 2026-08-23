package com.rrb.selfprac.data

import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val goTrue: GoTrue
) {
    suspend fun signIn(email: String, password: String) {
        goTrue.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signUp(email: String, password: String) {
        goTrue.signUpWith(Email) {
            this.email = email
            this.password = password
        }
    }

    suspend fun signOut() {
        goTrue.signOut()
    }

    val currentUser get() = goTrue.currentSessionOrNull()?.user
}
