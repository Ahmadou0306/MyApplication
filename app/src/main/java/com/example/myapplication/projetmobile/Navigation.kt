package com.example.myapplication.projetmobile

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.projetmobile.ui.detailsProject.DetailHome
import com.example.myapplication.projetmobile.ui.detailsProject.composable.TasksChart
import com.example.myapplication.projetmobile.ui.home.Home
import com.example.myapplication.projetmobile.ui.project.AddFormProject

sealed class NavRoute(val route: String) {
    object Home : NavRoute("Home")
    object AddTaskScreen : NavRoute("AddTaskScreen")
    object AddMemberForm:NavRoute("AddMemberForm")
    object DetailHome:NavRoute("DetailHome")
    object AddFormProject:NavRoute("AddFormProject")
    object TasksChart:NavRoute("TasksChart")
}

@Composable
fun NavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoute.Home.route,
    ) {
        //ROUTE HOME
        composable(NavRoute.Home.route) {
            Home(onNavigate = {project ->
                if(project==null){
                    navController.navigate(NavRoute.AddFormProject.route)
                }else{
                    navController.navigate(NavRoute.DetailHome.route+"/${project.id}")
                }
            })
        }

        //REDIRECT DETAILS VIEW

        composable(
            NavRoute.DetailHome.route+"/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
            ){backStackEntry->
            DetailHome(selectedId = backStackEntry.arguments?.getInt("id")?:1) {


            }
        }



        //REDIRECT FORM ADD PROJECT

       composable(NavRoute.AddFormProject.route){
           AddFormProject {
               navController.navigate(NavRoute.AddFormProject.route)
           }
       }





    }
}