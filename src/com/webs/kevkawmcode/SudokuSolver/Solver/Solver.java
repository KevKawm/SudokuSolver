package com.webs.kevkawmcode.SudokuSolver.Solver;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Solver {

	public static void backtrackSolve(Board b) {
		List<Point> prev = new ArrayList<Point>();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (!b.isFilled(x, y)) {
					for (int i = b.get(x, y) + 1; i <= 10; i++) {
						System.out.println(x + "," + y + "," + i);
						if (i == 10) {
							b.setNotRed(x, y, 0);
							Point prevP = prev.get(prev.size() - 1);
							x = prevP.x;
							y = prevP.y;
							prev.remove(prev.size() - 1);
							i = b.get(x, y);
						} else if (isPossible(b, x, y, i)) {
							prev.add(new Point(x, y));
							b.set(x, y, i);
							break;
						}
					}
				}
			}
		}
	}

	public static boolean isPossible(Board b, int x, int y, int i) {
		return findPoss(b, x, y).contains(i);
	}

	public static void solve(Board b) {
		// Set up possibilities list
		List<List<List<Integer>>> poss = new ArrayList<List<List<Integer>>>();
		updatePoss(poss, b);

		// Find values
		// Find value using only possibility
		boolean found = true;
		while (found) {
			found = false;
			System.out.println("Finding");
			for (int x = 0; x < 9; x++) {
				for (int y = 0; y < 9; y++) {
					List<Integer> list = poss.get(x).get(y);
					if (list.size() == 1 && b.get(x, y) == 0) {
						b.set(x, y, list.get(0));
						updatePoss(poss, b);
						found = true;
					}
				}
			}
		}

		System.out.println("bts");
		
		if(!b.isSolved()){ 
			backtrackSolve(b);
		}
	}

	private static void updatePoss(List<List<List<Integer>>> poss, Board b) {
		poss.clear();
		for (int x = 0; x < 9; x++) {
			List<List<Integer>> list = new ArrayList<List<Integer>>();
			for (int y = 0; y < 9; y++) {
				List<Integer> list1 = new ArrayList<Integer>();
				if (b.get(x, y) != 0) {
					list1.add(b.get(x, y));
				} else {
					list1 = findPoss(b, x, y);
				}
				list.add(list1);
			}
			poss.add(list);
		}
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				System.out.println(x + "," + y + ": " + poss.get(x).get(y));
			}
		}
	}

	private static List<Integer> findPoss(Board b, int x, int y) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= 9; i++) {
			list.add(i);
		}

		for (int i : getRow(b, y)) {
			list.remove(list.indexOf(i));
		}
		for (int i : getColumn(b, x)) {
			if (list.contains(i))
				list.remove(list.indexOf(i));
		}
		for (int i : getSquare(b, x, y)) {
			if (list.contains(i))
				list.remove(list.indexOf(i));
		}

		return list;
	}

	private static List<Integer> getRow(Board b, int i) {
		List<Integer> list = new ArrayList<Integer>();
		for (int x = 0; x < 9; x++) {
			if (b.get(x, i) != 0) {
				list.add(b.get(x, i));
			}
		}
		return list;
	}

	private static List<Integer> getColumn(Board b, int i) {
		List<Integer> list = new ArrayList<Integer>();
		for (int y = 0; y < 9; y++) {
			if (b.get(i, y) != 0) {
				list.add(b.get(i, y));
			}
		}
		return list;
	}

	private static List<Integer> getSquare(Board b, double x, double y) {
		List<Integer> list = new ArrayList<Integer>();
		int squareX = (int) (Math.ceil((x + 1) / 3) - 1);
		int squareY = (int) (Math.ceil((y + 1) / 3) - 1);
		int posX = squareX * 3;
		int posY = squareY * 3;
		for (int x1 = posX; x1 < posX + 3; x1++) {
			for (int y1 = posY; y1 < posY + 3; y1++) {
				if (b.get(x1, y1) != 0) {
					list.add(b.get(x1, y1));
				}
			}
		}
		return list;
	}

}
