package de.torfstack.aoc23.day05

import java.nio.file.Path
import kotlin.io.path.readLines

class Fertilizer {

    fun solvePart1(filePath: Path): Long {
        val input = parseInput(filePath)
        val result = input.first.minOfOrNull { seed ->
            var copySeed = seed
            input.second.forEach { map ->
                copySeed = map.convert(copySeed)
            }
            copySeed }!!
        println("Result of $filePath for part 1: $result")
        return result
    }

    fun solvePart2(filePath: Path): Long {
        val input = parseInput(filePath)

        val minimalLocationsForSeeds = input.first.chunked(2)
            .map { it[0]..<it[0]+it[1] }
            .map { minimalLocationForSeedRange(it, input.second) }

        val result = minimalLocationsForSeeds.min()
        println("Result of $filePath for part 2: $result")
        return result
    }

    private fun minimalLocationForSeedRange(seedRange: LongRange, maps: List<Map>): Long {
        var temp = listOf(seedRange)
        maps.forEach { map ->
            temp = temp.flatMap { map.convert(it) }
        }
        return temp.minBy { it.first }.first
    }

    private fun parseInput(filePath: Path): Pair<List<Long>, List<Map>> {
        val lines = filePath.readLines()

        val digitRegex = "\\d+".toRegex()
        val seeds = digitRegex.findAll(lines[0]).map { it.value.toLong() }.toList()

        val maps = splitAtEmptyLines(lines.subList(2, lines.size))
            .map { mapStrings ->
                Map(mapStrings.subList(1, mapStrings.size)
                    .map { digitRegex.findAll(it).map { converter -> converter.value.toLong() }.toList() }
                    .map { Triple(it[0], it[1], it[2]) })
            }

        return seeds to maps
    }

    private fun splitAtEmptyLines(lines: List<String>): List<List<String>> {
        val index = lines.indexOfFirst { it.isEmpty() }
        if (index == -1) {
            return listOf(lines)
        }
        return listOf(lines.subList(0, index)).plus(splitAtEmptyLines(lines.subList(index+1, lines.size)))
            .filter { it.isNotEmpty() }
    }
}