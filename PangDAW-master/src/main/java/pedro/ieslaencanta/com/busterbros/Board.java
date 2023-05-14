/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros;

import java.util.Optional;

import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;

import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.util.Pair;
import pedro.ieslaencanta.com.busterbros.basic.*;
import pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable;

import static javafx.scene.input.KeyCode.*;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderColision.DOWN;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderColision.LEFT;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderColision.RIGHT;
import static pedro.ieslaencanta.com.busterbros.basic.interfaces.IMovable.BorderColision.TOP;

;

/**
 * Tablero del juego, posee un fondo (imagen estática, solo se cambia cuando se
 * cambia el nivel), Una bola, un vector de niveles, un disparador y una matriz
 * de bolas gestiona la pulsación de tecla derecha e izquierda
 *
 * @author Pedro
 */
public class Board implements IKeyListener {

    private Rectangle2D game_zone;

    private GraphicsContext gc;
    private GraphicsContext bggc;
    private final Dimension2D original_size;

    private boolean debug;
    private boolean left_press, right_press, up_press, down_press;
    private Level levels[];
    private int actual_level = -1;
    private MediaPlayer backgroundsound;
    private MediaPlayer ballsound;
    private MediaPlayer explotesound;
    private MediaPlayer shootsound;

    private Element[] elements;
    private ElementWithGravity jugador;
    private ElementWithGravity ball;
    private Balls balls;

    private Crossbow crossbow;
    private Hook hook;
    private boolean si=false;
    private int vidas=3;
    private boolean exploto;

    public Board(Dimension2D original) {
        this.gc = null;
        this.game_zone = new Rectangle2D(8, 8, 368, 192);
        this.original_size = original;
        this.right_press = false;
        this.left_press = false;
        this.up_press = false;
        this.down_press = false;

        this.debug = false;

        this.actual_level = 12;

        this.createLevels();
        this.nextLevel();

        this.jugador = new ElementWithGravity(0, 2, false, true, 2, 2, this.game_zone.getMaxX()/2, this.game_zone.getMaxY()-32, 32, 32);

        this.balls = new Balls(4);
        this.balls.addBall(new Ball(0, 0.03, true, true, 0.3, 0.3, 20, 20, BallType.BIG.getWidth(), BallType.BIG.getHeight(),BallType.BIG));
        this.balls.addBall(new Ball(0, 0.03, true, true, 0.3, 0.3, 70, 70, BallType.BIG.getWidth(), BallType.BIG.getHeight(), BallType.BIG));
        this.crossbow = new Crossbow(20, 70, 10, 10);
        this.hook = new Hook(0, 4);

    }

    private void detectColisionWithBricks(){
        for (int i=0; i<this.balls.getSize();i++){
            if(this.balls.getBall(i) != null){
                for(int j=0; j<this.elements.length;j++){
                    if(this.elements[j] != null && this.elements[j] instanceof Brick){
                        //if(this.elements[j] instanceof Brick || this.elements[j] instanceof BricBreakable){
                        Optional<Colision> oc = this.balls.getBall(i).collision(this.elements[j]);
                        if(oc.isPresent()){
                            // this.balls.getBall(i).stop();
                            this.balls.getBall(i).move(oc.get().getSeparator().getX(), oc.get().getSeparator().getY());
                            if(oc.get().getSeparator().getX() != 0){
                                this.balls.getBall(i).setVx(-this.balls.getBall(i).getVx());
                            }
                            if(oc.get().getSeparator().getY() != 0){
                                this.balls.getBall(i).setVy(-this.balls.getBall(i).getVy());
                            }
                        }
                        // }
                    }
                }
            }
        }
    }

    private void createLevels() {
        int y = 44;
        this.levels = new Level[50];
        for (int i = 0; i < 25; i++) {
            this.levels[2 * i] = new Level();//(8, y);
            this.levels[2 * i].setX(8);
            this.levels[2 * i].setY(y);
            this.levels[2 * i].setImagename("bricks");
            this.levels[2 * i].setBackgroundname("backgrounds");
            this.levels[2 * i].setSoundName("fondo");
            this.levels[2 * i].setTime(30);

            this.levels[2 * i + 1] = new Level();//(8, y);
            this.levels[2 * i + 1].setX(400);
            this.levels[2 * i + 1].setY(y);
            this.levels[2 * i + 1].setImagename("bricks");
            this.levels[2 * i + 1].setBackgroundname("backgrounds");
            this.levels[2 * i + 1].setSoundName("fondo");

            this.levels[2 * i + 1].setTime(30);

            y += 216;
        }
    }

    private void createElementsLevel() {
        Brick tempo;
        BricBreakable rompe;
        Ladder escalera;
        ElementMovable m;
        Pair<Level.ElementType, Rectangle2D>[] fi = this.levels[this.actual_level].getFigures();
        this.elements = new Element[fi.length];
        for (int i = 0; i < fi.length; i++) {

            switch (fi[i].getKey()) {
                case FIXED:
                    tempo = new Brick((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight()
                    );
                    tempo.setImg(Resources.getInstance().getImage("bricks"));
                    tempo.setOriginal(fi[i].getValue());
                    this.elements[i] = tempo;
                    break;
                case BREAKABLE:
                    rompe = new BricBreakable((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight()
                    );
                    rompe.setImg(Resources.getInstance().getImage("bricks"));
                    rompe.setOriginal(fi[i].getValue());
                    this.elements[i] = rompe;
                    break;
                case LADDER:
                    escalera = new Ladder((fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                            (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                            fi[i].getValue().getWidth(),
                            fi[i].getValue().getHeight()
                    );
                    escalera.setImg(Resources.getInstance().getImage("bricks"));
                    escalera.setOriginal(fi[i].getValue());
                    this.elements[i] = escalera;
                    break;
                   /* m = new ElementMovable(2,0,(fi[i].getValue().getMinX() - this.levels[this.actual_level].getX()),
                    (fi[i].getValue().getMinY() - this.levels[this.actual_level].getY()),
                    fi[i].getValue().getWidth(),
                    fi[i].getValue().getHeight()
                     );
                   m.setColor(Color.CORAL);
                    this.elements[i] = m;

                    break;*/
            }
        }

    }


    public void setDebug() {
        this.debug = !this.debug;
        this.jugador.setDebug(debug);
        this.hook.setDebug(debug);
        for (int i = 0; i < this.elements.length; i++) {
            this.elements[i].setDebug(debug);
        }
    }

    public void setGraphicsContext(GraphicsContext gc) {
        this.gc = gc;

    }

    public void setBackGroundGraphicsContext(GraphicsContext gc) {
        this.bggc = gc;
        this.paintBackground();
    }

    /**
     * cuando se produce un evento
     */
    public synchronized void TicTac() {
        this.process_input();

        this.update();
        this.render();
        //actualizar
    }

    private void evalBorder(ElementMovable e) {
        e.move();
        IMovable.BorderColision b = e.isInBorder(game_zone);
        switch (b) {
            case DOWN:
                e.setVy(-Math.abs(e.getVy()));
                e.setPosition(e.getRectangle().getMinX(),
                        this.game_zone.getMaxY() - e.getRectangle().getHeight());
                ballsound = Resources.getInstance().getSound("pared");
                ballsound.seek(Duration.ZERO);
                ballsound.play();
                break;
            case TOP:
                e.setVy(Math.abs(e.getVy()));
                ballsound = Resources.getInstance().getSound("pared");
                ballsound.seek(Duration.ZERO);
                ballsound.play();
                break;
            case LEFT:
                e.setVx(Math.abs(e.getVx()));
                ballsound = Resources.getInstance().getSound("pared");
                ballsound.seek(Duration.ZERO);
                ballsound.play();
                break;
            case RIGHT:
                e.setVx(-Math.abs(e.getVx()));
                ballsound = Resources.getInstance().getSound("pared");
                ballsound.seek(Duration.ZERO);
                ballsound.play();
                break;
        }
    }

    private void update() {

        for (int i = 0; i < this.balls.getSize(); i++) {
            if (this.balls.getBall(i) != null) {
                this.balls.getBall(i).move();
                this.evalBorder(this.balls.getBall(i));
                this.detectColisionWithBricks();
                if ( this.exploto==false && this.hook.getRectangle().intersects(this.balls.getBall(i).getRectangle())) {
                    explotesound = Resources.getInstance().getSound("romper");
                    explotesound.seek(Duration.ZERO);
                    explotesound.play();
                    Ball[] b = this.balls.getBall(i).explotar();
                    this.balls.removeBall(this.balls.getBall(i));
                    this.balls.addBall(b[0]);
                    this.balls.addBall(b[1]);

                }
                if ( this.hook.getRectangle().intersects(this.balls.getBall(i).getRectangle())
                        && (this.balls.getBall(i).getBalltype() == BallType.BIG)) {
                    System.out.println(this.balls.getBall(i).getBalltype());
                    this.balls.removeBall(this.balls.getBall(i));
                    this.exploto=true;
                }

                if ( this.balls.getBall(i)!=null
                        && this.jugador.getRectangle().intersects(this.balls.getBall(i).getRectangle())) {
                    this.balls.getBall(i).setPosition(20, 20);
                    this.jugador.setPosition(this.game_zone.getMaxX()/2, this.game_zone.getMaxY()-32);
                    vidas--;
                    System.out.println("Vida Total: "+vidas);
                    if(vidas<1){
                        PararJuego();
                        System.out.println("GAME OVER");
                    }
                }

                }
            }
        if (si == true) {
            this.hook.resizeHeigth();
            if (this.hook.getHeight() <= 8) {
                this.hook.pararDisparo();
            }
        }
        this.crossbow.update();
        }











//this.evalBorder(jugador);


    private void evalCollisions() {

    }

    private void render() {
        this.clear();
        if (this.elements != null) {
            for (int i = 0; i < this.elements.length; i++) {

                this.elements[i].paint(gc);
            }
        }
        this.jugador.paint(gc);
        this.crossbow.paint(gc, this.jugador);
        this.jugador.setColor(Color.GOLD);
        if(this.hook.isInBorder(game_zone) != IMovable.BorderColision.TOP){
            this.hook.paint(gc);
        }
        // this.ball.paint(gc);
        // this.ball.setColor(Color.CORAL);
        for (int i = 0; i < this.balls.getSize(); i++) {
            if (this.balls.getBall(i) != null)
                this.balls.getBall(i).paint(gc);
        }

    }
    public void PararJuego(){
        this.jugador.setPosition(-140,-140);
        this.hook.setPosition(-140,-140);
        for (int i = 0; i < this.balls.getSize(); i++) {
            if (this.balls.getBall(i) != null)
                this.balls.getBall(i).setPosition(-10000,-100000);

        }
    }

    private void process_input() {

        if (this.left_press) {
            this.jugador.moveLeft();
            if (this.jugador.isInBorder(game_zone) == IMovable.BorderColision.LEFT) {
                this.jugador.setPosition(this.game_zone.getMinX(),
                        this.jugador.getRectangle().getMinY());
            }

        } else if (this.right_press) {
            this.jugador.moveRight();
            if (this.jugador.isInBorder(game_zone) == IMovable.BorderColision.RIGHT) {
                this.jugador.setPosition(this.game_zone.getMaxX() - this.jugador.getRectangle().getWidth(),
                        this.jugador.getRectangle().getMinY());
            }

        }
        if (this.up_press) {
            this.jugador.moveUp();
            if (this.jugador.isInBorder(game_zone) == IMovable.BorderColision.TOP) {
                this.jugador.setPosition(this.jugador.getRectangle().getMinX(),
                        this.game_zone.getMinY());
            }
        }
        if (this.down_press) {
            this.jugador.moveDown();
            if (this.jugador.isInBorder(game_zone) == IMovable.BorderColision.DOWN) {
                this.jugador.setPosition(this.jugador.getRectangle().getMinX(),
                        this.game_zone.getMaxY() - this.jugador.getRectangle().getHeight());
            }
        }

    }

    /**
     * limpiar la pantalla
     */
    private void clear() {
        this.gc.restore();
        this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
    }

    /**
     * pintar el fonodo
     */
    public void paintBackground() {
        if (this.bggc != null) {
            this.bggc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            this.bggc.setFill(Color.BLACK);
            this.bggc.fillRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            if (this.gc != null) {
                this.gc.clearRect(0, 0, this.original_size.getWidth() * Game.SCALE, (this.original_size.getHeight() + Game.INFOAREA) * Game.SCALE);
            }

            this.bggc.drawImage(this.levels[actual_level].getBackground(),
                    this.levels[actual_level].getX(), this.levels[actual_level].getYBackground(), this.original_size.getWidth(), this.original_size.getHeight(),
                    0, 0, this.original_size.getWidth() * Game.SCALE, this.original_size.getHeight() * Game.SCALE);
        }

    }

    /**
     * gestión de pulsación
     *
     * @param code
     */
    @Override
    public void onKeyPressed(KeyCode code) {
        switch (code) {
            case LEFT:
                this.left_press = true;

                break;
            case RIGHT:

                this.right_press = true;
                break;
            case UP:
                this.up_press = true;
                break;
            case DOWN:
                this.down_press = true;
                break;
            case G:
                shootsound = Resources.getInstance().getSound("disparo");
                shootsound.seek(Duration.ZERO);
                shootsound.play();
                break;
        }
        }



    @Override
    public void onKeyReleased(KeyCode code) {

        switch (code) {
            case LEFT:
                this.left_press = false;
                break;
            case RIGHT:
                this.right_press = false;
                break;
            case UP:
                this.up_press = false;
                break;
            case DOWN:
                this.down_press = false;
                break;
            case D:
                this.setDebug();
                break;

            case N:
                this.nextLevel();
                break;
            case E:

                if (this.balls.getBall(0) != null) {
                    Ball[] b = this.balls.getBall(0).explotar();
                    this.balls.removeBall(this.balls.getBall(0));

                    this.balls.addBall(b[0]);
                    this.balls.addBall(b[1]);

                }
                if (this.balls.getBall(1) != null) {
                    Ball[] b = this.balls.getBall(1).explotar();
                    this.balls.removeBall(this.balls.getBall(1));

                    this.balls.addBall(b[0]);
                    this.balls.addBall(b[1]);

                }
                    break;
            case G:
                this.hook= new Hook(0, 4);
                this.hook.shoot();

                this.hook.setPosition(this.jugador.getCenter().getX(),this.jugador.getCenter().getY()+10);

                si=true;
                break;
            case J:
                this.hook.pararDisparo();
                if(vidas<1){
                PararJuego();
                }
                }




        }





    private void nextLevel() {
        this.actual_level++;
        if (this.actual_level >= this.levels.length) {
            this.actual_level = 0;
        }
        if (this.backgroundsound != null) {
            this.backgroundsound.stop();
        }
        this.backgroundsound = Resources.getInstance().getSound("fondo");
        this.backgroundsound.play();
        this.levels[this.actual_level].analyze();
        this.createElementsLevel();

        this.paintBackground();
        Game.reset_counter();

    }
}
