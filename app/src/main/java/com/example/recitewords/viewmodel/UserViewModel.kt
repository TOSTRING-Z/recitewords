package com.example.recitewords.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recitewords.data.table.User
import com.example.recitewords.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val mDayWordsNum by lazy {
        repository.mDayWordsNum
    }

    companion object {
        private var INSTANCE: UserViewModel? = null

        fun getInstance(): UserViewModel {
            INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE =
                        UserViewModel(UserRepository.getInstance())
                }
            }
            return INSTANCE!!
        }
    }

    fun count() = repository.count()
    fun getDay() = repository.getDay()
    fun getDayWordsNum() = repository.getDayWordsNum()

    fun setDay(day: String) {
        launch({
            repository.setDay(day)
        })
    }

    fun setDayWordsNum(dayWordsNum: Int) {
        launch({
            repository.setDayWordsNum(dayWordsNum)
        })
    }

    fun insert(user: User) {
        launch({
            repository.insert(user)
        })
    }

    private fun errorHandle(throwable: Throwable) {
        throwable.printStackTrace()
    }

    private inline fun launch(
        crossinline action: suspend CoroutineScope.() -> Unit,
        crossinline errorAction: (Throwable) -> Unit = ::errorHandle
    ) {

        viewModelScope.launch {
            try {
                action()
            } catch (e: Exception) {
                errorAction(e)
            }
        }
    }

    /**
     * async 操作异常没有传到父协程, 请求操作发生错误应用直接闪退, 所以手动捕获异常, 上报父协程
     *
     */
    private inline fun <R> CoroutineScope.asyncTryAsync(crossinline action: suspend () -> R) = async {
        try {
            action()
        } catch (e: Exception) {
            e
        }
    }
}