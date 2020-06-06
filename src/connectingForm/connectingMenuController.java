package connectingForm;

import game.Constants;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class connectingMenuController {

    Image connectingImage;

    @FXML
    ImageView connecting;

    private int time = 500;
    private Runnable timer;
    List<String> connectingsList = new ArrayList();
    int count = 0;

    public void initialize() {
        connectingsList.add(Constants.WIFI_ONE);
        connectingsList.add(Constants.WIFI_TWO);
        connectingsList.add(Constants.WIFI_THREE);
        connectingsList.add(Constants.WIFI_FULL);
        connectingImage = new Image(Constants.WIFI_ONE);
        Timeline timeline = new Timeline (
                new KeyFrame(
                        Duration.millis(1000 ),
                        ae -> {
                            if(count == 4){count = 0;}
                            connectingImage = new Image(connectingsList.get(count));
                            connecting.setImage(connectingImage);
                            count++;


                        }
                )
        );

        timeline.setCycleCount(9999);
        timeline.play();
    }
}
