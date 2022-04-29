package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class MyPlayer extends Pane implements Player{
    private Position position;
    private Map map;
    Tank tank;

    public MyPlayer(Position position, Map map) {
        this.position = position;
        this.map = map;
        //getChildren().add(tank);
    }

        @Override
    public void moveRight() {
            position.setX(position.getX()+1);
    }

    @Override
    public void moveLeft() {
        position.setX(position.getX()-1);
    }

    @Override
    public void moveUp() {
        position.setY(position.getY()-1);
    }

    @Override
    public void moveDown() {
        position.setY(position.getY()+1);
     }

    @Override
    public void setMap(Map map) {
        this.map = map;
    }





}