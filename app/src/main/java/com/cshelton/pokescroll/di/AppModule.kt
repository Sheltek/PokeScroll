package com.cshelton.pokescroll.di

import com.cshelton.pokescroll.domain.PokeRepository
import com.cshelton.pokescroll.domain.PokeRepositoryImpl
import com.cshelton.pokescroll.network.PokeService
import com.cshelton.pokescroll.network.PokeServiceImpl
import com.cshelton.pokescroll.network.createClient
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { createClient() }
    single<PokeService> { PokeServiceImpl() }
    single<PokeRepository> { PokeRepositoryImpl() }
}