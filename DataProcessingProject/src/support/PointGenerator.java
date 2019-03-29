package support;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import support.Point;

public class PointGenerator {

	public static void main(String[] args) {
		
		generatePoints(100);
		
	}

	private static void generatePoints(int numPoints) {

		Random ran = new Random();

		Point[][] data = new Point[numPoints][numPoints];

		for (int i = 0; i < numPoints; i++) {
			for (int j = 0; j < numPoints; j++) {
				data[i][j] = new Point(ran.nextInt(800), ran.nextInt(800), 0);
			}
		}
		
		File file = new File("../dataset.csv");
		try {
			FileOutputStream fos = new FileOutputStream(file);
			for (int i = 0; i < numPoints; i++) {
				for (int j = 0; j < numPoints; j++) {
					System.out.println(data[i][j].getX() +" " + data[i][j].getY());
					fos.write(data[i][j].toString().getBytes());
				}
			}
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
