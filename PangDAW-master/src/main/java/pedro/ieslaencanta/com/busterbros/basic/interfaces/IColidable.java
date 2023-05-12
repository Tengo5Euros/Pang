/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.busterbros.basic.interfaces;

import java.util.Optional;
import pedro.ieslaencanta.com.busterbros.basic.Colision;
import pedro.ieslaencanta.com.busterbros.basic.Element;

/**
 *
 * @author DAWTarde
 */
public interface IColidable {
    public Optional<Colision> collision(Element e);
}
