/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;




public class ShortestPath {
	public static int shortestPath(String[][] map, Cell start, Cell end, 
                                                           Stack<Cell> path) {
    // initialize distances array filled with infinity
    int[][] distances = new int[map.length][];
    for (int i = 0; i < map.length; i++) {
        distances[i] = new int[map[i].length];
        Arrays.fill(distances[i], Integer.MAX_VALUE);
    }

    // the start node should get distance 0
    int distance = 0;
    List<Cell> currentCells = Arrays.asList(start);

    while (distances[end.row][end.col] == Integer.MAX_VALUE
                && !currentCells.isEmpty()) {
        List<Cell> nextCells = new ArrayList<>();

        // loop over all cells added in previous round
        // set their distance 
        //    and add their neighbors to the list for next round
        for (Cell cell : currentCells) {
            if (distances[cell.row][cell.col] == Integer.MAX_VALUE 
                    && !map[cell.row][cell.col].equals("1")) {
                distances[cell.row][cell.col] = distance;
                addNeighbors(cell, nextCells, map.length, map[0].length);
            }
        }

        // prepare for next round
        currentCells = nextCells;
        distance++;
    }

    // find the path
    if (distances[end.row][end.col] < Integer.MAX_VALUE) {
        Cell cell = end;
        path.push(end);
        for (int d = distances[end.row][end.col]-1; d >= 0; d--) {
            cell = getNeighbor(cell, d, distances);
            path.push(cell);
        }
    }

    return distances[end.row][end.col];
}
        
        // add all valid neighbors of a cell to the list
    // where "valid" means: indices inside the maze
private static void addNeighbors(Cell cell, List<Cell> list, 
                                      int maxRow, int maxCol) {
    int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] d : ds) {
        int row = cell.row + d[0];
        int col = cell.col + d[1];          
        if (isValid(row, col, maxRow, maxCol))
            list.add(new Cell(row, col));
    }
}

// find the neighbor of a cell having a certain distance from the start        
private static Cell getNeighbor(Cell cell, int distance, int[][] distances) {
    int[][] ds = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    for (int[] d : ds) {
        int row = cell.row + d[0];
        int col = cell.col + d[1];          
        if (isValid(row, col, distances.length, distances[0].length)
                && distances[row][col] == distance)
            return new Cell(row, col);              
    }
    return null;
}

// check if coordinates are inside the maze
private static boolean isValid(int row, int col, int maxRow, int maxCol) {
    return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
}
}



