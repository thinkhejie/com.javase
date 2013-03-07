//package j.se.base;
//
//import j.se.base.PrintExercise.Direction;
//
//public class PrintExercise {
//
//	/**
//	 * 需要打印的数组的长度
//	 */
//	public static int SIZE = 9;
//
//	enum Direction {
//		H, // 水平走
//		V, // 垂直走
//		D, // 向下走 [斜向下的走]
//		T, // 向上走 [斜向上的走]
//	}
//
//	public static void main(String[] args) {
//		int[][] all = new int[SIZE][SIZE];
//
//		// 初始位置
//		int x = 0;
//		int y = 0;
//		all[x][y] = 1;
//
//		Direction lastDirection = Direction.H; // 上一次走的方向
//
//		for (int i = 1; i < SIZE * SIZE; i++) {
//			Direction position = getNextDirection(lastDirection, x, y); // 得到要走的方向
//			lastDirection = position;
//
//			int[] nextLocation = getNextLocation(position, x, y); // 得到要走的坐标
//			x = nextLocation[0];
//			y = nextLocation[1];
//
//			all[x][y] = i + 1; // 给坐标赋值
//		}
//
//		// 打印
//		printArray(all);
//	}
//
//	/**
//	 * <p>
//	 * Description: 打印数组
//	 * </p>
//	 */
//	private static void printArray(int[][] array) {
//		for (int i = 0; i < SIZE; i++) {
//			for (int j = 0; j < SIZE; j++) {
//				if (j == 0) {
//					System.out.println("");
//				}
//				System.out.print(" " + array[j][i]);
//			}
//		}
//	}
//
//	/**
//	 * <p>
//	 * Description: 得到下一个坐标
//	 * </p>
//	 * 
//	 * @param position
//	 *            要走的方向
//	 * @param x
//	 * @param y
//	 * @return
//	 */
//	private static int[] getNextLocation(Direction position, int x, int y) {
//		int[] nextLocation = new int[2];
//
//		if (Direction.H.equals(position)) {
//			nextLocation[0] = x + 1; // 水平走, x + 1, y不变
//			nextLocation[1] = y;
//
//		} else if (Direction.V.equals(position)) {
//			nextLocation[0] = x; // 垂直走, x, y + 1
//			nextLocation[1] = y + 1;
//
//		} else if (Direction.D.equals(position)) {
//			nextLocation[0] = x - 1; // 向下走, x - 1, y + 1
//			nextLocation[1] = y + 1;
//
//		} else if (Direction.T.equals(position)) {
//			nextLocation[0] = x + 1; // 向上走, x + 1, y - 1
//			nextLocation[1] = y - 1;
//		}
//
//		return nextLocation;
//	}
//
//	/**
//	 * <p>
//	 * Description: 得到下一步的方向
//	 * </p>
//	 * 
//	 * @param lastDirection
//	 * @param x
//	 * @param y
//	 */
//	private static Direction getNextDirection(Direction lastDirection, int x, int y) {
//		if (x == 2 && y == 2) {
//			System.out.println();
//		}
//		if (!(x == 0 || y == 0 || x == SIZE - 1 || y == SIZE - 1))
//			return lastDirection;
//
//		if (x == 0 && y == 0)
//			return Direction.H;
//
//		if (x + y < SIZE - 1) { // 前半程的方向判断
//			if (x == 0 && y % 2 == 0)
//				return Direction.T;
//			else if (x == 0 && y % 2 != 0)
//				return Direction.V;
//			else if (y == 0 && x % 2 != 0)
//				return Direction.D;
//			else if (y == 0 && x % 2 == 0)
//				return Direction.H;
//
//		} else { // 后半程的方向判断
//			if (SIZE % 2 == 0) { // 注意此处. 由于SIZE的%2不同, 带来了对后半程的方向的逻辑不一样.
//				if (x == SIZE - 1 && y % 2 == 0)
//					return Direction.D;
//				else if (x == SIZE - 1 && y % 2 != 0)
//					return Direction.V;
//				else if (y == SIZE - 1 && x % 2 != 0)
//					return Direction.T;
//				else if (y == SIZE - 1 && x % 2 == 0)
//					return Direction.H;
//
//			} else {
//				if (x == SIZE - 1 && y % 2 != 0)
//					return Direction.D;
//				else if (x == SIZE - 1 && y % 2 == 0)
//					return Direction.V;
//				else if (y == SIZE - 1 && x % 2 == 0)
//					return Direction.T;
//				else if (y == SIZE - 1 && x % 2 != 0)
//					return Direction.H;
//			}
//		}
//		return null; // 理论上不会有null返回的
//	}
//
//}
