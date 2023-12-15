package de.torfstack.aoc23.day15

import java.nio.file.Path
import kotlin.io.path.readText

class LensHash {
    fun solvePart1(input: Path): Int {
        return parseInput(input).sumOf { computeHash(it) }
    }

    fun solvePart2(input: Path): Int {
        val hashMap = mutableMapOf<Int, MutableList<Pair<String, Int>>>()

        parseInput(input).forEach {
            val label = label(it)
            val hash = computeHash(label.first)
            if (label.second == null) {
                hashMap[hash]?.let { lenses -> lenses.removeIf { lens -> lens.first == label.first }}
            } else {
                if (hashMap[hash] == null) {
                    hashMap[hash] = mutableListOf()
                }
                val box = hashMap[hash]!!
                val index = box.indexOfFirst { lens -> lens.first == label.first }
                if (index == -1) {
                    box.add(label.first to label.second!!)
                } else {
                    box[index] = label.first to label.second!!
                }
            }
        }

        return hashMap.map { box ->
            box.value.mapIndexed { index, lens->
                (box.key+1) * (index+1) * lens.second
            }.sum()
        }.sum()
    }

    private fun label(ofInput: String): Pair<String, Int?> {
        if (ofInput[ofInput.length-1] == '-') {
            return ofInput.substring(0, ofInput.length-1) to null
        }
        return ofInput.substring(0, ofInput.length-2) to ofInput.last().digitToInt()
    }

    private fun computeHash(input: String): Int {
        return input.fold(0) { acc: Int, c: Char ->
            (acc + c.code)*17 % 256
        }
    }

    private fun parseInput(input: Path): List<String> {
        return input.readText().split(",")
    }
}
