package me.weishu.kernelsu.ui.screen.about

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.dropUnlessResumed
import me.weishu.kernelsu.BuildConfig
import me.weishu.kernelsu.R
import me.weishu.kernelsu.ui.AuthorLinks
import me.weishu.kernelsu.ui.component.onboarding.FirstRunGuide
import me.weishu.kernelsu.ui.navigation3.LocalNavigator

@Composable
fun AboutScreen() {
    var showGuide by remember { mutableStateOf(false) }
    val navigator = LocalNavigator.current
    val uriHandler = LocalUriHandler.current
    val htmlString = stringResource(
        id = R.string.about_author_links,
        "<b><a href=\"${AuthorLinks.KUAISHOU_ZHUANJIANG}\">跳转作者快手：钻酱</a></b>",
        "<b><a href=\"${AuthorLinks.KUAISHOU_YONGJIU}\">跳转作者快手：永久</a></b>"
    )
    val state = AboutUiState(
        title = stringResource(R.string.about),
        appName = stringResource(R.string.app_name),
        versionName = BuildConfig.VERSION_NAME,
        links = extractLinks(htmlString),
    )
    val actions = AboutScreenActions(
        onBack = dropUnlessResumed { navigator.pop() },
        onOpenLink = uriHandler::openUri,
        onShowGuide = { showGuide = true },
    )

    AboutScreenMaterial(state, actions)
    if (showGuide) {
        FirstRunGuide(onDismiss = { showGuide = false })
    }
}
