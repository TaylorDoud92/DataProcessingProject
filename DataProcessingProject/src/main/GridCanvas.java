package main;

import java.awt.Canvas;
import java.awt.Graphics;

public class GridCanvas extends Canvas {

	private int rows, columns;
	
	//function to create a canvas with provided width and height
	public GridCanvas(int rows, int columns, int width, int height) {
		this.columns = columns;
		this.rows = rows;
		setSize(width, height);
	}
	
	//function to create a canvas without provided width and height
	public GridCanvas(int rows, int columns) {
		this.columns = columns;
		this.rows = rows;
	}
	
	public void paint(Graphics grid) {
		
		int rowHeight = getSize().height/rows;
		int colWidth = getSize().width/columns;
		
		for(int i=0; i<rows; i++) {
			grid.drawLine(0, (i*rowHeight), getSize().width, (i*rowHeight));
//			grid.drawRect((getSize().width/2)-3, 0, 4, getSize().height);
			grid.fillRect((getSize().width/2)-6, 0, 4, getSize().height);
			grid.fillRect(0, (getSize().height/2)-6, getSize().width, 4);
		}
		
		for(int i=0; i<columns;i++) {
			grid.drawLine((i*colWidth), 0, (i*colWidth), getSize().height);
		}		
	}
	
	public void paintPoint(Graphics grid) {
		
		grid.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
	}
}
