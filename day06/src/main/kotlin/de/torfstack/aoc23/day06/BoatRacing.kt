package de.torfstack.aoc23.day06

import java.nio.file.Path
import kotlin.io.path.readLines

class BoatRacing {
    fun solve(filePath: Path): Int {
        val races = readInFile(filePath)
        val result = races.map { it.numberOfWaysToBeatRecord() }
            .reduce { acc, i -> acc*i }.toInt()
        println("Result for $filePath in part 1 is $result")
        return result
    }

    private fun readInFile(filePath: Path): List<Race> {
        val digitRegex = "\\d+".toRegex()
        val lines = filePath.readLines()
        val times = digitRegex.findAll(lines[0]).map { it.value.toLong() }.toList()
        val distances = digitRegex.findAll(lines[1]).map { it.value.toLong() }.toList()
        return times.mapIndexed { index, time -> time to distances[index] }
    }
}

typealias Race = Pair<Long, Long>
fun Race.time() = first
fun Race.distance() = second
fun Race.numberOfWaysToBeatRecord(): Long {
    val firstTime = (0..time()).first {
        it * (time()-it) > distance()
    }
    return time() - 2*firstTime + 1
}
