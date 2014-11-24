package com.webs.kevkawmcode.SudokuSolver;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.webs.kevkawmcode.SudokuSolver.Solver.Board;
import com.webs.kevkawmcode.SudokuSolver.Solver.ImageManager;
import com.webs.kevkawmcode.SudokuSolver.Solver.Selector;
import com.webs.kevkawmcode.SudokuSolver.Solver.Solver;

public class Display extends JPanel implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;

	Thread thread = new Thread(this);

	public Frame frame;

	Board board;

	public Selector s;

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

		s = new Selector(this, new Point(xOff, yOff + size * 9 + yOff / 3), size * 9);
	}

	@Override
	public void run() {

		board = new Board(this);

		for (int i = 1; i < 10; i++) {
			imgs.add(ImageManager.get(i + ""));
			sImgs.add(ImageManager.get("selected_" + i));
		}

		this.addMouseListener(s);
		frame.addKeyListener(s);
		this.addMouseListener(board);

		while (true) {
			repaint();
			if (frame.getHeight() / 12 != size) {
				resize();
			}
		}
	}

	public void resize() {
		size = frame.getHeight() / 12;
		xOff = (frame.getWidth() - size * 9) / 2;
		yOff = (frame.getHeight() - size * 9) / 4;
		s.p = new Point(xOff, yOff + size * 9 + yOff / 3);
		s.length = size * 9;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
		board.render(g);
		s.render(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 's'){
			Solver.solve(board);
		} else if(e.getKeyChar() == 'f'){
			int[][] a = {
					{4,0,1},
					{7,0,6},
					{1,1,1},
					{2,1,2},
					{3,1,3},
					{7,1,8},
					{0,2,5},
					{3,2,8},
					{8,2,3},
					{5,3,3},
					{6,3,4},
					{0,4,1},
					{2,4,7},
					{6,4,9},
					{8,4,6},
					{2,5,3},
					{3,5,6},
					{0,6,7},
					{5,6,4},
					{8,6,9},
					{1,7,6},
					{5,7,9},
					{6,7,7},
					{7,7,2},
					{1,8,2},
					{4,8,7}
			};
			for(int[] i : a){
				board.setNotRed(i[0], i[1], i[2]);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
