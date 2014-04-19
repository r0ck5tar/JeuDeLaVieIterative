package model;

import gui.CellChangedEvent;
import gui.CellChangedListener;

import java.util.ArrayList;

/**
 * Created by Hakim on 19/04/14.
 */
public class Cell {
    private int xCoordinate;
    private int yCoordinate;
    private boolean alive;
    private boolean willLive = false;
    private ArrayList<Cell> neighbours;
    private CellChangedListener listener;

    private int livingNeighboursCount() {
        int livingNeighbours = 0;
        for(Cell c : neighbours) { if (c.isAlive()) livingNeighbours++;}
        return livingNeighbours;
    }

    public  void calculateNextStep() {
        /*Determine the next step (whether the cell will live or not) based on the
          number of living neighbours and applying the rules of the Game of Life. */
        switch(livingNeighboursCount()) {
            case 2: willLive = isAlive(); break;
            case 3: willLive = true; break;
            default: willLive = false;
        }
    }

    public void applyNextStep() {
        if(alive != willLive) {
            alive = willLive;
            listener.cellChanged(new CellChangedEvent(this));

            //System.out.print("Cell [" + xCoordinate + ", " + yCoordinate +"] is now ");
            //if(alive) System.out.println("alive"); else System.out.println("dead");
        }
    }

    public boolean isAlive() { return this.alive; }
    public void setAlive(boolean alive) { this.alive = alive; listener.cellChanged(new CellChangedEvent(this));}

    public void setCoordinates(int x, int y)     {xCoordinate = x; yCoordinate = y;}
    public int getxCoordinate() { return xCoordinate; }
    public int getyCoordinate() { return yCoordinate; }

    public ArrayList<Cell> getNeighbours() { return neighbours; }
    public void setNeighbours(ArrayList<Cell> neighbours) {this.neighbours = neighbours;}
    public void setListener(CellChangedListener listener) { this.listener = listener; }

    @Override
    public String toString(){
        String id = Integer.toString(xCoordinate) + ", " + Integer.toString(yCoordinate) + " nghbrs = " + livingNeighboursCount();
        if(alive) return  id + " - alive";
        else      return  id + " - dead";
    }
}
