package com.example.pojo;

public class WeatherData {
	private String date;
	private TemperatureData temperature;
	private String sunrise;
	private String sunset;

	public WeatherData(String date, TemperatureData temperature, String sunrise, String sunset) {
		super();
		this.date = date;
		this.temperature = temperature;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}

	public WeatherData() {
		// TODO Auto-generated constructor stub
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public TemperatureData getTemperature() {
		return temperature;
	}

	public void setTemperature(TemperatureData temperature) {
		this.temperature = temperature;
	}

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

}


