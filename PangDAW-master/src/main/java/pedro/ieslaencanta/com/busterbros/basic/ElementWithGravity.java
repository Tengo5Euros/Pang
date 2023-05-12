/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IGravity;

/**
 *
 * @author DAWTarde
 */
public class ElementWithGravity extends ElementMovable implements IGravity {

   
    private double gx;
    private double gy;
    private boolean activegravityyx;
    private boolean activegravityyy;

    public ElementWithGravity() {
        super();
    }

    public ElementWithGravity(double gx, double gy, boolean activegravityyx, boolean activegravityyy, double vx, double vy, double x, double y, double width, double height) {
        super(vx, vy, x, y, width, height);
        this.gx = gx;
        this.gy = gy;
        this.activegravityyx = activegravityyx;
        this.activegravityyy = activegravityyy;
    }
     /**
     * @return the gx
     */
    public double getGx() {
        return gx;
    }

    /**
     * @param gx the gx to set
     */
    public void setGx(double gx) {
        this.gx = gx;
    }

    /**
     * @return the gy
     */
    public double getGy() {
        return gy;
    }

    /**
     * @param gy the gy to set
     */
    public void setGy(double gy) {
        this.gy = gy;
    }

    @Override
    public boolean isActive() {
        return this.activegravityyx || this.activegravityyy;
    }

    @Override
    public boolean isActiveHorizontalGravity() {
        return this.activegravityyx;
    }

    @Override
    public boolean isActiveVerticalGravity() {
        return this.activegravityyy;
    }

    @Override
    public void activeGravity() {
        this.activegravityyx=true;
        this.activegravityyy=true;
    }

    @Override
    public void activeHorizontalGravity() {
        this.activegravityyx=true;
    }

    @Override
    public void activeVarticalGravity() {
        this.activegravityyy=true;
    }

    @Override
    public void unActiveGravity() {
        this.activegravityyx=false;
        this.activegravityyy=false;
    }

    @Override
    public void unActiveHorizontalGravity() {
        this.activegravityyx=false;   
    }

    @Override
    public void unActiveVerticalGravity() {
        this.activegravityyy=false;
    }

    @Override
    public void setHorizontalGravity(double gravity) {
        gravity=this.getGx();
    }

    @Override
    public void setVerticalGravity(double gravity) {
        gravity=this.getGy();
    }

    @Override
    public double getHorizontalGravity() {
        return this.getGx();
    }

    @Override
    public double getVerticalGravity() {
        return this.getGy();
    }
    @Override
    public void move(){
        /*this.rectangle= new Rectangle2D(this.rectangle.getMinX() + this.gx + this.getVx(),
                                        this.rectangle.getMinY() + this.gy + this.getVy(),
                                        this.rectangle.getWidth(),
                                        this.rectangle.getHeight());*/
        if(this.isActiveHorizontalGravity()){
            this.setVy(this.getVy() + this.getGy());
        }
        if(this.isActiveVerticalGravity()){
            this.setVx(this.getVx() + this.getGx());
        }
        
        
        super.move();
    }
    
}
