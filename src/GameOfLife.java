import exceptions.InitializingPatternOutOfBoundsException;
import gui.GridView;
import gui.MainView;
import model.Grid;
import model.Pattern;

import java.io.IOException;

/**
 * Created by Hakim on 19/04/14.
 */
public class GameOfLife{

    public static void main(String argv[])
            throws InitializingPatternOutOfBoundsException {
        System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
        GameOfLife life = new GameOfLife(45, 45);
        byte bpattern[][] = {
                {0,0,0,0,0,0,1,1,0,0,0,0,1,0,1,0,0,0,0},
                {0,0,0,0,0,0,1,1,0,0,0,0,1,0,0,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0},
                {0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0,0,0,1},
                {1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0},
                {1,1,0,1,1,0,0,0,1,0,0,0,1,0,0,0,1,0,0},
                {0,0,0,1,0,1,1,0,1,0,1,1,0,0,0,0,0,1,0},
                {0,0,0,1,0,0,0,0,1,0,1,1,0,0,0,0,1,0,0},
                {0,0,0,0,1,1,1,1,0,0,0,0,1,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,0,0,0},
                {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,0,0},
                {1,0,1,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,0,0},
                {0,1,1,0,0,0,0,0,0,1,0,0,1,0,0,0,1,1,0},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
        life.grid.initializeWithPattern(new Pattern(bpattern));
        life.run();
    }

    private Grid grid;
    private MainView mainView ;
    private GridView gridView;

    public GameOfLife() { this(25, 25); }

    public GameOfLife(int width, int height) {
        grid = new Grid(width, height);
        mainView = new MainView(width, height);
        gridView = mainView.getGridView();

        for(int x = 0; x < width; x ++) {
            for (int y = 0; y < height; y++) {
                grid.getCell(x, y).setListener(gridView);
            }
        }
    }

    public void run() {
        int generation = 0;
        while(true) {
            System.out.println("generation: " + generation);

            for(int x = 0; x < grid.getWidth(); x ++) {
                for (int y = 0; y < grid.getHeight(); y++) {
                    //System.out.println(grid.getCell(x, y));
                    grid.getCell(x, y).calculateNextStep();
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /*
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            */

            for(int x = 0; x < grid.getWidth(); x ++) {
                for (int y = 0; y < grid.getHeight(); y++) {
                    grid.getCell(x, y).applyNextStep();
                }
            }

            generation++;
        }
    }
}
