package javaapplication5;
import java.util.ArrayList;
import java.util.Random;
import javaapplication5.agent.Agent;
import javaapplication5.Cell;
import javaapplication5.Path;
/**
 *
 * @author Aurel
 */
public class Grille {
    
    // grille qui contient les agents
    private String[][] grille;
    //grille qui contient la liste des objectifs de chaque agent (0 = pas objectif / 1 = un objectif)
    private String[][] grilleObjectif;
    
    // Taille de la grille
    private final static int MAX_X = 5;
    private final static int MAX_Y = 5;
    
    public Grille(){
        System.out.println("Instanciation grille");
        this.grille = new String[MAX_X][MAX_Y];
        this.grilleObjectif = new String[MAX_X][MAX_Y];
        this.initialisationGrille();
    }
   
    //retourne la grille
    public String[][] getGrille(){
        return this.grille;
    }
    
    // initialise les 2 grilles, met toutes les cases du tableau a "0"
    public void initialisationGrille(){
         for(int i = 0; i  < this.grille.length ; i++)
        {
            for(int j = 0 ; j < this.grille[i].length;j++)
            {
                this.grille[i][j] = "0";
                this.grilleObjectif[i][j] = "0";
            }
        }
    }
    
     // Affiche la grille
    public void afficher(){
        System.out.println();
         for(int i = 0; i  < this.grille.length ; i++)
        {
            for(int j = 0 ; j < this.grille[i].length;j++)
            {
                // si cell == 1 (agent)
                if(this.grille[i][j].equals("1"))
                    System.out.print(AgentManager.getAgentByCoordinates(i, j).getNumero());
                else
                    System.out.print(this.grille[i][j]);

            }
            System.out.println();
        }
    }
    
    // ajoute l'agent de numero numAgent au coordonne [x,y]
    public void ajouterAgent(int x,int y, int numAgent)
    {
        this.grille[x][y] = "1";
    }
    
    public void ajouterObjectifAgent(int x, int y){
         this.grilleObjectif[x][y] = "1";

    }
    
  
    
    // deplace l'agent a vers les coordonnees c 
    public void deplacerAgent(Agent a, Cell c)
    {        
       // MAJ de la grille
        this.grille[a.x][a.y] = "0";
        this.grille[c.row][c.col] = "1";
  
        // MAJ de l'agent
        a.x = c.row;
        a.y = c.col;
    }
    
    
    // Retourne un tableau int[2] contenu une coordonne aleatoire libre dans une grille String[][]
    public int[] getFreeCoordonate(boolean grilleAgent){
        String[][] grilleToFind;
        if(grilleAgent)
              grilleToFind = this.grille;
        else
            grilleToFind = this.grilleObjectif;
        
        
        int[] freeCoordonate = new int[2];
        
        int x;
        int y;
  
        boolean foundFreeCoordinate = false;
        
        while(!foundFreeCoordinate)
        {
            Random r =new Random();
            x = r.nextInt(5);
            y = r.nextInt(5);
            if(grilleToFind[x][y] == "0"){
                foundFreeCoordinate = true;
                freeCoordonate[0] = x;
                freeCoordonate[1] = y;
            }
        }
        return freeCoordonate;
    }
    
    public int[] getFreeCoordonateByGoal(int x,int y){
        int[] coordonatesGoal = new int[2];
        coordonatesGoal =  this.getFreeCoordonate(x, y, false);
        this.grilleObjectif[coordonatesGoal[0]][coordonatesGoal[1]] = "1";
        return coordonatesGoal;
    }
    
    public int[] getFreeCoordonate(int x_forbidden, int y_forbidden, boolean grilleAgent){
            int[] freeCoordonate = this.getFreeCoordonate(grilleAgent);
            while(freeCoordonate[0] == x_forbidden && freeCoordonate[1] == y_forbidden)
            {
                freeCoordonate = this.getFreeCoordonate(grilleAgent);
            }
            return freeCoordonate;
    }
    
    // verifie si l'emplacement grille[x,y] est libre
    public boolean isFreeCoordinate(int x, int y)
    {
        boolean isFree = false;
        if(this.grille[x][y] == "0")
        {
            isFree = true;
            // System.out.println("case libre : [" + x + "  " + y  + "] " +this.grille[x][y]);
        }
        else{
            //  System.out.println("case occupee ["+ x + "  " + y + " ] " +this.grille[x][y]);

        }
        return isFree;
    }
    
    // retourne une liste de cellule autour d'un agent de type cellType("0", "1")
    public ArrayList<Cell> getAgentCellAroundAgent(int x, int y, String cellType)
    {
        ArrayList<Cell> listCell = new ArrayList<Cell>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // pour chaque direction
        for(int[] direction : directions)
        {
            // Si coordonne valide
            if(Path.isValid(x+direction[0], y+direction[1], Grille.MAX_X, Grille.MAX_Y))
            {
                // si un agent est sur cette case 
                if(this.grille[x+direction[0]][y+direction[1]] == cellType)
                {
                    listCell.add(new Cell(x+direction[0], y+direction[1]));
                }
            }
        }
        
        return listCell;
    }
    
    public  String[][] copyGrille(){
        String[][] copy = new String[MAX_X][MAX_Y];
        
       for(int i = 0 ; i <  this.grille.length ; i++)
       {
           copy[i] = this.grille[i].clone();
       }
        return copy;
    }
}
