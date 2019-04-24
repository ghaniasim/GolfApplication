package com.example.golfapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_course.*
import org.json.JSONObject

class CourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)


        // get data from intent
        val bundle: Bundle? = intent.extras;
        if (bundle != null) {
            val courseString = bundle!!.getString("course")
            val course = JSONObject(courseString)

            addressTextView.text = course["address"].toString()
            emailTextView.text = course["email"].toString()
            phoneTextView.text = course["phone"].toString()
            descriptionTextView.text = course["text"].toString()
            webTextView.text = course["web"].toString()
            Glide.with(imageView.context).load("http://ptm.fi/materials/golfcourses/"+course["image"]).into(imageView)
        }
    }
}