package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;

public class Bullet {
    Pane pane = new Pane();
    Map map;
    Circle bullet;
    int direction;

    public Bullet(int direction, int x, int y, Map map, Color color) {
        this.bullet = new Circle(x + 25, y + 25, 5);
        bullet.setFill(color);
        this.direction = direction;

        this.map = map;
        this.map.getChildren().add(this.pane);
        pane.getChildren().add(bullet);
        fired();
    }

    public void fired() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isIntersected((int) bullet.getCenterX(), (int) bullet.getCenterY())) {
                    pane.getChildren().clear();
                    stop();
                }else {
                    move(direction);
                }
            }
        };
        timer.start();
    }
    public void move(int direction) {
        switch (direction) {
            case 1:
                bullet.setCenterY(bullet.getCenterY() - 4);
                break;
            case 2:
                bullet.setCenterY(bullet.getCenterY() + 4);
                break;
            case 3:
                bullet.setCenterX(bullet.getCenterX() - 4);
                break;
            case 4:
                bullet.setCenterX(bullet.getCenterX() + 4);
                break;
        }
    }

    public boolean isIntersected(int centerX, int centerY) {
        if (!((0 <= centerX && (map.getSize() * 50) >= centerX) && (0 <= centerY && (map.getSize() * 50) >= centerY)))
            return true;
        else {
            for (int i = 0; i < map.getSize(); i++) {
                for (int j = 0; j < map.getSize(); j++) {
                    int x = j * 50;
                    int y = i * 50;
                    if ((x <= centerX && (x + 50) > centerX) && (y <= centerY && (y + 50) > centerY)) {
                        if (map.matrix[i][j].equals("S")) {
                            return true;
                        } else if (map.matrix[i][j].equals("B")) {
                            for (BrickWall brickWall : map.brickWalls){
                                if (brickWall.getX() == j * 50 && brickWall.getY() == i * 50) {
//                                    System.out.println(brickWall.getX() + " " + brickWall.getY());
                                    brickWall.canBeBroken();
                                    if (brickWall.getHealth() == 0) {
                                        map.matrix[i][j] = "0";
                                        map.getChildren().remove(brickWall);
                                    }
                                }
                            }
                            return true;
                        } else if (map.matrix[i][j].equals("T")) {
                            bullet.setVisible(false);
                            return false;
                        } else {
                            bullet.setVisible(true);
                        }
                    }
                }
            }
            return false;
        }
    }
}

