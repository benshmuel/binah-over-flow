package com.example.binahoverflow.repository

import android.util.Log
import com.example.binahoverflow.api.StackExchangeService
import com.example.binahoverflow.data.QuestionAdapterItem
import com.example.binahoverflow.data.QuestionsResponseClass
import java.util.concurrent.TimeUnit

class QuestionsRepository {

    companion object {
        const val FIVE_MINUTES = 5 * 60 * 1000
    }

    private val stackExchangeService: StackExchangeService = StackExchangeService.create()

    private var questionCache: SimpleQuestionCache = SimpleQuestionCache()

    suspend fun getQuestion(): List<QuestionAdapterItem> {


        val cachedQuestions = questionCache.get()

        if (cachedQuestions != null) {
            return cachedQuestions
        }

        val newQuestions = stackExchangeService.getQuestions().items
        questionCache.put(newQuestions)

        return newQuestions
    }


    inner class SimpleQuestionCache {


        private var loggedTime: Long = 0

        private var items: List<QuestionsResponseClass.Item>? = null

        fun put(newQuestions: List<QuestionsResponseClass.Item>) {
            items = newQuestions
            //After saving it to our simple cache, we will log the time.
            //this will be the indicator for refreshing or not.
            //for instance if i saved 'newQuestions' in 10:00AM and the next time I'm trying to get
            //it is 10:15AM we won't take it from cache, we will send a new request.
            loggedTime = System.currentTimeMillis()
        }

        fun get(): List<QuestionsResponseClass.Item>? {

            if (loggedTime + FIVE_MINUTES < System.currentTimeMillis()) {
                //It's time for new questions from stackoverflow
                items = null
            }

            return items
        }


    }

}