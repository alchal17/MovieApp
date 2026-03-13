package com.example.core.domain.usecases

import com.example.core.domain.repositories.InnerStorageRepository
import com.example.core.domain.repositories.SharedPreferencesKeyNames
import javax.inject.Inject

class SetColumnsNumberUseCase @Inject constructor(private val innerStorageRepository: InnerStorageRepository) {
    suspend operator fun invoke(number: Int) {
        innerStorageRepository.setInt(SharedPreferencesKeyNames.COLUMNS_NUMBER, number)
    }
}