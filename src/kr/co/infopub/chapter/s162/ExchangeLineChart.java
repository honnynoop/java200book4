package kr.co.infopub.chapter.s162;
import org.json.JSONArray;
import org.json.JSONObject;
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
  @Override
  public void start(Stage primaryStage) {
	primaryStage.setTitle("Exchage Rate");  
	String date=RestDay.todates();  // ����
	String base="USD";              // 1�޷��� ȯ��   
	
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis(1080,1230,10);
	yAxis.setLabel("KRW /1 "+base);
	xAxis.setLabel("Date");
	LineChart<String, Number> lineChart = 
			        new LineChart<String, Number>(xAxis, yAxis);
	lineChart.setTitle("Exchage Rates "+date);
	
	XYChart.Series<String, Number> series1 =
			             new XYChart.Series<String, Number>();
	physicalChartData(series1,base, date);

	Scene scene = new Scene(lineChart, 1400, 800);
    lineChart.getData().addAll(series1);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
  public  void physicalChartData(XYChart.Series<String, Number> series1, 
		  String base,String date ) {
    series1.setName("KRW");
    JSONObject jsobj=FixerConvertJSON.convert(date,base);
    JSONArray rates=jsobj.getJSONArray("rates");
	for(int i=0; i< rates.length() ;i++){ // �ϳ� 0~365 366��
		JSONObject fix=rates.getJSONObject(i);
		// 365���� ���� ���ñ���
		String todays=RestDay.toWantedDay(date,i+1-rates.length()); //1+365-366
    	if(!RestDay.isRest(todays)){
    		series1.getData().add(
    		  new XYChart.Data<String, Number>(todays, fix.getDouble("KRW") ));
    		System.out.println(fix);
    	}
	}
  }
}