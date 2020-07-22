package com.nytimes.ui.description

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.nytimes.R
import com.nytimes.support.SharedPreference_class
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description.*
import kotlinx.android.synthetic.main.content_scrolling.*

class DescriptionActivity : AppCompatActivity() {


    lateinit var spfs : SharedPreference_class


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        spfs = SharedPreference_class(this)
        supportActionBar?.title = spfs.getValueString("section").toString() +" "+  spfs.getValueString("type").toString()


        // toolbar.title = spfs.getValueString("type")
        Picasso.get().load(spfs.getValueString("image")).into(mainImage)
        tvTitle.text = spfs.getValueString("title").toString()

       tvBy.text =  spfs.getValueString("byline")
        tvHeading.text  = spfs.getValueString("caption").toString() +"\n"+
                spfs.getValueString("abstract").toString()

        tvsection.text = spfs.getValueString("section").toString() +" "+  spfs.getValueString("type").toString()
        tvtime.text = spfs.getValueString("published_date").toString()


        tvcopyright.text =  spfs.getValueString("copyright")



       // findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout).title = title
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}