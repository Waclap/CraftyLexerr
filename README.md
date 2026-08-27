# CraftyLex

## 概要
1行~3行程度の短いプログラムなどの字句解析を楽に実装できます. ([コード例](#使用方法))

## 導入方法(Gradle)
``` kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Waclap:CraftyLexerr:v1.1.0")
}
```

## 使用方法
- `TokenType`の実装にはEnumが最適です.
- `CraftyLex`の`getTokens`メソッドによって入力された文字列をトークン列に変換します.

### コード例
``` kotlin
import net.waclap.craftylex.CraftyLex
import net.waclap.craftylex.TokenType

fun main() {
    val lexer = CraftyLex()

    val input = "int a = 3 + 4 / (4 - 3 * (2 + 4))"
    val lexerResult = lexer.getTokens(input, SetInstTokenType.entries)
    println(lexerResult.tokens)
}

enum class SetInstTokenType(private val regex: Regex, private val isSkipped: Boolean = false) : TokenType {
    ASSIGN("""=""".toRegex()), ADD_OP("""[+-]""".toRegex()), MUL_OP("""[*/%]""".toRegex()), PS("""\(""".toRegex()), PE("""\)""".toRegex()),
    ID("""[a-zA-Z][a-zA-Z0-9_]*""".toRegex()), NUM("""[0-9]+""".toRegex()), SPACE("""[ \t\r\n]+""".toRegex(), true);

    override fun matches(line: String): Boolean = regex.matches(line)
    override fun isSkipped(): Boolean = isSkipped
}
```

### 実行結果
```terminaloutput
[ID(int), ID(a), ASSIGN(=), NUM(3), ADD_OP(+), NUM(4), MUL_OP(/), PS((), NUM(4), ADD_OP(-), NUM(3), MUL_OP(*), PS((), NUM(2), ADD_OP(+), NUM(4), PE()), PE())]
```

## ライセンス
このプロジェクトは **MIT ライセンス** のもとで公開されています.
詳細は [LICENSE](LICENSE) ファイルをご覧ください.

## 作者
Waclap
- Github: [@Waclap](https://github.com/Waclap)

## サポート
バグ報告等は[Issues](https://github.com/Waclap/CraftyLex/issues)までお願いします.