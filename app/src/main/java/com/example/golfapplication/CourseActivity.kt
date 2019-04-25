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
            
            val textView: TextView = findViewById(R.id.locationTextView)

            textView.setOnClickListener{
                // get latitude and longitude values
                val lat = course["lat"].toString().toDouble()
                val lng = course["lng"].toString().toDouble()

                // Build the intent
                val location = Uri.parse("geo:$lat,$lng")
                val mapIntent = Intent(Intent.ACTION_VIEW, location)

                // Verify it resolves
                val activities: List<ResolveInfo> = packageManager.queryIntentActivities(mapIntent, 0)
                val isIntentSafe: Boolean = activities.isNotEmpty()

                // Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent)
                } else {
                    Toast.makeText(this, "There is no activity to handle map intent!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
