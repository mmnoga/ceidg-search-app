package pl.careaboutit.ceidgapp.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.screens.AdvancedSearchScreen
import pl.careaboutit.ceidgapp.ui.screens.CompanyDetailsScreen
import pl.careaboutit.ceidgapp.ui.screens.CompanyLocationScreen
import pl.careaboutit.ceidgapp.ui.screens.HomeMenuScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByFiltersResultScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchFormByNipScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchFormByPkdScreen
import pl.careaboutit.ceidgapp.ui.viewmodel.AdvancedSearchViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.LocationViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.SearchByFiltersResultViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.SearchFormByNipViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.SearchFormByPkdViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainScreenNavigation(
    navController: NavHostController,
    locationViewModel: LocationViewModel,
    searchFormByNipViewModel: SearchFormByNipViewModel,
    searchFormByPkdViewModel: SearchFormByPkdViewModel,
    advancedSearchViewModel: AdvancedSearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = NavigationScreen.Home.route
    ) {
        composable(NavigationScreen.Home.route) {
            HomeMenuScreen(
                navController = navController
            )
        }

        composable(NavigationScreen.SearchByNip.route) {
            SearchFormByNipScreen(
                navController = navController,
                viewModel = searchFormByNipViewModel
            )
        }

        composable("${NavigationScreen.Details.route}/{id}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id") ?: ""

            CompanyDetailsScreen(companyId = id, navController = navController)
        }

        composable("${NavigationScreen.CompanyLocation.route}/{id}") { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getString("id") ?: ""

            CompanyLocationScreen(locationViewModel = locationViewModel, companyId = id)
        }

        composable("${NavigationScreen.ListResult.route}/{queryParams}") { navBackStackEntry ->
            val queryParams = navBackStackEntry.arguments?.getString("queryParams") ?: ""
            val viewModel = viewModel<SearchByFiltersResultViewModel>()

            SearchByFiltersResultScreen(
                viewModel = viewModel,
                queryParams = queryParams,
                navController = navController
            )
        }

        composable(NavigationScreen.SearchByPkd.route) {
            SearchFormByPkdScreen(
                navController = navController,
                viewModel = searchFormByPkdViewModel
            )
        }

        composable(route = NavigationScreen.AdvancedSearch.route) {
            AdvancedSearchScreen(
                navController = navController,
                viewModel = advancedSearchViewModel
            )

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CeidgAppBar(
    navController: NavHostController,
    currentRoute: String?,
    titleResourceId: Int?,
    navigateUpEnabled: Boolean = currentRoute != NavigationScreen.Home.route
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
                    imageVector = if (navigateUpEnabled) {
                        Icons.AutoMirrored.Filled.ArrowBack
                    } else {
                        Icons.Default.Home
                    },
                    contentDescription = null
                )
            }
        }
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CeidgApp(
    locationViewModel: LocationViewModel = viewModel(),
    searchFormByNipViewModel: SearchFormByNipViewModel = viewModel(),
    searchFormByPkdViewModel: SearchFormByPkdViewModel = viewModel(),
    advancedSearchViewModel: AdvancedSearchViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val currentRoute = currentRoute(navController)
    val currentScreen = NavigationScreen.allScreens.find { it.route == currentRoute }

    val titleResourceId = currentScreen?.resourceId

    Scaffold(
        topBar = {
            val navigateUpEnabled = currentRoute != NavigationScreen.Home.route
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
                locationViewModel = locationViewModel,
                searchFormByNipViewModel = searchFormByNipViewModel,
                searchFormByPkdViewModel = searchFormByPkdViewModel,
                advancedSearchViewModel = advancedSearchViewModel
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