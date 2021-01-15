/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.util.Random;
import javaapplication5.agent.Agent;

/**
 *
 * @author Aurel
 */
public class Grille {
    // Grille qui contient les pieces
    // grille[i][j] = 0 si aucune piece et id de la piece sinon
    private String[][] grille;
    
    // Taille du tableau
    private final static int MAX_X = 5;
    private final static int MAX_Y = 5;
    
    public Grille(){
        System.out.println("Instanciation grille");
        this.grille = new String[MAX_X][MAX_Y];
        this.initialisationGrille();
    }
   
    public String[][] getGrille(){
        return this.grille;
    }
    
    // met toutes les cases du tableau a 0
    public void initialisationGrille(){
         for(int i = 0; i  < this.grille.length ; i++)
        {
            for(int j = 0 ; j < this.grille[i].length;j++)
            {
                this.grille[i][j] = "0";
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
    
    
    public void deplacerAgent(int x_initial, int y_initial, int x_target, int y_target)
    {
        // remet les anciennes valeurs 
        
        // copie le numero dans nouvelle case
        this.grille[x_target][y_target] = this.grille[x_initial][y_initial];
        // supprime l'ancienne case
        this.grille[x_initial][y_initial] = "0";
    }
    
    
    // Retourne un tableau int[2] contenu une coordonne libre pour un agent
    public int[] getFreeCoordonate(){
        int[] freeCoordonate = new int[2];
        
        int x;
        int y;
  
        boolean foundFreeCoordinate = false;
        
        while(!foundFreeCoordinate)
        {
            Random r =new Random();
            x = r.nextInt(5);
            y = r.nextInt(5);
            if(this.grille[x][y] == "0"){
                foundFreeCoordinate = true;
                freeCoordonate[0] = x;
                freeCoordonate[1] = y;
            }
        }
        return freeCoordonate;
    }
    
    public int[] getFreeCoordonate(int x_forbidden, int y_forbidden){
            int[] freeCoordonate = this.getFreeCoordonate();
            while(freeCoordonate[0] == x_forbidden && freeCoordonate[1] == y_forbidden)
            {
                freeCoordonate = this.getFreeCoordonate();
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
}
