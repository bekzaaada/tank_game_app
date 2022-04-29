package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Tank extends MyPlayer implements Player {
    private Map map;
    private Position position;
    private final static int lives = 3;
    private int direction = 1;  // 1- up, 2-down, 3-left, 4-right
    private Image image;
    private ImageView imageView = new ImageView();
    private Bullet bullet;
//    ArrayList<Bullet> bullets = new ArrayList<>();

    public Tank(Position position, Map map) {
        super(position, map);
//        this.bullets = new ArrayList<>();
        this.position = position;
        this.map = map;
        image = new Image((new File("src\\sample\\image\\up.jpg").toURI().toString()));
        imageView.setImage(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setX(position.getX() * 50);
        imageView.setY(position.getY() * 50);
    }

    public ImageView getImage() {
        return imageView;
    }

    public void setImage(Image image) {
        this.image = image;
        this.imageView.setImage(this.image);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void toUp() {
        direction = 1;
        imageView.setRotate(0);
    }

    public void toDown() {
        direction = 2;
        imageView.setRotate(180);
    }

    public void toLeft() {
        direction = 3;
        imageView.setRotate(270);
    }

    public void toRight() {
        direction = 4;
        imageView.setRotate(90);
    }

    private void changingDirection(int direction) {
        this.direction = direction;
        switch (direction) {
            case 1:
                toUp();
                break;
            case 2:
                toDown();
                break;
            case 3:
                toLeft();
                break;
            case 4:
                toRight();
                break;
        }
    }

    private void nextPosition(int direction) {
        Position nextPosition = new Position();
        if (direction == 1) {
            nextPosition = new Position(getPosition().getX(), getPosition().getY() - 1);
        } else if (direction == 2) {
            nextPosition = new Position(getPosition().getX(), getPosition().getY() + 1);
        } else if (direction == 3) {
            nextPosition = new Position(getPosition().getX() - 1, getPosition().getY());
        } else if (direction == 4) {
            nextPosition = new Position(getPosition().getX() + 1, getPosition().getY());
        }
        this.position = nextPosition;
    }

    @Override
    public void moveRight() {
        if (position.getX() < map.getSize() - 1 && map.isRoadForTank(new Position(getPosition().getX() + 1, getPosition().getY()))) {
            nextPosition(4);
            changeImagePosition();
            System.out.println("Right");
        }
        else
            System.out.println("Invalid Position");
    }

    @Override
    public void moveLeft() {
        if (position.getX() > 0 && map.isRoadForTank(new Position(getPosition().getX() - 1, getPosition().getY()))) {
            nextPosition(3);
            changeImagePosition();
            System.out.println("Left");
        }else
            System.out.println("Invalid Position");
    }

    @Override
    public void moveDown() {
        if (position.getY() < map.getSize() - 1 && map.isRoadForTank(new Position(getPosition().getX(), getPosition().getY() + 1))) {
            nextPosition(2);
            changeImagePosition();
            System.out.println("Down");
        }else
            System.out.println("Invalid Position");
    }

    @Override
    public void moveUp() {
        if (position.getY() > 0 && map.isRoadForTank(new Position(getPosition().getX(), getPosition().getY() - 1))) {
            nextPosition(1);
            changeImagePosition();
            System.out.println("Up");
        }else
            System.out.println("Invalid Position");
    }

    public void changeImagePosition() {
        imageView.setX(position.getX() * 50);
        imageView.setY(position.getY() * 50);
    }

    @Override
    public void setMap(Map map) {
        this.map = map;
    }

    public void shooting() {
        bullet = new Bullet(this.direction, this.getPosition().getX() * 50 , this.getPosition().getY() * 50, this.map, Color.WHITE);
    }
    public void moveWithKeyboard(KeyCode key) {
        if (key == KeyCode.UP) {
            if (direction != 1)
                changingDirection(1);
            else
                moveUp();
        } else if (key == KeyCode.DOWN) {
            if (direction != 2)
                changingDirection(2);
            else
                moveDown();
        } else if (key == KeyCode.RIGHT) {
            if (direction != 4)
                changingDirection(4);
            else
                moveRight();
        } else if (key == KeyCode.LEFT) {
            if (direction != 3)
                changingDirection(3);
            else
                moveLeft();
        }else if(key == KeyCode.ENTER){
            shooting();
        }
    }
}
