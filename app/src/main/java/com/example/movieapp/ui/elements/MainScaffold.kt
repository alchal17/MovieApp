package com.example.movieapp.ui.elements

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.datastores.LocalDataStorage
import com.example.movieapp.ui.pages.MoviesListPage
import com.example.movieapp.ui.pages.Routes
import com.example.movieapp.ui.pages.SettingsPage
import com.example.movieapp.ui.theme.MainBG
import com.example.movieapp.viewmodels.MovieViewModel
import kotlinx.coroutines.launch

private data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val isSelected: Boolean,
    val onClick: () -> Unit
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MainScaffold(
    animatedVisibilityScope: AnimatedVisibilityScope,
    movieViewModel: MovieViewModel,
    mainNavController: NavController,
    localDataStorage: LocalDataStorage<Int>
) {
    val scaffoldVavController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val lazyGridState = rememberLazyGridState()
    val coroutineScope = rememberCoroutineScope()

    var currentNavigationIndex by rememberSaveable { mutableIntStateOf(0) }

    val navigationItems = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            isSelected = currentNavigationIndex == 0
        ) {
            scaffoldVavController.navigate(Routes.MainScaffoldRoutes.MovieList)

        },
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            isSelected = currentNavigationIndex == 1
        ) {
            scaffoldVavController.navigate(Routes.MainScaffoldRoutes.Settings)
        }
    )

    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
        ModalDrawerSheet {
            navigationItems.forEachIndexed { index, navItem ->
                NavigationDrawerItem(colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = MainBG,
                    selectedTextColor = Color.White,
                    selectedIconColor = Color.White
                ),
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    label = { Text(navItem.title) },
                    selected = navItem.isSelected,
                    onClick = {
                        coroutineScope.launch { drawerState.close() }
                        if (currentNavigationIndex != index) {
                            currentNavigationIndex = index
                            navItem.onClick()
                        }
                    },
                    icon = {
                        Icon(
                            imageVector =
                            if (navItem.isSelected) navItem.selectedIcon
                            else navItem.unselectedIcon,
                            contentDescription = null
                        )
                    })
            }
        }
    }) {
        Scaffold(containerColor = MainBG, topBar = {
            MainTopBar(
                coroutineScope,
                currentNavigationIndex
            ) { drawerState.open() }
        }) { padding ->
            NavHost(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                navController = scaffoldVavController,
                startDestination = Routes.MainScaffoldRoutes.MovieList
            ) {
                composable<Routes.MainScaffoldRoutes.MovieList> {
                    MoviesListPage(
                        LocalContext.current,
                        animatedVisibilityScope = animatedVisibilityScope,
                        moviesViewModel = movieViewModel, lazyVerticalGridState = lazyGridState,
                        localDataStorage = localDataStorage,
                        cardOnClick = { posterPath: String?,
                                        id: String,
                                        title: String,
                                        overview: String,
                                        releaseDate: String,
                                        originalLanguage: String ->
                            mainNavController.navigate(
                                Routes.MovieInfo(
                                    posterPath,
                                    id,
                                    title,
                                    overview,
                                    releaseDate,
                                    originalLanguage
                                )
                            )
                        },
                    )
                }
                composable<Routes.MainScaffoldRoutes.Settings> {
                    SettingsPage(
                        LocalContext.current,
                        localDataStorage
                    )
                }
            }
        }
    }
}