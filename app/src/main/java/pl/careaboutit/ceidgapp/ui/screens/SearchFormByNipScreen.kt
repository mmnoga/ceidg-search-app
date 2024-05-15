package pl.careaboutit.ceidgapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import pl.careaboutit.ceidgapp.R
import pl.careaboutit.ceidgapp.ui.navigation.NavigationScreen
import pl.careaboutit.ceidgapp.ui.screens.components.CustomButton
import pl.careaboutit.ceidgapp.ui.screens.components.CustomText
import pl.careaboutit.ceidgapp.ui.screens.components.CustomTextField
import pl.careaboutit.ceidgapp.ui.viewmodel.FormState
import pl.careaboutit.ceidgapp.ui.viewmodel.FormViewModel
import pl.careaboutit.ceidgapp.utils.Utils.isNipValid
import pl.careaboutit.ceidgapp.utils.buildQueryParamsFromObject
import timber.log.Timber

private val initialFormState = FormState(
    fields = mutableMapOf(
        "nip" to ""
    )
)

@Composable
fun SearchFormByNipScreen(
    navController: NavHostController,
    viewModel: FormViewModel = koinViewModel { parametersOf(initialFormState) }
) {
    val formState = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier.height((LocalConfiguration.current.screenHeightDp / 5).dp)
        )

        CustomText(stringResource(id = R.string.text_search))

        Spacer(modifier = Modifier.height(15.dp))

        CustomTextField(
            label = stringResource(id = R.string.nip),
            value = formState.fields["nip"] ?: "",
            onValueChange = {
                viewModel.updateField("nip", it)
            },
            keyboardType = KeyboardType.Number,
            isError = !isNipValid(viewModel.getField("nip").toString())
        )

        Spacer(modifier = Modifier.height(15.dp))

        CustomButton(
            text = stringResource(id = R.string.search_btn),
            onClick = {
                val queryParams = buildQueryParamsFromObject(formState)

                Timber.d("queryParams: $queryParams")

                navController.navigate("${NavigationScreen.ListResult.route}/$queryParams")
            },
            enabled = isNipValid(viewModel.getField("nip").toString())
        )
    }

}