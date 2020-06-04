import game.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URI;

public class Main extends Application {

    private static MediaPlayer mediaPlayer;

    static {
        createMediaPlayer();
        mediaPlayer.setOnEndOfMedia(Main::createMediaPlayer);
    }

    private static void createMediaPlayer(){
        final File file = new File(Constants.MEDIA_URL_1);
        final URI uri = file.toURI();
        final String uriStr = uri.toString();
        Media media = new Media(uriStr);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnError(() -> System.err.println("MediaPlayerStopped."));
        mediaPlayer.setAutoPlay(true);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //createMediaPlayer();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu/mainMenuScene.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage= new Stage();
        stage.setTitle("BOMBERMEN");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root, 620, 480));
        stage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
