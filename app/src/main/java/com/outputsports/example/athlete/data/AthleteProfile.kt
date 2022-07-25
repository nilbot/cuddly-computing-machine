package com.outputsports.example.athlete.data

import android.icu.text.DateFormat
import java.util.*

data class AthleteProfile(val first_name:String, val second_name:String, val dob_year: Int, val dob_month: Int, val dob_day: Int) {
    private val dob = Calendar.getInstance().set(dob_year, dob_month, dob_day)
    fun getFullName() : String { return getFullName(first_name, second_name) }
    fun getAge() : Int { return getAgeFromDOB(dob_year, dob_month, dob_day) }
    fun getUserName(): String { return getUserName(first_name,second_name, getDOBString())}
    private fun getDOBString() : String { return DateFormat.getPatternInstance(DateFormat.YEAR_NUM_MONTH_DAY).format(getDOBCalendar().timeInMillis) }
    fun getPhotoURL() : String { return avatarURL }
    fun getDOBCalendar() : Calendar {
        val d = Calendar.getInstance()
        d.set(dob_year, dob_month, dob_day)
        return d
    }
}

fun getFullName(first: String, second : String) : String {
    return listOf(first, second).joinToString(separator = " ")
}
/**
 * WARNING
 * TODO
 *
 * a side note for handling date in java: Calendar.Month starts at 0 instead of 1.
 * ALWAYS handle that to avoid bugs.
 */
fun getAgeFromDateOfBirthString(date_of_birth: String) : Int {
    val (year, month, day) = date_of_birth.split("-".toRegex()).map{ it.toInt() }
    return getAgeFromDOB(year, month - 1, day)
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

fun getUserName(first: String, second: String, dob: String) : String {
    return listOf(first, second, dob).joinToString(separator = "_")
}

