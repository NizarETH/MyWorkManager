package com.ofppt.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
           // .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        var request = OneTimeWorkRequestBuilder<MyWork>()
            .setConstraints(constraints)
            .build()

        // PeriodicWorkRequestBuilder time = 20 min la valeur par défaut est 15 min.

      /*  val request = PeriodicWorkRequestBuilder<MyWork>(20, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()*/
        findViewById<View>(R.id.btnClick).setOnClickListener {

            WorkManager.getInstance(this).enqueue(request)
        }

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer {

                val status: String = it.state.name
                Toast.makeText(this,status, Toast.LENGTH_SHORT).show()
            })

    }
}