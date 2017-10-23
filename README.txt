USE CASE #1: Add Mood to the application. 

Decorator Implementation - Youngmin Son
app.java - Line 48
MoodController.java - Line 116

Input prompt Implementation - Youngmin Son
Login.fxml - Line 19, 20
AddMood.fxml - Line 18,19,20

Object-Oriented Pattern: Iterator - Justin Grant
FoodMoodController.java - Line 22, 148-169
Description: Rather than implementing a custom iterator or overriding the 
standard Java libraries iterator, I utilized the existing iterator from the
Java Collection framework. By using this pattern, we can cut down significantly
on the need for extra code (eg. using for loops to iterate through a list).
Other classes that will be affected are moodController and foodController (once
these classes are built). Iterator code for these classes will be identitical 
or very similar depending on the needed operations as defined by the use cases. 

User Interface Pattern: Sorting/Filtering Data - Justin Grant
FoodMood.fxml - Line 15-20, 23-25
FoodMoodController.fxml - Line 159-169
Description: Since the Food/Mood list contains a list of all Food/Mood entries,
it will theoretically be the longest list in the program. This requires more
advanced data management in order for users to be able to easily find the data
they are looking for. The classes above have been modified to allow for sorting
(ascending/descending) by column, and entering start/end dates for filtering by
date. This should allow the user to more easily find needed data entries from
this list. Start/end date filtering is handled via list iteration (see above), 
and column sorting is handled via the "sortable" property in javafx TableColumn.
