package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import io.github.jan.supabase.postgrest.result.PostgrestResult
import kotlinx.coroutines.launch

class ListActivity2 : AppCompatActivity() {
    lateinit var shoeList: List<Map<String, Object>>

    lateinit var progressBar: ProgressBar
    lateinit var scrollView: ScrollView
    lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list2)

        progressBar = findViewById(R.id.progressBar)
        scrollView = findViewById(R.id.scrollView)
        linearLayout = findViewById(R.id.linearScrollLayout)

        lifecycleScope.launch {
            var res: Shoe? = SupabaseClient.getShoes()
            updateList(res.toString())
        }
    }

    fun updateList(data: String) {
        Log.i("YAAY", data)
        progressBar.isVisible = false
        scrollView.isVisible = true

        linearLayout.addView(ShoeComponent(this))
    }
}