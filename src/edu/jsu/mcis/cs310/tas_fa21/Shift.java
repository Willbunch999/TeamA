/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa21;

import java.time.LocalTime;

/**
 *
 * @author Stephen Thompson
 */
public class Shift {
    private LocalTime lunchDuration;
    private LocalTime shiftDuration;
    private int id;
    private String description;
    private LocalTime start;
    private LocalTime stop;
    private int interval;
    private LocalTime gracePeriod;
    private int dock;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private int lunchDeduct;
        
    public Shift(){
            
    }
        
    public void setID(){
            
    }
        
    public void setDescription(String description){
        this.description = description;    
    }
        
    public void setStart(LocalTime start){
        this.start = start;    
    }
        
    public void setStop(LocalTime stop){
        this.stop = stop;     
    }
        
    public void setInterval(int interval){
        this.interval = interval;
    }
        
    public void setGracePeriod(LocalTime gracePeriod){
        this.gracePeriod = gracePeriod;
    }
        
    public void setDock(int dock){
        this.dock = dock;
    }
        
    public void setLunchStart(LocalTime lunchStart){
        this.lunchStart = lunchStart;
    }
        
    public void setLunchStop(LocalTime lunchStop){
        this.lunchStop = lunchStop;
    }
        
    public void setLunchDeduct(int lunchDeduct){
        this.lunchDeduct = lunchDeduct;
    }
        
    public int getID(){
        return id;
    }
        
    public String getDescription(){
        return description;
    }
        
    public LocalTime getStart(){
        return start;
    }
        
    public LocalTime getStop(){
        return stop;
    }
        
    public int getInterval(){
        return interval;
    }
        
    public LocalTime getGracePeriod(){
        return gracePeriod;
    }
        
    public int getDock(){
        return dock;
    }
        
    public LocalTime getLunchStart(){
        return lunchStart;
    }
        
    public LocalTime getLunchStop(){
        return lunchStop;
    }
        
    public int getLunchDeduct(){
        return lunchDeduct;
    }
        
    @Override
    public String toString(){
        return "";
    }
}
