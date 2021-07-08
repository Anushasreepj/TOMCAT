package Logic;

import GUI.Data;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {
    public int width;
    public int height;

    public int generation = 0;
    public boolean isRunning = false;

    public boolean[][] board;
    public boolean[][] nextBoard;

    public Simulation(int init_width, int init_height, int max_width, int max_height) {
        this.width = init_width;
        this.height = init_height;

        board = new boolean[max_height][max_width];
        nextBoard = new boolean[max_height][max_width];

        clearBoard();
        clearNextBoard();
    }

    public void changeBoardSize(int new_width, int new_height) {
        if (new_width < width) {
            for(int y = 0; y < height; y ++) {
                for(int x = new_width; x < this.width; x ++) {
                    board[y][x] = false;
                }
            }
        }

        if (new_height < height) {
            for(int y = new_height; y < height; y ++) {
                for(int x = 0; x < this.width; x ++) {
                    board[y][x] = false;
                }
            }
        }

        this.width = new_width;
        this.height = new_height;
    }

    public void switchCell(int yPosition, int xPosition) {
        board[yPosition][xPosition] = !board[yPosition][xPosition];
    }

    public void randomBoard() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                board[y][x] = Math.random() > 0.5;
            }
        }
    }

    public void clearBoard() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                board[y][x] = false;
            }
        }
    }

    public void clearNextBoard() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                nextBoard[y][x] = false;
            }
        }
    }

    private int countNeighbours(int yPosition, int xPosition) {
        int liveNeighbours = 0;

        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                int ySearch = (yPosition + i + height) % height;
                int xSearch = (xPosition + j + width) % width;

                liveNeighbours += board[ySearch][xSearch] ? 1 : 0;
            }
        }

        return liveNeighbours - (board[yPosition][xPosition] ? 1 : 0);
    }

    public void simStep() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                boolean state = board[y][x];
                int neighbours = countNeighbours(y, x);

                if (!state && neighbours == 3) {
                    nextBoard[y][x] = true;
                } else if(state && (neighbours < 2 || 3 < neighbours)) {
                    nextBoard[y][x] = false;
                } else {
                    nextBoard[y][x] = state;
                }
            }
        }

        for(int y = 0; y < height; y++) {
            System.arraycopy(nextBoard[y], 0, board[y], 0, width);
        }

        generation++;
        clearNextBoard();
    }
}
