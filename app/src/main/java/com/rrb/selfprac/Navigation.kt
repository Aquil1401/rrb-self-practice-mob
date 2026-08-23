package com.rrb.selfprac

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rrb.selfprac.features.auth.LoginScreen
import com.rrb.selfprac.features.exam.ExamScreen
import com.rrb.selfprac.features.tutor.TutorScreen
import com.rrb.selfprac.features.subscription.SubscriptionScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen()
        }
        composable("exam") {
            ExamScreen()
        }
        composable("tutor") {
            TutorScreen()
        }
        composable("subscription") {
            SubscriptionScreen()
        }
    }
}
