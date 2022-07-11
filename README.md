# Edvora_Test
An app built using Kotlin to fetch user data and cab rides detail from the API and then list them out after sorting. 

The app consists of three major screens/fragments : 
- nearest rides 
- upcoming rides
- past rides

Each screen has 10-20 cab ride details fetched according to the API response.

## Functioning of the app - 
The app consists of a 'Tablayout' having different sections which displays the different fragment when you scroll past each. 

- Nearest Rides : this section displays the list of rides which are near to the user. The API fetches the user details first which has its current location/station code and then compares it with the coordinates of the ride's station path and then shares the nearest rides accordingly.
- Upcoming Rides : this section display the list of rides which are filtered out on the criteria of date after getting compared to the current date. It shows the rides which has dates of the future ie date > current date.
- Past Rides : this section also displays the list of rides before to the current date after filtering and getting compared to the current date. It shows the rides which has dates of the past  ie date < current date.
