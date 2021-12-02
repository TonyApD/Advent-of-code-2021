package day1

import readLinesFromFile

fun main() {
    val lines = readLinesFromFile("day1")
    val measurements = lines.map { it.toInt() }

    println("Part 1:")
    println(countIncreasedMeasurementsPart1(measurements))

    println("Part 2:")
    println(countIncreasedMeasurementsPart2(measurements))
}

fun countIncreasedMeasurementsPart1(measurements: List<Int>):Int {
    var higherMeasurements = 0
    var previousMeasurement = 0
    for ((index, measurement) in measurements.withIndex()) {
        if (index > 0 && measurement > previousMeasurement) {
            higherMeasurements++
        }
        previousMeasurement = measurement
    }
    return higherMeasurements
}

fun countIncreasedMeasurementsPart2(measurements: List<Int>):Int {
    var higherMeasurements = 0
    var previousMeasurement = 0

    for (index in measurements.indices) {
        if (index + 3 > measurements.count()) break

        val windowSum = measurements.subList(index, index + 3).sum()
        if (index > 0 && windowSum > previousMeasurement) {
            higherMeasurements++
        }
        previousMeasurement = windowSum
    }
    return higherMeasurements
}