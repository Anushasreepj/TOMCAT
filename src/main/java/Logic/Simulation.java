package Logic;

public class Simulation {
    int width;
    int height;

    public boolean[][] board;

    public Simulation(int width, int height) {
        this.width = width;
        this.height = height;

        board = new boolean[height][width];
        clearBoard();
    }

    public void randomBoard() {
        for(int i = 0; i < height; i ++) {
            for(int j = 0; j < width; j ++) {
                board[i][j] = Math.random() > 0.5;
            }
        }
    }

    public void clearBoard() {
        for(int i = 0; i < height; i ++) {
            for(int j = 0; j < width; j ++) {
                board[i][j] = false;
            }
        }
    }
}
