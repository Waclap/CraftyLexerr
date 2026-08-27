package net.waclap.craftylex.lexer

import net.waclap.craftylex.TokenData
import net.waclap.craftylex.TokenType

internal class Lexer(private val input: String, private val tokenTypes: List<TokenType>) {
    fun apply(): List<TokenData> {
        val result = arrayListOf<TokenData>()
        for (row in input.lines()) {
            if (row.isEmpty()) continue
            val lineLexer = Lexer(row, tokenTypes)
            result.addAll(lineLexer.applyIncludeLineBreaks())
        }
        return result
    }

    fun applyIncludeLineBreaks(): List<TokenData> {
        val result = arrayListOf<TokenData>()

        var oldType = -1
        var buffer = ""
        var oldBuffer = ""
        var i = 0
        while (i < input.length) {
            buffer += input[i]
            val type = findType(buffer)
            if (oldType >= 0 && type == -1) {
                val typeData = tokenTypes[oldType]
                if (!typeData.isSkipped()) {
                    result.add(TokenData(typeData, oldBuffer))
                }
                buffer = ""
                i--
            }

            oldType = type
            oldBuffer = buffer
            i++
        }

        if (buffer.isNotEmpty()) {
            val type = findType(buffer)
            if (type >= 0) {
                result.add(TokenData(tokenTypes[type], buffer))
            } else {
                result.add(TokenData(null, buffer))
            }
        }

        return result
    }

    private fun findType(line: String): Int {
        var i = 0
        while (i < tokenTypes.size) {
            val current = tokenTypes[i]
            if (current.matches(line)) {
                return i
            }
            i++
        }
        return -1
    }
}