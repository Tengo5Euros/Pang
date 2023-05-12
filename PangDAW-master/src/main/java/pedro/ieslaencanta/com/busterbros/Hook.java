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
    public void paint(GraphicsContext gc) {
        gc.setFill(Color.GREY);
        //se tendrá que sustituro por img

        gc.fillRect( this.rectangle.getMinX(),this.rectangle.getMinY()* Game.SCALE,
                this.rectangle.getWidth()* Game.SCALE,this.rectangle.getHeight()* Game.SCALE);

    }
}
