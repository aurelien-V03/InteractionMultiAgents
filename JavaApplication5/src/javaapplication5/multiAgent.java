/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;
import java.awt.GridLayout;
import java.util.ArrayList;
import javaapplication5.*;
import javaapplication5.agent.*;
import javaapplication5.Path;
import javaapplication5.AgentManager;
import javaapplication5.AgentThread;
import javax.swing.*;
/**
 *
 * @author Aurel
 */
public class multiAgent {

    public static void main(String[] args) {
        
        // instanciation de la grille du jeu
        Grille g = new Grille();
        
        // Contient tous les agents du presents sur la grille
        ArrayList listeAgents = new ArrayList();
        
        // instanciation des agents
        Agent agent2 = new Agent(g);
        Agent agent1 = new Agent(g);
        Agent agent3 = new Agent(g);
        Agent agent4 = new Agent(g);
        
        Agent agent5 = new Agent(g);
        Agent agent6 = new Agent(g);
        
        
       Agent agent7 = new Agent(g);
       Agent agent8 = new Agent(g);
        
        //Agent agent9 = new Agent(g);
       //Agent agent10 = new Agent(g);
        
        listeAgents.add(agent2);
        listeAgents.add(agent5);
        listeAgents.add(agent3);
        listeAgents.add(agent4);
        listeAgents.add(agent1);
        listeAgents.add(agent6);
        
        listeAgents.add(agent7);
        listeAgents.add(agent8);

        //listeAgents.add(agent9);
        //listeAgents.add(agent10);
    
        
        
        AgentManager agentManag = new AgentManager(listeAgents,g);
        System.out.println("Grille de base");
        g.afficher();
        System.out.println();
       
       while(!agentManag.allAgentsInFinalPosition()){
           agentManag.moveAgent();
       }
       
       
       // Affichage etat final
       agentManag.afficherEtatFinal();
       
       
   
    }
    
}
