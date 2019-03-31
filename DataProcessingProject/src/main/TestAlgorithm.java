package main;

import java.util.ArrayList;

import support.Point;

public class TestAlgorithm {

    public static void main(String[] args) {
        
        int x_dimension = 800;
        int y_dimension = 800;
        
        ArrayList<Point> data = new ArrayList<Point>();
        
        System.out.println((int)(Math.random()*800));
        
        for(int i=0;i<100;i++){
            data.add(new Point(400+i,400+i,0));
            data.add(new Point(600+i,600+i,0));
            data.add(new Point(200+i,200+i,0));
        }
        
        kMeans kmeans = new kMeans(data, x_dimension, y_dimension);
        
        kmeans.cluster(2, 10, null);
    }

}
