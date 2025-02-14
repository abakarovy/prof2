package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import io.github.jan.supabase.CurrentPlatformTarget

class HomeFragment : Fragment() {

    lateinit var searchField: EditText
    lateinit var recentSearch: LinearLayout
    lateinit var mapBtn: ImageButton

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

        mapBtn.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), MapsActivity::class.java))
            requireActivity().finish()
        }

        searchField.setOnFocusChangeListener { v, hasFocus ->
            recentSearch.visibility = if (hasFocus) View.VISIBLE else View.GONE
        }

        return view
    }
}