package com.example.settings.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.repositories.InnerStorageRepository
import com.example.core.domain.repositories.SharedPreferencesKeyNames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(private val innerStorageRepository: InnerStorageRepository) :
    ViewModel() {
    private val _currentColumnNumber = MutableStateFlow(3)
    val currentColumnNumber = _currentColumnNumber.asStateFlow()

    init {
        viewModelScope.launch {
            val savedColumnsNumber = innerStorageRepository.getInt(
                SharedPreferencesKeyNames.COLUMNS_NUMBER,
                3
            )

            _currentColumnNumber.update { savedColumnsNumber }
        }
    }

    fun setColumnsNumber(number: Int) {
        viewModelScope.launch {
            innerStorageRepository.setInt(
                SharedPreferencesKeyNames.COLUMNS_NUMBER,
                number
            )
        }
        _currentColumnNumber.update { number }
    }
}