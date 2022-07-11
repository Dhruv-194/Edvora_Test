# Edvora_Test
An app built using Kotlin to fetch user data and cab rides detail from the API and then list them out after sorting. 

The app consists of three major screens/fragments : 
- nearest rides 
- upcoming rides
- past rides

Each screen has 10-20 cab ride details fetched according to the API response.

## Functioning of the app - 
The app consists of a 'Tablayout' having different sections which displays the different fragment when you scroll past each. 

- ####  Nearest Rides : this section displays the list of rides which are near to the user. The API fetches the user details first which has its current location/station code and then compares it with the coordinates of the ride's station path and then shares the nearest rides accordingly.
- #### Upcoming Rides : this section display the list of rides which are filtered out on the criteria of date after getting compared to the current date. It shows the rides which has dates of the future ie ride after current date (date > current date).
- #### Past Rides : this section also displays the list of rides after filtering and getting compared to the current date. It shows the rides which has dates of the past ie ride before to the current date (date < current date).

> Each tab item of 'Upcoming Rides' and 'Past Rides' also shows the item count of their own respective lists. 

> The AppBar layout shows the details of the user after fetching from the user API endpoint. 

## Built using - 
- Android Studio 
- Kotlin 
- Assesment API 

## Concepts used - 
- Material Design Guidelines 
- TabLayout : to display each particular ride fragment 
- Custom AppBar Layout 
- Recyclerview : to display the list of cabs 
- viewBinding 
- MVVM architecture : viewModels and Livedata to live update the itemcount of the upcoming and past rides and their respective list. 
- Retrofit Library : to fetch the ride details and the user details. 

## Demo/Screenshots - 

## Future Enhancements - 
- Add a fliter button and filter the rides according to the state and city. 
- Show the upcoming and past rides in the same way as that of nearest rides ie after comparing with the station_path.
- Use an image library to display the images of rides and user. 
- Calculate and show the distance of the nearest rides in the 'Distance' textview.

