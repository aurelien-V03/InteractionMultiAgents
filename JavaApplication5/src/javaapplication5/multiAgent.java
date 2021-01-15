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
import javaapplication5.ShortestPath;
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
        Agent agent2 = new Agent(g,4,4,2,3);
        Agent agent1 = new Agent(g,3,4,2,2);
        Agent agent3 = new Agent(g,4,3,1,1);
        Agent agent4 = new Agent(g,3,3,1,1);

        listeAgents.add(agent2);
        listeAgents.add(agent1);
        listeAgents.add(agent3);
        listeAgents.add(agent4);

        AgentManager agentManag = new AgentManager(listeAgents);
        
        g.afficher();
       
       while(!agentManag.allAgentsInFinalPosition()){
   
           agentManag.moveAgent();
         
       }
       
       
       // Affichage etat final
       agentManag.afficherEtatFinal();
       
       
       
       
       /*
        ArrayList listeAgentsThread = new ArrayList();

       
       Thread a1 = new Thread(new AgentThread(g));
       Thread a2 = new Thread(new AgentThread(g));
       Thread a3 = new Thread(new AgentThread(g));
       Thread a4 = new Thread(new AgentThread(g));

                     
       listeAgentsThread.add(a1);
       listeAgentsThread.add(a2);
       listeAgentsThread.add(a3);
       listeAgentsThread.add(a4);
       
       for(int i = 0 ; i < listeAgentsThread.size();i++)
       {
           Thread thread =  (Thread)listeAgentsThread.get(i);
           thread.start();
       }
       
       */
       
        
    }
    
}
