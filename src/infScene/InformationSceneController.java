package infScene;

import game.Constants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.StageUtils;

import java.io.IOException;

public class InformationSceneController {
    @FXML
    ImageView backImg;

    private Image back;
    private Image selBack;

    @FXML
    public void initialize() {
        back = new Image(Constants.BACK_TO_LEVEL_MENU);
        selBack = new Image(Constants.SEL_BACK_TO_LEVEL_MENU);
    }

    @FXML
    private void onBackToLevelMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainMenu/mainMenuScene.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setTitle("BOMBERMEN");
        stage.setScene(new Scene(root, 620, 480));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        StageUtils.closeStage(backImg);
    }

    @FXML
    public void onBackEntered() {
        backImg.setImage(selBack);
    }

    @FXML
    public void onBackExited() {
        backImg.setImage(back);
    }
}
