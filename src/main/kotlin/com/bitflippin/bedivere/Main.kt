package com.bitflippin.bedivere

import com.bitflippin.bedivere.model.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
    val json = Json.encodeToString(ArgMap(listOf(Citation()), listOf(Claim()), listOf(Ideology()), listOf(Source()), listOf(Support())))
    val asdf = Json.decodeFromString<ArgMap>(json)
    println(json)
    println(asdf)
}