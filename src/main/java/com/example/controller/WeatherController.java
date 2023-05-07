package com.example.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pojo.TemperatureData;
import com.example.pojo.WeatherData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class WeatherController {

	@GetMapping("/weather")
	public WeatherData getWeather(@RequestParam String date) throws IOException {
		String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=12.97&longitude=77.59&hourly=temperature_2m&timezone=auto&current_weather=true&daily=sunrise&daily=sunset";
		apiUrl += "&date=" + date;

		URL url = new URL(apiUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = reader.readLine()) != null) {
				response.append(inputLine);
			}
			reader.close();

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response.toString());
			System.out.println(rootNode);
			TemperatureData temperatureData = new TemperatureData();
			List<String> timeList = new ArrayList<>();
			List<Double> temperatureList = new ArrayList<>();
			WeatherData weatherData = new WeatherData();
			if (rootNode.has("hourly")) {
				JsonNode hourlyNode = rootNode.get("hourly");
				System.out.println(hourlyNode);
				if (hourlyNode.has("temperature_2m")) {
					JsonNode temperatureNode = hourlyNode.get("temperature_2m");
					if (temperatureNode.isArray()) {
						for (JsonNode node : temperatureNode) {
							temperatureList.add(node.asDouble());
						}
					}
				}
				if (hourlyNode.has("time")) {
					JsonNode timeNode = hourlyNode.get("time");
					if (timeNode.isArray()) {
						for (JsonNode node : timeNode) {
							timeList.add(node.asText());
						}
					}
				}
				temperatureData.setTime(timeList);
				temperatureData.setTemperature_2m(temperatureList);
				weatherData.setTemperature(temperatureData);
			}


			if (rootNode.has("daily")) {
				JsonNode dailyNode = rootNode.get("daily");
				System.out.println(dailyNode);
				if (dailyNode.isObject()) {
					String sunrise="";
					sunrise=dailyNode.get("sunrise").get(0).asText();
					String sunset="";
					sunset=dailyNode.get("sunset").get(0).asText();
					weatherData.setSunrise(sunrise);
					weatherData.setSunset(sunset);
				}
			}
			weatherData.setDate(date);
			if (weatherData.getTemperature() != null && !weatherData.getTemperature().getTemperature_2m().isEmpty()) {
				return weatherData;
			} else {
				// If no temperature data is available, return default values
				weatherData.setTemperature(new TemperatureData());
				weatherData.getTemperature().setTime(new ArrayList<>());
				weatherData.getTemperature().setTemperature_2m(new ArrayList<>());
				return weatherData;
			}
		} else {
			throw new RuntimeException("HTTP error code : " + responseCode);
		}
	}
}




