package com.example.myapplication.projetmobile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.projetmobile.ui.detailsProject.DetailHome
import com.example.myapplication.projetmobile.ui.home.Home
import com.example.myapplication.projetmobile.ui.member.AddMemberForm
import com.example.myapplication.projetmobile.ui.task.AddTaskScreen

sealed class NavRoute(val route: String) {
    object Home : NavRoute("Home")
    object AddTaskScreen : NavRoute("AddTaskScreen")
    object AddMemberForm:NavRoute("AddMemberForm")
    object DetailHome:NavRoute("DetailHome")
}

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
    ) {
        composable(NavRoute.Home.route) {
            Home(navController)
        }

        composable(NavRoute.AddTaskScreen.route) {
            AddTaskScreen{
                navController.navigate(NavRoute.AddTaskScreen.route)
            }
        }
        composable(NavRoute.DetailHome.route) {
            DetailHome{
                navController.navigate(NavRoute.DetailHome.route)
            }
        }

        composable(NavRoute.AddMemberForm.route) {
            AddMemberForm{
                navController.navigate(NavRoute.AddMemberForm.route)
            }
        }

        composable(NavRoute.AddTaskScreen.route) {
            DetailHome{
                navController.navigate(NavRoute.AddMemberForm.route)
            }
        }
    }
}