package com.aydinnrz.newsapp.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.aydinnrz.newsapp.R


abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        initUi()
        setObservers()
    }

    /** get viewBinding from child fragment and set it in OnCreateView */
    abstract fun getViewBinding(): VB

    /** initialize and implement click listeners of views here */
    open fun setClickListeners() {}

    /** implement data observers */
    open fun setObservers() {}

    /** init views here */
    open fun initUi() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}