package com.example.pojo;

import java.util.List;

public class TemperatureData {
	private List<String> time;
    private List<Double> temperature_2m;
    
	public TemperatureData(List<String> time, List<Double> temperature_2m) {
		super();
		this.time = time;
		this.temperature_2m = temperature_2m;
	}
	public TemperatureData() {
		// TODO Auto-generated constructor stub
	}

 

	public List<String> getTime() {
		return time;
	}
	public void setTime(List<String> time) {
		this.time = time;
	}
	public List<Double> getTemperature_2m() {
		return temperature_2m;
	}
	public void setTemperature_2m(List<Double> temperature_2m) {
		this.temperature_2m = temperature_2m;
	}


}
