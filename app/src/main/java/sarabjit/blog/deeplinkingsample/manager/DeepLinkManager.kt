package sarabjit.blog.deeplinkingsample.manager

import android.content.Context
import android.content.Intent
import android.net.Uri
import sarabjit.blog.deeplinkingsample.activity.DashboardActivity
import sarabjit.blog.deeplinkingsample.enums.DeepLinks


class DeepLinkManager {

    /**
     * Create and return the App Supported DeepLink  String
     */
    private fun getAppSupportedDeepLinkString(scheme: String, host: String): String {
        return Uri.Builder().scheme(scheme).authority(host).build().toString()
    }

    /**
     * Iterate over the application supported DeepLinks and return the matched deepLink
     */
    private fun checkAndGetDeepLink(context: Context, intent: Intent): DeepLinks? {
        val deepLinks = DeepLinks.values()
        for (link in deepLinks) {
            if (intent.data.toString().contains(
                    getAppSupportedDeepLinkString(
                        context.getString(link.mScheme),
                        context.getString(link.mHost)
                    )
                )
            ) {
                return link
            }
        }
        return null
    }

    /**
     * Resolve the deep link for appropriate action
     */
    fun resolveLink(context: Context, intent: Intent) {
        intent.let {
            val appDeepLink = checkAndGetDeepLink(context, it)
            navigate(context, appDeepLink, it)
        }
    }


    /**
     * Navigate the user to the directed Screen
     */
    private fun navigate(
        context: Context,
        appDeepLink: DeepLinks?,
        deepLinkIntent: Intent
    ) {
        val intent = getDeepLinkIntent(context, appDeepLink)
        intent?.run {
            intent.data = deepLinkIntent.data
            context.startActivity(intent)
        }
    }

    /**
     * This  method will check and return the entry point of the deeplink
     */
    private fun getDeepLinkIntent(
        context: Context,
        appDeepLink: DeepLinks?
    ): Intent? {
        return appDeepLink?.run {
            if (appDeepLink == DeepLinks.DASHBOARD) {
                return Intent(context, DashboardActivity::class.java)
            }
            return null
        }
    }
}