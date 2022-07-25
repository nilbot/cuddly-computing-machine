package com.outputsports.example.athlete.data

const val avatarURL = "https://i.imgur.com/MIgAJK4h.jpgi.imgur.com/MIgAJK4h.jpg"
/**
 * WARNING
 * TODO
 *
 * a side note for handling date in java: Calendar.Month starts at 0 instead of 1.
 * ALWAYS handle that to avoid bugs.
 */
var listOfAthletes = listOf(
    AthleteProfile("Alex", "Bob", 1994, 4, 22),
    AthleteProfile("Amanda", "Clarke", 1997, 7, 11),
    AthleteProfile("Brenda", "Coal", 2000, 11, 2),
    AthleteProfile("Ben", "Aftertaste", 2002, 12, 20), // Month 12 is illegal in Gregorian calendar
    AthleteProfile("Emily", "Rose", 2005, 1, 9),
    AthleteProfile("Honda", "Yamaha", 1999, 2, 10),
    AthleteProfile("Jack", "Bauer", 1982, 5, 25),
    AthleteProfile("V", "von Vendetta", 1990, 9, 21),
    AthleteProfile("Zelda", "with Breath", 1133, 6, 6)
)

class AthleteDatabase {

    private val storage : ArrayList<AthleteProfile> = arrayListOf()
    constructor(initial_list: List<AthleteProfile>){
        storage.addAll(initial_list)
    }
    fun getProfile(index: Int) : AthleteProfile {
        return storage.get(index)
    }

    fun addProfile(profile: AthleteProfile) {
        storage.add(profile)
    }

    fun clearStorage() {
        storage.clear()
    }

    fun size() : Int {
        return storage.size
    }
}

object Database {
    val instance = AthleteDatabase(listOfAthletes)
}