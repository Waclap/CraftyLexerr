package net.waclap.craftylex

interface TokenType {
    fun matches(line: String): Boolean
    fun isSkipped(): Boolean = false
}