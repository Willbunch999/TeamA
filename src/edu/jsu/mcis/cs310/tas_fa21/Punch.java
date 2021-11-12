package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Brice
 */

public class Punch {
    
    private int id;
    private int terminalid;
    private String badgeid;
    private int punchtypeid;
    private long originaltimestamp;
    private String adjustedtimestamp;
    private String adjustmenttype;
    
    /**
     *
     * @param terminalid
     * @param badge
     * @param punchtypeid
     */
    public Punch(int terminalid, Badge badge, int punchtypeid){
    
    this.badgeid = badgeid;
    this.terminalid = terminalid;
    this.punchtypeid = punchtypeid;
    this.id = 0;
    this.originaltimestamp = System.currentTimeMillis();
    this.adjustedtimestamp = null;
    this.adjustmenttype = null;
  }
    
    /*
       new punch
     */
    
    public Punch(String badge, int terminalid, int punchtypeid) {
        this.badgeid = badgeid;
        this.terminalid = terminalid;
        this.punchtypeid = punchtypeid;
        
        
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
    public long getOriginaltimestamp(){
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
    public int getPunchtypeid(){
      return punchtypeid;
  }
  
    /**
     *
     * @param id
     */
    public void setID(int id) {
      this.id = id;
  }
   
    /**
     *
     * @param adjustmenttype
     */
    public void setAdjustmenttype( String adjustmenttype){
      this.adjustmenttype = adjustmenttype; 
  }
  
    /**
     *
     * @param neworiginaltimestamp
     */
    public void setOriginalTimeStamp(Long neworiginaltimestamp) {
      this.originaltimestamp = neworiginaltimestamp;
  }
     
    public String printAdjustedTimestamp(){
        return adjustedtimestamp;
    }
    
    public void setAdjustedTimestamp(String adjustedtimestamp){
        this.adjustedtimestamp = adjustedtimestamp;
    }
    
    /**
     *
     * @return
     */
    public String printOriginalTimeStamp(){
      GregorianCalender gc = new GregorianCalender();
      StringBulder sb = new StringBuilder();
      gc.setTimeInMillis(OringinalTimeStamp);
      SimpleDateFormat sdf = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
      
      sb.append("#").append(badgeid);
      
      switch (punchtypeid) {
          
          case 0:
              sb.append(" CLOCKED OUT: ");
              break;
              
          case 1:
              sb.append(" CLOCKED IN: ");
              break;
          
          case 2:
              sb.append(" TIMED OUT: ");
              break;
              
          default:
              sb.append(" ERROR ");
              break;
      }
        String ots = sdf.format(gc.getTime());
        sb.append(ots);
        
        return (sb.toString().toUpperCase());
    }
      
       public String adjustTimestamp(GregorianCalendar gc, adjustmentnote){ 
           
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
        
        sb.append("#").append(badgeid);

        switch (punchtypeid) {
            case 0:
                sb.append(" CLOCKED OUT: ");
                break;
            case 1:
                sb.append(" CLOCKED IN: ");
                break;
            case 2:
                sb.append(" TIMED OUT: ");
                break;
            default:
                sb.append(" ERROR ");
                break;
        }
        
        String ots = sdf.format(gc.getTime()).toUpperCase();
        sb.append(ots);
        sb.append(" ").append(adjustmentnote);

        return (sb.toString());
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
  
  
  