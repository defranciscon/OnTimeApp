Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?

The app I designed was a mobile app for tracking events. The requirements of the app were to build an app that tracks user defined events that that stores and display events on grid-like layout.
The app stored the event names, dates, times, location, and description on the main page. The app needed to provide the ability for users to add events, edit events, and delete events. Users should 
be able to login to the app with a username and password or register if they have never logged in before. The app needed to check permissions to see if the user would allow the SEND_SMS permission 
for automatic notifications as well. 

The screens necessary were the main screen after successful login. I used a recycler view so that the user could scroll the screen if they needed to. I believe the designs were successful because I 
designed around the user interaction. I made sure each screen only contained relevant necessary information and that the components had a enough color contrast to stand out. 
For coding, I certainly did a lot more coding than I initially planned. I did however try to keep things as modular as possible especially when it came to the UI versus the application logic. 

For testing, I did multiple runs as I added more features and functionality. I used the debug and logcat feature quite a bit because there were so many errors i dealt with as I was building the app.
The debugger was really handy when it came to that. 
I believe my biggest innovation in coding the application was adapting the calendar java class to the TimePicker and DatePicker dialogs and text views. The app need to read input through the dialog
boxes as integer values, convert them into strings, and then render them back as strings with formatting to give them the appearance of integers. this was challenging because of the many fragments 
that were nested some of the views. I had to ensure that the data persisted throughout the different levels of the app. I felt very strongly in my input validation to ensure that the database operations for verifying username, password, and exisiting users functioned properly.
