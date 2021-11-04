/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa21;
import java.time.*;
import java.util.ArrayList;

/**
 *
 * @author wabun
 */
public class TAS {
   public static int calculateTotalMinutes(ArrayList<Punch> dailyPunchList, Shift shift){
       
       int totalMinutes = 0;
       
       for (int i = 0; i < dailyPunchList.size(); i++){
            Punch punch = dailyPunchList.get(i);
            PunchType punchID = punch.getPunchtypeid();
            if (punchID == CLOCK_IN){
                ;
            } else if (punchID == CLOCK_OUT){
                ;
            } else if (punchID == TIME_OUT){
                ;
            } else {
                    
            }
       }
       return totalMinutes;
   }
}
