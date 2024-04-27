package pl.careaboutit.ceidgapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import pl.careaboutit.ceidgapp.ui.screens.CompanyLocationScreen
import pl.careaboutit.ceidgapp.ui.screens.HomeMenuScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByNipResultScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByNipScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByPkdResultScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByPkdScreen

import pl.careaboutit.ceidgapp.ui.screens.common.CompanyDetailsScreen
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel
import pl.careaboutit.ceidgapp.viewmodels.LocationViewModel

sealed class NavigationScreens(
    val route: String,
    @StringRes val resourceId: Int
) {
    object Home :
        NavigationScreens("Home", R.string.app_name)

    object SearchByNip :
        NavigationScreens("SearchByNip", R.string.search_by_nip)

    object SearchByNipResult :
        NavigationScreens("SearchByNipResult", R.string.search_result)

    object CompanyDetails :
        NavigationScreens("CompanyDetails", R.string.company_details)

    object CompanyLocation :
        NavigationScreens("CompanyLocation", R.string.company_location)

    object SearchByPkd :
        NavigationScreens("SearchByPkd", R.string.search_by_pkd)

    object SearchByPkdResult :
        NavigationScreens("SearchByPkdResult", R.string.search_result)

    companion object {
        val allScreens: List<NavigationScreens>
            get() = listOf(Home, SearchByNip, SearchByNipResult, CompanyDetails, CompanyLocation)
    }
}

@Composable
private fun MainScreenNavigation(
    navController: NavHostController,
    companyViewModel: CompanyViewModel,
    locationViewModel: LocationViewModel
) {
    NavHost(navController, startDestination = NavigationScreens.Home.route) {
        composable(NavigationScreens.Home.route) {
            HomeMenuScreen(navController)
        }
        composable(NavigationScreens.SearchByNip.route) {
            SearchByNipScreen(navController, companyViewModel)
        }
        composable(NavigationScreens.SearchByNipResult.route) {
            SearchByNipResultScreen(navController, companyViewModel)
        }
        composable(route = NavigationScreens.CompanyDetails.route) {
            CompanyDetailsScreen(navController = navController, companyViewModel, locationViewModel)
        }
        composable(route = "${NavigationScreens.CompanyLocation.route}/{address}") { backStackEntry ->
            val address = backStackEntry.arguments?.getString("address")
            CompanyLocationScreen(locationViewModel = locationViewModel, address = address)
        }
        composable(route = NavigationScreens.SearchByPkd.route) {
            SearchByPkdScreen(navController = navController, viewModel = companyViewModel)
        }
        composable(route = NavigationScreens.SearchByPkdResult.route) {
            SearchByPkdResultScreen(navController = navController, viewModel = companyViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CeidgAppBar(
    navController: NavHostController,
    currentRoute: String?,
    titleResourceId: Int?,
    navigateUpEnabled: Boolean = currentRoute != NavigationScreens.Home.route
) {
    val title =
        titleResourceId?.let { stringResource(it) } ?: stringResource(id = R.string.app_name)

    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    if (navigateUpEnabled) {
                        navController.popBackStack()
                    }
                }
            ) {
                Icon(
                    imageVector = if (navigateUpEnabled) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Home,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun CeidgApp(
    companyViewModel: CompanyViewModel = viewModel(),
    locationViewModel: LocationViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val currentRoute = currentRoute(navController)
    val currentScreen = NavigationScreens.allScreens.find { it.route == currentRoute }

    val titleResourceId = currentScreen?.resourceId

    Scaffold(
        topBar = {
            val navigateUpEnabled = currentRoute != NavigationScreens.Home.route
            CeidgAppBar(
                navController = navController,
                currentRoute = currentRoute,
                titleResourceId = titleResourceId,
                navigateUpEnabled = navigateUpEnabled
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            MainScreenNavigation(
                navController = navController,
                companyViewModel = companyViewModel,
                locationViewModel = locationViewModel
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    return currentRoute?.substringBefore("/") ?: currentRoute
}