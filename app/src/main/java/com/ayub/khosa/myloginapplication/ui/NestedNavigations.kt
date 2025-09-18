package com.ayub.khosa.myloginapplication.ui


import android.annotation.SuppressLint
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.ayub.khosa.myloginapplication.api.RetrofitBuilder
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.DashboardScreen
import com.ayub.khosa.myloginapplication.ui.screens.dashboard.DashboardViewModel
import com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.forgotpassword.ForgotPasswordScreen
import com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.forgotpassword.ForgotPasswordViewModel
import com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.login.LoginScreen
import com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.login.LoginViewModel
import com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.registration.RegistrationScreen
import com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.registration.RegistrationViewModel
import com.ayub.khosa.myloginapplication.utils.NetworkHelper

/**
 * Login, registration, forgot password screens nav graph builder
 * (Unauthenticated user)
 */
@SuppressLint("ViewModelConstructorInComposable")
fun NavGraphBuilder.unauthenticatedGraph(navController: NavController) {

    navigation(
        route = NavigationRoutes.Unauthenticated.NavigationRoute.route,
        startDestination = NavigationRoutes.Unauthenticated.Login.route
    ) {

        // Login
        composable(route = NavigationRoutes.Unauthenticated.Login.route) {


            val context = LocalContext.current
            val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)
            val repository: MainActivityRepository by lazy {
                val apiService = RetrofitBuilder.provideRestApiService(
                    RetrofitBuilder.getRetrofit(
                        RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                        RetrofitBuilder.providesBaseUrl()
                    )
                )
                MainActivityRepository(context.applicationContext, apiService)


            }

            val viewModel: LoginViewModel = LoginViewModel(repository, networkHelper)


            LoginScreen(
                viewModel,
                onNavigateToRegistration = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.Registration.route+"/${it}")
                },
                onNavigateToForgotPassword = {
                    navController.navigate(route = NavigationRoutes.Unauthenticated.ForgotPassword.route+"/${it}")
                },
                onNavigateToAuthenticatedRoute = {
                    navController.navigate(route = NavigationRoutes.Authenticated.Dashboard.route+"/${it}") {
                        popUpTo(route = NavigationRoutes.Unauthenticated.NavigationRoute.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

        // Registration
        composable(route = NavigationRoutes.Unauthenticated.Registration.route+"/{email}",
            arguments = (listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            ))) {
            val email = it.arguments!!.get("email")
            val context = LocalContext.current
            val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)
            val repository: MainActivityRepository by lazy {
                val apiService = RetrofitBuilder.provideRestApiService(
                    RetrofitBuilder.getRetrofit(
                        RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                        RetrofitBuilder.providesBaseUrl()
                    )
                )
                MainActivityRepository(context.applicationContext, apiService)


            }

            val viewModel: RegistrationViewModel = RegistrationViewModel(repository, networkHelper)

            RegistrationScreen(
                viewModel, email as String,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }





        // ForgotPassword
        composable(route = NavigationRoutes.Unauthenticated.ForgotPassword.route+"/{email}",
            arguments = (listOf(
                navArgument("email") {
                    type = NavType.StringType
                }
            ))) {
            val email = it.arguments!!.get("email")
            val context = LocalContext.current
            val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)
            val repository: MainActivityRepository by lazy {
                val apiService = RetrofitBuilder.provideRestApiService(
                    RetrofitBuilder.getRetrofit(
                        RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                        RetrofitBuilder.providesBaseUrl()
                    )
                )
                MainActivityRepository(context.applicationContext, apiService)


            }

            val viewModel: ForgotPasswordViewModel = ForgotPasswordViewModel(repository, networkHelper)

            ForgotPasswordScreen(
                viewModel, email as String,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

    }
}

/**
 * Authenticated screens nav graph builder
 */
@SuppressLint("ViewModelConstructorInComposable")
fun NavGraphBuilder.authenticatedGraph(navController: NavController) {
    navigation(
        route = NavigationRoutes.Authenticated.Dashboard.route,
        startDestination = NavigationRoutes.Authenticated.Dashboard.route+"/{email}"
    ) {
        // Dashboard
        composable(route = NavigationRoutes.Authenticated.Dashboard.route  +"/{email}",
            arguments = (listOf(
            navArgument("email") {
                type = NavType.StringType
            }
        ))) {
            val email = it.arguments!!.get("email")
            val context = LocalContext.current
            val networkHelper: NetworkHelper = NetworkHelper(context.applicationContext)
            val repository: MainActivityRepository by lazy {
                val apiService = RetrofitBuilder.provideRestApiService(
                    RetrofitBuilder.getRetrofit(
                        RetrofitBuilder.provideOkHttpClient(RetrofitBuilder.providesLoggingInterceptor()),
                        RetrofitBuilder.providesBaseUrl()
                    )
                )
                MainActivityRepository(context.applicationContext, apiService)


            }

            val viewModel: DashboardViewModel = DashboardViewModel(repository, networkHelper)

            DashboardScreen(viewModel,email as String , onNavigateBack = {
                navController.navigateUp()
            })
        }
    }
}