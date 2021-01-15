/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication5;

import javaapplication5.agent.Agent;
import javaapplication5.Grille;
/**
 *
 * @author Aurel
 */
public class AgentThread extends Agent implements Runnable
{
    public AgentThread(Grille g)
    {
        super(g);
    }
    
    public AgentThread(Grille g,  int x, int y, int x_final, int y_final)
    {
        super(g,x,y,x_final,y_final);
    }

    @Override
    public void run() {
           while(!super.estDansPositionFinale())
           {
               super.deplacer();
           }
    }
}
