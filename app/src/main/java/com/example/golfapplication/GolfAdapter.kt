package com.example.golfapplication

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import kotlinx.android.synthetic.main.golf_item.view.*

// Golf Adapter, used in RecyclerView in MainActivity
class GolfAdapter(private val courses: JSONArray) : RecyclerView.Adapter<GolfAdapter.ViewHolder>() {

    // create UI View Holder from XML layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GolfAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.golf_item, parent, false)
        return ViewHolder(view)
    }

    // return item count in courses
    override fun getItemCount(): Int = courses.length()

    // bind data to UI View Holder
    override fun onBindViewHolder(holder: GolfAdapter.ViewHolder, position: Int) {
        // course to bind UI
        val course: JSONObject = courses.getJSONObject(position)
        // name, address, email, phone, image
        holder.nameTextView.text = course["course"].toString()
        holder.addressTextView.text = course["address"].toString()
        holder.emailTextView.text = course["email"].toString()
        holder.phoneTextView.text = course["phone"].toString()
        Glide.with(holder.imageView.context).load("http://ptm.fi/materials/golfcourses/"+course["image"]).into(holder.imageView)
    }

    // View Holder class to hold UI views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.nameTextView
        val addressTextView: TextView = view.addressTextView
        val emailTextView: TextView = view.emailTextView
        val phoneTextView: TextView = view.phoneTextView
        val imageView: ImageView = view.imageView

        // add a item click listener
        init {
            itemView.setOnClickListener {
                // create an explicit intent
                val intent = Intent(view.context, CourseActivity::class.java)
                // add selected course json as a string data
                intent.putExtra("course",courses[adapterPosition].toString())
                // start a new activity
                view.context.startActivity(intent)
            }
        }
    }

}