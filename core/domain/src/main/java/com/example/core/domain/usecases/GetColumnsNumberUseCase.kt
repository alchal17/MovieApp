package com.example.core.domain.usecases

import com.example.core.domain.repositories.InnerStorageRepository
import com.example.core.domain.repositories.SharedPreferencesKeyNames
import javax.inject.Inject

class GetColumnsNumberUseCase @Inject constructor(private val innerStorageRepository: InnerStorageRepository) {
    suspend fun invoke(): Int =
        innerStorageRepository.getInt(SharedPreferencesKeyNames.COLUMNS_NUMBER)
}