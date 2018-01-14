package kr.co.infopub.chapter.s168;
public class TemperatureConv {
	public static double toCelsius(double fahrenheit){
		return 5./9 *(fahrenheit-32);
	}
	public static double toFahrenheit(double celsius){
		return 9.0/5*celsius+32;
	}
}
