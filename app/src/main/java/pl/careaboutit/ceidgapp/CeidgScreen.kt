package pl.careaboutit.ceidgapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import pl.careaboutit.ceidgapp.ui.screens.SearchByNipResultScreen
import pl.careaboutit.ceidgapp.ui.screens.SearchByNipScreen
import pl.careaboutit.ceidgapp.viewmodels.CompanyViewModel

enum class CeidgScreen(@StringRes val title: Int) {
    SearchByNip(title = R.string.app_name),
    SearchByNipResult(title = R.string.text_result)
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
                        contentDescription = stringResource(R.string.back_btn)
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
        backStackEntry?.destination?.route ?: CeidgScreen.SearchByNip.name
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
            startDestination = CeidgScreen.SearchByNip.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = CeidgScreen.SearchByNip.name) {
                SearchByNipScreen(navController = navController, viewModel = viewModel)
            }
            composable(route = CeidgScreen.SearchByNipResult.name) {
                SearchByNipResultScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}