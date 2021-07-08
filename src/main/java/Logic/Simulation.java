package Logic;

public class Simulation {
    public int width;
    public int height;

    public int generation = 0;
    public boolean isRunning = false;

    public boolean[][] board;

    public Simulation(int init_width, int init_height, int max_width, int max_height) {
        this.width = init_width;
        this.height = init_height;

        board = new boolean[max_width][max_height];
        clearBoard();
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

    public void startSim() {

    }

    public void stopSim() {

    }

    public void simStep() {

    }
}
