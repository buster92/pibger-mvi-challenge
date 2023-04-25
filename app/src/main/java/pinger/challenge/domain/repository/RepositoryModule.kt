package com.vsch.mvi.domain.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import pinger.challenge.domain.repository.IPingerRepository
import pinger.challenge.domain.repository.PingerRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindTodoRepository(repository: PingerRepository): IPingerRepository

}