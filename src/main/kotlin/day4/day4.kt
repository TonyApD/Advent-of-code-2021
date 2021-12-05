package day4

import readLinesFromFile

fun main() {
    val lines = readLinesFromFile("day4")
    val drawnNumbers = lines.first().split(",").map { it.toInt() }.toIntArray()
    val bingoCards = lines.drop(2)
        .map { it.split(" ")
            .filter { number -> number.isNotBlank() }
            .map { number -> number.toInt() }
            .toIntArray() }
        .windowed(5, step = 6)
        .map { BingoCard(it) }

    println("Part 1:")
    println(part1(bingoCards, drawnNumbers))

    println("Part 2:")
    println(part2(bingoCards, drawnNumbers))
}

fun part1(bingoCards: List<BingoCard>, drawnNumbers: IntArray): Int {
    var drawn = IntArray(0)
    for (drawnNumber in drawnNumbers) {
        drawn += drawnNumber
        val winningCard = bingoCards.firstOrNull { it.hasBingo(drawn) }

        if (winningCard != null) {
            return winningCard.getUndrawnNumbers(drawn).sum() * drawnNumber
        }
    }
    return 0
}

fun part2(bingoCards: List<BingoCard>, drawnNumbers: IntArray): Int {
    var nonWinningCards = bingoCards
    var drawn = IntArray(0)
    for (drawnNumber in drawnNumbers) {
        drawn += drawnNumber

        // If the last card has bingo, then we have the result
        if (nonWinningCards.size == 1 && nonWinningCards.first().hasBingo(drawn)) {
            return nonWinningCards.first().getUndrawnNumbers(drawn).sum() * drawnNumber
        }

        nonWinningCards = nonWinningCards.filter { !it.hasBingo(drawn) }
    }
    return 0
}

