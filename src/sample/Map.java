package sample;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map extends Pane {
    private int size;
    private Position position;
    String[][] matrix;
    ArrayList<BrickWall> brickWalls;


    public Map(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        this.size = Integer.parseInt(scan.nextLine());
        matrix = new String[size][size];
        int a = 0;
        brickWalls = new ArrayList<>();
        while (scan.hasNext()) {
            String line = scan.nextLine();
            matrix[a++]= line.split(" ");
        }
        setPrefSize(size*50, size*50);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j].equals("P")) {
                    position = new Position(j, i);
                    matrix[i][j] = "0";
                } else if (matrix[i][j].equals("S")) {
                    this.getChildren().add(new SteelWall(j*50, i*50));
                } else if (matrix[i][j].equals("B")) {
                    BrickWall brickWall = new BrickWall(j*50, i*50);
                    brickWalls.add(brickWall);
                    this.getChildren().add(brickWall);
                } else if (matrix[i][j].equals("W")) {
                    this.getChildren().add(new Water(j*50, i*50));
                } else if (matrix[i][j].equals("T")) {
                    this.getChildren().add(new Tree(j*50, i*50));
                }
            }
        }
    }
    public void setSize(int size) {
        this.size = size;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getSize() {
        return size;
    }

    public Position getPosition() {
        return position;
    }
    public char getValueAt(int x, int y){
        return matrix[y][x].charAt(0);
    }
    public int getX(){
        return this.position.getX();
    }
    public int getY(){
        return this.position.getY();
    }

    public boolean isRoadForTank(Position position) {
        return getValueAt(position.getX(), position.getY()) == '0' || getValueAt(position.getX(), position.getY()) == 'T';
    }
    public void print(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}




