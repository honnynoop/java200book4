package kr.co.infopub.chapter.s149;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
public class Biorythms extends Application{
 public static void main(String[] args) {
    launch(args);
  }
  int dayWith=25;   // 기준일에 대하여 앞뒤로 25일 보이기
  @Override
  public void start(Stage primaryStage) {
	// 생일  
	int year=1971;
	int month=9;
	int day=28;
	long live=BioCalendar.days(year, month, day)  ;  // 오늘까지 살아온날
	
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	yAxis.setLabel("Bio Power");
	xAxis.setLabel("Date");
	LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	lineChart.setTitle("My Biorhythm");
    // 신체지수 
	XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
	XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();
	physicalChartData(series1,live);  // 신체지수 그리기
	vertical(series5);                // 수직선 그리기
	
	Scene scene = new Scene(lineChart, 1200, 600);
    lineChart.getData().addAll(series1,series5); // 점들을 선으로 연결
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  // 오늘 수직선
  public  void vertical(XYChart.Series<String, Number> series ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("Today");
    // 오늘- 최대값, 최소값을 수직연결
    series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(todayCal), 125));
    series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(todayCal), -125));
  }
  // 신체 지수 그리기 - 점들연결
  public  void physicalChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("PHYSICAL");
    for (int i = -dayWith; i < dayWith+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);  // 살아온날
    	 double value=BioCalendar.getPhysical(live+i,100); // 신체지수 값
    	 // 날짜에 대하여 신체지수값을 XY로 점을 찍고 연결
    	 series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
}
