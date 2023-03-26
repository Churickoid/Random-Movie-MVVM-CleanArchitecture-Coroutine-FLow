package com.example.randommovie.presentation.screen

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.example.randommovie.databinding.DialogRatingBinding
import com.example.randommovie.domain.entity.Movie
import com.example.randommovie.domain.entity.UserInfoAndMovie
import com.example.randommovie.presentation.screen.filter.FilterListDialogFragment
import com.example.randommovie.presentation.tools.parcelable
import com.example.randommovie.presentation.tools.parcelableArrayList

class RatingDialogFragment : DialogFragment() {

    private val movie: Movie
        get() = requireArguments().parcelable(ARG_MOVIE)
            ?: throw IllegalArgumentException("Can't work without movie")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val binding = DialogRatingBinding.inflate(layoutInflater)

        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            binding.selectedRatingTextView.text = rating.toInt().toString()
            binding.selectedRatingTextView.background
                .setTint(getRatingColor(rating.toDouble(),requireContext()))
        }



        return AlertDialog.Builder(requireContext())
            .setPositiveButton("Apply") { _, _ ->
                val rating = binding.ratingBar.rating
                val inWatchlist = binding.watchlistCheckBox.isChecked
                if (inWatchlist || rating>0.0) callResult(rating.toInt(),inWatchlist)
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .setView(binding.root)
            .create()
    }

    private fun callResult(rating: Int, inWatchlist : Boolean) {
        val userInfoAndMovie = UserInfoAndMovie(movie = movie, userRating = rating, inWatchlist = inWatchlist)
        parentFragmentManager.setFragmentResult(
            TAG, bundleOf(KEY_USER_INFO_AND_MOVIE to userInfoAndMovie)
        )
    }

    companion object {

        private val TAG = RatingDialogFragment::class.java.simpleName
        private const val KEY_USER_INFO_AND_MOVIE = "KEY_USER_INFO_AND_MOVIE"
        private const val ARG_MOVIE = "ARG_MOVIE"

        fun show(manager: FragmentManager, movie: Movie) {
            val dialogFragment = RatingDialogFragment()
            dialogFragment.arguments = bundleOf(ARG_MOVIE to movie)
            dialogFragment.show(manager, TAG)
        }

        fun setupListener(
            manager: FragmentManager,
            lifecycleOwner: LifecycleOwner,
            listener: (UserInfoAndMovie) -> Unit
        ) {
            manager.setFragmentResultListener(TAG, lifecycleOwner) { _, result ->
                listener.invoke(result.parcelable(KEY_USER_INFO_AND_MOVIE) ?:
                throw IllegalArgumentException("Result doesn't exist"))
            }
        }
    }
}