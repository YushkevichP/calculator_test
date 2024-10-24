package com.example.calculator2024

class Calculator {

    private val priorityMap = mapOf(
        "+" to 1,
        "-" to 1,
        "*" to 2,
        "/" to 2
    )

    private val regFindNum = Regex("[-*/+]")
    private val regFindSigns = Regex("[^-+*/=]")

    fun calculateNoBraces(text: String): Double {

        val listNumbers = toListNumbers(text).toMutableList()
        val listSigns = toListSigns(text).toMutableList()

        while (listSigns.isNotEmpty()) {
            val index = getIndexOperation(listSigns)
            val left = listNumbers.removeAt(index).toDouble()
            val right = listNumbers.removeAt(index).toDouble()
            val operation = listSigns.removeAt(index)
            val result = calculateOneOperation(left, operation, right)
            listNumbers.add(index, result.toString())
        }

        return listNumbers.last().toDouble()
    }

    private fun toListSigns(text: String): List<String> {
        return text.split(regFindSigns).filter {
            it.isNotEmpty()
        }
    }

    private fun toListNumbers(text: String): List<String> {
        return text.split(regFindNum).filter {
            it.isNotEmpty()
        }
    }

    private fun getIndexOperation(list: List<String>): Int {
        var index = -1
        var currentPriority = -1

        for (i in list) {
            if (currentPriority < priorityMap.getValue(i)) {
                currentPriority = priorityMap.getValue(i)
                index = list.indexOf(i)
            }
        }
        return index
    }

    private fun calculateOneOperation(
        leftPart: Double,
        operation: String,
        rightPart: Double
    ): Double {

        val result = when (operation) {
            "*" -> (leftPart * rightPart)
            "/" -> (leftPart / rightPart)
            "+" -> (leftPart + rightPart)
            "-" -> (leftPart - rightPart)
            else -> throw Exception("Error in calculation")
        }

        if (result.toString() == "Infinity") {
            throw Exception("На ноль делить нельзя!!!")
        } else return result
    }
}

