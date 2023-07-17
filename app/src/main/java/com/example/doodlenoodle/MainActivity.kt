package com.example.doodlenoodle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.doodlenoodle.data.entities.Board
import com.example.doodlenoodle.data.entities.Doodle
import com.example.doodlenoodle.data.entities.User
import com.example.doodlenoodle.models.DoodleNoodleViewModel
import com.example.doodlenoodle.ui.*
import com.example.doodlenoodle.ui.components.DrawScreen
import com.example.doodlenoodle.ui.theme.DoodleNoodleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // create ViewModel and some test data
        val noodleViewModel = ViewModelProvider(this)[DoodleNoodleViewModel::class.java]


        setContent {

            // initialize the nav controller
            val navController = rememberNavController()

            DoodleNoodleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Create the hierarchy for the NavController used in the application
                    NavHost(navController = navController, startDestination = "welcomeScreen"){

                        // set routes to their corresponding composable

                        composable("welcomeScreen"){
                            ContentScreen(noodleViewModel,navController)
                        }
                        composable("boardList"){
                            BoardList(noodleViewModel,navController)
                        }
                        composable("joinScreen"){
                            JoinGroupScreen(noodleViewModel,navController)
                        }
                        composable("newScreen"){
                            CreateGroupScreen(noodleViewModel,navController)
                        }
                        composable("drawScreen"){
                            DrawScreen(noodleViewModel,navController)
                        }
                    }
                }
            }
        }
    }
}