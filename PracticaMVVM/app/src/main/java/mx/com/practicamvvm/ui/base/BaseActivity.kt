package mx.com.practicamvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint



open class BaseActivity : AppCompatActivity() {
    private val loader by lazy { LoaderDialog() }
    var isOnLandscape = false
    private var isLoaderVisible=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showLoader() {
        try {
            val loaderDialog = supportFragmentManager.findFragmentByTag("Loader") as? LoaderDialog
            val isShowing = loader.dialog?.isShowing ?: false
            if (loaderDialog != null && loaderDialog.isAdded || isShowing) {
                return
            }

            if (!isLoaderVisible && !loader.isAdded && !loader.isVisible && !isShowing && !isOnLandscape) {
                val newLoader = LoaderDialog()
                newLoader.show(supportFragmentManager, "Loader")
                supportFragmentManager.executePendingTransactions()
                isLoaderVisible = true
            }
        } catch (e: Exception) {
            //ERROR
        }
    }

    fun dismissLoader() {
        val loaderDialog = supportFragmentManager.findFragmentByTag("Loader") as? LoaderDialog
        if (loaderDialog != null && loaderDialog.isAdded) {
            if (loaderDialog.isResumed) {
                loaderDialog.dismiss()
            } else {
                loaderDialog.dismissAllowingStateLoss()

            }
            isLoaderVisible = false
        }
    }
}