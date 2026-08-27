package net.waclap.craftylex

data class LexerResult(val tokens: List<TokenData>, val errorTokens: List<String>) {
    val hasError: Boolean = errorTokens.isNotEmpty()

    fun errorsToString(): String {
        return errorTokens.toString()
    }
}
