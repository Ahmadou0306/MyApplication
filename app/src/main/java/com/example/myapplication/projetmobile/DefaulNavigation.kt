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
import com.example.myapplication.projetmobile.ui.welcome.AppDescriptionPage
import com.example.myapplication.projetmobile.ui.welcome.PresentationFinished
import com.example.myapplication.projetmobile.ui.welcome.WelcomePage

sealed class NavRouteDefault(val route: String) {
    object WelcomePage : NavRouteDefault("WelcomePage")
    object AppDescriptionPage:NavRouteDefault("NavRouteDefault")
    object PresentationFinished:NavRouteDefault("PresentationFinished")
}

@Composable
fun DefaultNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRouteDefault.WelcomePage.route,
    ) {
        //ROUTE HOME
        composable(NavRouteDefault.WelcomePage.route) {
            WelcomePage(
                onNext = {
                    navController.navigate(NavRouteDefault.AppDescriptionPage.route)
                }
            )
        }

        composable(NavRouteDefault.AppDescriptionPage.route) {
           AppDescriptionPage (
               onNext = {
                   navController.navigate(NavRouteDefault.PresentationFinished.route)
               }
           )
        }
        composable(NavRouteDefault.PresentationFinished.route) {
            PresentationFinished(
                onMenuRoute = {
                    navController.navigate(NavRoute.Home.route)
                }
            )
        }

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
                    navController.navigate(NavRoute.Home.route)
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