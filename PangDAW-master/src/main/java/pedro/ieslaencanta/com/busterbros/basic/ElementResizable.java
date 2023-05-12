package pedro.ieslaencanta.com.busterbros.basic;

import javafx.geometry.Rectangle2D;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IResizable;

public class ElementResizable extends ElementDynamic implements IResizable {
    protected double iw;
    protected double ih;

    public ElementResizable() {
    }

    public ElementResizable(double iw, double ih) {
        super(200,200,8,8);
        this.iw = iw;
        this.ih = ih;

    }

    @Override
    public void resizeWidth() {

    }

    @Override
    public void resizeWidth(double inc) {

    }

    @Override
    public void resizeHeigth() {
    this.rectangle= new Rectangle2D(this.rectangle.getMinX(),this.rectangle.getMinY()-this.ih,
            this.rectangle.getWidth(),this.rectangle.getHeight()+this.ih);

    }

    @Override
    public void resizeHeight(double inc) {

    }

    @Override
    public void setDefaultIncWidth(double incw) {

    }

    @Override
    public void setDefaultIncHeight(double inch) {
       inch=this.ih;

    }
}
