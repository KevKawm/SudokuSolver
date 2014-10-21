package com.webs.kevkawmcode.SudokuSolver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.webs.kevkawmcode.SudokuSolver.Solver.Board;
import com.webs.kevkawmcode.SudokuSolver.Solver.ImageManager;
import com.webs.kevkawmcode.SudokuSolver.Solver.Selector;

public class Display extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;

	Thread thread = new Thread(this);
	
	public Frame frame;
	
	Board board;
	
	Selector s;
	
	int size;
	int xOff;
	int yOff;
	
	public List<Image> imgs = new ArrayList<Image>();
	public List<Image> sImgs = new ArrayList<Image>();
	
	public Display(Frame frame) {
		this.frame = frame;
		thread.start();
		
		size = frame.getHeight() / 12;
		xOff = (frame.getWidth() - size * 9) / 2;
		yOff = (frame.getHeight() - size * 9) / 4;
		
		s = new Selector(this,new Point(xOff, yOff + size * 9 + yOff / 3), size * 9);
	}

	@Override
	public void run() {
		
		board = new Board(this);
		
		for(int i = 1; i < 10; i++){
			imgs.add(ImageManager.get(i + ""));
			sImgs.add(ImageManager.get("selected_" + i));
		}
		
		this.addMouseListener(s);
		this.addMouseListener(board);
		
		while(true){
			repaint();
			if(frame.getHeight() / 12 != size){
				resize();
			}
		}
	}

	public void resize(){
		size = frame.getHeight() / 12;
		xOff = (frame.getWidth() - size * 9) / 2;
		yOff = (frame.getHeight() - size * 9) / 4;
		s.p = new Point(xOff, yOff + size * 9 + yOff / 3);
		s.length = size * 9;
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.clearRect(0,0,frame.getWidth(),frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		board.render(g);
		s.render(g);
	}
	
}
