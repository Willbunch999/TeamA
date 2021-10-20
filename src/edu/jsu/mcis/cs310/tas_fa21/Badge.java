/*
    A badgle class to represent employee badges
    Objects of the Badge class will contain information from the database about a single employee badge.
    Objects of the Badge classes will be used to represent already-existing badges from the database.
    All the three classes should provide  the necessary setter and getter methods for all instance fields,
    and should include methods to output their state in string form, as shown in the attached unit tests.
 */
package edu.jsu.mcis.cs310.tas_fa21;

/**
 *
 * @author Ruri
 */

public class Badge  
{
    // declaring a private variable
    private final String descript;   
    private final String Badgeid;            

    public Badge(String description, String Badgeid) // 
    {
        // descript is a string and is assigned to description;
        descript = description;         
        // creating a constructor
        this.Badgeid = Badgeid;
    }

    public String getDescript() // getDescript is an object method and it's returning the description value.
    {
        return descript;        // Returning description value.
    }

    public String getBadgeid() {
        return Badgeid;
    }

    //Overriding the "toString()" method.
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append("(").append(this.descript).append(")");
        output.append("#").append(this.Badgeid).append(" ");
        return output.toString();
    }
}