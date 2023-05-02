package com.skbhati199.androidassignment

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.skbhati199.androidassignment.data.ResponsePhotoItem
import com.skbhati199.androidassignment.di.apiModule
import com.skbhati199.androidassignment.di.netModule
import com.skbhati199.androidassignment.di.repositoryModule
import com.skbhati199.androidassignment.di.viewModelModule
import com.skbhati199.androidassignment.respository.PhotoRepository
import com.skbhati199.androidassignment.ui.fragments.PhotoDetailsFragment
import com.skbhati199.androidassignment.utils.Resource
import com.skbhati199.androidassignment.viewmodels.PhotoViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class PhotoListFragmentTest  {

    private lateinit var viewModel: PhotoViewModel

    @MockK
    private lateinit var repository: PhotoRepository

    @MockK
    private lateinit var mockObserver: Resource<ArrayList<ResponsePhotoItem>>

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        startKoin {
            androidLogger()
//            androidContext()
            modules(viewModelModule, apiModule, netModule, repositoryModule)
        }
        viewModel = PhotoViewModel(repository)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testPhotoDetailsFragment() {
        // Mock the repository
        val photo = ResponsePhotoItem(1, 1, "", "Title", "")
        val list = ArrayList<ResponsePhotoItem>()
        list.add(photo)
        var res  = list as ArrayList<ResponsePhotoItem>
        coEvery { repository.getPhotos() } returns Resource.Success(res)
        // ... other mocks ...

        // Initialize the Koin DI container


        // Set up the mock NavController
        val navController = mock(NavController::class.java)
        val bundle = bundleOf("photoItem" to photo)
        val scenario = launchFragmentInContainer<PhotoDetailsFragment>(bundle)
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }

        // Click the "Back" button
//        onView(withId(R.id.backButton)).perform(click())

        // Verify that the NavController navigates back to the previous destination
//        verify(navController).popBackStack()
    }

}