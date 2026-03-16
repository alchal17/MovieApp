package com.example.movies.presentation.elements

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

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
) {
//    val scaffoldVavController = rememberNavController()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val lazyGridState = rememberLazyGridState()
//    val coroutineScope = rememberCoroutineScope()
//
//    var currentNavigationIndex by rememberSaveable { mutableIntStateOf(0) }
//
//    val navigationItems = listOf(
//        NavigationItem(
//            title = "Home",
//            selectedIcon = Icons.Filled.Home,
//            unselectedIcon = Icons.Outlined.Home,
//            isSelected = currentNavigationIndex == 0
//        ) {
//            scaffoldVavController.navigate(Routes.MainScaffoldRoutes.MovieList)
//
//        },
//        NavigationItem(
//            title = "Settings",
//            selectedIcon = Icons.Filled.Settings,
//            unselectedIcon = Icons.Outlined.Settings,
//            isSelected = currentNavigationIndex == 1
//        ) {
//            scaffoldVavController.navigate(Routes.MainScaffoldRoutes.Settings)
//        }
//    )
//
//    ModalNavigationDrawer(drawerState = drawerState, drawerContent = {
//        ModalDrawerSheet {
//            navigationItems.forEachIndexed { index, navItem ->
//                NavigationDrawerItem(colors = NavigationDrawerItemDefaults.colors(
//                    selectedContainerColor = MainBG,
//                    selectedTextColor = Color.White,
//                    selectedIconColor = Color.White
//                ),
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
//                    label = { Text(navItem.title) },
//                    selected = navItem.isSelected,
//                    onClick = {
//                        coroutineScope.launch { drawerState.close() }
//                        if (currentNavigationIndex != index) {
//                            currentNavigationIndex = index
//                            navItem.onClick()
//                        }
//                    },
//                    icon = {
//                        Icon(
//                            imageVector =
//                            if (navItem.isSelected) navItem.selectedIcon
//                            else navItem.unselectedIcon,
//                            contentDescription = null
//                        )
//                    })
//            }
//        }
//    }) {
//        Scaffold(containerColor = MainBG, topBar = {
//            MainTopBar(
//                coroutineScope,
//                currentNavigationIndex
//            ) { drawerState.open() }
//        }) { padding ->
//            NavHost(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding),
//                navController = scaffoldVavController,
//                startDestination = Routes.MainScaffoldRoutes.MovieList
//            ) {
//                composable<Routes.MainScaffoldRoutes.MovieList> {
//                    MoviesListPage(
//                        LocalContext.current,
//                        animatedVisibilityScope = animatedVisibilityScope,
//                        moviesViewModel = movieViewModel, lazyVerticalGridState = lazyGridState,
//                        localDataStorage = localDataStorage,
//                        cardOnClick = { posterPath: String?,
//                                        id: String,
//                                        title: String,
//                                        overview: String,
//                                        releaseDate: String,
//                                        originalLanguage: String ->
//                            mainNavController.navigate(
//                                Routes.MovieInfo(
//                                    posterPath,
//                                    id,
//                                    title,
//                                    overview,
//                                    releaseDate,
//                                    originalLanguage
//                                )
//                            )
//                        },
//                    )
//                }
//                composable<Routes.MainScaffoldRoutes.Settings> {
//                    SettingsPage(
//                        LocalContext.current,
//                        localDataStorage
//                    )
//                }
//            }
//        }
//    }
}