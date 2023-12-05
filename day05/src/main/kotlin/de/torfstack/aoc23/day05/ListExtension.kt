package de.torfstack.aoc23.day05

fun List<LongRange>.areAllRangesDisjoint(): Boolean {
    var disjoint = true
    forEachIndexed { index, longRange ->
        if (index != size-1) {
            subList(index+1, size).forEach {
                disjoint = disjoint && longRange.intersection(it) == null
            }
        }
    }
    return disjoint
}
