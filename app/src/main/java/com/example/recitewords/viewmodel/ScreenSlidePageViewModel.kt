package com.example.recitewords.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recitewords.data.table.Words
import com.example.recitewords.repository.WordsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.File

class ScreenSlidePageViewModel(private val repository: WordsRepository) : ViewModel() {

    val mCount by lazy {
        repository.mCount
    }
    val mConutRemenber by lazy {
        repository.mConutRemenber
    }

    companion object {
        private var INSTANCE: ScreenSlidePageViewModel? = null

        fun getInstance(): ScreenSlidePageViewModel {
            INSTANCE ?: synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE =
                        ScreenSlidePageViewModel(WordsRepository.getInstance())
                }
            }
            return INSTANCE!!
        }
    }

    fun get(limit: Int, boolean: Boolean = false): MutableList<Words> {
        if (boolean) {
            val getWords = repository.get(limit)
            launch({
                asyncTryAsync {
                    repository.update(repository.get(500).map { it.id }, 0)
                    repository.update(getWords.map { it.id }, 1)
                }
            })
            return getWords
        } else {
            return repository.getCur(limit)
        }
    }

    fun insert(path: String) {
        launch({
            asyncTryAsync {
                repository.delete()
            }
            asyncTryAsync {
                val words = mutableListOf<Words>()
                val items = Regex(""""(.*?)"\t"([\d]+)"\t"(.*?)"[\s]+""",RegexOption.DOT_MATCHES_ALL).findAll(File(path).readText())
                try {
                    items.groupBy {
                        words.add(Words(it.groups.get(1)!!.value, it.groups.get(2)!!.value.toInt(), it.groups.get(3)!!.value, 0))
                    }
                } finally {
                    repository.insert(words.toList())
                }
            }
        })
    }

    fun update(ids: List<Int>, state: Int) {
        launch({
            repository.update(ids, state)
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