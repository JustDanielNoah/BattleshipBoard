import java.util.Arrays;
import java.util.Scanner;

public class Battleship {

	private static final int BOARD_SIZE = 20;
	private static final char EMPTY_CELL = '0';
	private static final char SHIP_CELL = '-';
	private static final char HIT_CELL = 'X';
	private static final char MISS_CELL = '?';
	private char[][] board;

	public void createBoard() {
		board = new char[BOARD_SIZE][BOARD_SIZE];
		for (char[] row : board) {
			Arrays.fill(row, EMPTY_CELL);
		}
	}

	private void placeShip(int x, int y, int length, String type, String direction) {
		if (direction.equalsIgnoreCase("vertical")) {
			for (int i = 0; i < length; i++) {
				if (x + i >= BOARD_SIZE || board[x + i][y] != EMPTY_CELL) {
					System.out.println("Invalid ship placement. Please try again.");
					return;
				}
			}
			for (int i = 0; i < length; i++) {
				board[x + i][y] = SHIP_CELL;
			}
		} else {
			for (int i = 0; i < length; i++) {
				if (y + i >= BOARD_SIZE || board[x][y + i] != EMPTY_CELL) {
					System.out.println("Invalid ship placement. Please try again.");
					return;
				}
			}
			for (int i = 0; i < length; i++) {
				board[x][y + i] = SHIP_CELL;
			}
		}
	}

	public boolean checkCoordinates(int x, int y) {
		return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
	}

	private boolean endGame() {
		for (char[] row : board) {
			for (char cell : row) {
				if (cell == SHIP_CELL)
					return false;
			}
		}
		return true;
	}

	public void printBoard() {
		for (char[] row : board) {
			for (char cell : row) {
				System.out.print(cell + " ");
			}
			System.out.println();
		}
	}

	public void fire(int x, int y) {
		if (!checkCoordinates(x, y)) {
			System.out.println("Coordinates out of bounds. Please try again.");
			return;
		}
		char cell = board[x][y];
		if (cell == SHIP_CELL) {
			System.out.println("Hit!");
			board[x][y] = HIT_CELL;
		} else if (cell == EMPTY_CELL) {
			System.out.println("Miss!");
			board[x][y] = MISS_CELL;
		} else {
			System.out.println("Already fired at this location. Please try again.");
		}

	}

	public static void main(String[] args) {
		Battleship battleShip = new Battleship();
		battleShip.createBoard();

		System.out.println("----------------------------------------");

		try (Scanner input = new Scanner(System.in)) {
			while (true) {
				System.out.println("Enter (x, y) coordinates to place ship, and 0 to end ship placement: ");

				while (!input.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer.");
					input.next(); // clear the invalid input
				}
				int m = input.nextInt();

				while (!input.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer.");
					input.next(); // clear the invalid input
				}
				int n = input.nextInt();

				if (m == 0 && n == 0) {
					break; // Exit the ship placement loop
				}
				battleShip.placeShip(m, n, 3, "battleship", "vertical");
			}

			while (true) {
				System.out.println("Enter coordinates to fire (x, y): ");

				while (!input.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer.");
					input.next();
				}
				int x = input.nextInt();

				while (!input.hasNextInt()) {
					System.out.println("Invalid input. Please enter an integer.");
					input.next();
				}

				int y = input.nextInt();

				battleShip.fire(x, y);
				battleShip.printBoard();

				if (battleShip.endGame()) {
					System.out.println("Game Over!");
					break;
				}
			}
		}
	}
}
