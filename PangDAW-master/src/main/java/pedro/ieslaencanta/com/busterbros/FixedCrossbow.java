package pedro.ieslaencanta.com.busterbros;

import javafx.scene.canvas.GraphicsContext;
import pedro.ieslaencanta.com.busterbros.basic.ElementResizable;

public class FixedCrossbow extends Weapon{
    private final int LIVE_TIME=5;
    private int counter;

    private final double IMG_X=2;

    private final double IMG_Y=2;

    public FixedCrossbow(){
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
