package pedro.ieslaencanta.com.busterbros;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pedro.ieslaencanta.com.busterbros.basic.ElementResizable;
import pedro.ieslaencanta.com.busterbros.basic.ElementWithGravity;

public class Crossbow {
    private final int MIN_X=398;
    private final int MIN_Y=1570;

    private final int MAX_X=408;

    private final int MAX_Y=1758;

    private double x=20,y=20, width, height;
    private final int INC_Y=4;
    public Crossbow( double x, double y, double width, double height) {
        this.x= x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public void Shoot() {

    }

    public void update(){

    }

    /* public ElementResizable shoot(double x, double y){
     return
     }*/

    public void paint(GraphicsContext gc, ElementWithGravity jugador) {
        gc.setFill(Color.GREY);
        //se tendrá que sustituro por img
        gc.fillRect( jugador.getCenter().getX() * Game.SCALE, jugador.getCenter().getY() * Game.SCALE, 15 * Game.SCALE, 20 * Game.SCALE);

    }
    public void removeAmmunitions(ElementResizable e){

    }
}
