/*
teama
Colbylee
Will
Stephen
Ruri
Brice
*/
package edu.jsu.mcis.cs310.tas_fa21;


import java.sql.*;
import java.sql.Connection;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class TASDatabase {
    
    Connection conn;
    ResultSetMetaData metadata;
    ResultSet resultset = null;        
    PreparedStatement pstSelect = null, pstUpdate = null;
    
    String server = ("jdbc:mysql://localhost/tas_fa21_v1");
    
    String username = "tasluser";
    String password = "teama";
    
    //Create and store database connection
    public TASDatabase(){

         try {
             /* Open Connection*/
             conn = DriverManager.getConnection(server, username, password);
             System.out.println("Connection Successful");
         }

         catch (SQLException e) {}  
    }
    public Punch getPunch(int id){
        
        Punch punch = null;
        
        /*Prepare query for punch with provided id*/
        String query = "SELECT * FROM punch WHERE id=?";
            
        try{
           
            pstSelect = conn.prepareStatement(query);
            pstSelect.setInt(1, id);
            boolean hasresults = pstSelect.execute();
            
            if(hasresults){
                
                resultset = pstSelect.getResultSet();
                resultset.first();
                
                int punchtypeid = resultset.getInt("punchtypeid");
                String badgeid = resultset.getString("badgeid");
                int terminalid = resultset.getInt("terminalid");
                LocalDateTime originaltimestamp = resultset.getTimestamp("originaltimestamp").toLocalDateTime();
 
                punch = new Punch(punchtypeid, badgeid, terminalid, originaltimestamp);
                
            }
            
        } catch (SQLException e) {}
        
        return punch;
    }
    public Badge getBadge(String id){
        
        String query = "SELECT * FROM badge WHERE id=?";
        
        String description = null;
        
        try{
           
            pstSelect = conn.prepareStatement(query);
            pstSelect.setString(1, String.valueOf(id));
            boolean hasresults = pstSelect.execute();
            
            if(hasresults){
                
                resultset = pstSelect.getResultSet();
                resultset.next();
                
                description = resultset.getString("description");
               
            }
        } catch (SQLException e) {}
                
        Badge outputbadge = new Badge(id, description);
        return outputbadge;
    }
    public Shift getShift(int shiftID){
        Shift outputshift = null;
        
        try {
            pstSelect = conn.prepareStatement("SELECT * from shift where id = ?");
            pstSelect.setInt(1, shiftID);
            
            boolean hasresult = pstSelect.execute();
            if(hasresult) {
                System.err.println("Getting shift data. ");
                ResultSet rs = pstSelect.getResultSet();
                rs.first();
                
                String description = rs.getString("description");
                LocalTime Start = LocalTime.parse(rs.getString("start"));
                LocalTime Stop = LocalTime.parse(rs.getString("stop"));
                int interval = rs.getInt("interval");
                int gracePeriod = rs.getInt("graceperiod");
                int dock = rs.getInt("dock");
                
                LocalTime lunchStart = LocalTime.parse(rs.getString("lunchstart"));
                LocalTime lunchStop = LocalTime.parse(rs.getString("lunchstop"));
                
                int LunchDeduct = rs.getInt("lunchdeduct");

                
                outputshift = new Shift(shiftID, description, Start, Stop, interval, gracePeriod, dock, lunchStart, lunchStop, LunchDeduct);
            }
        }
        catch(SQLException e) {
            System.err.println("** getShift: " + e.toString());
    }   
    return outputshift;
    
    }
    //get shift
    public Shift getShift(Badge b) {
        
        Shift s = null;
        
        try {
            pstSelect = conn.prepareStatement("SELECT employee.shiftid, shift.* FROM employee, shift WHERE employee.shiftid = shift.id AND employee.badgeid = ?");
            pstSelect.setString(1, b.getBadgeid());
            pstSelect.setString(2, b.getBadgeid());
            
            boolean hasresult = pstSelect.execute();
             
            if (hasresult) {
                
                ResultSet resultset = pstSelect.getResultSet();
                resultset.first();

                int shiftID = resultset.getInt("shiftid");
                
                String description = resultset.getString("description");
                LocalTime Start = LocalTime.parse(resultset.getString("start"));
                LocalTime Stop = LocalTime.parse(resultset.getString("stop"));
                int interval = resultset.getInt("interval");
                int gracePeriod = resultset.getInt("graceperiod");
                int dock = resultset.getInt("dock");
                LocalTime lunchStart = LocalTime.parse(resultset.getString("lunchstart"));
                LocalTime lunchStop = LocalTime.parse(resultset.getString("lunchstop"));
                int LunchDeduct = resultset.getInt("lunchdeduct");

                s = new Shift(shiftID, description, Start, Stop, interval, gracePeriod, dock, lunchStart, lunchStop, LunchDeduct);
                }
              
            }

        catch(SQLException e) {
            System.err.println("** getShift: " + e.toString());
        }
        
        
        return s;
    }
    
    public int insertPunch(Punch p) {
        String query;
        
        int updateCount;
        
        int results = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime originalTime = p.getOriginaltimestamp();
        
        String badgeid = p.getBadgeid().toString();
        int terminalid = p.getTerminalid(); 
        PunchType punchtypeid = p.getPunchtypeid();
        
        try {
            //prepare for the query
            query = "INSERT INTO tas_fa_v1 (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)"; 

            pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstUpdate.setInt(1, terminalid);
            pstUpdate.setString(2, badgeid);
            pstUpdate.setString(3, dtf.format(originalTime));
            pstUpdate.setInt(4, punchtypeid.ordinal());
        
            updateCount = pstUpdate.executeUpdate();

            if(updateCount > 0){

                ResultSet resultset = pstUpdate.getGeneratedKeys(); 

                if (resultset.next()){
                    results = resultset.getInt(1);
                }
            }
        }
        catch(SQLException e) {
        }
        System.err.println("New Punch ID: " + results);
        return results;              
    }
    
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, LocalDate date) {

        ArrayList<Punch> list = null;
        Punch punch = null; 
        list = new ArrayList<>(); 
        String strbadge = badge.getBadgeid();
        try {
            String query = "SELECT * FROM punch WHERE badgeid=? AND DATE(originaltimestamp)=?";
            pstSelect = conn.prepareStatement(query);
            pstSelect.setString(1, badge.getBadgeid());
            pstSelect.setDate(2, java.sql.Date.valueOf(date));
            
            
            boolean hasResults = pstSelect.execute();
            
            
            if(hasResults){
                
               list = new ArrayList<>();
                
                ResultSet resultsSet = pstSelect.getResultSet();
                while(resultsSet.next()) {
                    
                    int id = resultsSet.getInt("id");
                    int terminalid = resultsSet.getInt("terminalid");
                    String badgeid = resultsSet.getString("badgeid");
                    LocalDateTime originaltimestamp = resultsSet.getTimestamp("originaltimestamp").toLocalDateTime();
                    int punchtype = resultsSet.getInt("punchTypeId");
                    
                    Punch punchlist = new Punch(punchtype, badgeid, terminalid, originaltimestamp);
                    punch.setID(resultsSet.getInt("id"));
                    
                    list.add(punchlist);
                }
            }
                  
        }
        catch (Exception e) {e.printStackTrace(); }
        return list;
    }

    public void close(){
        
        /*close the database*/
        try{
            conn.close();
        } catch (SQLException e) {}

    }   
}
