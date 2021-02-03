package javaapplication5;

import java.util.ArrayList;
import javaapplication5.agent.Agent;
import javax.swing.*;

/**
 *
 * @author Aurel
 */
public class AgentManager {
    
    // liste des agents presents dans la grille
    public static ArrayList<Agent> listAgent;
    public Grille grille;
    // nombre de deplacement realisé dans la grille
    public static int round = 1;
    
    public AgentManager(ArrayList list, Grille grille)
    {
        this.listAgent = list;
    }
    
    public Agent getAgentBlocking(Agent agentStuck){
        Agent a = null;
        int i = 0;
        boolean foundBlockingAgent = false;
        // boucle tant qu'on est pas à la fin de la liste des agents et qu'on a pas trouvé un agent bloquant
        while(i < this.listAgent.size() && foundBlockingAgent == false)
        {
            Agent currentAgent = this.listAgent.get(i);
            // l'agent n'est pas bloque ?
            if(!currentAgent.isStuck()){
                // si il bouge il debloque l'agent ?
                 String[][] copyGrille = this.grille.copyGrille();
                copyGrille[agentStuck.x][agentStuck.y] = "s";
                copyGrille[agentStuck.x_final][agentStuck.y_final] = "e";

                // si oui return currentAgent
            }
            
            i++;
        }
        
        
        return a;
    }
    
    /*
     return true si tous les agents sont dans leur position finale
            false si au moins 1 agent n'est pas dans sa position finale;
    */
    public boolean allAgentsInFinalPosition(){
        boolean allAgentsInFinalPosition = true;
        
        int i = 0;
        while(i < this.listAgent.size() && allAgentsInFinalPosition)
        {
            if(this.listAgent.get(i).estDansPositionFinale() == false)
            {
                allAgentsInFinalPosition = false;
            }
            i++;

        }
        return allAgentsInFinalPosition;
    }
    
    public boolean allAgentsStuck(){
        boolean allAgentsAreStuck = false;
        int i = 0;
        while(i <  this.listAgent.size() && allAgentsAreStuck == false)
        {
            if(this.listAgent.get(i).isStuck())
                allAgentsAreStuck = true;
            i++;
        }
        
        
        return allAgentsAreStuck;
    }
    
    // On fait bouger d'une case chaque agent si celui-ci n'est pas dans sa case finale et pas coince
    public void moveAgent(){
        for(int i = 0; i < this.listAgent.size();i++){
            //
            if(!this.listAgent.get(i).estDansPositionFinale()){
                System.out.println("Round N° " + round);
                this.listAgent.get(i).deplacer();
                round++;
            }
        }
    }
    
    
    // Affiche l'etat final de chaque agent
    public void afficherEtatFinal(){
        System.out.println("Etat final");
        for(int i = 0 ; i < this.listAgent.size() ; i++)
        {
            System.out.println(this.listAgent.get(i).toString());
        }
    }
    
    // retourne l'agent qui se trouve aux coordonnes [x,y]
    public static Agent getAgentByCoordinates(int x, int y){
        Agent agent = null;
        
        boolean trouve = false;
        int i = 0;
        
        while(i < listAgent.size() &&  !trouve ){
            Agent currentAgent = listAgent.get(i);
            if(currentAgent.x == x && currentAgent.y == y)
            {
                agent = currentAgent;
                trouve = true;
            }
            i++;
        }
        return agent;
    }
    
    // retourne les agents qui se trouvent aux coordonnees cells
    public static ArrayList<Agent> getAgentByCoordinates(ArrayList<Cell> cells)
    {
        ArrayList<Agent> toReturn =new ArrayList<Agent>();
        for(Cell c : cells)
            toReturn.add(getAgentByCoordinates(c.row, c.col));
        return toReturn;
    }
    
    
    // trie une liste d'agents en fonction d'un critere (dans la position finale)
    public static ArrayList<Agent> getAgentInSpecificPosition(ArrayList<Agent> agentList, boolean inFinalePosition){
        ArrayList<Agent> toReturn = new ArrayList<Agent>();
        
        for(Agent a : agentList)
        {
            if(inFinalePosition)    // On garde les agents qui sont dans leur position finale
            {
                if(a.estDansPositionFinale())
                    toReturn.add(a);
            }
        else{ // on garde les agents qui ne sont pas dans leur position finale
                if(!a.estDansPositionFinale())
                        toReturn.add(a);
            }
        }
        
        
        return toReturn;
    }
    
    
    
}
