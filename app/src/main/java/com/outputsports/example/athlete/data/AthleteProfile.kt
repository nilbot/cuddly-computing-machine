package com.outputsports.example.athlete.data

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class AthleteProfile(val first_name:String, val second_name:String, val dob_year: Int, val dob_month: Int, val dob_day: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    fun getFullName() : String { return getFullName(first_name, second_name) }
    fun getAge() : Int { return getAgeFromDOB(dob_year, dob_month, dob_day) }
    fun getUserName(): String { return getUserName(first_name,second_name, getDOBString())}
    private fun getDOBString() : String { return listOf(dob_year, dob_month, dob_day).joinToString(separator = "-") { it.toString() }}
    fun getPhotoURL() : String { return avatarURL }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(first_name)
        parcel.writeString(second_name)
        parcel.writeInt(dob_year)
        parcel.writeInt(dob_month)
        parcel.writeInt(dob_day)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AthleteProfile> {
        override fun createFromParcel(parcel: Parcel): AthleteProfile {
            return AthleteProfile(parcel)
        }

        override fun newArray(size: Int): Array<AthleteProfile?> {
            return arrayOfNulls(size)
        }
    }
}

fun getFullName(first: String, second : String) : String {
    return listOf(first, second).joinToString(separator = " ")
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

fun getUserName(first: String, second: String, dob: String) : String {
    return listOf(first, second, dob).joinToString(separator = "_")
}

const val avatarURL = "https://i.imgur.com/MIgAJK4h.jpgi.imgur.com/MIgAJK4h.jpg"