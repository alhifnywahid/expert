package com.submission.core.utils

fun String.formatJobType(): String = when (this) {
    "full_time" -> "Full Time"
    "internship" -> "Internship"
    "part_time" -> "Part Time"
    "contractual" -> "Contract"
    else -> this
}

fun String.formatExperience(): String = when (this) {
    "freshgraduate" -> "Kurang dari 1 tahun"
    "one_to_three_years" -> "1-3 tahun"
    "four_to_five_years" -> "4-5 tahun"
    "six_to_ten_years" -> "6-10 tahun"
    "more_than_ten_years" -> "Lebih dari 10 tahun"
    else -> this
}