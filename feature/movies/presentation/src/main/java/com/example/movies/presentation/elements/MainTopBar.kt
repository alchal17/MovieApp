package com.example.movies.presentation.elements

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    coroutineScope: CoroutineScope,
    currentTitleIndex: Int,
    onNavigationIconClick: suspend () -> Unit
) {
//    CenterAlignedTopAppBar(
//        navigationIcon = {
//            IconButton(onClick = { coroutineScope.launch { onNavigationIconClick() } }) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = "Menu", tint = Color.White
//                )
//            }
//        },
//        title = {
//            Text(
//                text = listOf("Featured Films", "Settings")[currentTitleIndex],
//                style = TextStyle(
//                    color = LightGray,
//                    fontSize = 35.sp,
//                    fontFamily = FontFamily(Font(R.font.oswald_semi_bold, FontWeight.Normal))
//                ),
//            )
//        },
//        colors = TopAppBarColors(
//            containerColor = Transparent,
//            titleContentColor = LightGray,
//            actionIconContentColor = Transparent,
//            navigationIconContentColor = Transparent, scrolledContainerColor = Transparent
//        )
//    )
}