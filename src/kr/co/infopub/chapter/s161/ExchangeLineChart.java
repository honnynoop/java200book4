package kr.co.infopub.chapter.s161;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ExchangeLineChart extends Application{
  public static void main(String[] args) {
    launch(args);
  }
  int range=50;
  @Override
  public void start(Stage primaryStage) {
	primaryStage.setTitle("Exchange Rate");  
	String date=RestDay.todates();  // 오늘
	String base="USD";              // 1달러당 환율
	
	CategoryAxis xAxis = new CategoryAxis();         // X축-날짜
	NumberAxis yAxis = new NumberAxis(1080,1180,10); // Y축-환율
	yAxis.setLabel("KRW / 1 "+base);   // Y축 라벨
	xAxis.setLabel("Date");                          // X축 라벨
	LineChart<String, Number> lineChart = 
			new LineChart<String, Number>(xAxis, yAxis); // 좌표
	lineChart.setTitle("Exchange Rate "+date);

	XYChart.Series<String, Number> series1 = 
			   new XYChart.Series<String, Number>(); //(X, Y) 점들
	physicalChartData(series1,base, date);
	
	Scene scene = new Scene(lineChart, 1400, 800);
    lineChart.getData().addAll(series1);    // 좌표에  (X, y)점 붙일준비 
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public  void physicalChartData(
		  XYChart.Series<String, Number> series1, 
		  String base,String date ) {
    series1.setName("KRW");
    for (int i = -range; i < 1; i++) {
    	String todays=RestDay.toWantedDay(date,i);  // 오늘에서 i일전날
    	if(!RestDay.isRest(todays)){     //토, 일요일 제외
    		// 날짜별 환율정보 객체 얻기
    		FixerRate fixerrate=FixerConvert.convert(todays,base);
	    	series1.getData().add(  // (X,Y)->(날짜, 환율) 점 붙이기
	    	new XYChart.Data<String, Number>(todays, fixerrate.krw));
    	}
    }
  }
}
