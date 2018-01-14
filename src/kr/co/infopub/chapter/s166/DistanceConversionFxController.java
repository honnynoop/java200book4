package kr.co.infopub.chapter.s166;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
public class DistanceConversionFxController {
 @FXML
 private TextField tfinput;
 @FXML
 private TextField tfyard;
 @FXML
 private TextField tfmile;
 @FXML
 private TextField tfcm;
 @FXML
 private TextField tfm;
 @FXML
 private TextField tfinch;
 @FXML
 private ComboBox<String> cbdistance;
 @FXML
 private TextField tffeet;

 double [][] convDistance;
 // Conversion.names={"Cm","M","Inch","Feet","Yard","Mile"};
 @FXML
 void initialize() {
	ObservableList<String> options = FXCollections.observableArrayList();
	options.addAll( Conversion.names );  //static �̶� ��ü ���� �ʿ����.
	cbdistance.setItems(options);
	
	//�Ÿ� ȯ��ǥ(2���� �迭) �ۼ�
	convDistance=Conversion.convert();
 }
 @FXML
 void cbdistanceOnAction(ActionEvent event) {

	String sinput=tfinput.getText();
	double value=0.0;
	if(sinput!=null && !sinput.equals("")){
		value=Double.parseDouble(sinput);
	}
	
	int selectIndex=-1;
	if(cbdistance.getSelectionModel()!=null){
		selectIndex=cbdistance.getSelectionModel().getSelectedIndex();
	}
			
	if(selectIndex>=0){
		//2���� �迭�� ���� ��� => value*convDistance[selectIndex][0]
		tfcm.setText(String.format("%.10f", value*convDistance[selectIndex][0]));
    	tfm.setText(String.format("%.10f", value*convDistance[selectIndex][1]));
    	tfinch.setText(String.format("%.10f", value*convDistance[selectIndex][2]));
    	tffeet.setText(String.format("%.10f", value*convDistance[selectIndex][3]));
    	tfyard.setText(String.format("%.10f", value*convDistance[selectIndex][4]));
    	tfmile.setText(String.format("%.10f", value*convDistance[selectIndex][5]));
	}
 }
}