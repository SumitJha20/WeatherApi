# WeatherApi
The Open Meter API is a free to use API for getting simple weather data. The following API call will give you hourly temperature in °C, sunrise and sunset time for 7 days.

# Dependencies
This API uses the following libraries:
com.fasterxml.jackson.core:jackson-databind:2.13.0: for JSON parsing and serialization
org.springframework:spring-web:5.3.8: for building the RESTful API

# Approaches
This is a Java Spring Boot @RestController that handles a GET request to the "/weather" endpoint with a required query parameter date.
The API URL is constructed with the given latitude, longitude, timezone, and date parameters.
A HttpURLConnection is opened to the constructed URL and a GET request is made.
If the response code is HTTP_OK, the response is read and stored in a StringBuilder.
The response is parsed into a JSON tree using the Jackson library's ObjectMapper.
If the JSON tree has an "hourly" object, the temperature data is extracted from the "temperature_2m" array and the time data is extracted from the "time" array. These data are stored in a TemperatureData object.
If the JSON tree has a "daily" object, the sunrise and sunset data are extracted and stored in the WeatherData object.
The WeatherData object is populated with the TemperatureData, date, sunrise, and sunset data.
If temperature data is available, the WeatherData object is returned. Otherwise, default values are set for the temperature data and the WeatherData object is returned.

The WeatherData and TemperatureData classes are POJOs used to represent the JSON data returned from the API. The WeatherData class has date, sunrise, and sunset fields, and a TemperatureData field. The TemperatureData class has time and temperature_2m fields. The JSON data is parsed into these objects for easier handling in the Java code.

If no temperature data is available, the code returns default values. If an HTTP error code is received, the code throws a RuntimeException.

# Usage
Request
The API endpoint accepts a date parameter in the format yyyy-mm-dd. The parameter is required.

GET /weather?date=

# Response
The API returns a JSON object containing the temperature data and sunrise/sunset times for the given date.
{"date": "yyyy-mm-dd","temperature": 
{"time": [],"temperature_2m": []},"sunrise": "","sunset": ""}
