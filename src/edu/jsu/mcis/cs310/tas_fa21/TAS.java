/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa21;
import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;
import java.time.format.DateTimeFormatter;

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
                        shift.setStart(punch.getOriginaltimestamp().toLocalTime());
                    } else if ("CLOCK OUT".equals(punchID.toString())){
                        shift.setStop(punch.getOriginaltimestamp().toLocalTime());
                    }  
                }
                catch(Exception e){}
            }
            
            totalMinutes += shift.getStart().until(shift.getStop(), MINUTES);
        }
       
        return totalMinutes;
    }
            public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE" + " LL/dd/uuuu HH:mm:ss");
        ArrayList<HashMap<String, String>> jsonData = new ArrayList<>();
//{\"originaltimestamp\":\"SAT 08\\/11\\/2018 05:54:58\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 06:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1087\",\"punchtype\":\"CLOCK IN\"}
        for(Punch punch: dailypunchlist){
            
            HashMap<String, String> punchData = new HashMap<>();
            
            punchData.put("id", String.valueOf(punch.getId()));
            punchData.put("badgeid", String.valueOf(punch.getBadgeid()));
            punchData.put("terminalid", String.valueOf(punch.getTerminalid()));
            punchData.put("punchtype", String.valueOf(punch.getPunchtypeid()));
            punchData.put("adjustmenttype", String.valueOf(punch.getAdjustmenttype()));
            punchData.put("originaltimestamp", String.valueOf(punch.getOriginaltimestamp().format(format).toUpperCase()));
            punchData.put("adjustedtimestamp", String.valueOf(punch.getAdjustedTimestamp().format(format).toUpperCase()));
            jsonData.add(punchData);
            
        }
        
        String json = JSONValue.toJSONString(jsonData);

        System.out.println(json);
        return json;
    }
}
