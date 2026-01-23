package io.bz.nox.features.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.bz.nox.features.chats.presentation.list.ChatsListScreenStarter
import io.bz.nox.theme.NoxTheme
import kotlinx.coroutines.launch
import io.bz.nox.features.CommonScreenFlows
import io.bz.nox.features.settings.presentation.main.SettingsMainScreenStarter
import io.bz.nox.features.users.presentation.main.UsersMainScreenStarter


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    flow: (CommonScreenFlows) -> Unit,
) {

    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    Column {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            listOf(
                CommonScreenFlows.Users,
                CommonScreenFlows.Chats,
                CommonScreenFlows.Settings
            ).forEachIndexed { index, title ->
                Tab(selected = pagerState.currentPage == index, onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = { Text(title.toString()) })
            }
        }

        HorizontalPager(
            state = pagerState, modifier = Modifier.fillMaxSize()
        ) { page ->
//            Text(text = "page {${page}}")
            when (page) {
                0 -> UsersMainScreenStarter(modifier, flow)
                1 -> ChatsListScreenStarter(modifier, flow)
                2 -> SettingsMainScreenStarter(modifier, flow)
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    NoxTheme {
        MainScreen(Modifier.fillMaxSize(), {})
    }
}