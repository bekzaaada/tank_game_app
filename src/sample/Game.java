package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Game extends Application {
    private Map map;
    private Player player;

    public void setMap(Map m){
        this.map = m;
    }
    public void addPlayer(Player player){
        this.player = player;
        player.setMap(map);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        String filePath = "map.txt";
        File file = new File(filePath);
        map = new Map(file);
        Tank tank = new Tank(map.getPosition(), map);
        Pane gamePane = new Pane(tank.getImage(), map);

        gamePane.setStyle("-fx-background-color: BLACK");
        Scene scene=new Scene(gamePane);
        scene.setOnKeyPressed(event -> {
             tank.moveWithKeyboard(event.getCode());
        });

        primaryStage.setTitle("Project2");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
