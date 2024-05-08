package pl.careaboutit.ceidgapp.data.model

data class CompanyListState(
    val companyList: List<CompanyElementDisplayable>,
    val isLoading: Boolean,
    val error: String?
)