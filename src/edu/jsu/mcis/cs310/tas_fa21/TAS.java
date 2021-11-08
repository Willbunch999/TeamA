/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa21;
import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;

/**
 *
 * @author wabun, Stephen Thompson
 */
public class TAS {
    public static void main(String[] args){
        
    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailyPunchList, Shift shift){
       
        int totalMinutes = 0;
       
        for (int i = 0; i < dailyPunchList.size(); i++){
            Punch punch = dailyPunchList.get(i);
            PunchType punchID = punch.getPunchtypeid();
            
            if (punchID.toString() != "TIME OUT"){
                try{
                    if ("CLOCK IN".equals(punchID.toString())){
                        shift.setStart(punch.getOriginalTimeStamp().toLocalTime());
                    } else if ("CLOCK OUT".equals(punchID.toString())){
                        shift.setStop(punch.getOriginalTimeStamp().toLocalTime());
                    }  
                }
                catch(Exception e){}
            }
            
            totalMinutes += shift.getStart().until(shift.getStop(), MINUTES);
        }
       
        return totalMinutes;
    }
}
