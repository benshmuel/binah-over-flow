package com.example.binahoverflow.data

interface BinahAdapterItem {

    fun getItemIdentifier(): String
    fun getDisplayName(): String
    fun getItemTitle(): String
    fun getItemOwnerImgUrl(): String
    fun getQuestionUrl(): String
    fun getVotes(): String

}


interface QuestionAdapterItem : BinahAdapterItem {
    companion object {
        const val ANSWERED = "Answered"
        const val NOT_ANSWERED = "Not answered"
    }

    fun isQuestionAnswered(): Boolean
}