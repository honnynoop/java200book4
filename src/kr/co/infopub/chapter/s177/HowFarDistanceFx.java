package kr.co.infopub.chapter.s177;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Worker.State;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
public class HowFarDistanceFx extends Application {
 public static void main(String[] args) {
        launch(args);
 }
 JavaApplication app;
 Label lbhowfar=new Label("");
 @Override
 public void start(Stage stage) {
	stage.setTitle("거리는 얼마인가요?");
	WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    URL url = getClass().getResource("geonew.html");
    //웹호출
    webEngine.load(url.toExternalForm());
    app=new JavaApplication(stage);
    webEngine.getLoadWorker().stateProperty().addListener(
       (ov,  oldState,  newState) ->{
        System.out.println("------state-------"+newState);
        if(newState == State.SUCCEEDED){
        	JSObject window = (JSObject) webEngine.executeScript("window");
            window.setMember("app", app);  //app이름으로 호출할 JS
        }
      });  
    VBox root = new VBox(10);       // 수직 - 웹뷰와 HBox 붙이기
    HBox hbox = new HBox(10);       // 수평 - 버튼들 붙이기
    hbox.setPadding(new Insets(5));
    hbox.setAlignment(Pos.CENTER);
    // Exit 버튼
    Button btnExit = new Button("Exit");
    btnExit.setOnAction(e -> {Platform.exit();});
    // About 버튼
    Button btnAbout = new Button("About");
    btnAbout.setOnAction(v-> {
        webEngine.executeScript( " updateMessage(' " +
        stage.getTitle()+" "+new Date() + " ') " );}
    );
    // Clear버튼
    Button btnClear = new Button("Clear");
    btnClear.setOnAction( v->{
    	webEngine.executeScript( "clearMessage()" );
    	lbhowfar.setText("");}
    );
    // ReadFx 버튼
    Button btnReadFx = new Button("ReadFx");
    btnReadFx.setOnAction(v-> {
    	FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        try (BufferedReader reader = new BufferedReader(
        		                   new FileReader(file))){
            Object obj = JSONValue.parse(reader);
            //json을 자바스트립트에 넝어서 라인을 그림
            webEngine.executeScript("drawLine(" + obj + ")");
            System.out.println("read------->"+file.getAbsolutePath());
            //거리 구하기
            HowFarDistance2 howfarw=new HowFarDistance2(); //json
            howfarw.makeDistances(file.getAbsolutePath());
            double dd=howfarw.howfar();
            lbhowfar.setText(String.format("거리: %.2fkm", dd));
            webEngine.executeScript( 
            	" updateMessage(' " + "You walk "+dd+" Km." + " ') " );
        } catch (Exception ex) {
           System.out.println("read----Exception---------->"+ex);
           webEngine.executeScript( "clearMessage() " );
        }}
    );
    // Refresh 버튼
    Button Refresh = new Button("Refresh");
    Refresh.setOnAction(v->{	
     webEngine.load(url.toExternalForm());
     JSObject window = (JSObject) webEngine.executeScript("window");
     window.setMember("app", app= new JavaApplication(stage));}
    );
    // 거리구하기 버튼
    Button btndist = new Button("거리구하기");
     btndist.setOnAction(
    		 v->{webEngine.executeScript(" calculateFX()");}
    );
    // 수평으로 붙이기 - 7개의 버튼을 수평으로 붙이기
    hbox.getChildren().addAll(
       lbhowfar,btnAbout,btnClear,btnReadFx,btndist,Refresh,btnExit);
    // 수직으로 웹뷰, hbox 붙이기
    root.getChildren().addAll(webView, hbox);
    Scene scene = new Scene(root, 1400, 700);
    stage.setScene(scene);
    stage.show();
 }
 // JS에서 app.메서드()로 호출
 public class JavaApplication {
  Stage stage;
 public JavaApplication(Stage stage){
	this.stage=stage;
	
 }
 // JS-> 자바메서드 호출, 웹뷰 파일을 자바파일로 저장
 public void saveJSToJavaFxFile(String msg) {
	System.out.println("--------------------->"+msg);
	FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(this.stage);
    try (BufferedWriter writer = new BufferedWriter(
    		                           new FileWriter(file))) {
        writer.write(msg);
    } catch (IOException ex) {
    	System.out.println("------------>"+ex);
    }
 }
 // JS-> 자바메서드 호출, 거리 총합 구하기, 웹뷰에 붙이기
 public void calculate(String msg) {
	double tot=0.0;
	// 2차원 배열을 이용하여 거리 구하기
	tot=HowFarDistance2.howfarDistance(getLatLng(msg))	;	
	lbhowfar.setText(String.format("거리: %.2fkm", tot));
 }
 // JSON을 이차원 배열로
 private  double [][] getLatLng(String json){
	JSONObject jObject = new JSONObject(json);
	JSONArray loc = jObject.getJSONArray("loc");
	double [][]latlng=new double[loc.length()][2];
	if(loc!=null){
		for(int i=0; i<loc.length(); i++) {
			JSONObject local = loc.getJSONObject(i);
			String markerX = local.getString("markerX");
			String markerY = local.getString("markerY");
			latlng[i][0]=Double.parseDouble(markerX.trim());
			latlng[i][1]=Double.parseDouble(markerY.trim());
		}	
	}
	return latlng;
  }
 }// nested class JavaApplication
}//class 
