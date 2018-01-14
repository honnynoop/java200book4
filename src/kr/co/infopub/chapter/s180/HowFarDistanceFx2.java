package kr.co.infopub.chapter.s180;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

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
@Override
public void start(Stage stage) {
	
	stage.setTitle("World EarthQuakes");
	
	WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    URL url = getClass().getResource("geonew3.html");
    //��ȣ��
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
//                            window.setMember("app", app);  //app�̸����� ȣ���� JS
//                        }
//                    }
//                });
     webEngine.getLoadWorker().stateProperty().addListener(
        (ov,  oldState,  newState) ->{
                System.out.println("------state-----------------------------------"+newState);
                if(newState == State.SUCCEEDED){
                	JSObject window = (JSObject) webEngine.executeScript("window");
                    window.setMember("app", app);  //app�̸����� ȣ���� JS
                }
            });
    //�����غ�
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
    
    Button btnAbout = new Button("WebData");
    //�ڹٿ��� ��ũ��Ʈ�� ����
    btnAbout.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	Object obj=getJson();
            webEngine.executeScript("drawLine(" + obj+ ")");
        }
    });
    
    Button btn2017 = new Button("Web2017");
    //�ڹٿ��� ��ũ��Ʈ�� ����
    btn2017.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	Object obj=getJson("2017","2017");
            webEngine.executeScript("drawLine(" + obj+ ")");
        }
    });
    Button btn2016 = new Button("Web2016");
    //�ڹٿ��� ��ũ��Ʈ�� ����
    btn2016.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	Object obj=getJson("2016","2016");
            webEngine.executeScript("drawLine(" + obj+ ")");
        }
    });
    Button btn2015 = new Button("Web2015");
    //�ڹٿ��� ��ũ��Ʈ�� ����
    btn2015.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	Object obj=getJson("2015","2015");
            webEngine.executeScript("drawLine(" + obj+ ")");
        }
    });
    Button btn2014 = new Button("Web2014");
    //�ڹٿ��� ��ũ��Ʈ�� ����
    btn2014.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	Object obj=getJson("2014","2014");
            webEngine.executeScript("drawLine(" + obj+ ")");
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
                //json�� �ڹٽ�Ʈ��Ʈ�� �վ ������ �׸�
                System.out.println(obj);
                webEngine.executeScript("drawLine(" + obj + ")");
                System.out.println("read------------------------------------->"+file.getAbsolutePath());
                //�Ÿ� ���ϱ�
                //HowFarDistance2 howfarw=new HowFarDistance2(); //json
                //howfarw.makeDistances(file.getAbsolutePath());
                //double dd=howfarw.howfar();
                
                //webEngine.executeScript( " updateMessage(' " + "You walk "+dd+" Km." + " ') " );
            } catch (Exception ex) {
               System.out.println("read----Exception--------------------------------->"+ex);
              // webEngine.executeScript( "clearMessage() " );
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
    
    hbox.getChildren().addAll(btnAbout,btn2017,btn2016,btn2015,btn2014,btnReadFx,Refresh,btnExit);
    root.getChildren().addAll(webView, hbox);
    //Scene scene = new Scene(root, 1300, 650); //600,600
    Scene scene = new Scene(root, 1220, 720);
    stage.setScene(scene);
    stage.show();
}
 public static Object getJson(){
	return getJson("2010");
 }
 public static Object getJson(String fyear){
	return getJson(fyear, todate2(new Date()));
 }
 public static Object getJson(String fyear,String tyear){
	EarthRequest rfw=new EarthRequest();
	rfw.earthrequests(tyear);
	Object obj = JSONValue.parse(GeoPrint2.toJson2(rfw.getEarthquakes()));
	return obj;
 }
public static String todate2(Date dd){
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	return sdf.format(dd);
}
public static String para(String key, String value){
	return String .format("%s=%s",key,value);
}
/**
 * ���������� ȣ���� �ڹٽ�ũ����
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
}
}
