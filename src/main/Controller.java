package main;

import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private ToggleButton masterControl;
    @FXML
    private RadioButton instructorRadio, workStationRadio;
    @FXML
    private JFXToggleButton mouseToggle, keyboardToggle, instructorDisplay, projectorDisplay, allDisplays;
    @FXML
    private Pane leftPane, centerPane, rightPane;
    @FXML
    private Label selectStationLabel, selectOutputLabel;
    @FXML
    private Button connectButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("initialization");
        comboBox.getItems().addAll(
                "--None--", "SWS-T01", "SWS-T02", "SWS-T03", "SWS-T04", "SWS-T05", "SWS-T06", "SWS-T07", "SWS-T08", "SWS-T09", "SWS-T10"
        );
        comboBox.getSelectionModel().selectFirst();
        masterControl.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> leftPane.setDisable(wasSelected));
        masterControl.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> centerPane.setDisable(wasSelected));
        masterControl.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> rightPane.setDisable(wasSelected));

    }

    public void onPower(ActionEvent event) {
        if (event.getSource() == masterControl) {
            System.out.println("MasterControl");
            if (masterControl.isSelected()) {
                masterControl.setText("On");
                masterControl.setTextFill(Color.WHITE);
                masterControl.setSelected(true);
                leftPane.setDisable(false);
                centerPane.setDisable(false);
                rightPane.setDisable(false);
                instructorRadio.setSelected(true);
                mouseToggle.setSelected(true);
                keyboardToggle.setSelected(true);
                instructorDisplay.setSelected(true);
            } else {
                masterControl.setText("OFF");
                masterControl.setTextFill(Color.BLACK);
                masterControl.setSelected(false);
                leftPane.setDisable(true);
                centerPane.setDisable(true);
                rightPane.setDisable(true);
                instructorRadio.setSelected(false);
                workStationRadio.setSelected(false);
                mouseToggle.setSelected(false);
                keyboardToggle.setSelected(false);
                instructorDisplay.setSelected(false);
                projectorDisplay.setSelected(false);
                allDisplays.setSelected(false);
                comboBox.getSelectionModel().selectFirst();
                selectStationLabel.setVisible(false);
                selectOutputLabel.setVisible(false);
            }
        }
    }

    public void onComboItemSelected() {
        if (comboBox.getSelectionModel().getSelectedIndex() != 0) {
            workStationRadio.setSelected(true);
            selectStationLabel.setVisible(false);
            centerPane.setDisable(false);
            rightPane.setDisable(false);
            onConnect();
        }
        else {
            instructorRadio.setSelected(true);
            centerPane.setDisable(false);
            rightPane.setDisable(false);
            selectStationLabel.setVisible(false);
            mouseToggle.setSelected(true);
            keyboardToggle.setSelected(true);
            instructorDisplay.setSelected(true);
        }
    }

    public void OnRadioClicked(ActionEvent event) {
        if (event.getSource() == instructorRadio) {
            selectStationLabel.setVisible(false);
            centerPane.setDisable(false);
            rightPane.setDisable(false);
        }
        else {
            if (comboBox.getSelectionModel().getSelectedIndex() == 0) {
                centerPane.setDisable(true);
                rightPane.setDisable(true);
                selectStationLabel.setVisible(true);
                selectOutputLabel.setVisible(false);
            }
            else {
                centerPane.setDisable(false);
                rightPane.setDisable(false);
                selectStationLabel.setVisible(false);
            }
        }
    }

    public void onConnect() {
        if (!instructorDisplay.isSelected() && !projectorDisplay.isSelected() && !allDisplays.isSelected()) {
            connectButton.setDisable(true);
            selectOutputLabel.setVisible(true);
        }
        else {
            connectButton.setDisable(false);
            selectOutputLabel.setVisible(false);
        }

    }
}
