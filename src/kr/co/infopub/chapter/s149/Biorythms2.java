package kr.co.infopub.chapter.s149;
import java.util.Calendar;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
public class Biorythms2 extends Application{
 public static void main(String[] args) {
    launch(args);
  }
  int dayWith=25;   // 기준일에 대하여 앞뒤로 25일 보이기
  @Override
  public void start(Stage primaryStage) {
	// 생일  
	int year=191;
	int month=9;
	int day=28;
	// 생일부터 오늘까지 몇일 살았는가?
	long live=BioCalendar.days(year, month, day)  ;  //오늘까지
	
	CategoryAxis xAxis = new CategoryAxis(); // X축
	NumberAxis yAxis = new NumberAxis();     // Y축
	yAxis.setLabel("Bio Power");
	xAxis.setLabel("Date");
	// XY 평면 준비
	LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	lineChart.setTitle("My Biorhythm");
    // 1. 신체, 2. 감정, 3. 지능, 4. (1+2+3)/3 복합, 5. 오늘
	XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>(); // 1
	XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>(); // 2
	XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>(); // 3
	XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>(); // 4
	XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>(); // 5
	physicalChartData(series1,live);
	emotionalChartData(series2,live);
	intellectualChartData(series3,live);
	compositeChartData(series4,live);
	vertical(series5);
	
	Scene scene = new Scene(lineChart, 1200, 600);
    lineChart.getData().addAll(series1, series2, series3,series4,series5);
    
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  // 오늘 수직선
  public  void vertical(XYChart.Series<String, Number> series ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("Today");
    series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(todayCal), 125));
    series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(todayCal), -125));
  }
  // 신체 지수 그리기 - 점들연결
  public  void physicalChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("PHYSICAL");
    for (int i = -dayWith; i < dayWith+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getPhysical(live+i,100);
    	 series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
  // 감정 지수 그리기
  public  void emotionalChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("EMOTIONAL");
    for (int i = -dayWith; i < dayWith+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getEmotional(live+i,100);
    	 series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
  // 지능 지수 그리기
  public  void intellectualChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("INTELLECTUAL");
    for (int i = -dayWith; i < dayWith+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getIntellectual(live+i,100);
    	 series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
  // 복합 (신체+감정+지능)/3
  public  void compositeChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("COMPOSITE");
    for (int i = -dayWith; i < dayWith+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getIntellectual(live+i,100)
    			 +BioCalendar.getEmotional(live+i,100)
    			 +BioCalendar.getPhysical(live+i,100);
    	 series.getData().add(new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value/3.0));
    }
  }
}
