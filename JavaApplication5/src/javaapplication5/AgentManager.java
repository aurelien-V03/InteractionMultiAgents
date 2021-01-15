package javaapplication5;

import java.util.ArrayList;
import javaapplication5.agent.Agent;
import javax.swing.*;

/**
 *
 * @author Aurel
 */
public class AgentManager {
    
    public ArrayList<Agent> listAgent;
    
    public AgentManager(ArrayList list)
    {
        this.listAgent = list;
       
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
    
    // On fait bouger d'une case chaque agent si celui-ci n'est pas dans sa case finale et pas coince
    public void moveAgent(){
        for(int i = 0; i < this.listAgent.size();i++){
            //
            if(!this.listAgent.get(i).estDansPositionFinale()){
                this.listAgent.get(i).deplacer();
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
    
}
