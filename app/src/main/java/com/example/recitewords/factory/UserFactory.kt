package com.example.recitewords.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recitewords.repository.UserRepository
import com.example.recitewords.viewmodel.UserViewModel

class UserFactory(
    private val repository: UserRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserViewModel(
            repository
        ) as T
    }
}