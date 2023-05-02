# Android Assignment Readme
This Android assignment is a machine test that involves implementing the following:

- Retrieving data from an API endpoint using Retrofit client
- Displaying the data in a RecyclerView using MVVM design pattern and data binding
- Using Picasso library for image rendering
- Writing unit test cases for the implemented code
## Technologies used
- Kotlin programming language
- Android Studio IDE
- Retrofit for API calls
- Koin for dependency injection
- MVVM design pattern
- Data binding and View binding
- Picasso library for image rendering
- JUnit and Mockito for unit testing
## API endpoint
The API endpoint used in this assignment is `https://jsonplaceholder.typicode.com/photos.` It returns a JSON array of photo objects.

## Implementation
The implementation of this assignment consists of the following components:

- MainActivity: The main activity of the application. It hosts the PhotoListFragment.
- PhotoListFragment: This fragment retrieves data from the API endpoint using Retrofit and displays it in a RecyclerView using data binding. It uses the ViewModel and LiveData classes to retrieve and store the data respectively.
- PhotoRepository: This repository class retrieves data from the API endpoint using Retrofit and returns it to the ViewModel.
- PhotoViewModel: This ViewModel class retrieves data from the PhotoRepository and stores it in a LiveData object.
- NetworkModule: This module provides a Retrofit client instance using Dagger Hilt.
- AppModule: This module provides instances of the Picasso library and the PhotoRepository using Dagger Hilt.
PhotoDetailsFragment: This fragment displays details of a photo item including a header image, title, and description. It uses the Navigation component to navigate from the PhotoListFragment to the PhotoDetailsFragment passing the selected photo item data.
- PhotoDetailsViewModel: This ViewModel class retrieves the selected photo item data from the Navigation arguments and provides it to the PhotoDetailsFragment for display.

## Unit testing

The implemented code has unit tests written for it. The unit tests include testing the ViewModel, Repository, and API calls. The tests use the JUnit and Mockito libraries.

## Conclusion
This assignment has been implemented using MVVM design pattern, Retrofit, Dagger Hilt, data binding, and Picasso library. Unit tests have been written using JUnit and Mockito.