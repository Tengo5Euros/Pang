/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic.interfaces;

/**
 *
 * @author DAWTarde
 */
public interface IGravity {
    public boolean isActive();
    
    public boolean isActiveHorizontalGravity();
    
    public boolean isActiveVerticalGravity();
    
    public void activeGravity();
    
    public void activeHorizontalGravity();
    
    public void activeVarticalGravity();
    
    public void unActiveGravity();
    
    public void unActiveHorizontalGravity();
    
    public void unActiveVerticalGravity();
    
    public void setHorizontalGravity(double gravity);
    
    public void setVerticalGravity(double gravity);
    
    public double getHorizontalGravity();
    
    public double getVerticalGravity();
}
