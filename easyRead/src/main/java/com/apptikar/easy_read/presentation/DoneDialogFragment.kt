package com.apptikar.easy_read.presentation

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.apptikar.easy.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class DoneDialogFragment : DialogFragment() {


    override fun getTheme() = R.style.RoundedCornersDialog
    private lateinit var nav: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_done_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nav = Navigation.findNavController(requireParentFragment().requireView())
        dismissTimer()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        nav.previousBackStackEntry?.savedStateHandle?.set("key", "done")
        nav.popBackStack()
    }


    private fun dismissTimer()
    {

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            dialog?.dismiss()
        }
    }

}