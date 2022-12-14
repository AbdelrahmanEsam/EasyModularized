package com.apptikar.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.apptikar.common.utils.readToken
import com.apptikar.presentation.databinding.FragmentMoneyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoneyFragment : Fragment() {

    lateinit var binding: FragmentMoneyBinding
    private lateinit var nav: NavController
    private val viewModel: MoneyViewModel by viewModels()
    private val args:MoneyFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().window.statusBarColor = ContextCompat.getColor(requireContext(),R.color.white)
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_money,container,false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = Navigation.findNavController(view)
        navigateObserver()
        binding.confirmButton.setOnClickListener {
            confirm()
          }

        binding.backImageView.setOnClickListener {
            nav.popBackStack()
        }
        listenToFragmentCallBack()
        }

    private fun confirm(){
        if(!viewModel.isNetworkConnected.get()){
            Toast.makeText(context , getString(R.string.please_connect_to_internet) ,Toast.LENGTH_LONG).show()
            return
        }
        if (binding.numberInputLayout.editText?.text?.isNotEmpty()!!){
            var token :String?
            lifecycleScope.launch(Dispatchers.IO){
                token = requireContext().readToken("tokenKey")
                viewModel.makeInvoiceRequest(token!!,args.code)
            }

        }
    }

    private fun listenToFragmentCallBack()
    {
        nav.currentBackStackEntry?.savedStateHandle?.getLiveData<String>("key")?.observe(
            viewLifecycleOwner) {
            nav.popBackStack()
        }
        val sum = { num :Int , num2:Int -> num+num2 }

    }


    private fun navigateObserver(){
        viewModel.navigateMakeInvoice.observe(viewLifecycleOwner){
            if (it.status == 1){
                nav.navigate(MoneyFragmentDirections.actionMoneyFragmentToDoneDialogFragment())
            }else{
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }



}