package com.mala.joke.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mala.joke.R
import com.mala.joke.databinding.FragmentSplashScreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreen () : Fragment() {

lateinit var binding:FragmentSplashScreenBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSplashScreenBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation=AnimationUtils.loadAnimation(requireContext(),R.anim.smart_animation)
        binding.image.startAnimation(animation)
        binding.imageRectangle.startAnimation(animation)
        lifecycleScope.launch {
            delay(5500)
            findNavController().navigate(R.id.action_splashScreen_to_mainProjectFragment)
        }
    }

}