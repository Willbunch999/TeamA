package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;
import java.time.*;


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
    
public Punch(int terminalid, Badge badge, int punchtypeid){
    
    this.badgeid = badgeid;
    this.terminalid = terminalid;
    this.punchtypeid = PunchType.values()[punchtypeid];
    this.id = 0;
    this.originaltimestamp = LocalDateTime.now();
    this.adjustedtimestamp = LocalDateTime.now();
    this.adjustmenttype = null;
  }

  public Badge getBadgeid() {
        return badgeid;
  }
  
  public int getId() {
        return id;
    }
  
  public int getTerminalID(){
    return terminalid;
  }
  
  public LocalDateTime getOriginalTimeStamp(){
      return originaltimestamp;
  }
  
  public String getAdjustmenttype(){
    return adjustmenttype;
}
  
  public PunchType getPunchtypeid(){
      return punchtypeid;
  }
  
  public void setID(int id) {
      this.id = id;
  }
   
  public void setAdjustmenttype( String adjustmenttype){
      this.adjustmenttype = adjustmenttype; 
  }
  
  public void setOriginalTimeStamp(LocalDateTime originaltimestamp) {
      this.originaltimestamp = originaltimestamp;
  }
  
  
  public String printOriginal(){
        return "#" + badgeid + " " + punchtypeid + ": " + originaltimestamp;
    }
  
}
  
  
  