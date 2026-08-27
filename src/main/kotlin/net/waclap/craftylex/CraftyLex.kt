package net.waclap.craftylex

import net.waclap.craftylex.lexer.Lexer
import net.waclap.craftylex.lexer.LexerError

class CraftyLex {
    fun getTokens(input: String, tokenTypes: List<TokenType>): LexerResult {
        val lexer = Lexer(input, tokenTypes)
        val tokens = lexer.apply()
        val errors = LexerError.getErrorTokens(tokens)
        return LexerResult(tokens, errors)
    }
}