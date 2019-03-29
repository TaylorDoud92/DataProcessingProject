package support;

public class Point {

	private int x;
	private int y;
	private int[] coordinates;
	private int clusterID;
	
	public Point(int x, int y, int clusterID) {
		this.x = x;
		this.y = y;
		this.coordinates = new int[2];
		
		coordinates[0] = x;
		coordinates[1] = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int[] getCoordinates() {
		return coordinates;
	}
	
	@Override
	public String toString() {
		return "("+ x + " "+y+"),";
	}

    public int getClusterID() {
        return clusterID;
    }

    public void setClusterID(int clusterID) {
        this.clusterID = clusterID;
    }
}
