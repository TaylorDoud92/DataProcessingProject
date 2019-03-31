package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import support.Centroid;
import support.Point;

public class KMeansCanvas extends Canvas {

    private Centroid[] centroidList;
    private ArrayList<Point> data;
    private int numClusters;

    // function to create a canvas with provided width and height
    public KMeansCanvas(ArrayList<Point> data, int numClusters, Centroid[] centroids, int width, int height) {
        
        setSize(width, height);
        
        this.data = data;
        this.numClusters = numClusters;

        if (centroids != null) {

            this.centroidList = centroids;

            System.out.println("Imported Centroids!");

        } else {

            // Randomly create Centroids

            centroidList = new Centroid[this.numClusters];

            System.out.println("Current Centroids:");

            for (int i = 0; i < numClusters; i++) {
                centroidList[i] = new Centroid((int) (Math.round((Math.random() * width))),
                        (int) (Math.round((Math.random() * height))), i + 1);
                System.out.println(centroidList[i]);
            }

            System.out.println("Randomly Generated Centroids!");
        }
    }

    public void cluster(int iterations, Centroid[] centroids, Graphics graphics) throws InterruptedException {

        Centroid[] currentCentroidList = centroidList;
        int count = 0;

        do {
            System.out.println(count);
            Thread.sleep(500);
            repaint();

            System.out.println("Current Count: " + count);
            for (int i = 0; i < data.size(); i++) {
                
                int clusterID = nearestPoint(data.get(i));
                centroidList[clusterID - 1].getPointList().add(data.get(i));
                data.get(i).setClusterID(clusterID);
            }
            
           update(graphics);

            // Update Centroids
            updateCentroids();
            count++;

        } while (count <= iterations || !convergence(currentCentroidList, centroidList)); // Add
                                                                                          // convergence
        System.out.println("Clustering ends on iteration:" + count);
        System.out.println("final Centroid locations");
        
        for (int i = 0; i < data.size(); i++) {
            
            int clusterID = nearestPoint(data.get(i));
            centroidList[clusterID - 1].getPointList().add(data.get(i));
            data.get(i).setClusterID(clusterID);
        }

        for (int i = 0; i < numClusters; i++) {
            System.out.println(centroidList[i]);
        }
        
    }

    // Support function to find nearestPoint
    private int nearestPoint(Point pt) {

        double nearest = euclideanDist(pt.getX(), pt.getY(), centroidList[0].getX(), centroidList[0].getY());
        int clusterID = 1;

        for (int i = 1; i < numClusters; i++) {
            double testDist = euclideanDist(pt.getX(), pt.getY(), centroidList[i].getX(), centroidList[i].getY());
            if (testDist < nearest) {
                nearest = testDist;
                clusterID = i + 1;
            }
        }
        return clusterID;
    }

    // Support function to update Centroids.
    private void updateCentroids() {

        for (int i = 0; i < numClusters; i++) {
            centroidList[i].computeMean();
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setClusterID(0);
        }

    }

    // Support function for euclideanDistance
    private double euclideanDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    // converging centroids
    private boolean convergence(Centroid[] oldCentroidList, Centroid[] newCentroidList) {
        double baseDistance = 0;
        for (int i = 0; i < numClusters; i++) {
            System.out.println(numClusters);
            double distance = euclideanDist(oldCentroidList[i].getX(), oldCentroidList[i].getY(),
                    newCentroidList[i].getX(), newCentroidList[i].getY());
            if (baseDistance < distance) {
                baseDistance = distance;
            }
        }

        if (baseDistance < 500)
            return true;
        else
            return false;
    }

    @Override
    public void paint(Graphics graphics) {
        drawGrid(graphics);
        if (centroidList != null) {
            ArrayList<Point> list;
            for (int i = 0; i < centroidList.length; i++) {
                list = centroidList[i].getPointList();
                drawCentroid(graphics, centroidList[i]);
                if(list.size() != 0){
                    for (int j = 0; j < list.size(); j++) {
                        drawLine(graphics, list.get(j), centroidList[i]);
                    }
                }
            }

            for (int i = 0; i < data.size(); i++) {
                drawPoint(graphics, data.get(i), centroidList);
            }
        }
    }

    public void drawPoint(Graphics graphics, Point point, Centroid[] centroidList) {
        
        setPointColor(graphics, point);
        graphics.fillOval(point.getX(), point.getY(), 2, 2);
    }
    
    public void drawLine(Graphics graphics, Point point, Centroid centroid){
//        Centroid centroid = centroidList[point.getClusterID()];
        graphics.drawLine(point.getX(), point.getY(), centroid.getX(), centroid.getY());        
    }

    public void drawCentroid(Graphics graphics, Centroid centroid) {
        setPointColor(graphics, centroid);
        graphics.fillOval(centroid.getX() - 5, centroid.getY() - 5, 5, 5);
    }

    public void drawGrid(Graphics grid) {
        int rowHeight = getSize().height / 2;
        int colWidth = getSize().width / 2;
        grid.drawLine(0, (rowHeight), getSize().width, (rowHeight));
        grid.drawLine((colWidth), 0, (colWidth), getSize().height);
    }

    public void setPointColor(Graphics graphics, Point point) {

        switch (point.getClusterID()) {
        case 0:
            graphics.setColor(Color.WHITE);
            break;
        case 1:
            graphics.setColor(Color.RED);
            break;
        case 2:
            graphics.setColor(Color.BLUE);
            break;
        case 3:
            graphics.setColor(Color.GREEN);
            break;
        case 4:
            graphics.setColor(Color.YELLOW);
            break;
        case 5:
            graphics.setColor(Color.PINK);
            break;
        case 6:
            graphics.setColor(Color.ORANGE);
            break;
        case 7:
            graphics.setColor(Color.MAGENTA);
            break;
        case 8:
            graphics.setColor(Color.CYAN);
            break;
        case 9:
            graphics.setColor(Color.DARK_GRAY);
            break;
        case 10:
            graphics.setColor(Color.LIGHT_GRAY);
            break;
        default:
            graphics.setColor(Color.WHITE);
        }
    }
}
