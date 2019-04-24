package com.example.golfapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.MyToolBar)
        setSupportActionBar(toolbar)

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.collapse_toolbar)
        collapsingToolbar.title = "Golf Courses"

        // Use LinearManager as a layout manager for recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data - remember use your own data here
        val url = "http://ptm.fi/materials/golfcourses/golf_courses.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Get golf courses from JSON
                val courses = response.getJSONArray("courses")
                // Create Golf Adapter with courses data
                recyclerView.adapter = GolfAdapter(courses)
            },
            Response.ErrorListener { error ->
                Log.d("JSON",error.toString())
            }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }
}
