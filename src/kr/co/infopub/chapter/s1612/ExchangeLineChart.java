package kr.co.infopub.chapter.s1612;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal=Calendar.getInstance();
		String date=sdf.format(cal.getTime());
		//String base="JPY";     
		String base="USD";     
		
		CategoryAxis xAxis = new CategoryAxis();
		NumberAxis yAxis = new NumberAxis(1080,1180,10);
		//NumberAxis yAxis = new NumberAxis(9.5,10.7,0.1);
		yAxis.setLabel("KRW ,10JPY, 165CNY/ 1 "+base);
		xAxis.setLabel("Date");
		LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);

		lineChart.setTitle("Exchange Rate "+date);

		
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		//XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
		//XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
		physicalChartData(series1,base, date);

		
		Scene scene = new Scene(lineChart, 1400, 800);
	    lineChart.getData().addAll(series1);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	  }

	  public  void physicalChartData(
			  XYChart.Series<String, Number> series1, 
			  String base,String date ) {
		//Calendar todate =todate(date);
	    series1.setName("KRW");
	   // series2.setName("JPY");
	    //series3.setName("CNY");
/*	    double max=0.0;
	    double min=0.0;*/
	    for (int i = -range; i < 1; i++) {
	    	String todays=RestDay.toWantedDay(date,i);
	    	if(!RestDay.isRest(todays)){
	    		FixerRate fixerrate=FixerConvert.convert(todays,base);
		    	series1.getData().add(new XYChart.Data<String, Number>(todays, fixerrate.krw));
	    	}
	    }
	  }
		
}
