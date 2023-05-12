package pedro.ieslaencanta.com.busterbros;

import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.basic.ElementResizable;

public class DoubleCrossbow extends Weapon{
    private final double IMG_X=2;

    private final double IMG_Y=2;

    public DoubleCrossbow(){
        super();
    }

    @Override
    public boolean canShoot(){
        return true;
    }

    public void update(){

    }

    /* public ElementResizable shoot(double x, double y){
     return
     }*/
    public  void paint(GraphicsContext gc){

    }
    public void removeAmmunitions(ElementResizable e){

    }
}
