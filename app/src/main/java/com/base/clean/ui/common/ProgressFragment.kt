package com.base.clean.ui.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.clean.R
import com.base.clean.databinding.FragmentProgressBinding
import com.base.clean.ui.common.view.ProgressView

class ProgressFragment : Fragment() {
    lateinit var binding: FragmentProgressBinding

    lateinit var progress: ProgressView
    private var messageId: Int = 0
    private var iconId: Int = R.drawable.ic_launcher_foreground

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressBinding.inflate(inflater, container, false)


        return binding.root
    }

    private fun updateUi() {
        if (messageId != 0) {
            progress.setText(resources.getString(messageId))
        } else {
            progress.setText("")
        }

        progress.setIcon(iconId)
    }

    fun setText(messageId: Int) {
        this.messageId = messageId

        if (isAdded) {
            updateUi()
        }
    }

    fun setIcon(drawable: Int) {
        this.iconId = drawable
    }

    companion object {
        fun newInstance(messageId: Int, icon: Int): ProgressFragment {
            val fragment = ProgressFragment()

            val bundle = Bundle()
            bundle.putInt("message-id", messageId)
            bundle.putInt("icon-id", icon)

            fragment.arguments = bundle

            return fragment
        }
    }

}