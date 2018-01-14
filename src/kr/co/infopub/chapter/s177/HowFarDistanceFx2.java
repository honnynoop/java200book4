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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class HowFarDistanceFx2 extends Application {
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
        URL url = getClass().getResource("geonew2.html");
        //웹호출
        webEngine.load(url.toExternalForm());
        app=new JavaApplication(stage);
        
//        webEngine.getLoadWorker().stateProperty().addListener(
//                new ChangeListener<State>(){
//                     
//                    @Override
//                    public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
//                    	System.out.println("------state-----------------------------------"+newState);
//                        if(newState == State.SUCCEEDED){
//                        	JSObject window = (JSObject) webEngine.executeScript("window");
//                            window.setMember("app", app);  //app이름으로 호출할 JS
//                        }
//                    }
//                });
         webEngine.getLoadWorker().stateProperty().addListener(
            (ov,  oldState,  newState) ->{
                    System.out.println("------state-----------------------------------"+newState);
                    if(newState == State.SUCCEEDED){
                    	JSObject window = (JSObject) webEngine.executeScript("window");
                        window.setMember("app", app);  //app이름으로 호출할 JS
                    }
                });
        //세팅준비
//        webEngine.load(url.toExternalForm());
//        JSObject window = (JSObject) webEngine.executeScript("window");
//        window.setMember("app", new JavaApplication(stage));    

        
        VBox root = new VBox(10);
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(5));
        hbox.setAlignment(Pos.CENTER);
        
       
        
        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            Platform.exit();
        });
        Button btnAbout = new Button("About");
        //자바에서 스크립트로 전달
        btnAbout.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
            	webEngine.executeScript( " updateMessage(' " + stage.getTitle()+" "+new Date() + " ') " );
            }
        });
        //자바에서 스크립트로 전달
        Button btnClear = new Button("Clear");
        btnClear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
            	webEngine.executeScript( "clearMessage()" );
            	lbhowfar.setText("");
            }
        });
        
        Button btnReadFx = new Button("ReadFx");
        btnReadFx.setOnAction(new EventHandler<ActionEvent>(){
        	
            @Override
            public void handle(ActionEvent arg0) {
            	FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage);
               
                try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                    Object obj = JSONValue.parse(reader);
                    //json을 자바스트립트에 넝어서 라인을 그림
                    webEngine.executeScript("drawLine(" + obj + ")");
                    System.out.println("read------------------------------------->"+file.getAbsolutePath());
                    //거리 구하기
                    HowFarDistance2 howfarw=new HowFarDistance2(); //json
                    howfarw.makeDistances(file.getAbsolutePath());
                    double dd=howfarw.howfar();
                    lbhowfar.setText(String.format("거리: %.2fkm", dd));
                    webEngine.executeScript( " updateMessage(' " + "You walk "+dd+" Km." + " ') " );
                } catch (Exception ex) {
                   System.out.println("read----Exception--------------------------------->"+ex);
                   webEngine.executeScript( "clearMessage() " );
                }
            }
        });
        
        Button Refresh = new Button("Refresh");
        Refresh.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {	
             webEngine.load(url.toExternalForm());
             JSObject window = (JSObject) webEngine.executeScript("window");
             window.setMember("app", app= new JavaApplication(stage));
            }
        });
        Button btndist = new Button("거리구하기");
        btndist.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {	
            	webEngine.executeScript( " calculateFX()" );
            }
        });
        hbox.getChildren().addAll(lbhowfar,btnAbout,btnClear,btnReadFx,btndist,Refresh,btnExit);
        root.getChildren().addAll(webView, hbox);
        //Scene scene = new Scene(root, 1300, 650); //600,600
        Scene scene = new Scene(root, 1400, 700);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * 웹엔진에서 호출할 자바스크립ㅌ
     */
    public class JavaApplication {
       
    	Stage stage;
    	public JavaApplication(Stage stage){
    		this.stage=stage;
    		
    	}
        public void saveJSToJavaFxFile(String msg) {
        	System.out.println("-------------------------------->"+msg);
        	FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(this.stage);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(msg);
            } catch (IOException ex) {
            	System.out.println("-------------------------------->"+ex);
            }
        }
        public void calculate(String msg) {
        	double tot=0.0;
        	tot=HowFarDistance2.howfarDistance(getLatLng(msg))	;	
        	lbhowfar.setText(String.format("거리: %.2fkm", tot));
        }
        private  double [][] getLatLng(String json){
    		JSONObject jObject = new JSONObject(json);
    		JSONArray loc = jObject.getJSONArray("loc");
    		double [][]latlng=new double[loc.length()][2];
    		if(loc!=null){
        		for(int i=0; i<loc.length(); i++) {
        			JSONObject local = loc.getJSONObject(i);
        			int marknum=local.getInt("marknum");
        			String markerX = local.getString("markerX");
        			String markerY = local.getString("markerY");
        			latlng[i][0]=Double.parseDouble(markerX.trim());
        			latlng[i][1]=Double.parseDouble(markerY.trim());
        		}	
    		}
    		return latlng;
    	}
    }
}
