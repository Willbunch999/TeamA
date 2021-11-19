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
    
     public String printOriginal(){
         DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
         StringBuilder s = new StringBuilder();
          
        s.append("#").append(badgeid).append(" ").append(punchtypeid);
        s.append(": ").append(originaltimestamp.format(dtf).toUpperCase());
       
        return s.toString();
           
    }
    
    
    
    public String printAdjusted(){
        StringBuilder s = new StringBuilder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEE" + " LL/dd/uuuu HH:mm:ss");
        
        s.append('#').append(badgeid).append(" ").append(punchtypeid);
        s.append(": ").append(dtf.format(adjustedtimestamp).toUpperCase());
        s.append(" (").append(adjustmenttype).append(")");
        System.out.println(s);
        
        return s.toString();
                
    }
    
   
    
    
    public void adjust(Shift s){
        TemporalField usWeekDay = WeekFields.of(Locale.US).dayOfWeek();
        int dayofweek = originaltimestamp.get(usWeekDay);
         
        LocalDateTime Start = s.getStart().atDate(originaltimestamp.toLocalDate());
        LocalDateTime Stop = s.getStop().atDate(originaltimestamp.toLocalDate());
        
        LocalDateTime lunchStart = s.getLunchStart().atDate(originaltimestamp.toLocalDate());
        LocalDateTime lunchStop = s.getLunchStop().atDate(originaltimestamp.toLocalDate());
        
        LocalDateTime StartInterval = Start.minusMinutes(s.getInterval());
        LocalDateTime StartGrace = Start.plusMinutes(s.getGracePeriod());
        LocalDateTime StartDock = Start.plusMinutes(s.getDock());
      
        LocalDateTime StopInterval = Stop.plusMinutes(s.getInterval());
        LocalDateTime StopGrace = Stop.minusMinutes(s.getGracePeriod());
        LocalDateTime StopDock = Stop.minusMinutes(s.getDock());
        
        int roundint = originaltimestamp.toLocalTime().getMinute()% s.getInterval();
        int half = s.getInterval()/2;
        long roundlong;
        
        
        if(dayofweek != Calendar.SATURDAY && dayofweek != Calendar.SUNDAY) {
           if(punchtypeid == PunchType.CLOCK_IN) {
               
               //interval adjustment
               if((originaltimestamp.isAfter(StartInterval) || originaltimestamp.isEqual(StartInterval)) && originaltimestamp.isBefore(Start)) {
                  adjustedtimestamp = Start;
                  adjustmenttype = "Start";
               }
               //dock adjustment
               else if ((originaltimestamp.isBefore(StartDock) || originaltimestamp.isEqual(StartInterval)) && originaltimestamp.isAfter(StartGrace)) {
                   adjustedtimestamp = StartDock;
                   adjustmenttype = "Dock";
               }
               //grace adjustment
               else if ((originaltimestamp.isBefore(StartGrace) || originaltimestamp.isEqual(StartGrace)) && originaltimestamp.isAfter(Start)) {
                   adjustedtimestamp = Start;
                   adjustmenttype = "Start";
               }
               //lunch adjustment
               else if ((originaltimestamp.isBefore(lunchStop) || originaltimestamp.isEqual(lunchStop)) && originaltimestamp.isAfter(lunchStart)) {
                   adjustedtimestamp = lunchStop;
                   adjustmenttype = "Lunch Stop";
               }
           }
           else if (punchtypeid == PunchType.CLOCK_OUT) {
                //interval adjustment
                if ((originaltimestamp.isBefore(StopInterval) || originaltimestamp.isEqual(StopInterval)) && originaltimestamp.isAfter(Stop)){
                    adjustedtimestamp = Stop;
                    adjustmenttype = "Stop";
                }
                //dock adjustment
                else if ((originaltimestamp.isAfter(StopDock) || originaltimestamp.isEqual(StopDock)) && originaltimestamp.isBefore(StopGrace)){
                    adjustedtimestamp = StopDock;
                    adjustmenttype = "Shift Dock";
                }
                //grace adjustment
                else if ((originaltimestamp.isAfter(StopGrace) || originaltimestamp.isEqual(StopGrace)) && originaltimestamp.isBefore(Stop)){
                    adjustedtimestamp = Stop;
                    adjustmenttype = "Shift Stop";
                }
                //lunch adjustment
                else if (originaltimestamp.isBefore(lunchStop) && (originaltimestamp.isAfter(lunchStart) || originaltimestamp.isEqual(lunchStart))){
                    adjustedtimestamp = lunchStart;
                    adjustmenttype = "Lunch Start";
                }    
            }
        }
      
      
  }
  
   
  
}
  
  
  