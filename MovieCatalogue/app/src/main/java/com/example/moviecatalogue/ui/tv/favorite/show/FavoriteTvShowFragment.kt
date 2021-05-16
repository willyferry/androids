package com.example.moviecatalogue.ui.tv.favorite.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogue.databinding.FragmentTvShowBinding
import com.example.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

            val favoriteTvShowAdapter = FavoriteTvShowAdapter()
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteShows().observe(viewLifecycleOwner, { shows ->
                fragmentTvShowBinding.progressBar.visibility = View.GONE
                favoriteTvShowAdapter.submitList(shows)
            })

            with(fragmentTvShowBinding.rvTvShows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowAdapter
            }
        }
    }
}