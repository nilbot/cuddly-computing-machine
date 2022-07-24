package com.outputsports.example.athlete.data

import java.util.*

data class AthleteProfile(val first_name:String, val second_name:String, val dob_year: Int, val dob_month: Int, val dob_day: Int){
    fun getFullName() : String { return getFullName(first_name, second_name) }
    fun getAge() : Int { return getAgeFromDOB(dob_year, dob_month, dob_day) }
}

fun getFullName(first: String, second : String) : String {
    return StringBuilder().append(first).append(" ").append(second).toString()
}

fun getAgeFromDateOfBirthString(date_of_birth: String) : Int {
    val (year, month, day) = date_of_birth.split("-".toRegex()).map{ it.toInt() }
    return getAgeFromDOB(year, month, day)
}

fun getAgeFromDOB(year:Int, month:Int, day:Int) : Int {
    val today = Calendar.getInstance()
    val dob = Calendar.getInstance()
    dob.set(year, month, day)
    var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
    if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
        age -= 1
    }
    return age
}