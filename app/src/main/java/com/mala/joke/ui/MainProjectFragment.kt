package com.mala.joke.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.mala.joke.R
import com.mala.joke.databinding.FragmentMainProjectBinding
import com.mala.joke.databinding.FragmentSplashScreenBinding
import com.mala.joke.model.State
import com.mala.joke.model.domain.TheJoke
import com.mala.joke.viewModel.MyViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainProjectFragment() : Fragment() {
    private lateinit var binding:FragmentMainProjectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainProjectBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUp()
    }



    fun setUp() {
        val myViewModel= ViewModelProvider(this)[MyViewModel::class.java]
        binding.btnAnotherJoke.setOnClickListener {
            myViewModel.getMyJokBro()

        }
        myViewModel.getJoke.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    showLoadingIndicator()
                    hideImage()
                }

                is State.Success -> {
                    hideLoadingIndicator()
                    hideImage()
                    displayJoke(state.data!!)
                    binding.delivery.isVisible = true
                    binding.setup.isVisible = true

                }

                is State.Error -> {
                    hideLoadingIndicator()
                    showError(state.message)
                }
            }
        }
        binding.btnAnotherJoke.setOnClickListener { myViewModel.getMyJokBro() }
    }

    private fun showError(message: String) {
       binding.error.isVisible=true
        binding.error.playAnimation()


    }

    private fun hideImage() {
        binding.error.isVisible = false
        binding.error.pauseAnimation()
    }

    private fun displayJoke(joke: TheJoke) {
            binding.apply {
                delivery.text = joke.delivery
                setup.text = joke.setup
                delivery.isVisible = true
                cardOfDelivery.isVisible=true
                cardOfSetup.isVisible=true
                setup.isVisible = true
            }

    }

    private fun hideLoadingIndicator() {
        binding.loading.pauseAnimation()
        binding.loading.isVisible=false
        binding.apply {
            delivery.isVisible = false
            cardOfDelivery.isVisible=false
            cardOfSetup.isVisible=false
            setup.isVisible = false


        }
    }

    private  fun showLoadingIndicator() {
        lifecycleScope.launch {
            binding.loading.isVisible = true
            binding.loading.playAnimation()
            delay(2000)
        }

        binding.apply {
            delivery.isVisible = false
            cardOfDelivery.isVisible=false
            cardOfSetup.isVisible=false
            setup.isVisible = false


        }
    }

}

