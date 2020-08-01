package com.example.recitewords.utlis

import com.example.recitewords.data.AppDataBase
import com.example.recitewords.data.dao.DaoUser
import com.example.recitewords.data.dao.DaoWords
import com.example.recitewords.factory.ScreenSlidePageFactory
import com.example.recitewords.factory.UserFactory
import com.example.recitewords.repository.UserRepository
import com.example.recitewords.repository.WordsRepository

object InjectorUtils {
    fun getWordsRepository(daoWords: DaoWords) = WordsRepository(daoWords)
    fun provideScreenSlidePageFactory() =
        ScreenSlidePageFactory(getWordsRepository(AppDataBase.getInstance().DaoWords()))

    fun getUserRepository(daoUser: DaoUser) = UserRepository(daoUser)
    fun provideUserFactory() = UserFactory(getUserRepository(AppDataBase.getInstance().DaoUser()))
}