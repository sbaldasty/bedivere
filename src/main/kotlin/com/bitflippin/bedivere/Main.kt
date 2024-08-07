package com.bitflippin.bedivere

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

import com.bitflippin.bedivere.antlr.BedivereLexer
import com.bitflippin.bedivere.antlr.BedivereParser

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val name = "Kotlin"
    //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
    // to see how IntelliJ IDEA suggests fixing it.
    println("Hello, " + name + "!")

    for (i in 1..5) {
        //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
        // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
        println("i = $i")
    }
    val lexer = BedivereLexer(readSampleFile())
    val tokens = CommonTokenStream(lexer)
    val parser = BedivereParser(tokens)
    while (parser.currentToken.type != BedivereParser.EOF) {
        val nextLine = parser.line()
        val kw = nextLine.keyValue()
        val key = kw.key().text
        val value = kw.separatorAndValue().chars()
            .joinToString(separator = "") { it.text }

        println("$key = $value")
    }
}

private fun readSampleFile(): CharStream {
    return CharStreams.fromString("asdf=1")
}