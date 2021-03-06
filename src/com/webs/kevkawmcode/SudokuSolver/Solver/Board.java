package com.webs.kevkawmcode.SudokuSolver.Solver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import com.webs.kevkawmcode.SudokuSolver.Display;

public class Board implements MouseListener{
	
	Display display;
	
	List<List<Integer>> board = new ArrayList<List<Integer>>();
	List<String> set = new ArrayList<String>();
	
	public Board(Display display){
		this.display = display;
		for(int i = 1; i < 10; i++){
			List<Integer> list = new ArrayList<Integer>();
			for(int j = 1; j < 10; j++){
				list.add(0);
			}
			board.add(list);
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		int size = display.frame.getHeight() / 12;
		int xOff = (display.frame.getWidth() - size * 9) / 2;
		int yOff = (display.frame.getHeight() - size * 9) / 4;
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				if(board.get(x).get(y) != 0){
					if(set.contains(x + "," + y)){
						BufferedImage img = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
						img.getGraphics().drawImage(ImageManager.get(board.get(x).get(y).toString()), 0, 0, null);
						ImageManager.replaceColor(Color.BLACK.getRGB(), Color.RED.getRGB(), img);
						g.drawImage(img, xOff + x * size, yOff + y * size, size, size, null);
					} else {
						g.drawImage(ImageManager.get(board.get(x).get(y).toString()), xOff + x * size, yOff + y * size, size, size, null);
					}
				}
				g.drawRect(xOff + x * size, yOff + y * size, size, size);
				g.fillRect((xOff + 9 * size) - (size / 30), yOff, size / 15, size * 9);
				g.fillRect(xOff, (yOff + 9 * size) - (size / 30), size * 9, size / 15);
				if(x % 3 == 0){
					g.fillRect(xOff + x * size - (size / 30), yOff + y * size, size / 15, size);
				}
				if(y % 3 == 0){
					g.fillRect(xOff + x * size, yOff + y * size - (size / 30), size, size / 15);
				}
			}
		}
	}

	public int get(int x, int y){
		return board.get(x).get(y);
	}
	
	public void set(int x, int y, int value){
		board.get(x).set(y, value);
		set.add(x + "," + y);
	}
	
	public void setNotRed(int x, int y, int value){
		board.get(x).set(y, value);
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Point p = e.getPoint();
		int size = display.frame.getHeight() / 12;
		int xOff = (display.frame.getWidth() - size * 9) / 2;
		int yOff = (display.frame.getHeight() - size * 9) / 4;
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				Point pos = new Point(xOff + x * size, yOff + y * size);
				if(p.x > pos.x && p.x < pos.x + size && p.y > pos.y && p.y < pos.y + size){
					if(e.getButton() == 1){
						board.get(x).set(y, display.s.selected);
					} else if(e.getButton() == 3){
						board.get(x).set(y, 0);
					}
				}
			}
		}
	}
	
	public boolean isFilled(int x, int y){
		return get(x,y) != 0;
	}
	
	public boolean isSolved(){
		for(int x = 0; x < 9; x++){
			for(int y = 0; y < 9; y++){
				if(!isFilled(x,y))
					return false;
			}
		}
		return true;
	}
	
}
