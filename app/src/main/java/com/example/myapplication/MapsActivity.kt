package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MapsActivity : AppCompatActivity() {
    lateinit var mapView: MapView
    lateinit var cancelButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("0154b4b4-7643-42f6-b4e6-7a7875c33b75")
        MapKitFactory.initialize(this)

        setContentView(R.layout.activity_maps)

        mapView = findViewById(R.id.mapView)
        cancelButton = findViewById(R.id.cancelButton)

        mapView.mapWindow.map.move(
            CameraPosition(
                Point(42.9827112, 47.4969968),
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            )
        )

        cancelButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        MapKitFactory.getInstance().onStop()
        mapView.onStop()
    }
}