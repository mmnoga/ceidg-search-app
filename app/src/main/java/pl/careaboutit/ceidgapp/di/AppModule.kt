package pl.careaboutit.ceidgapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.careaboutit.ceidgapp.ui.viewmodel.FormState
import pl.careaboutit.ceidgapp.ui.viewmodel.FormViewModel
import pl.careaboutit.ceidgapp.ui.viewmodel.PkdViewModel

val appModule = module {
    viewModel { (initialState: FormState) -> FormViewModel(initialState) }

    viewModel { PkdViewModel() }
}