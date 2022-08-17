package dev.tigrao.activityscopeexample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dev.tigrao.activityscopeexample.databinding.FragmentButtonsBinding

private const val FACTOR = 1

class ButtonsFragment : Fragment(R.layout.fragment_buttons) {

    private val viewModel by activityViewModels<ControlViewModel>()

    private var _viewBinding: FragmentButtonsBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = FragmentButtonsBinding.bind(view)

        setupObserver()
        setupViews()
    }

    private fun setupViews() {
        viewBinding.apply {
            minus.setOnClickListener {
                viewModel.decrement(FACTOR)
            }

            sum.setOnClickListener {
                viewModel.increment(FACTOR)
            }
        }
    }

    private fun setupObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            viewBinding.text.text = it.toString()
        }
    }

    override fun onDestroy() {
        _viewBinding = null

        super.onDestroy()
    }
}