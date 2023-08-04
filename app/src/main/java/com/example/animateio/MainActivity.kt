package com.example.animateio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.animateio.ui.presentation.screens.ContactDetail
import com.example.animateio.ui.presentation.screens.ContactList
import com.example.animateio.ui.theme.AnimateIOTheme

class MainActivity : ComponentActivity() {
    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val controller = rememberNavController()
            AnimateIOTheme {
                Scaffold(
                    contentWindowInsets = WindowInsets(
                        left = 16.dp,
                        right = 16.dp,
                        top = 32.dp
                    )
                ) { paddingValues ->

                    NavHost(navController = controller, startDestination = "contactList"){
                        composable("contactList"){
                            ContactList(
                                paddingValues = paddingValues,
                                controller = controller
                            )
                        }
                        composable("contactDetail/{id}"){ entry ->
                            val id = entry.arguments?.getString("id", "0")?.toInt()
                            ContactDetail(
                                paddingValues = paddingValues,
                                index = id!!
                            )
                        }
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AnimateIOTheme {

    }
}