/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic;

import java.util.Optional;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IColidable;

/**
 *
 * @author DAWTarde
 */
public class ElementDynamic extends Element implements IColidable{

    public ElementDynamic() {
    }

    public ElementDynamic(double x, double y, double width, double height) {
        super(x, y, width, height);
        
    }
    
    @Override
    public Optional<Colision> collision(Element e) {
        Colision c = null;
        Optional<Colision> r;
        if(this.getRectangle().intersects(e.getRectangle())){
            c = new Colision();
            c.setA(this);
            c.setB(e);
            c.setDistance(this.getCenter().distance(e.getCenter()));
            //falta el separate
            
        }
        r=(c == null?Optional.empty(): Optional.of(c));
        return r;
    }
    
}
