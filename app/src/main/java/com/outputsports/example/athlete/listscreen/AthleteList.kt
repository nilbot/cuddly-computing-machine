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
import com.google.android.material.floatingactionbutton.FloatingActionButton

import com.outputsports.example.athlete.R
import com.outputsports.example.athlete.data.*
import com.squareup.picasso.Picasso

class AthleteList : Fragment() {
    private val database = Database.instance
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
            database
        )
        view.findViewById<RecyclerView>(R.id.athlete_list_recycler).run {
            setHasFixedSize(true)
            adapter = viewAdapter
        }
        view.findViewById<FloatingActionButton>(R.id.sign_up_btn).setOnClickListener{
            it.findNavController().navigate(R.id.action_btn_to_form)
        }
        return view
    }
}

class MyAdapter(private val myDataset: AthleteDatabase) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item.findViewById<TextView>(R.id.listview_full_name).text =
            myDataset.getProfile(position % myDataset.size()).getFullName()
        holder.item.findViewById<TextView>(R.id.listview_age).text =
            myDataset.getProfile(position % myDataset.size()).getAge().toString()

        // profile photo loaded remotely from URL
        val imageView = holder.item.findViewById<ImageView>(R.id.listview_photo)
        Picasso.get().load(avatarURL).fit().centerCrop()
            .placeholder(R.drawable.photo_avatar_placeholder)
            .error(R.drawable.photo_avatar_error)
            .into(imageView)
        holder.item.setOnClickListener {
            val bundle = bundleOf(
                NAME_KEY to myDataset.getProfile(position).getFullName(),
                AGE_KEY to myDataset.getProfile(position).getAge().toString()
                // photo URL key could also goes in here later
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
        return myDataset.size()
    }

    companion object {
        const val NAME_KEY = "nameKey"
        const val AGE_KEY = "ageKey"
    }
}
