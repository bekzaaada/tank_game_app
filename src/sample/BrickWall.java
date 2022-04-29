package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class BrickWall extends Pane implements Obstacles{
    private int x;
    private int y;
    private int health = 4;
    Image image = new Image(new File("src\\sample\\image\\brickWall.png").toURI().toString());

    public BrickWall(int x, int y) {
        this.x = x;
        this.y = y;
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        getChildren().add(imageView);
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealth(){
        return this.health;
    }
    @Override
    public boolean canBeBroken() {
        health--;
        if (health <= 0)
            return false;
        return true;
    }

    @Override
    public boolean canGoThrough() {
        return false;
    }

    @Override
    public boolean canPassThrough() {
        return false;
    }
}
