package javaapplication5;


// clsse Cell correspond a une cellule de la grille 
public class Cell {
    // ligne et colonne 
    public int row;     
    public int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;         
    }

    @Override
    public String toString() {
        return "{" + row + ", " + col + "}";
    }
}
