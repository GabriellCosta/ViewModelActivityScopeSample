package dev.tigrao.activityscopeexample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.slider.Slider
import dev.tigrao.activityscopeexample.databinding.FragmentSliderBinding

class SliderFragment : Fragment(R.layout.fragment_slider), Slider.OnChangeListener {

    private val viewModel by activityViewModels<ControlViewModel>()

    private var _viewBinding: FragmentSliderBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewBinding = FragmentSliderBinding.bind(view)

        setupObserver()
        setupViews()
    }

    private fun setupViews() {
        viewBinding.slider.apply {
            showContextMenu()
            valueTo = 100f
            valueFrom = 0f

            addOnChangeListener(this@SliderFragment)
        }
    }

    private fun setupObserver() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            viewBinding.slider.value = it.toFloat()
        }
    }

    override fun onDestroy() {
        _viewBinding = null

        super.onDestroy()
    }

    override fun onValueChange(slider: Slider, value: Float, fromUser: Boolean) {
        viewModel.setTotal(value.toInt())
    }
}