package com.ayub.khosa.myloginapplication.ui


sealed class NavigationRoutes {

    // Unauthenticated Routes
    sealed class Unauthenticated(val route: String) : NavigationRoutes() {
        object NavigationRoute : Unauthenticated(route = "unauthenticated")
        object Login : Unauthenticated(route = "login")
        object Registration : Unauthenticated(route = "registration")
        object ForgotPassword : Unauthenticated(route = "forgotpassword")
    }

    // Authenticated Routes
    sealed class Authenticated(val route: String) : NavigationRoutes() {

        object Dashboard : Authenticated(route = "Dashboard")
    }
}