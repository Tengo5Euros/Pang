package pedro.ieslaencanta.com.busterbros;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pedro.ieslaencanta.com.busterbros.basic.ElementResizable;
import pedro.ieslaencanta.com.busterbros.basic.ElementWithGravity;

public class Hook extends ElementResizable {
     boolean disparo= false;
    public Hook (){
        super();
    }
    public Hook (double iw, double ih){
        super(iw, ih);

    }
    public double shoot(){
        return ih+2;

    }

    public void paint(GraphicsContext gc, ElementWithGravity jugador) {
        gc.setFill(Color.GREY);
        //se tendrá que sustituro por img
        gc.fillRect( jugador.getCenter().getX() * Game.SCALE, jugador.getCenter().getY() * Game.SCALE, 15 * Game.SCALE, 20 * Game.SCALE);

    }

    public void pararDisparo(){
        this.ih=0;
    }
}
