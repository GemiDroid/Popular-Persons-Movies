package com.gemidroid.di

import com.gemidroid.data.datasource.network.PersonDetailsAPI
import com.gemidroid.data.repository.PersonDetailsRepository
import com.gemidroid.data.repository.PersonDetailsRepositoryImpl
import com.gemidroid.domain.PersonDetailsUseCase
import com.gemidroid.presentation.PersonDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val personDetailsModule = module {
    single { get<Retrofit>().create(PersonDetailsAPI::class.java) }
    factory<PersonDetailsRepository> { PersonDetailsRepositoryImpl(get()) }
    factory { PersonDetailsUseCase(get(), get(), get()) }
    viewModel { PersonDetailsViewModel(get()) }
}
