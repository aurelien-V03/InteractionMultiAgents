package javaapplication5.agent;
import java.util.ArrayList;
import java.util.Stack;
import javaapplication5.Grille;
import javaapplication5.Cell;
import javaapplication5.Path;
import javaapplication5.AgentManager;


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
        
        // recupere des coordonnees pour l'agent
        int[] CoordonateSource = this.grille.getFreeCoordonate(true);
        
        this.x = CoordonateSource[0];
        this.y = CoordonateSource[1];

        this.numeroAgent = nbPieces;
        // ajout de l'agent dans le tableau
        g.ajouterAgent(x, y, nbPieces);
        
        // recupere coordonnee a atteindre
        int[] destination = this.grille.getFreeCoordonateByGoal(this.x, this.y);
        
        this.x_final = destination[0];
        this.y_final = destination[1];
        
        // ajout de l'objectif de l'agent dans la grille
         g.ajouterObjectifAgent(this.x_final, this.y_final);

        
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
        g.ajouterAgent(x, y, this.numeroAgent);
        g.ajouterObjectifAgent(x_final, y_final);
        nbPieces++;
        System.out.println(this.toString());

    }
    // retourne vrai si l'agent est dans sa position finale
    public boolean estDansPositionFinale()
    {
        return this.isAtFinalDestination;
    }
    
    // retourne le numero de l'agent
    public int getNumero(){
        return this.numeroAgent;
    }
 
    public String toString(){
        return "Agent N° " + this.numeroAgent + " :  coordonnee [" + this.x + "," + this.y + "] / objectif ["+this.x_final + "," + this.y_final+"]";
    }
    
    
    //    Deplace l'agent sur la case disponible suivant le chemin le plus proche de la destination
    //    si agent bloque -> ne fait rien  
    public void deplacer(){
    
        // Cree une copie de la grille avec et on ajoute la source (s) et destination (e)
        String[][] copyGrille = this.grille.copyGrille();
        copyGrille[this.x][this.y] = "s";
        copyGrille[this.x_final][this.y_final] = "e";

        // liste des cellules du chemin entre l'agent et sa destination
        Stack<Cell> path = new Stack<>();

        // cellule ou se trouve l'agent
        Cell origin = new Cell(this.x, this.y);
        // cellule ou doit se rendre l'agent
        Cell destination = new Cell(this.x_final, this.y_final);

        // On recupere le chemin le plus court dans la grille dans la variable path
        Path.shortestPath(copyGrille, origin, destination, path);

        System.out.println(this.toString());

        // Affichage du chemin
        System.out.print("Chemin : ");
        for(Cell c : path)
            System.out.print(c);
         System.out.println();

         
        // cellule ou l'agent se deplacera
        Cell moveTo = null;

        // Si l'agent se trouve a coté de sa case finale 
        if(path.size() == 2){
            Cell finalRow = path.get(0);

            // cellule disponible
            if(this.grille.getGrille()[finalRow.row][finalRow.col].equals("0"))
            {
                  System.out.println("L'agent se deplace dans sa case finale : " + finalRow.toString());
                   this.grille.deplacerAgent(this, finalRow);
            }
            else{
                // on essaye de deplacer l'agent qui bloque 
                 Agent agentBloquant =  AgentManager.getAgentByCoordinates(finalRow.row, finalRow.col);
                   // si agent n'est pas bloqué alors le déplacer
                   if(!agentBloquant.isStuck()){
                       agentBloquant.decaler(this.x,this.y);
                       this.deplacer();
                   }
            }
        }
        // Si il existe un chemin entre l'agent et son objectif
        else if(!path.isEmpty()){
            // recuperes la case ou il faut aller
            moveTo = path.get(path.size() - 2);
           System.out.print("Agent N° "+ this.numeroAgent +" se deplace de ["+this.x+","+this.y+"] a ["+moveTo.row+", "+moveTo.col+"]");

           // l'agent se déplace dans la case adjacente
           this.grille.deplacerAgent(this, moveTo);
        }
        // Si il n'y a pas de chemin, un agent adjacent se décale
        else{
            System.out.println("Agent ne peut pas bouger"+ "");

            // Recuperes la liste des cellules avec des agents autour de lui
             ArrayList<Cell> agentCell = this.grille.getAgentCellAroundAgent(this.x, this.y,"1");

             int i = 0;
             boolean anyAgentMove = true;

             while(i < agentCell.size() && anyAgentMove)
             {
                // recupere la cellule 
                Cell c = agentCell.get(i);
                // recupere l'agent de cette cellule
                Agent agentBloquant =  AgentManager.getAgentByCoordinates(c.row, c.col);
                   // si agent n'est pas bloqué alors le déplacer
                   if(!agentBloquant.isStuck()){
                       agentBloquant.decaler(this.x,this.y);
                       anyAgentMove = false;
                   }
                   i++;
             }

             if(anyAgentMove == false){
                 System.out.println("agent Rebouge");
                 this.deplacer();
             }
           


        }
            // On verifie si l'agent est dans sa case finale
            this.checkFinalPosition();

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

        // retourne true si l'agent est bloquee (aucune case "0" autour de lui)
        public boolean isStuck(){
            if(this.grille.getAgentCellAroundAgent(this.x, this.y, "0").size() == 0)
                return true;
            else 
                return false;
        }

        // l'agent se decale sur une cable disponible autour de lui, de preference pas la meme colonne et ligne
        public void decaler(int x, int y){
            ArrayList<Cell> availableCell = this.grille.getAgentCellAroundAgent(this.x, this.y, "0");   

            ArrayList<Cell> bestCellToMove = new ArrayList<Cell>();
            ArrayList<Cell> worstCellToMove = new ArrayList<Cell>();

            for(int i = 0; i < availableCell.size(); i++)
            {
                Cell c = availableCell.get(i);
                if(c.row != x && c.col != y) // colonne et ligne differente    
                    bestCellToMove.add(c);        
                else
                     worstCellToMove.add(c);      
            }

            Cell toMove = null;

            if(bestCellToMove.size() > 0)
                toMove = bestCellToMove.get(0);
            else
                toMove = worstCellToMove.get(0);

            this.grille.getGrille()[this.x][this.y] = "0";
            this.grille.getGrille()[toMove.row][toMove.col] = "1";

            System.out.print("Agent N° "+ this.numeroAgent +" se decale de ["+this.x+","+this.y+"] a ["+toMove.row+", "+toMove.col+"]");
            this.x = toMove.row;
            this.y = toMove.col;

            this.checkFinalPosition();
        }
  
}
