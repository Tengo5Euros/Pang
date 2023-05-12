/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import java.util.Optional;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 *
 * @author DAWTarde
 */
public class Ball extends ElementWithGravity {
    private double originalvy;

    public Ball( double gx, double gy, boolean activegravityyx, boolean activegravityyy, double vx, double vy, double x, double y, double width, double height) {
        super(gx, gy, activegravityyx, activegravityyy, vx, vy, x, y, width, height);
        this.originalvy = vy;
    }
    
    public void resetVy(){
        this.setVy(originalvy);
    }
    public Ball[] explotar(){
        Ball[] children;
        
        children = new Ball[2];
        
            children[0] = new Ball(0,this.getGy()/2,true,true,
                            -this.getVx(),this.getVy()*0.75,
                                this.getCenter().getX(),this.getCenter().getY(),
                                this.getWidth()/2,this.getHeight()/2);
            children[1] = new Ball(0,this.getGy()/2,true,true,
                            this.getVx(),this.getVy()*0.75,
                                this.getCenter().getX(),this.getCenter().getY(),
                                this.getWidth()/2,this.getHeight()/2);
        
        

        return children;
    }
    /*
    @Override
    public Optional<Colision> collision(Element e){
        Optional<Colision> c = super.collision(e);
        if (c.isPresent()){
            double dx = this.evalColisionX(e);
            double dy = this.evalColisionY(e);
            c.get().setSeparator(new Point2D(dx));
        }
    }
    
    public double evalColisionY(Element e){
        Rectangle2D y = new Rectangle2D(this.rectangle.getMinX(), 
                this.rectangle.getMinY()-this.getVy(), 
                this.rectangle.getWidth(),
                this.rectangle.getHeight());
        if(e.getRectangle().intersects(y)){
            
        }
    }
    public double evalColisionX(Element e){
        Rectangle2D x = new Rectangle2D(this.rectangle.getMinX(), 
                this.rectangle.getMinY()-this.getVy(), 
                this.rectangle.getWidth(),
                this.rectangle.getHeight());
        if(e.getRectangle().intersects(x)){
            
        }
    }*/
}
