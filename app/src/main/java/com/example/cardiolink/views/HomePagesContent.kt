package com.example.cardiolink.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ScreenContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.clickable { onClick() },
            text = name,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

//@Composable
//fun HomeContent(name: String) {
//
//}
//fun ScanContent(name: String) {
//
//}
//fun ChatContent(name: String) {
//
//}
//fun HistoryContent(name: String) {
//}
//@Composable
//fun ProfileContent(name: String, onClick: () -> Unit) {
//    //val authViewModel: AuthViewModel = viewModel()
//    Button(
//        onClick = {
//            //authViewModel.signOut()
//        },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Text(text = "Sign Out")
//    }
//}