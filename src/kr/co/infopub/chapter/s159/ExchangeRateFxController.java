package kr.co.infopub.chapter.s159;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
public class ExchangeRateFxController {
    @FXML
    private Label lbfrom;
    @FXML
    private TextField tffrom;
    @FXML
    private TextField tfjpy;
    @FXML
    private Label lbto;
    @FXML
    private RadioButton rdother;
    @FXML
    private ComboBox<String> cbfinance;
    @FXML
    private TextField tfusd;
    @FXML
    private Button btncon;
    @FXML
    private TextField tfto;
    @FXML
    private RadioButton rdkrw;
    @FXML
    private TextField tfcny;
    @FXML
    private Label lbboard;
    @FXML
    void initialize() {}
    //콤보박스의 아이템을 선택
    @FXML
    void cbdistanceOnAction(ActionEvent event) {}
    //라디오-다른 국가의 화폐로 환전
    @FXML
    void onOtherAction(ActionEvent event) {}
    //라디오-한화로 환전
    @FXML
    void onKrwAction(ActionEvent event) {}
    //최신 환율 가져오기
    @FXML
    void onConvertAction(ActionEvent event) {}
}
