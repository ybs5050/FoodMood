username: test
password: test

Use Case #1: View Moods, View Mood Details
(Login -> Main Menu -> "View Moods" -> Select a Mood row from the table
 	-> "View Mood Details")

Use Case #2: Filter FoodMood by date 
(Login -> Main Menu -> "View Past Food/Mood" -> Select dates -> "Filter")

Use Case #3: Add FoodMood to Favorites and Show only favorites
(Login -> Main Menu -> "View Past Food/Mood" -> 
      Select a FoodMood row from the table -> "Add To Favorites" -> "Show Only Favorites")

Use Case #4: View Food/Mood Details 
(Login -> Main Menu -> "View Past Food/Mood" -> 
      Select a FoodMood row from the table -> "View Food/Mood Details")

Refactorimg Implementation

Youngmin Son- Create an Alert object generator class to remove duplicate code usage.
Classes used: Alerts, LoginController, FoodMoodListController, MoodListController,
FoodMoodController, MoodController
