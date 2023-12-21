package mx.com.practicamvvm.ui.welcome

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import mx.com.practicamvvm.R
import mx.com.practicamvvm.databinding.FragmentWelcomeBinding
import mx.com.practicamvvm.sys.utils.Resource
import mx.com.practicamvvm.sys.utils.Status
import mx.com.practicamvvm.ui.base.BaseActivity
import mx.com.practicamvvm.ui.home.HomeActivity
import mx.com.practicamvvm.ui.welcome.viewmodel.WelcomeViewModel


@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLiveData()
        listeners()
    }

    private fun listeners(){
        binding.btnLogin.setOnClickListener {
            viewModel.getAuthUser(binding.tilEmail.editText!!.text.toString().trim(), binding.tilPassword.editText!!.text.toString())
        }
        binding.btnLoginWithGoogle.setOnClickListener{

        }

        binding.btnDonotHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_WelcomeFragment_to_RegisterFragment)
        }
    }


    private fun initLiveData() {
        viewModel.resultLogin.observe(viewLifecycleOwner, this::resultLogin)
    }


    private fun resultLogin(result: Resource<Boolean>) {
        when (result.status) {
            Status.LOADING -> {
                (activity as BaseActivity).showLoader()
            }
            Status.SUCCESS -> {
                (activity as BaseActivity).dismissLoader()
                (activity as WelcomeActivity).finish()
                val intent=Intent(requireContext(),HomeActivity::class.java)
                startActivity(intent)
            }
            Status.ERROR -> {
                (activity as BaseActivity).dismissLoader()
                (activity as BaseActivity).showAlert(result.message!!)
            }
        }

    }

}