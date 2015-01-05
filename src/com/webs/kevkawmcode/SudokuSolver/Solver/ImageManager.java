package com.webs.kevkawmcode.SudokuSolver.Solver;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class ImageManager {
	
	static ClassLoader cl = ImageManager.class.getClassLoader();
	
	public static Image get(String name){
		return new ImageIcon(cl.getResource("com/webs/kevkawmcode/SudokuSolver/Assets/" + name + ".png")).getImage();
	}
	
	public static void replaceColor(int oldRGB, int newRGB, BufferedImage img){
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getWidth(); y++){
				if(img.getRGB(x, y) == oldRGB){
					img.setRGB(x, y, newRGB);
				}
			}
		}
	}
	
	public static Image getIcon(){
		return new ImageIcon(cl.getResource("com/webs/kevkawmcode/SudokuSolver/Assets/icon.png")).getImage();
	}
	
}
