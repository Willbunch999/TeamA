package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;

/**
 *
 * @author Brice
 */

public class Punch {
    
    private int id;
    private int terminalid;
    private String badgeid;
    private PunchType punchtypeid;
    private LocalDateTime originaltimestamp;
    private LocalDateTime adjustedtimestamp;
    private String adjustmenttype;
    
    /**
     *
     * @param terminalid
     * @param badgeid
     * @param punchtypeid
     */
    public Punch(int terminalid, String badgeid, int punchtypeid){
    
    this.badgeid = badgeid;
    this.terminalid = terminalid;
    this.punchtypeid = PunchType.values()[punchtypeid];
    this.id = 0;
    this.originaltimestamp = LocalDateTime.now();
    this.adjustedtimestamp = LocalDateTime.now();
    this.adjustmenttype = null;
  }
    
    /*
        punch
     */
    
    public Punch(int punchtypeid, String badgeid, int terminalid, LocalDateTime originaltimestamp){
        this.punchtypeid = PunchType.values()[punchtypeid];
        this.badgeid = badgeid;
        this.terminalid = terminalid;
        this.originaltimestamp = originaltimestamp;
        
    }

    /**
     *
     * @return
     */
    public String getBadgeid() {
        return badgeid;
  }
  
    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }
  
    /**
     *
     * @return
     */
    public int getTerminalid(){
    return terminalid;
  }
  
    /**
     *
     * @return
     */
    public LocalDateTime getOriginaltimestamp(){
      return originaltimestamp;
  }
  
    /**
     *
     * @return
     */
    public String getAdjustmenttype(){
    return adjustmenttype;
}
  
    /**
     *
     * @return
     */
    public PunchType getPunchtypeid(){
      return punchtypeid;
  }
    
    public LocalDateTime getAdjustedTimestamp(){
        return adjustedtimestamp;
    }
    
    public void setBadge(String badgeid){
        this.badgeid = badgeid;
    }
            
   /**
     *
     * @param id
     */
    public void setID(int id) {
      this.id = id;
  }
    
    public void setTerminalid(int terminalid){
        this.terminalid = terminalid;
    }
    
     /**
     *
     * @param originaltimestamp
     */
    
    public void setOriginalTimeStamp(LocalDateTime originaltimestamp) {
      this.originaltimestamp = originaltimestamp;
  }
    
    /**
     *
     * @param adjustmenttype
     */
    public void setAdjustmenttype( String adjustmenttype){
      this.adjustmenttype = adjustmenttype; 
  }
    
    public String printAdjusted(){
        StringBuilder s = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE" + " LL/dd/uuuu HH:mm:ss");
        
        s.append('#').append(badgeid).append(" ").append(punchtypeid);
        s.append(": ").append(formatter.format(adjustedtimestamp).toUpperCase());
        s.append(" (").append(adjustmenttype).append(")");
        System.out.println(s);
        
        return s.toString();
                
    }
    
    public String printOriginal(){
        StringBuilder s = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE" + " LL/dd/uuuu HH:mm:ss");
        
        s.append('#').append(badgeid).append(" ").append(punchtypeid);
        s.append(": ").append(formatter.format(originaltimestamp).toUpperCase());
        System.out.println(s.toString());
        
        return s.toString();
           
    }
    
    
    public void adjust(Shift s){
        TemporalField usWeekDay = WeekFields.of(Locale.US).dayOfWeek();
        
        LocalDateTime Start = s.getStart().atDate(originaltimestamp.toLocalDate());
        LocalDateTime Stop = s.getStop().atDate(originaltimestamp.toLocalDate());
        
        LocalDateTime lunchStart = s.getLunchStart().atDate(originaltimestamp.toLocalDate());
        LocalDateTime lunchStop = s.getLunchStop().atDate(originaltimestamp.toLocalDate());
        
        
        
        int dayofweek = originaltimestamp.get(usWeekDay);
        
           
      
      
  }
  
   
  
}
  
  
  