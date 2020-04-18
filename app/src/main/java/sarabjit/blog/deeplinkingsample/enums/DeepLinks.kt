package sarabjit.blog.deeplinkingsample.enums

import sarabjit.blog.deeplinkingsample.R


/**
 * Enums that corresponds to the Supported DeepLinks
 */
enum class DeepLinks(val mScheme: Int, val mHost: Int) {
    DASHBOARD(
        R.string.deep_link_scheme,
        R.string.deep_link_host_dashboard
    );
}