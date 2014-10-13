package com.webs.kevkawmcode.SudokuSolver.Solver;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import com.webs.kevkawmcode.SudokuSolver.Display;

public class Selector implements MouseListener {

	int selected = 1;

	public Point p;
	public int length;
	
	Display display;
	
	public Selector(Display display, Point p, int length) {
		this.display = display;
		this.p = p;
		this.length = length;
	}

	public void render(Graphics g) {
		int size = length / 9;
		for (int i = 0; i < 9; i++) {
			BufferedImage img = display.imgs.get(i);
			if (i == selected) {
				img = display.sImgs.get(i);
			}
			g.drawImage(img, p.x + i * size, p.y, size, size, null);
			g.drawRect(p.x + i * size, p.y, size, size);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int size = length / 9;
		if (e.getPoint().y > p.y && e.getPoint().y < p.y + size) {
			for(int i = 0; i < 9; i++){
				if(e.getPoint().x > p.x + i * size && e.getPoint().x < p.x + (i + 1) * size){
					if(e.getButton() == 1){
						selected = i;
					} else if(e.getButton() == 3){
						selected = 0;
					}
				}
			}
		}
	}
}
