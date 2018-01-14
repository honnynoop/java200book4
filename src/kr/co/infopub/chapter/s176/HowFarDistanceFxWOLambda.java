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
// lambda�� ������� �ʰ� 
public class HowFarDistanceFxWOLambda extends Application {
 public static void main(String[] args) {
    launch(args);
 }
 JavaApplication app;   // JS�� JAVA �޼��� ȣ�Ⱑ��
 @Override
 public void start(Stage stage) {
	stage.setTitle("�󸶳� �ɾ���?");
	WebView webView = new WebView();
    WebEngine webEngine = webView.getEngine();
    URL url = getClass().getResource("geo.html");
    //��ȣ��
    webEngine.load(url.toExternalForm());
    //�ڹٿ� �� ������ ���� ����
    app=new JavaApplication(stage);
    webEngine.getLoadWorker().stateProperty().addListener(
	    new ChangeListener<State>(){
	      @Override
	      public void changed(ObservableValue<? extends State> ov, State oldState, State newState) {
	      	System.out.println("------state-----------------------------------"+newState);
	          if(newState == State.SUCCEEDED){
	          	JSObject window = (JSObject) webEngine.executeScript("window");
	              window.setMember("app", app);  //app�̸����� ȣ���� JS
	          }
	      }
	    });
    VBox root = new VBox(10);      // �������� ���̱�
    HBox hbox = new HBox(10);      // �������� ���̱�
    hbox.setPadding(new Insets(5));
    hbox.setAlignment(Pos.CENTER);
    // Exit ��ư
    Button btnExit = new Button("Exit");
    btnExit.setOnAction(e -> {
        Platform.exit();
    });
    // About ��ư
    Button btnAbout = new Button("About");
    // �ڹٿ��� ��ũ��Ʈ�� ����
    btnAbout.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	webEngine.executeScript( " updateMessage(' " + stage.getTitle()+" "+new Date() + " ') " );
        }
    });
    // Clear ��ư
    Button btnClear = new Button("Clear");
    // �ڹٿ��� ��ũ��Ʈ�� ����
    btnClear.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {
        	webEngine.executeScript( "clearMessage()" );
        }
    });
    // ReadFx ��ư
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
                //�Ÿ� ���ϱ�
                howfarw.makeDistances(file.getAbsolutePath());
                double dd=howfarw.howfar();
                webEngine.executeScript( " updateMessage(' " + "You walk "+dd+" Km." + " ') " );
            } catch (Exception ex) {
               System.out.println("read----Exception--------------------------------->"+ex);
               webEngine.executeScript( "clearMessage() " );
            }
        }
    });
    // Refresh ��ư
    // refresh ��ư�� �����ϰ� �̺�Ʈ�� ����Ѵ�.
    Button refresh = new Button("Refresh");
    refresh.setOnAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent arg0) {	
         webEngine.load(url.toExternalForm());
         JSObject window = (JSObject) webEngine.executeScript("window");
         window.setMember("app", app= new JavaApplication(stage));
        }
    });
    // hbox�� ���� ��ư�� ������ ���δ�.
    hbox.getChildren().addAll(btnAbout,btnClear,btnReadFx,refresh,btnExit);
    // ���� vbox�� ���並 ���̰� ���� ��ư�� ���� hbox�� �Ʒ��� ���δ�.
    root.getChildren().addAll(webView, hbox);
    Scene scene = new Scene(root, 600, 300);
    stage.setScene(scene);
    stage.show();
}
 // ���������� �ڹٽ�ũ��Ʈ�� �ڹٸ� ������ �� �ʿ�
 // �ڹٽ�ũ��Ʈ�� �̺�Ʈ�� �ڹ��� �޼��带 ȣ���Ѵ�.
 public class JavaApplication {        // ���� Ŭ���� -> ���ó�� ��ü �����ؼ� ���
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
