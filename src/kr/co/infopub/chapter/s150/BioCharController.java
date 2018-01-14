package kr.co.infopub.chapter.s150;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;

public class BioCharController {
 @FXML
 private DatePicker dateTime;
 @FXML
 LineChart<String, Number> lineChart;
 @FXML
 CategoryAxis xAxis;
 @FXML
 NumberAxis yAxis;
 int daywidth=50;  // 양옆 날짜 수
 @FXML
 void onDateTimeClick(ActionEvent event) {
	lineChart.getData().clear();
	LocalDate date = dateTime.getValue();
    String ldStr = date.format(DateTimeFormatter.ISO_DATE);
    System.out.println("Local  Date: " + ldStr);
    // 생일부터 오늘까지 경과일
	long live=BioCalendar.days(
			date.getYear(), date.getMonthValue(), date.getDayOfMonth())  ; 
	yAxis.setLabel("Bio Power");
	xAxis.setLabel("Date");
	lineChart.setTitle("My Biorhythm");
	//lineChart.setLegendVisible(false);   // 레전드(보기표시) 제거
	lineChart.setCreateSymbols(false);     // dot 제거
	xAxis.setTickLabelsVisible(true);
	XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
	XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
	XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
	XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
	// 오늘 표시
	XYChart.Series<String, Number> series5 = new XYChart.Series<String, Number>();

	physicalChartData(series1,live);
	emotionalChartData(series2,live);
	intellectualChartData(series3,live);
	compositeChartData(series4,live);
	vertical(series5);
	lineChart.getData().addAll(series1, series2, series3,series4,series5);
 }
  public  void vertical(XYChart.Series<String, Number> series ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("Today");
    series.getData().add(
    		new XYChart.Data<String, Number>(RestDay.toStrDate(todayCal), 125));
    series.getData().add(
    		new XYChart.Data<String, Number>(RestDay.toStrDate(todayCal), -125));
  }
  public  void physicalChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("PHYSICAL");
    for (int i = -daywidth; i < daywidth+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getPhysical(live+i,100);
    	 series.getData().add(
    			 new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
  public  void emotionalChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("EMOTIONAL");
    for (int i = -daywidth; i < daywidth+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getEmotional(live+i,100);
    	 series.getData().add(
    			 new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
  public  void intellectualChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("INTELLECTUAL");
    for (int i = -daywidth; i < daywidth+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getIntellectual(live+i,100);
    	 series.getData().add(
    			 new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value));
    }
  }
  public  void compositeChartData(XYChart.Series<String, Number> series, long live ) {
	Calendar todayCal=Calendar.getInstance();
    series.setName("COMPOSITE");
    for (int i = -daywidth; i < daywidth+1; i++) {
    	 Calendar bf15day=RestDay.dateAfOrBf(todayCal,i);
    	 double value=BioCalendar.getIntellectual(live+i,100)
    			 +BioCalendar.getEmotional(live+i,100)
    			 +BioCalendar.getPhysical(live+i,100); 
    	 series.getData().add(
    			 new XYChart.Data<String, Number>(RestDay.toStrDate(bf15day), value/3.0));
    }
  }
}
