package pedro.ieslaencanta.com.busterbros;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pedro.ieslaencanta.com.busterbros.basic.ElementResizable;

public abstract class Weapon{
    protected Image imagen;
    protected  final double  WIDTH=2;
    protected final double HEIGHT=10;

    protected ElementResizable ammunitions;

    public void update(){

    }
    public ElementResizable shoot(double x, double y){
        return ammunitions;
    }

    public Weapon(){

    }

    public ElementResizable getAmmunitions(){
        return ammunitions;
    }

    public void remove(ElementResizable e){


    }

    public boolean canShoot(){
    return true;
    }

    /* public ElementResizable shoot(double x, double y){
     return
     }*/


    public void removeAmmunitions(ElementResizable e){

    }

}
