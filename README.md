# WeatherApi
The Open Meter API is a free to use API for getting simple weather data. The following API call will give you hourly temperature in °C, sunrise and sunset time for 7 days.

# Dependencies
This API uses the following libraries:
com.fasterxml.jackson.core:jackson-databind:2.13.0: for JSON parsing and serialization
org.springframework:spring-web:5.3.8: for building the RESTful API

# Approaches
Created two Pojo class 
The code above is a Java Spring MVC controller that handles a GET request to the endpoint "/weather". It takes in a query parameter "date" and sends a request to the Open-Meteo API to get the weather data for the specified date at the latitude and longitude coordinates provided in the API URL.

The response from the API is in JSON format, which is parsed using the Jackson ObjectMapper library to create a JsonNode object. The code then extracts the required data from the JSON response, including the temperature data and sunrise/sunset times. It populates a WeatherData object with this data and returns it to the client.

If no temperature data is available, the code returns default values. If an HTTP error code is received, the code throws a RuntimeException.

# Usage
Request
The API endpoint accepts a date parameter in the format yyyy-mm-dd. The parameter is required.

GET /weather?date=

# Response
The API returns a JSON object containing the temperature data and sunrise/sunset times for the given date.
{"date": "yyyy-mm-dd","temperature": 
{"time": [],"temperature_2m": []},"sunrise": "","sunset": ""}
