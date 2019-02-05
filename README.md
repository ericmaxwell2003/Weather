### Weather
This is a demo app built for teaching concepts.  It shows the weather for various cities by zip.

### Usage

1. Add a zip code.
2. It reverse geocodes the lat/long and location name from the zip code.
3. Uses the [darksky.net](https://darksky.net) weather API to fetch current forecast data.
4. Stores all of the data on device, persistent across launches.
5. Weather forecast data is cached, but refreshed by the repository on launch.

### Libraries + Concepts
* *Architectural Patterns* - Model-View-ViewModel, Repository, Dependency Injection.
* *Android GeoCoder API* - For reverse geocoding the zip code.
* *Architecture Compnents* - LiveData, Room, ViewModel, Navigation Architecture Components.

### Screenshots
![Get the weatherList forecast in your city!](WeatherAppScreenshots.png).

### Status
This is a work in progress, still adding tests and cleaning up organization.

### What this app is and isn't

**Features**
This app is meant for educational purposes as a reference to blog posts and other teachings.  
It is not meant to be a feature complete product.  

**Best Practices**
The code, while is meant to be written and tested with best practices, may not always reflect the latest.
For example, this project makes use of Anko and an early version of Coroutines that may not reflect current best practices.  

**Collaboration**
Since this app is a personal playground used for teaching purposes, I would prefer not to accept any PRs or Issues at this time. Though suggestions are always welcome.  You can reach out to me on Twitter @[emmax](https://twitter.com/emmax).
