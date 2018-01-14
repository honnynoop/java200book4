package kr.co.infopub.chapter.s167;
public class TemperatureConv {
 public static double toCelsius(double fahrenheit){
	return 5./9 *(fahrenheit-32);
 }
 public static double toFahrenheit(double celsius){
	return 9.0/5*celsius+32;
 }
 public static void main(String[] args) {
	double celsius=29;
	double fahrenheit=0.0;
	fahrenheit=toFahrenheit(celsius);
	System.out.printf("%.2f ¼·¾¾= %.2f È­¾¾.\n",celsius,fahrenheit);
	
	celsius=toCelsius(fahrenheit);
	System.out.printf("%.2f È­¾¾= %.2f ¼·¾¾.\n",fahrenheit,celsius);
 }
}
