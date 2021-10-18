package edu.jsu.mcis.cs310.tas_fa21;

import java.sql.*;
import java.time.LocalTime;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.ArrayList;

//Feature 1
public class tasDatabase {

    private Connection conn = null;
    private String query;
    private PreparedStatement pstSelect = null, pstUpdate = null;
    private ResultSet resultsSet = null;
    private boolean hasResults;
    private int updateCount;

    public tasDatabase() {
        try {
            /*Identify the Server */

            String server = ("jdbc:mysql://localhost/tas");
            String username = "tasuser";
            String password = "teama";

            /*Load the MySQL JDBC Driver */
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            /* Open Connection */
            this.conn = DriverManager.getConnection(server, username, password);

            if (!conn.isValid(0)) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            System.out.println("SQL Connection failed! Invalid database setup?");
        } //catch(ClassNotFoundException e){ System.out.println("JDBC driver not found, make sure MySQLDriver is added as a library!"); }
        catch (Exception e) {
        }
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
        } finally {
            if (resultsSet != null) {
                try {
                    resultsSet.close();
                    resultsSet = null;
                } catch (SQLException e) {
                }
            }
            if (pstSelect != null) {
                try {
                    pstSelect.close();
                    pstSelect = null;
                } catch (SQLException e) {
                }
            }
        }
    }

    public Punch getPunch(int id) { // method of the database class and provide the punch ID as a parameter. 
        Punch outputPunch;

        try {

            /*Prepare Select Query*/
            query = "SELECT * FROM tas.punch WHERE id = " + id;
            pstSelect = conn.prepareStatement(query);

            /* Execute Select Query */
            hasResults = pstSelect.execute();

            while (hasResults || pstSelect.getUpdateCount() != -1) {
                if (hasResults) {

                    resultsSet = pstSelect.getResultSet();
                    resultsSet.next();

                    int terminalid = resultsSet.getInt("terminalid");
                    String badgeid = resultsSet.getString("badgeid");
                    long originaltimestamp = resultsSet.getTimestamp("originaltimestamp").getTime();
                    int punchtypeid = resultsSet.getInt("punchtypeid");

                    outputPunch = new Punch(getBadge(badgeid), terminalid, punchtypeid);
                    outputPunch.setOriginalTimeStamp(originaltimestamp);

                    return outputPunch;

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        //Shouldn't be reached with a valid punch id
        return null;
    }

    public Badge getBadge(String id) {  // method of the database class and provide the badge ID as a parameter.
        Badge outputBadge;

        try {
            /* Prepare Select Query */

            query = "SELECT * from tas.badge where id = \"" + id + "\"";
            pstSelect = conn.prepareStatement(query);

            /* Execute Select Query */
            hasResults = pstSelect.execute();

            while (hasResults || pstSelect.getUpdateCount() != -1) {
                if (hasResults) {
                    resultsSet = pstSelect.getResultSet();

                    resultsSet.next();
                    outputBadge = new Badge(resultsSet.getString("id"), resultsSet.getString("description"));

                    return outputBadge;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getBadge()");
        }

        //Shouldn't be reached with a valid badge ID.
        return null;
    }

    public Shift getShift(int shiftid) {

        Shift outputShift;

        try {

            query = "SELECT * FROM shift WHERE id = " + shiftid;
            pstSelect = conn.prepareStatement(query);

            hasResults = pstSelect.execute();

            while (hasResults || pstSelect.getUpdateCount() != -1) {
                if (hasResults) {
                    resultsSet = pstSelect.getResultSet();
                    resultsSet.next();

                    ShiftParameters parameters = new ShiftParameters();

                    parameters.setDescription(resultsSet.getString("description"));
                    parameters.setStart(LocalTime.parse(resultsSet.getString("start")));
                    parameters.setStop(LocalTime.parse(resultsSet.getString("stop")));
                    parameters.setInterval(resultsSet.getInt("interval"));
                    parameters.setGraceperiod(resultsSet.getInt("graceperiod"));
                    parameters.setDock(resultsSet.getInt("dock"));
                    parameters.setLunchstart(LocalTime.parse(resultsSet.getString("lunchstart")));
                    parameters.setLunchstop(LocalTime.parse(resultsSet.getString("lunchstop")));
                    parameters.setLunchdeduct(resultsSet.getInt("lunchdeduct"));
                    parameters.setId(shiftid);

                    outputShift = new Shift(parameters);

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        //Shouldnt be reached with a valid ID
        return null;
    }

    public Shift getShift(Badge badge) {
        try {

            // Prepare select query from employee table
            query = "SELECT * FROM tas.employee WHERE badgeid = \"" + badge.getId() + "\"";
            pstSelect = conn.prepareStatement(query);

            // Execute select query
            hasResults = pstSelect.execute();

            while (hasResults || pstSelect.getUpdateCount() != -1) {
                if (hasResults) {

                    resultsSet = pstSelect.getResultSet();
                    resultsSet.next();

                    int shiftid = resultsSet.getInt("shiftid");

                    return getShift(shiftid);
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        //Shouldn't be reached with a valid badge.
        return null;
    }

    public int insertPunch(Punch p) {

        // Extract punch data from the Punch object
        int terminalid = p.getTerminalid();
        String badgeid = p.getBadgeid();
        long originalTS = p.getOriginaltimestamp();
        int punchtypeid = p.getPunchtypeid();

        // Convert originalTS to a Timestamp string
        GregorianCalendar ots = new GregorianCalendar();
        ots.setTimeInMillis(originalTS);
        String originaltimestamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ots.getTime());

        // Insert this data into the database as a new punch
        try {
            // Prepare Update Query

            query = "INSERT INTO tas.punch (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)";
            pstUpdate = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstUpdate.setInt(1, terminalid);
            pstUpdate.setString(2, badgeid);
            pstUpdate.setString(3, originaltimestamp);
            pstUpdate.setInt(4, punchtypeid);

            // Execute Update Query
            updateCount = pstUpdate.executeUpdate();

            // Get New Key
            if (updateCount > 0) {

                resultsSet = pstUpdate.getGeneratedKeys();

                if (resultsSet.next()) {

                    // Return the id of the new punch (assigned by the database) as an integer
                    return resultsSet.getInt(1);

                }
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        // Not reached with a valid Punch object
        return -1;
    }

    public ArrayList<Punch> getDailyPunchList(Badge badge, long ts) {

        /* Initialize variables for punches */
        Punch obj;
        ArrayList<Punch> output = new ArrayList<>();
        String strbadge = badge.getId();

        /* Get search date for select query */
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(ts);
        String date = (new SimpleDateFormat("yyyy-MM-dd")).format(gc.getTime());

        try {

            /* Prepare SQL query */
            query = "SELECT * FROM tas.punch WHERE badgeid = ? AND DATE(originaltimestamp) = ?";
            pstSelect = conn.prepareStatement(query);
            pstSelect.setString(1, strbadge);
            pstSelect.setString(2, date);

            /* Execute query */
            hasResults = pstSelect.execute();

            while (hasResults || pstSelect.getUpdateCount() != -1) {
                if (hasResults) {

                    resultsSet = pstSelect.getResultSet();

                    while (resultsSet.next()) {

                        int terminalid = resultsSet.getInt("terminalid");
                        int punchtypeid = resultsSet.getInt("punchtypeid");

                        obj = new Punch(badge, terminalid, punchtypeid);
                        obj.setId(resultsSet.getInt("id"));
                        obj.setOriginalTimeStamp(resultsSet.getTimestamp("originaltimestamp").getTime());

                        output.add(obj);
                    }

                } else {
                    updateCount = pstSelect.getUpdateCount();
                    if (updateCount == -1) {
                        break;
                    }
                }
                hasResults = pstSelect.getMoreResults();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return output;
    }

}
