
# Airport Application
App that uses the users current location to get the nearest Airports and display the flight schedule for each Airport there is.


* The application uses a json rest api to get the nearest Airport, Flight timetable and cityname.

* The application uses the following:

- Aviation-Edge premium Apis full documentation here https://aviation-edge.com/
- Fabric Crashlytics version:2.9.6 to report crashes.
- Facebook Stetho version:1.5.0 for debugging i.e only enabled when running in debug mode.
- Google Maps(version:16.1.0) and FusedLocations (version: 16.1.0)  to get the users current location and plot it on the map.
- Android Navigation components for navigating from one screen to the next
  * to use this you must first enable the Navigation Editor:
    Click File > Settings(or Android Studio > Preferences on Mac), choose the Experimental category in the left panel, check Enable
    Navigation Editor, click OK and then restart Android Studio.
- Dagger2 version: 2.19 used to manage dependency to build components which can be easily enhanced
- LiveData
- Gson
- LocalBroadcast for communication between Activities, Fragments, Threads and Services
- EventBus version: 3.1.1 same use for communication between Activities, Fragments, Threads and Services
- MVVM and ViewModel 
 
- The application uses retrofit http client library for connecting to rest api.
 * For a full description of this http client library, visit 
   http://square.github.io/retrofit/
   
# Version Support

* minSdkVersion 21 and 
* targetSdkVersion 28 
* java version "1.8.0_101"
* buildToolsVersion '28.0.3'




