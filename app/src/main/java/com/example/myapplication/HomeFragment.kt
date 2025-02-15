package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.motion.widget.OnSwipe
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import io.github.jan.supabase.CurrentPlatformTarget

class HomeFragment : Fragment() {

    lateinit var searchField: EditText
    lateinit var recentSearch: LinearLayout
    lateinit var mapBtn: Button
    lateinit var swipeLayout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        searchField = view.findViewById(R.id.searchText)
        recentSearch = view.findViewById(R.id.recentSearch)
        mapBtn = view.findViewById(R.id.mapButton)
        swipeLayout = view.findViewById(R.id.swipeLayout)


        mapBtn.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), MapsActivity::class.java))
            requireActivity().finish()
        }

        searchField.setOnFocusChangeListener { v, hasFocus ->
            recentSearch.visibility = if (hasFocus) View.VISIBLE else View.GONE
        }

        swipeLayout.setOnTouchListener(object: SwipeGestureListener(this.requireContext()) {
            override fun onSwipeRight() {
                Toast.makeText(this@HomeFragment.requireContext(), "SWIOP{E RIGHT", Toast.LENGTH_LONG).show()
            }

            override fun onSwipeLeft() {
                Toast.makeText(this@HomeFragment.requireContext(), "SWIOP{E LEFT", Toast.LENGTH_SHORT).show()
            }

        })

        return view
    }

    inner class GestureListener() {

    }
}