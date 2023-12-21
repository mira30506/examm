package mx.com.practicamvvm.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.com.practicamvvm.R
import mx.com.practicamvvm.sys.utils.Status
import mx.com.practicamvvm.ui.splash.viewmodel.SplashViewModel



@AndroidEntryPoint
class SplashFragment : Fragment() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initCalls()
    }

    private fun initCalls(){
        viewModel.getFacts()
    }
    private fun initObservers() {
        viewModel.resultLiveData.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.SUCCESS -> {
                    findNavController().navigate(R.id.action_SplashFragment_to_WelcomeFragment)
                }

                Status.ERROR -> {

                }

                Status.LOADING -> {

                }
            }

        }
    }
}