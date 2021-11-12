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
    private int gracePeriod;
    private int dock;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private int lunchDeduct;
    
    public Shift(int id, String description, LocalTime start, LocalTime stop, int interval, int gracePeriod, int dock, LocalTime lunchStart, LocalTime lunchStop, int lunchDeduct){
        this.id = id;
        this.description = description;
        this.start = start;
        this.stop = stop;
        this.interval = interval;
        this.gracePeriod = gracePeriod;
        this.dock = dock;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchDeduct = lunchDeduct;
    }
        
    public void setID(int id){
        this.id = id;
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
        
    public void setGracePeriod(int gracePeriod){
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
    
    public void setLunchDuration(LocalTime lunchDuration){
        this.lunchDuration = lunchDuration;
    }
    
    public void setShiftDuration(LocalTime shiftDuration){
        this.shiftDuration = shiftDuration;
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
        
    public int getGracePeriod(){
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
    
    public LocalTime getShiftDuration(){
        return shiftDuration;
    }
    
    public LocalTime getLunchDuration(){
        return lunchDuration;
    }
        
    @Override
    public String toString(){
        return description + ": " + start + " - " + stop + " (" + shiftDuration
                + "); Lunch: " + lunchStart + " - " + lunchStop + " (" 
                + lunchDuration + ")";
    }
}
