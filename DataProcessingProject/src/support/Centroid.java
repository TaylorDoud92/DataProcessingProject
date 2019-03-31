package support;

import java.util.ArrayList;

public class Centroid extends Point {

    private ArrayList<Point> pointList;

    public Centroid(int x, int y, int clusterID) {
        super(x, y, clusterID);
        pointList = new ArrayList<Point>();
    }

    public Centroid(int x, int y, int clusterID, ArrayList<Point> pointList) {
        super(x, y, clusterID);
        this.pointList = pointList;
    }

    public ArrayList<Point> getPointList() {
        return pointList;
    }

    public void setPointList(ArrayList<Point> pointList) {
        this.pointList = pointList;
    }

    public void computeMean() {

        int totalX = 0;
        int totalY = 0;
        if (pointList.size() != 0) {

            for (int i = 0; i < pointList.size(); i++) {
                totalX += pointList.get(i).getX();
                totalY += pointList.get(i).getY();
            }

            super.setX(totalX / pointList.size());
            super.setY(totalY / pointList.size());

        pointList.clear();
        }
    }

}
