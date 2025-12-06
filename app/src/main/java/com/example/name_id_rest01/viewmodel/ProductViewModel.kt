package com.example.name_id_rest01.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.name_id_rest01.api.ApiClient
import com.example.name_id_rest01.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> get() = _isError

    init {
        refreshProducts()
    }

    fun refreshProducts() {
        viewModelScope.launch {
            getProducts()
        }
    }

    private suspend fun getProducts() {
        try {
            _isLoading.value = true
            _isError.value = false

            val response = ApiClient.apiService.getProducts()
            if (response.isSuccessful) {
                _products.value = response.body() ?: emptyList()
            } else {
                _isError.value = true
            }

        } catch (e: Exception) {
            _isError.value = true
        } finally {
            _isLoading.value = false
        }
    }
}