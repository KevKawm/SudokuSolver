package com.webs.kevkawmcode.SudokuSolver.Solver;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageManager {
	
	static ClassLoader cl = ImageManager.class.getClassLoader();
	
	public static BufferedImage get(String name){
		Image img = new ImageIcon(cl.getResource("com/webs/kevkawmcode/SudokuSolver/Assets/" + name + ".png")).getImage();
		BufferedImage bImg = new BufferedImage(img.getWidth(null), img.getHeight(null),BufferedImage.TRANSLUCENT);
		bImg.getGraphics().drawImage(img, 0, 0, null);
		return bImg;
	}
	
}
