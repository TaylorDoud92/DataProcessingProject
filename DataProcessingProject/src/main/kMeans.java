package main;

import java.util.ArrayList;

import support.Point;

public class kMeans {

    private double[][] dataset;
    private int[] cluster;
    private int numClusters;
    private int[] clusterID;
    private int[] inCluster;
    private double[][] centroids;
    
    private Point[] centroidList;

    private int x_dimension;
    private int y_dimension;

    public kMeans(int x_dimension, int y_dimension) {
    	this.x_dimension = x_dimension;
    	this.y_dimension = y_dimension;
    }
    
    
    public void cluster(int numClusters, int iterations, Point[] centroids) {
    	
    	this.numClusters = numClusters;
    	
    	if(centroids != null) {
    		this.centroidList = centroids;
    		
    		System.out.println("Imported Centroids!");
    		
    	} else {
    		
    		//Randomly create Centroids
    		
    		centroidList = new Point[this.numClusters];
    		for(int i=0;i<numClusters;i++) {
    			centroidList[i] = new Point((int)(Math.round((Math.random()*x_dimension))),(int)(Math.round((Math.random()*y_dimension))),0);
    		}
    		
    		System.out.println("Randomly Generated Centroids!");
    	}
    	
    	
    }
    
    public void cluster(int numClusters, int iterations, double[][] centroids) {

        this.numClusters = numClusters;
        
        if (centroids != null) {

            this.centroids = centroids;

            System.out.println("Created centroids");
        } else {
        }
//            centroids = new double[this.numClusters][];
//            ArrayList index = new ArrayList();
//            for (int i = 0; i < numClusters; i++) {
//                int currentCentroid;
//                do {
//
//                    centroid = (int) (Math.random()); // c = (int)(Math.random()*_nrows);
//                    
//                    } while (index.contains(c));
//
//                index.add(c);
//
//                centroids[i] = new double[y_dimension];
//                for (int j = 0; j < y_dimension; j++) {
//                    centroids[i][j] = dataset[c][j];
//                }
//                System.out.println("Selected centroids randomly");
//                }
//
//            double[][] c1 = centroids;
//            double threshold = 0.001;
//            int round = 0;
//
//            while (true) {
//                // update _centroids with the last round results
//                centroids = c1;
//
//                // assign record to the closest centroid
//                _label = new int[_nrows];
//                for (int i = 0; i < _nrows; i++) {
//                    _label[i] = closest(dataset[i]);
//                }
//
//                // recompute centroids based on the assignments
//                c1 = updateCentroids();
//                round++;
//                if ((iterations > 0 && round >= iterations) || converge(centroids, c1, threshold))
//                    break;
//
//            }
//
//        }
//
    }
    
    
    //Support function to update Centroids.
    private double[][] updateCentroids(){
        
        double [][] newCentroids = new double[numClusters][];
        int [] count = new int[numClusters];
        
        for(int i=0;i<numClusters;i++){
            count[i]=0;
            newCentroids[i] = new double[y_dimension];
            for(int j=0;j<y_dimension;j++){
                newCentroids[i][j]=0;
            }
        }
        
        for (int i=0; i<x_dimension; i++){
            int currentCentroid = clusterID[i]; // the cluster membership id for record i
            for (int j=0; j<x_dimension; j++){
              newCentroids[currentCentroid][j] += dataset[i][j]; // update that centroid by adding the member data record
            }
            count[currentCentroid]++;
          }

          // finally get the average
          for (int i=0; i< numClusters; i++){
            for (int j=0; j<y_dimension; j++){
              newCentroids[i][j]/= count[i];
            }
          } 

          return newCentroids;
    }
    
    
    //Support function to find nearestPoint
    private int nearestPoint(int x, int y){
        
        double nearest = euclideanDist(x, y, centroids[0].getX(), centroids[].getY());;
        int clusterID = 0;
        
        for(int i=0;i<numClusters;i++){
            double testDist = euclideanDist(x, y, centroids[i].getX(), centroids[i].getY());
            if(testDist > nearest){
                nearest = testDist;
                clusterID = i;
            }
        }
        return clusterID;
    }
    
    //Support function for euclideanDistance
    private double euclideanDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1), 2));
    }
    
    
    //converging centroids
    private boolean converge(double[][] c1, double[][] c2){
        double maxv = 0;
        for(int i=0;i<numClusters;i++){
            double distance = euclideanDist(x1, y1, x2, y2);
            if(maxv<distance){
                maxv = distance;
            }
        }
    }
    
    
}
