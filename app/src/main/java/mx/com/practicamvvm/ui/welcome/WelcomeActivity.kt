package mx.com.practicamvvm.ui.welcome

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import dagger.hilt.android.AndroidEntryPoint
import mx.com.practicamvvm.R
import mx.com.practicamvvm.databinding.ActivityWelcomeBinding
import mx.com.practicamvvm.ui.base.BaseActivity


@AndroidEntryPoint
class WelcomeActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_content_welcome)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_welcome)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}