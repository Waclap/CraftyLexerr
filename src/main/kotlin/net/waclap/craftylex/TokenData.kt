package net.waclap.craftylex

data class TokenData(val type: TokenType?, val content: String) {
    override fun toString(): String {
        return if (type != null) "$type($content)" else "ERROR($content)"
    }
}
