package com.example.settings.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecases.GetColumnsNumberUseCase
import com.example.core.domain.usecases.SetColumnsNumberUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val getColumnsNumberUseCase: GetColumnsNumberUseCase,
    private val setColumnsNumberUseCase: SetColumnsNumberUseCase
) :
    ViewModel() {
    private val _currentColumnNumber = MutableStateFlow(3)
    val currentColumnNumber = _currentColumnNumber.asStateFlow()

    init {
        viewModelScope.launch {
            val savedColumnsNumber = getColumnsNumberUseCase()
            _currentColumnNumber.update { savedColumnsNumber }
        }
    }

    fun setColumnsNumber(number: Int) {
        viewModelScope.launch {
            setColumnsNumberUseCase(number)
        }
        _currentColumnNumber.update { number }
    }
}