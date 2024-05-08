package pl.careaboutit.ceidgapp.data.model

data class CompanyDetailsState(
    val companyList: List<CompanyDetailsElementDisplayable>,
    val isLoading: Boolean,
    val error: String?
)