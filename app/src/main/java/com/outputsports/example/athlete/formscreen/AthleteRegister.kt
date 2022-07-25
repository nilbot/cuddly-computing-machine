package com.outputsports.example.athlete.formscreen

import java.util.Calendar
import android.app.DatePickerDialog
import android.icu.text.DateFormat
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.outputsports.example.athlete.R
import com.outputsports.example.athlete.data.AthleteDatabase
import com.outputsports.example.athlete.data.AthleteProfile
import com.outputsports.example.athlete.data.Database

class AthleteRegister : Fragment(), DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance()
    private val database = Database.instance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_athlete_register, container, false)

        val dobBtn = view.findViewById<Button>(R.id.dob_label_picker_btn_combo)
        dobBtn.setOnClickListener {
            DatePickerDialog(
                it.context,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        val submitBtn = view.findViewById<Button>(R.id.submit_btn)
        submitBtn.setOnClickListener {
            val firstName = view.findViewById<EditText>(R.id.edit_first_name_text).text.toString()
            val secondName = view.findViewById<EditText>(R.id.edit_second_name_text).text.toString()
            val today = Calendar.getInstance()
            if (!calendar.equals(today)) {
                if (firstName != "" && secondName != "") {
                    val profile = AthleteProfile(
                        firstName,
                        secondName,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DATE)
                    )
                    database.addProfile(profile)
                    it.findNavController().navigate(R.id.action_form_to_list)
                } else {
                    Log.e("Register", "name is not set")
                    it.findNavController().navigate(R.id.action_form_to_list)
                }
            } else {
                Log.e("Register", "date of birth set to today")
                it.findNavController().navigate(R.id.action_form_to_list)
            }
        }

        return view
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        calendar.set(year, month, day)
        displayDateFormatString(calendar)
    }

    private fun displayDateFormatString(calendar: Calendar) {
        val view = requireView().findViewById<Button>(R.id.dob_label_picker_btn_combo)
        view.text = DateFormat.getPatternInstance(DateFormat.YEAR_NUM_MONTH_DAY).format(calendar.timeInMillis)
    }
}