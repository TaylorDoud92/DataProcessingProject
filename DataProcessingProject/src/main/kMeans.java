package main;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

import support.Centroid;
import support.Point;

public class kMeans {

    private Centroid[] centroidList;
    private ArrayList<Point> data;
    private int numClusters;
    private int iterations;

    private int x_dimension;
    private int y_dimension;

    private KMeansCanvas canvas;

    public kMeans(ArrayList<Point> data, int x_dimension, int y_dimension) {
        this.x_dimension = x_dimension;
        this.y_dimension = y_dimension;
        this.data = data;
    }

    public kMeans(ArrayList<Point> data, int numClusters, Centroid[] centroids, int iterations, KMeansCanvas canvas,
            int x_dimension, int y_dimension) {
        this.x_dimension = x_dimension;
        this.y_dimension = y_dimension;
        this.data = data;
        this.numClusters = numClusters;
        this.iterations = iterations;
        this.canvas = canvas;

        if (centroids != null) {

            this.centroidList = centroids;

            System.out.println("Imported Centroids!");

        } else {

            // Randomly create Centroids

            centroidList = new Centroid[this.numClusters];

            System.out.println("Current Centroids:");

            for (int i = 0; i < numClusters; i++) {
                centroidList[i] = new Centroid((int) (Math.round((Math.random() * x_dimension))),
                        (int) (Math.round((Math.random() * y_dimension))), i + 1);
                System.out.println(centroidList[i]);
            }

            System.out.println("Randomly Generated Centroids!");
        }

    }

    public void cluster(int numClusters, int iterations, Centroid[] centroids) {

        Centroid[] currentCentroidList = centroidList;
        int count = 0;

        do {
            canvas.paint(canvas.getGraphics());

            System.out.println("Current Count: " + count);
            for (int i = 0; i < data.size(); i++) {
                int clusterID = nearestPoint(data.get(i));
                centroidList[clusterID - 1].getPointList().add(data.get(i));
                data.get(i).setClusterID(clusterID);
            }

            // Update Centroids
            updateCentroids();
            count++;

        } while (count <= iterations || !convergence(currentCentroidList, centroidList)); // Add
                                                                                          // convergence
        System.out.println("Clustering ends on iteration:" + count);
        System.out.println("final Centroid locations");

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
            if (testDist > nearest) {
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

}
