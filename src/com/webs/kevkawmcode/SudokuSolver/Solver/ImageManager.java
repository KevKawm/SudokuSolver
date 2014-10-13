package com.webs.kevkawmcode.SudokuSolver.Solver;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageManager {
	
	static ClassLoader cl = ImageManager.class.getClassLoader();
	
	public static Image get(String name){
		return new ImageIcon(cl.getResource("com/webs/kevkawmcode/SudokuSolver/Assets/" + name + ".png")).getImage();
	}
	
}
