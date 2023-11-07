package com.example.librarypluss_gruppe09.firebaseservice

import com.example.librarypluss_gruppe09.firebaseservice.impl.StorageGoals
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideStorageService(impl: StorageGoals): GoalsService
}