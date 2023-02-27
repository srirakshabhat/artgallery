# artgallery
A sample android app that shows arts list, search art and show details for each art.

https://user-images.githubusercontent.com/38211165/221505383-3bae4c19-2d51-4cc1-898a-35d0fdf34a04.mp4

### Implemented using MVVM with Clean Architecture
Structure of this project has 3 layers:
- Presentation
- Domain
- Data

- Have used Retrofit for network communication
- Coroutines have been used to handle long running tasks
- Hilt is used for dependency injection
- View Binding is used to interact with views
- Navigation library is used to navigate between screens

### Communication between layers

1. UI calls method from ViewModel.
2. ViewModel executes Use case.
3. Use case returns data to viewmodel.
4. Each Repository returns data from api Data Source.
5. Information flows back to the UI where we display the list of arts.


### Scenario
Used https://metmuseum.github.io/ public api to get data in the app

At a glance:

- Created a list of Art
- User can search art using search bar in the list screen
- On click of each item in the list, user is directed to art details screen
- Details screen shows art image, art name, title, department and additional photos if there are any
- UI tests have been added for activity
- Unit tests have been added for usecase layer and repo layer. 
