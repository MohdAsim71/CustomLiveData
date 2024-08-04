package com.example.customlivedata

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.livedatainternals.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
   private var myOwnLiveDataTextView:TextView?=null
   private var liveDataTextView:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        myOwnLiveDataTextView= findViewById(R.id.myOwnLiveDataTextView)
        liveDataTextView= findViewById(R.id.liveDataTextView)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mainViewModel.dataProvider.observe(this, Observer {
            liveDataTextView!!.text = it.toString()
        })

        mainViewModel.myDataProvider.addObserver(this, {
            it?.run {
                myOwnLiveDataTextView!!.text = this.toString()
            }
        })

        mainViewModel.runCounter()

    }
}