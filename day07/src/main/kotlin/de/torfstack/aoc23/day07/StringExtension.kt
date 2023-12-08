package de.torfstack.aoc23.day07

fun String.toCardsPart1(): List<Hand.Card> {
    return map { Hand.Card.fromPart1(it) }
}

fun String.toCardsPart2(): List<Hand.Card> {
    return map { Hand.Card.fromPart2(it) }
}
