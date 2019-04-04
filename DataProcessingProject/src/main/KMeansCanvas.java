package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import support.Centroid;
import support.Point;

/**
 * KMeans
 * 
 * K-Means is a spatial clustering algorithm for clustering numerical data.
 * 
 * @author Taylor Doud
 *
 */

public class KMeansCanvas extends Canvas {

    private Centroid[] centroidList;        //List that contains our centroids.
    private ArrayList<Point> data;          //List that contains our dataset.
    private int numCentroids;               //Number of centroids or clusters.

    /**
     * Constructor Class for instantiating KMeansCanvas
     * 
     * @param data
     *            Dataset that will be clustered.
     * @param numClusters
     *            number of clusters that will be created.
     * @param centroids
     *            Imported Centroids
     * @param width
     *            width of the window provided to the canvas for drawing
     *            purposes.
     * @param height
     *            height of the window provided to the canvas for drawing
     *            purposes.
     */
    public KMeansCanvas(ArrayList<Point> data, int numClusters, Centroid[] centroids, int width, int height) {

        setSize(width, height);

        this.data = data;
        this.numCentroids = numClusters;

        //Import Centroids
        if (centroids != null) {

            this.centroidList = centroids;

            System.out.println("Imported Centroids!");

        } else {

            // Randomly create Centroids

            centroidList = new Centroid[this.numCentroids];

            System.out.println("Current Centroids:");

            for (int i = 0; i < numClusters; i++) {
                centroidList[i] = new Centroid((int) (Math.round((Math.random() * width))),
                        (int) (Math.round((Math.random() * height))), i + 1);
                System.out.println(centroidList[i]);
            }

            System.out.println("Randomly Generated Centroids!");
        }
    }

    /**
     * Method to begin clustering the dataset.
     * 
     * @param iterations
     *            the max number of iterations.
     * @param graphics
     *            the graphical context for the canvas.
     * @throws InterruptedException
     *             Exception for wait.
     */
    public void cluster(int iterations, Graphics graphics) throws InterruptedException {

        Centroid[] currentCentroidList = new Centroid[centroidList.length];

        int count = 0;

        do {
            for (int i = 0; i < centroidList.length; i++) {
                currentCentroidList[i] = new Centroid(centroidList[i].getX(), centroidList[i].getY(),
                        centroidList[i].getClusterID());
            }

            Thread.sleep(500);

            System.out.println("Current Count: " + count);

            //Get the current nearest point for each centroid.
            
            for (int i = 0; i < data.size(); i++) {

                int clusterID = nearestPoint(data.get(i));
                centroidList[clusterID - 1].getPointList().add(data.get(i));
                data.get(i).setClusterID(clusterID);
            }

            update(graphics);

            // Update Centroids
            updateCentroids();
            count++;

        } while (!convergence(currentCentroidList, centroidList) && count <= iterations);

        System.out.println("Clustering ends on iteration:" + count);
        System.out.println("final Centroid locations");

        for (int i = 0; i < data.size(); i++) {
            int clusterID = nearestPoint(data.get(i));
            centroidList[clusterID - 1].getPointList().add(data.get(i));
            data.get(i).setClusterID(clusterID);
        }

        for (int i = 0; i < numCentroids; i++) {
            System.out.println(centroidList[i]);
        }

    }

    /**
     * Method to determine the nearest centroid to the point.
     * 
     * @param pt
     *            point that will be evaluated.
     * @return return the clusterID that will be associated with point.
     */
    private int nearestPoint(Point pt) {

        double nearest = euclideanDist(pt.getX(), pt.getY(), centroidList[0].getX(), centroidList[0].getY());
        int clusterID = 1;

        for (int i = 1; i < numCentroids; i++) {
            double testDist = euclideanDist(pt.getX(), pt.getY(), centroidList[i].getX(), centroidList[i].getY());
            if (testDist < nearest) {
                nearest = testDist;
                clusterID = i + 1;
            }
        }
        return clusterID;
    }

    /**
     * Method to update all the centroids.
     * 
     * @return returns the list of all updated Centroids.
     */
    private Centroid[] updateCentroids() {

        for (int i = 0; i < numCentroids; i++) {
            centroidList[i].computeMean();
        }

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setClusterID(0);
        }
        return centroidList;
    }

    /**
     * Method to determine the euclidean distance between two points.
     * 
     * @param x1
     *            X value of the first point
     * @param y1
     *            Y value of the first point
     * @param x2
     *            X value of the second point
     * @param y2
     *            Y value of the second point
     * 
     * @return returns the distance between the two points.
     */
    private double euclideanDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    /**
     * Method to determine the convergence point of all your clusters.
     * 
     * @param oldCentroidList
     *            the prior iterations centroid list.
     * @param newCentroidList
     *            the current iterations centroid list.
     * @return boolean as to whether the current iteration is the same as the
     *         prior.
     */
    private boolean convergence(Centroid[] oldCentroidList, Centroid[] newCentroidList) {
        double baseDistance = 999999;

        for (int i = 0; i < numCentroids; i++) {

            double distance = euclideanDist(oldCentroidList[i].getX(), oldCentroidList[i].getY(),
                    newCentroidList[i].getX(), newCentroidList[i].getY());
            if (baseDistance > distance) {
                baseDistance = distance;
            }
        }

        if (baseDistance < 0.1)
            return true;
        else
            return false;
    }

    /**
     * Override of the paint method to draw the centroids, the points and the line between them.
     */
    @Override
    public void paint(Graphics graphics) {
        drawGrid(graphics);
        if (centroidList != null) {
            ArrayList<Point> list;
            for (int i = 0; i < centroidList.length; i++) {
                list = centroidList[i].getPointList();
                drawCentroid(graphics, centroidList[i]);
                if (list.size() != 0) {
                    for (int j = 0; j < list.size(); j++) {
                        drawLine(graphics, list.get(j), centroidList[i]);
                    }
                }
            }

            for (int i = 0; i < data.size(); i++) {
                drawPoint(graphics, data.get(i));
            }
        }
    }

    /**
     * Method to draw a single point.
     * @param graphics current context of the canvas.
     * @param point point which will be drawn.
     */
    public void drawPoint(Graphics graphics, Point point) {

        setPointColor(graphics, point);
        graphics.fillOval(point.getX(), point.getY(), 2, 2);
    }

    /**
     * Method to draw a line between a single point and centroid.
     * @param graphics current context of the canvas.
     * @param point source point.
     * @param centroid source centroid.
     */
    public void drawLine(Graphics graphics, Point point, Centroid centroid) {
        graphics.drawLine(point.getX(), point.getY(), centroid.getX(), centroid.getY());
    }

    /**
     * Method to drawCentroid
     * @param graphics current context of the canvas.
     * @param centroid centroid to be drawn.
     */
    public void drawCentroid(Graphics graphics, Centroid centroid) {
        setPointColor(graphics, centroid);
        graphics.fillOval(centroid.getX() - 5, centroid.getY() - 5, 5, 5);
    }

    
    /**
     * Method to draw a grid on the canvas.
     * @param grid current context of the canvas.
     */
    public void drawGrid(Graphics grid) {
        int rowHeight = getSize().height / 2;
        int colWidth = getSize().width / 2;
        grid.drawLine(0, (rowHeight), getSize().width, (rowHeight));
        grid.drawLine((colWidth), 0, (colWidth), getSize().height);
    }

    /**
     * Support Method to set the color of the element to be drawn.
     * @param graphics current context of the canvas.
     * @param point point that will be drawn.
     */
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
