package com.example.binahoverflow.repository

import com.example.binahoverflow.api.StackExchangeService
import com.example.binahoverflow.data.QuestionAdapterItem
import com.example.binahoverflow.data.QuestionsResponseClass

class QuestionsRepository {

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
        private var items: List<QuestionsResponseClass.Item>? = null
        fun put(newQuestions: List<QuestionsResponseClass.Item>) {
            items = newQuestions
        }

        fun get(): List<QuestionsResponseClass.Item>? {
            return items
        }
    }

}