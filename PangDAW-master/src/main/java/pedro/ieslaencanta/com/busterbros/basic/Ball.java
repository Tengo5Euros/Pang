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
    private BallType balltype;
    public Ball( double gx, double gy, boolean activegravityyx, boolean activegravityyy, double vx, double vy, double x, double y, double width, double height, BallType balltype) {
        super(gx, gy, activegravityyx, activegravityyy, vx, vy, x, y, width, height);
        this.setBalltype(balltype);
        this.originalvy = vy;
    }

    public void resetVy(){
        this.setVy(originalvy);
    }
    public Ball[] explotar() {
        Ball[] children = new Ball[2];
        children[0] = new Ball(0, this.getGy() / 2, true, true,
                -this.getVx(), this.getVy(),
                this.getCenter().getX(), this.getCenter().getY(),
                BallType.MEDIUM.getWidth(), BallType.MEDIUM.getHeight(), BallType.MEDIUM);
        children[1] = new Ball(0, this.getGy() / 2, true, true,
                this.getVx(), this.getVy(),
                this.getCenter().getX(), this.getCenter().getY(),
                BallType.MEDIUM.getWidth(), BallType.MEDIUM.getHeight(), BallType.MEDIUM);
        return children;
    }

    @Override
    public Optional<Colision> collision(Element e){
        Optional<Colision> c = super.collision(e);
        if (c.isPresent()){
            double dx = this.evalColisionX(e);
            double dy = this.evalColisionY(e);
            c.get().setSeparator(new Point2D(dy,dx));
        }
        return c;
    }

    public double evalColisionY(Element e){
        Rectangle2D y = new Rectangle2D(this.rectangle.getMinX(),
                this.rectangle.getMinY()-this.getVy(),
                this.rectangle.getWidth(),
                this.rectangle.getHeight());
        if(e.getRectangle().intersects(y)){
            if(this.getCenterY() < e.getCenterY()){
                return Math.abs(e.getCenterY()- this.getCenterY()) - (this.getHeight()/2 + e.getHeight()/2);
            } else {
                return Math.abs((this.getCenterY() - e.getCenterY()) - (this.getHeight()/2 + e.getHeight()/2));
            }
        }else{
            return 0;
        }

    }
    public double evalColisionX(Element e){
        Rectangle2D x = new Rectangle2D(this.rectangle.getMinX(),
                this.rectangle.getMinY()-this.getVy(),
                this.rectangle.getWidth(),
                this.rectangle.getHeight());
        if(e.getRectangle().intersects(x)){
            if(this.getCenterX() < e.getCenterX()){
                return Math.abs(e.getCenterX()- this.getCenterX()) - (this.getWidth()/2 + e.getWidth()/2);
            } else {
                return Math.abs((this.getCenterX() - e.getCenterX()) - (this.getWidth()/2 + e.getWidth()/2));
            }

        } else{
            return 0;
        }
    }


    public BallType getBalltype() {
        return balltype;
    }

    public void setBalltype(BallType balltype) {
        this.balltype = balltype;
    }
}