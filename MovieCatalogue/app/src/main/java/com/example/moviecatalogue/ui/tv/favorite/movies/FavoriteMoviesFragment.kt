package com.example.moviecatalogue.ui.tv.favorite.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentMoviesBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMoviesFragment : Fragment() {

    private lateinit var fragmentMoviesBinding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMoviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return fragmentMoviesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteMoviesViewModel::class.java]

            val favoriteMoviesAdapter = FavoriteMoviesAdapter()
            fragmentMoviesBinding.progressBar.visibility = View.VISIBLE

            viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { movies ->
                fragmentMoviesBinding.progressBar.visibility = View.GONE
                favoriteMoviesAdapter.submitList(movies)
            })

            with(fragmentMoviesBinding.rvMovies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMoviesAdapter
            }
        }
    }
}