package pl.careaboutit.ceidgapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import pl.careaboutit.ceidgapp.ui.screens.HomeMenuScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByNipResultScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByNipScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByPkdResultScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByPkdScreen
import pl.careaboutit.ceidgapp.ui.screens.common.CompanyDetailsScreen
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

enum class CeidgScreen(@StringRes val title: Int) {
    HomeMenu(title = R.string.app_name),
    SearchByNip(title = R.string.search_by_nip),
    SearchByNipResult(title = R.string.text_result),
    SearchByPkd(title = R.string.search_by_pkd),
    SearchByPkdResult(title = R.string.text_result),
    CompanyDetails(title = R.string.text_details)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CeidgAppBar(
    currentScreen: CeidgScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_icon_description)
                    )
                }
            } else {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = stringResource(R.string.home_icon_description)
                    )
                }
            }
        }
    )
}

@Composable
fun CeidgApp(
    viewModel: CompanyViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CeidgScreen.valueOf(
        backStackEntry?.destination?.route ?: CeidgScreen.HomeMenu.name
    )

    Scaffold(
        topBar = {
            CeidgAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CeidgScreen.HomeMenu.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = CeidgScreen.HomeMenu.name) {
                HomeMenuScreen(navController = navController)
            }
            composable(route = CeidgScreen.SearchByNip.name) {
                SearchByNipScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = CeidgScreen.SearchByNipResult.name) {
                SearchByNipResultScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = CeidgScreen.SearchByPkd.name) {
                SearchByPkdScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = CeidgScreen.SearchByPkdResult.name) {
                SearchByPkdResultScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = CeidgScreen.CompanyDetails.name) {
                CompanyDetailsScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}