package sarabjit.blog.deeplinkingsample.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sarabjit.blog.deeplinkingsample.manager.DeepLinkManager

class DeepLinkHandlingActivity : AppCompatActivity() {
    private lateinit var mDeepLinkManager: DeepLinkManager

    /**
     * Deep Link Manager will handle the deep link and then we will quit our DeepLinkHandling
     * activity as it has performed its task successfully
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDeepLinkManager =
            DeepLinkManager()

        intent?.run {
            handleDeepLink()
            finish()
        }
        finish()
    }

    /**
     * Deep Link Manager takes the appropriate action
     */
    private fun handleDeepLink() {
        mDeepLinkManager.resolveLink(this, intent)
    }
}