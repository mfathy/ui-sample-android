# La-Delice-Android
Restaurant app inspiration demo for Android from uplabs.
https://www.uplabs.com/posts/restaurant-app-for-android

# Instructions
1. Navigate to [La-Delice-Android](https://github.com/mfathy/La-Delice-Android)
2. Clone locally using:
```git clone https://github.com/mfathy/La-Delice-Android.git```
3. Open project using Android studio, then wait until project syncs and builds successfuly.
4. Run App using Android studio.

# Discussion
I have used MVP architecture to build the App structure with only remote data source with offline server mock for API calls.
Also I have used the following frameworks: 
* [RxJava: for handling fetching data.](https://github.com/ReactiveX/RxJava)
* [Retrofit: for handling network calls.](https://github.com/square/retrofit)
* [Glide: for handling image loading and caching.](https://github.com/bumptech/glide)
* [Gson: for Java serialization/deserialization library to convert Java Objects into JSON and back.](https://github.com/google/gson)
* Android support libraries like AppCompat, Design, CardView, RecyclerView.
* Android testing frameworks like jUnit, Mockito, Hamcrest, Espresso.
* A utility module for common android helper classes.

# Project structure:
As I mentioned before I've used MVP architecture as follows:

    .
    ├── data           # data layer which uses a repository design pattern for handling reading from multiple sources.
    │   ├── model          # network java models.
    │   ├── remote         # remote data source files.
    │   ├── local          # local data sources files. ** Not supported in this project **
    │   ├── .....          # base data source interface and its implementation classes.
    ├── view           # view and presentaion layer which includes all UI required files and preseters.
    │   ├── adapter        # has all adapters and helper classes.
    │   ├── menu           # has MenuActivity and it's presenter and contract.
    │   ├── model          # has all view models only.
    │   ├── .....          # has other activities like AboutActivity, HomeActivity and ContactActivity.

# Task Requirements:
I've selected this [design](https://www.uplabs.com/posts/restaurant-app-for-android) and tried to find all the required files that match the design spirt, also I've added two screens to it (Menu & Contact) to make the design complete.

I've created an offline API reponse you can find under assets folder, this is used when the API server is not available which I am using on this project as the main data source.

I've created a design mockup for the project and completed all the design requirements and project requirement except the following I couldn't manage to finish them due to my time availability, those tasks are:
* Title Slide up and down animation in HomeActivity.
* Phone Call Icon Slide up and down animation in HomeActivity.
* Change preview ImageView to ViewPager in AboutActivity, and Add Fade animation to ViewPager AboutFragment.
* Add Fade animation to numbers when selecting meal.
* Complete ContactActivity design.

# Bonuses: 
I've included 2 screen as I mentioned before to complete the app requirements (Menu & Contact) screens.
Also I will try to finish the above missing requirements on another branch, then I will update this README as well so you can take a look on what I will do.

# Mockups:
![alt text](https://raw.githubusercontent.com/mfathy/La-Delice-Android/master/art/1531695753932.jpg "Mockups")
