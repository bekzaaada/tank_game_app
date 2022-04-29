package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

public class Water extends Pane implements Obstacles{
    private int x;
    private int y;
    Image image = new Image(new File("src\\sample\\image\\water.png").toURI().toString());

    public Water(int x, int y) {
        this.x = x;
        this.y = y;
        ImageView imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        getChildren().add(imageView);
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    @Override
    public boolean canBeBroken() {
        return false;
    }
    @Override
    public boolean canGoThrough() {
        return false;
    }
    @Override
    public boolean canPassThrough() {
        return true;
    }
}
