package de.torfstack.aoc23.day12

import java.nio.file.Path
import kotlin.io.path.readLines

class HotSprings {

    fun solvePart1(input: Path): Long {
        val onsen = parseInput(input)
        val results = onsen.map { it.numberOfValidArrangements() }
        val result = results.sum()
        println("Result for $input part 1 is $result")
        return result
    }

    fun solvePart2(input: Path): Long {
        val onsen = parseInput(input)
        val results = onsen
            .map {
                val s = it.springs
                val d = it.defects
                Onsen("$s?$s?$s?$s?$s", d.plus(d).plus(d).plus(d).plus(d))
            }
            .map { it.numberOfValidArrangements() }
        val result = results.sum()
        println("Result for $input part 2 is $result")
        return result
    }

    private fun parseInput(input: Path): List<Onsen> {
        return input.readLines().map { line ->
            val springs = line.split(" ")[0]
            val defects = line.split(" ")[1].split(",")
                .map { it.toInt() }
            Onsen(springs, defects)
        }
    }

    data class Onsen(val springs: String, val defects: List<Int>) {
        fun numberOfValidArrangements(): Long {
            val results = mutableMapOf<Pair<String, List<Int>>, Long>()
            return combinations(springs, defects, results)
        }

        private fun combinations(string: String, defects: List<Int>, results: MutableMap<Pair<String, List<Int>>, Long>): Long {
            if (results.contains(string to defects)) {
                return results[string to defects]!!
            }
            if (defects.isEmpty()) {
                return if (string.contains("#")) 0 else 1
            }
            val curr = defects.first()
            val remain = defects.subList(1, defects.size)
            val lengthToPutCurrIn = string.length - remain.size - remain.sum() - curr + 1
            var res = 0L
            (0..<lengthToPutCurrIn).forEach {
                if (string.substring(0, it).contains("#")) return@forEach
                val next = it + curr
                if (next <= string.length && !string.substring(it, next).contains(".") && (next == string.length || string[next] != '#')) {
                    val substring = if (next+1 >= string.length) "" else string.substring(next+1)
                    res += combinations(substring, remain, results)
                }
            }
            results[string to defects] = res
            return res
        }
    }
}
