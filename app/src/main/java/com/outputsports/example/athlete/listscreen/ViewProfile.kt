package com.outputsports.example.athlete.listscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.outputsports.example.athlete.R
import com.outputsports.example.athlete.data.avatarURL
import com.outputsports.example.athlete.listscreen.MyAdapter.Companion.AGE_KEY
import com.outputsports.example.athlete.listscreen.MyAdapter.Companion.NAME_KEY
import com.squareup.picasso.Picasso

class ViewProfile : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_athlete_profile, container, false)

        val athleteName = arguments?.getString(NAME_KEY) ?: "Default DemoMan"
        view.findViewById<TextView>(R.id.profileview_name).text = athleteName
        val athleteAge = arguments?.getString(AGE_KEY) ?: "99"
        view.findViewById<TextView>(R.id.profileview_age).text = athleteAge
        Picasso.get()
            .load(avatarURL) // photoURL is currently static, this could later become property of the profile object
            .fit()
            .placeholder(R.drawable.photo_avatar_placeholder).error(R.drawable.photo_avatar_error)
            .into(view.findViewById<ImageView>(R.id.profileview_photo))

        return view
    }
}