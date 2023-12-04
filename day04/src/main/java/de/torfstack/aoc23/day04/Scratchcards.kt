package de.torfstack.aoc23.day04

import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.math.min
import kotlin.math.pow

class Scratchcards {

    fun solvePart1(filePath: Path): Int {
        val sum = filePath.readLines().sumOf { line ->
            val count = countWinning(line)
            2.0.pow(count.toDouble().minus(1)).toInt()
        }
        println("Result of $filePath for part 1 is $sum")
        return sum
    }

    fun solvePart2(filePath: Path): Any {
        val lineCount = filePath.readLines().size
        val cardCount = mutableMapOf<Int, Int>()
        for (i in 0..<lineCount) {
            cardCount[i] = 1
        }

        filePath.readLines().forEachIndexed { index, line ->
            val count = countWinning(line)
            val toIncrement = min(count, lineCount - index - 1)
            for (i in 1..toIncrement) {
                cardCount[index+i] = cardCount[index+i]!! + cardCount[index]!!
            }
        }

        val sum = cardCount.values.sum()
        println("Result of $filePath for part 2 is $sum")
        return sum
    }

    private fun countWinning(line: String): Int {
        val game = line.split(":")[1].split("|")
        val digitRegex = "\\d+".toRegex()
        val winning = digitRegex.findAll(game[0]).map { it.value }.toList()
        val owned = digitRegex.findAll(game[1]).map { it.value }.toList()
        return owned.count { winning.contains(it) }
    }
}