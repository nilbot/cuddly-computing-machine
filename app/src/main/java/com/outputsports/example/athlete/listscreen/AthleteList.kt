package com.outputsports.example.athlete.listscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.outputsports.example.athlete.R
import com.outputsports.example.athlete.data.AthleteProfile
import com.outputsports.example.athlete.data.avatarURL
import com.squareup.picasso.Picasso

class AthleteList : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate view
        val view = inflater.inflate(R.layout.fragment_athlete_list, container, false)

        val viewAdapter = MyAdapter(
            // crudely and randomly generate data of athlete profiles
            // replace this block with actual data loading code when they are available
            listOfAthletes.toTypedArray()
        )

        view.findViewById<RecyclerView>(R.id.athlete_list).run {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
        return view
    }
}

class MyAdapter(private val myDataset: Array<AthleteProfile>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.findViewById<TextView>(R.id.listview_full_name).text =
            myDataset[position % myDataset.size].getFullName()
        holder.item.findViewById<TextView>(R.id.listview_age).text =
            myDataset[position % myDataset.size].getAge().toString()

        // profile photo loaded remotely from URL
        val imageView = holder.item.findViewById<ImageView>(R.id.listview_photo)
        Picasso.get().load(avatarURL).fit().centerCrop()
            .placeholder(R.drawable.photo_avatar_placeholder)
            .error(R.drawable.photo_avatar_error)
            .into(imageView)
        holder.item.setOnClickListener {
            val bundle = bundleOf(
                NAME_KEY to myDataset[position].getFullName(),
                AGE_KEY to myDataset[position].getAge().toString()
            )

            holder.item.findNavController().navigate(
                R.id.action_list_to_profile,
                bundle
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_view_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return myDataset.size
    }

    companion object {
        const val NAME_KEY = "nameKey"
        const val AGE_KEY = "ageKey"
    }
}

private val listOfAthletes = listOf(
    AthleteProfile("Alex", "Bob", 1994, 4, 22),
    AthleteProfile("Amanda", "Clarke", 1997, 7, 11),
    AthleteProfile("Brenda", "Coal", 2000, 11, 2),
    AthleteProfile("Ben", "Aftertaste", 2002, 12, 20),
    AthleteProfile("Emily", "Rose", 2005, 1, 9),
    AthleteProfile("Honda", "Yamaha", 1999, 2, 10),
    AthleteProfile("Jack", "Bauer", 1982, 5, 25),
    AthleteProfile("V", "von Vendetta", 1990, 9, 21),
    AthleteProfile("Zelda", "Wild", 1135, 6, 6)
)