package com.gemidroid.di

import com.gemidroid.data.datasource.network.PopularPersonsAPI
import com.gemidroid.data.repository.PopularPersonsRepository
import com.gemidroid.data.repository.PopularPersonsRepositoryImpl
import com.gemidroid.domain.PopularPersonsUseCase
import com.gemidroid.presentation.PopularPersonsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val popularPersonsModule = module {
    single { get<Retrofit>().create(PopularPersonsAPI::class.java) }
    factory<PopularPersonsRepository> { PopularPersonsRepositoryImpl(get()) }
    factory { PopularPersonsUseCase(get(), get(), get()) }
    viewModel { PopularPersonsViewModel(get()) }
}
