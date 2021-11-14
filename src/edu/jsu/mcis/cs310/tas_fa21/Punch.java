package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 *
 * @author Brice
 */

public class Punch {
    
    private int id;
    private int terminalid;
    private Badge badgeid;
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
    public Punch(int terminalid, Badge badgeid, int punchtypeid){
    
    this.badgeid = badgeid;
    this.terminalid = terminalid;
    this.punchtypeid = PunchType.values()[punchtypeid];
    this.id = 0;
    this.originaltimestamp = LocalDateTime.now();
    this.adjustedtimestamp = LocalDateTime.now();
    this.adjustmenttype = null;
  }
    
    /*
       new punch
     */
    
    public Punch(String badge, int terminalid, int punchtypeid) {
        this.badgeid = badgeid;
        this.terminalid = terminalid;
        this.punchtypeid = PunchType.values()[punchtypeid];;
        
        
    }

    /**
     *
     * @return
     */
    public Badge getBadgeid() {
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
    
    public void setBadge(Badge badgeid){
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

    
    
    public void adjust (Shift s){
        
           
      
      
  }
  
    /**
     *
     * @return
     */
    public String printOriginal(){
        return "#" + badgeid + " " + punchtypeid + ": " + originaltimestamp;
    }
  
}
  
  
  