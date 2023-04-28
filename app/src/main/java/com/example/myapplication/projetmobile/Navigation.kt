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
import com.example.myapplication.projetmobile.ui.home.componant.projectCalendar
import com.example.myapplication.projetmobile.ui.project.AddFormProject

sealed class NavRoute(val route: String) {
    object Home : NavRoute("Home")

    object DetailHome:NavRoute("DetailHome")
    object AddFormProject:NavRoute("AddFormProject")
    object TasksChart:NavRoute("TasksChart")
    object projectCalendar:NavRoute("projectCalendar")
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
            Home(
                onNavigate = {project ->
                if(project==null){
                    navController.navigate(NavRoute.AddFormProject.route)
                }else{
                    navController.navigate(NavRoute.DetailHome.route+"/${project.id}")
                }
            },
               onHomeNavigate = {
                   navController.navigateUp()
               }
            )
        }

        //REDIRECT DETAILS VIEW

        composable(
            NavRoute.DetailHome.route+"/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
            ){backStackEntry->
            DetailHome(
                selectedId = backStackEntry.arguments?.getInt("id")?:1,
                onNavigateFloat = { project ->
                    if(project==null){
                        navController.navigate(NavRoute.AddFormProject.route)
                    }
                },
                onHomeNavigate = {
                    navController.navigateUp()
                }

            )
        }

        composable(
            NavRoute.TasksChart.route+"/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ){backStackEntry->
            TasksChart(id=backStackEntry.arguments?.getInt("id")?:1 )
        }

        //REDIRECT FORM ADD PROJECT

       composable(NavRoute.AddFormProject.route){
           AddFormProject(onFormNavigate =  {
               navController.navigate(NavRoute.AddFormProject.route)
           }, onNavigate = {project ->
               if(project==null){
                   navController.navigate(NavRoute.AddFormProject.route)
               }
           },
           onHomeNavigate = {
               navController.navigateUp()
           }
           )
       }
    }
}