package com.apptikar.easy_read.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.apptikar.common.utils.readToken
import com.apptikar.easy.R
import com.apptikar.easy.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashFragment : Fragment() {

    lateinit var binding: FragmentSplashBinding
    private lateinit var nav: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_splash,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(),R.color.white)
        nav = Navigation.findNavController(view)
        navigateListener()
    }
    private fun navigateListener(){

        lifecycleScope.launch {
            var token :String?
            withContext(Dispatchers.IO){
                token = requireContext().readToken("tokenKey")
            }
            delay(2000)
            if (token == null || token == "" ){
                val extra = FragmentNavigatorExtras(binding.logoImageView to ViewCompat.getTransitionName(binding.logoImageView)!!)
                nav.navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment(),extra)


            }else{
                nav.navigate(SplashFragmentDirections.actionSplashFragmentToScanFragment())
            }
        }
    }



}