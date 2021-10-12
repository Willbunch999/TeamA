/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jsu.mcis.cs310.tas_fa21;
import java.time.*;

/**
 *
 * @author wabun
 */
public class TAS {
    public class Shift{
        private LocalTime lunchDuration;
        private LocalTime shiftDuration;
        
        public Shift(){
            
        }
        
        public void setID(){
            
        }
        
        public void setDescription(){
            
        }
        
        public void setStart(){
            
        }
        
        public void setStop(){
            
        }
        
        public void setInterval(){
            
        }
        
        public void setGracePeriod(){
            
        }
        
        public void setDock(){
            
        }
        
        public void setLunchStart(){
            
        }
        
        public void setLunchStop(){
            
        }
        
        public void setLunchDeduct(){
            
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
}
