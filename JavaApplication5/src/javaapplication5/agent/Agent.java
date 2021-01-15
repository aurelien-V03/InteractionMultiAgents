package javaapplication5.agent;
import java.util.ArrayList;
import java.util.Stack;
import javaapplication5.Grille;
import javaapplication5.Direction;
import javaapplication5.Cell;
import javaapplication5.ShortestPath;


/**
 *
 * @author Aurel
 */
public class Agent{
    // numero de l'agent
    private int numeroAgent;
    
    // Coordonnée de l'agent
    public int x;
    public int y;
    
    // Coordonnée que l'agent doit atteindre
    public int x_final;
    public int y_final;
    
    // Nombre de piece 
    protected static int nbPieces = 1;
    
    // Grille dans laquelle l'agent se trouve
    protected Grille grille;
    
    // est dans sa position finale
    private boolean isAtFinalDestination = false;

    
    public Agent(Grille g){
        
        this.grille = g;
        
        int[] source = this.grille.getFreeCoordonate();
        
        this.x = source[0];
        this.y = source[1];
        
        // On met l'agent dans le tableau
        g.ajouterAgent(x, y, nbPieces);
        
        int[] destination = this.grille.getFreeCoordonate(this.x, this.y);
        
        this.x_final = destination[0];
        this.y_final = destination[1];
        
        this.numeroAgent = nbPieces;
        
        // On ajoute une piece
        nbPieces++;
       
        System.out.println(this.toString());
    }
    
    public Agent(Grille g, int x, int y, int x_final, int y_final)
    {
        this.grille = g;
        this.x = x;
        this.y = y;
        this.x_final = x_final;
        this.y_final = y_final;
        this.numeroAgent = nbPieces;
        // On met l'agent dans le tableau
        g.ajouterAgent(x, y, nbPieces);
        nbPieces++;
        System.out.println(this.toString());

    }
    // retourne vrai si l'agent est dans sa position finale
    public boolean estDansPositionFinale()
    {
        return this.isAtFinalDestination;
    }
    
 
    public String toString(){
        return "Agent : coordonnee [" + this.x + "," + this.y + "] / objectif ["+this.x_final + "," + this.y_final+"]";
    }
    
    /*
        Deplace l'agent sur la case disponible suivant le chemin le plus proche de la destination
        si agent bloque -> ne fait rien  
    */
    public void deplacer(){
    // on sauvegarde ce qu'il y a dans la case de distination
    String destinationString =  this.grille.getGrille()[this.x_final][this.y_final];
    
    // On met ajout source (s) et destination (e)
    this.grille.getGrille()[this.x][this.y] = "s";
    this.grille.getGrille()[this.x_final][this.y_final] = "e";
 
    Stack<Cell> path = new Stack<>();
    Cell origin = new Cell(this.x, this.y);
    Cell destination = new Cell(this.x_final, this.y_final);
    
    // On recupere le chemin le plus court dans la grille dans la variable path
    ShortestPath.shortestPath(this.grille.getGrille(), origin, destination, path);

    // Si il existe un chemin
    if(!path.isEmpty()){
        System.out.println();
       Cell moveTo = path.get(path.size() - 2);
       System.out.print("Agent N° "+ this.numeroAgent +" moved from ["+this.x+","+this.y+"] to ["+moveTo.row+", "+moveTo.col+"]");
        
       this.grille.getGrille()[this.x][this.y] = "0";
        this.grille.getGrille()[this.x_final][this.y_final] = destinationString;
        this.grille.getGrille()[moveTo.row][moveTo.col] = "1";
  
        this.x = moveTo.row;
        this.y = moveTo.col;
        
        // On verifie si l'agent est dans sa case finale
        this.checkFinalPosition();
    }
    else{
        System.out.println("Agent N° " + this.numeroAgent + " can't move"
                + "");
        this.grille.getGrille()[this.x][this.y] = "1";
        this.grille.getGrille()[this.x_final][this.y_final] = destinationString;

    
    }
        this.grille.afficher();
                System.out.println();
        }
    // verifie si l'agent est dans sa position finale en mettant a jour sa variable
    public void checkFinalPosition(){
        if( (this.x == this.x_final) && ( this.y == this.y_final )){
            this.isAtFinalDestination = true;
        }
        else 
            this.isAtFinalDestination = false;
    }
}
