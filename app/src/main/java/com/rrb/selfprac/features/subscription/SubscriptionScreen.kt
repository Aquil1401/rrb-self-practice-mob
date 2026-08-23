package com.rrb.selfprac.features.subscription

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.razorpay.Checkout
import org.json.JSONObject

@Composable
fun SubscriptionScreen() {
    val context = LocalContext.current as Activity

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Get RRB JE Pro Pass", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))
        
        SubscriptionOption("Monthly Pass", "₹99") {
            startPayment(context, 9900, "Monthly Pass")
        }
        Spacer(modifier = Modifier.height(16.dp))
        SubscriptionOption("Exam Season Pass", "₹299") {
            startPayment(context, 29900, "Exam Season Pass")
        }
    }
}

@Composable
fun SubscriptionOption(title: String, price: String, onClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(title, style = MaterialTheme.typography.titleLarge)
                Text(price, style = MaterialTheme.typography.bodyLarge)
            }
            Button(onClick = onClick) {
                Text("Buy Now")
            }
        }
    }
}

private fun startPayment(activity: Activity, amount: Int, description: String) {
    val checkout = Checkout()
    checkout.setKeyID("YOUR_RAZORPAY_KEY")
    
    try {
        val options = JSONObject()
        options.put("name", "RRB JE Pro")
        options.put("description", description)
        options.put("theme.color", "#3399cc")
        options.put("currency", "INR")
        options.put("amount", amount)
        options.put("prefill.email", "aspirant@example.com")
        options.put("prefill.contact", "9876543210")
        
        checkout.open(activity, options)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
