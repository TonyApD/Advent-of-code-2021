package day3

import readLinesFromFile

fun main() {
    val lines = readLinesFromFile("day3")
    var reportUnits = lines.map { it.toCharArray() }

    println("Part 1:")
    println(part1(reportUnits))

    println("Part 2:")
    println(part2(lines))
}

fun part1(report: List<CharArray>): Int {
    var gammaRate: CharArray = charArrayOf()
    var epsilonRate: CharArray = charArrayOf()

    val amountOfChars = report.first()
    for (position in amountOfChars.indices) {
        val reportRow = report.map { it.get(position) }
            .groupBy { it }
            .toSortedMap()
            .map { it.value.count() }

        if (reportRow.get(0) > reportRow.get(1)) {
            gammaRate += '0'
            epsilonRate += '1'
        } else {
            gammaRate += '1'
            epsilonRate += '0'
        }
    }
    return gammaRate.binaryToInt() * epsilonRate.binaryToInt()
}

fun part2(report: List<String>): Int {
    var co2ScrubberLines = report
    var oxygenRatingLines = report

    val amountOfChars = report.first()
    for (position in amountOfChars.indices) {
        // Oxygen
        val mostCommonDigit = oxygenRatingLines.map { it.get(position) }
            .groupingBy { it }
            .eachCount()
            .toSortedMap(compareByDescending { it })
            .maxByOrNull { it.value }

        oxygenRatingLines = oxygenRatingLines.filter { it.get(position) == mostCommonDigit?.key }

        // CO2
        val leastCommonDigit = co2ScrubberLines.map { it.get(position) }
            .groupingBy { it }
            .eachCount()
            .toSortedMap()
            .minByOrNull { it.value }

        co2ScrubberLines = co2ScrubberLines.filter { it.get(position) == leastCommonDigit?.key }
    }

    return oxygenRatingLines.single().toCharArray().binaryToInt() * co2ScrubberLines.single().toCharArray().binaryToInt()
}

fun CharArray.binaryToInt(): Int {
    return this.joinToString("").toInt(2)
}

