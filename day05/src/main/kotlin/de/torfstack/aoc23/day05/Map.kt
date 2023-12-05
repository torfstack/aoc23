package de.torfstack.aoc23.day05

import java.lang.RuntimeException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Map(private val categories: List<Triple<Long, Long, Long>>) {

    private val categoryRanges: List<Pair<LongRange, LongRange>> = categories.map {
        LongRange(it.first, it.first+it.third-1) to LongRange(it.second, it.second+it.third-1)
    }.sortedBy { it.second.first }

    init {
        if (!categoryRanges.map { it.second }.areAllRangesDisjoint()) {
            throw RuntimeException("map source ranges are not disjoint, something went wrong during parsing probably")
        }
    }

    fun convert(number: Long): Long {
        val category = categories.find { (it.second..<it.second+it.third).contains(number) }
        return category?.let { it.first + (number - it.second) } ?: number
    }

    fun convert(range: LongRange): List<LongRange> {
        val ranges = mutableListOf<LongRange>()
        var tempRange = range
        categoryRanges.forEach { category ->
            val split = tempRange.splitAtAndConvertIntersection(category)
            split?.let {
                ranges.addAll(split.allButSuffix())
                if (it.suffix == null) {
                    return ranges
                } else {
                    tempRange = it.suffix
                }
            }
        }
        return ranges.plusElement(tempRange)
    }
}

fun LongRange.intersection(other: LongRange): LongRange? {
    if (this.last < other.first || this.first > other.last) {
        return null
    }
    return LongRange(max(this.first, other.first), min(this.last, other.last))
}

fun LongRange.splitAtIntersection(other: LongRange, interactionAction: (LongRange) -> LongRange): Split? {
    val intersection = intersection(other) ?: return null
    var split = Split(intersection = interactionAction(intersection))
    if (first < intersection.first) {
        split = Split(intersection = split.intersection, prefix = LongRange(first, intersection.first-1))
    }
    if (last > intersection.last) {
        split = Split(intersection = split.intersection, prefix = split.prefix, suffix = LongRange(intersection.last+1, last))
    }
    return split
}

data class Split(
    val prefix: LongRange? = null,
    val intersection: LongRange,
    val suffix: LongRange? = null
) {
    fun allButSuffix(): List<LongRange> = prefix?.let { listOf(prefix, intersection) } ?: listOf(intersection)
}

fun LongRange.splitAtAndConvertIntersection(category: Pair<LongRange, LongRange>): Split? {
    return this.splitAtIntersection(category.second) { intersection ->
        val offset = abs(intersection.first - category.second.first)
        val length = intersection.last - intersection.first
        LongRange(category.first.first+offset, category.first.first+offset+length)
    }
}