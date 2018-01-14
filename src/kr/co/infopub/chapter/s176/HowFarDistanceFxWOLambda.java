package kr.co.infopub.chapter.s176;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import org.json.simple.JSONValue;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
// lambda를 사용하지 않고 
public class HowFarDistanceFxWOLambda extends Application {
 public static void main(String[] args) {
    launch(args);
 }
 JavaApplication app;   // JS가 JAVA 메서드 호출가능
 @Override
 public void start(Stage stage) {
	stage.setTitle("얼마나 걸었어?");
	WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    URL url = getClass().getResource("geo.html");
    //웹호출
    webEngine.load(url.toExternalForm());
    //자바와 웹 브라우저 접근 가능
    app=new JavaApplication(stage);
    webEngine.getLoadWorker().stateProperty().addListener(
	    new ChangeListener<State>(){
	      @Override
	      public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
	      	System.out.println("------state-----------------------------------"+newState);
	          if(newState == State.SUCCEEDED){
	          	JSObject window = (JSObject) webEngine.executeScript("window");
	              window.setMember("app", app);  //app이름으로 호출할 JS
	          }
	      }
	    });
    VBox root = new VBox(10);      // 수직으로 붙이기
    HBox hbox = new HBox(10);      // 수평으로 붙이기
    hbox.setPadding(new Insets(5));
    hbox.setAlignment(Pos.CENTER);
    // Exit 버튼
    Button btnExit = new Button("Exit");
    btnExit.setOnAction(e -> {
        Platform.exit();
    });
    // About 버튼
    Button btnAbout = new Button("About");
    // 자바에서 스크립트로 전달
    btnAbout.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	webEngine.executeScript( " updateMessage(' " + stage.getTitle()+" "+new Date() + " ') " );
        }
    });
    // Clear 버튼
    Button btnClear = new Button("Clear");
    // 자바에서 스크립트로 전달
    btnClear.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	webEngine.executeScript( "clearMessage()" );
        }
    });
    // ReadFx 버튼
    Button btnReadFx = new Button("ReadFx");
    btnReadFx.setOnAction(new EventHandler<ActionEvent>(){	
        @Override
        public void handle(ActionEvent arg0) {
        	FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            try (BufferedReader reader = new BufferedReader(new FileReader(file))){
                Object obj = JSONValue.parse(reader);
                webEngine.executeScript("drawLine(" + obj + ")");
                System.out.println("read------------------------------------->"+file.getAbsolutePath());
                HowFarDistance2 howfarw=new HowFarDistance2(); //json
                //거리 구하기
                howfarw.makeDistances(file.getAbsolutePath());
                double dd=howfarw.howfar();
                webEngine.executeScript( " updateMessage(' " + "You walk "+dd+" Km." + " ') " );
            } catch (Exception ex) {
               System.out.println("read----Exception--------------------------------->"+ex);
               webEngine.executeScript( "clearMessage() " );
            }
        }
    });
    // Refresh 버튼
    // refresh 버튼을 생성하고 이벤트를 등록한다.
    Button refresh = new Button("Refresh");
    refresh.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {	
         webEngine.load(url.toExternalForm());
         JSObject window = (JSObject) webEngine.executeScript("window");
         window.setMember("app", app= new JavaApplication(stage));
        }
    });
    // hbox에 여러 버튼을 옆으로 붙인다.
    hbox.getChildren().addAll(btnAbout,btnClear,btnReadFx,refresh,btnExit);
    // 수직 vbox에 웹뷰를 붙이고 여러 버튼을 붙인 hbox를 아래로 붙인다.
    root.getChildren().addAll(webView, hbox);
    Scene scene = new Scene(root, 600, 300);
    stage.setScene(scene);
    stage.show();
}
 // 웹엔진에서 자바스크립트가 자바를 접근할 때 필요
 // 자바스크립트의 이벤트가 자바의 메서드를 호출한다.
 public class JavaApplication {        // 내부 클래스 -> 멤버처름 객체 생성해서 사용
  Stage stage;
  public JavaApplication(Stage stage){
	this.stage=stage;
  }
  public void saveJSToJavaFxFile(String msg) {
	System.out.println("--------------------------->"+msg);
	FileChooser fileChooser = new FileChooser();
    File file = fileChooser.showSaveDialog(this.stage);
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        writer.write(msg);
    } catch (IOException ex) {
    	System.out.println("------------------------>"+ex);
    }
  }
 }
}
