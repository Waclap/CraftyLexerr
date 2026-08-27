package net.waclap.craftylex.lexer

import net.waclap.craftylex.TokenData

internal object LexerError {
    fun getErrorTokens(tokens: List<TokenData>): List<String> {
        val result = arrayListOf<String>()
        for ((type, content) in tokens) {
            if (type == null) {
                result.addLast(content)
            }
        }
        return result
    }
}