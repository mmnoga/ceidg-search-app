package pl.careaboutit.ceidgapp.ui.navigation

import androidx.annotation.StringRes
import pl.careaboutit.ceidgapp.R

sealed class NavigationScreen(
    val route: String,
    @StringRes val resourceId: Int
) {
    data object Home :
        NavigationScreen("Home", R.string.app_name)

    data object SearchByNip :
        NavigationScreen("SearchByNip", R.string.search_by_nip)

    data object Details :
        NavigationScreen("Details", R.string.search_result)

    data object CompanyLocation :
        NavigationScreen("CompanyLocation", R.string.company_location)

    data object SearchByPkd :
        NavigationScreen("SearchByPkd", R.string.search_by_pkd)

    data object AdvancedSearch :
        NavigationScreen("AdvancedSearch", R.string.advanced_search)

    data object ListResult :
        NavigationScreen("ListResult", R.string.search_result)

    companion object {
        val allScreens: List<NavigationScreen>
            get() = listOf(
                Home, SearchByNip, Details, CompanyLocation,
                SearchByPkd, AdvancedSearch, ListResult
            )
    }

}