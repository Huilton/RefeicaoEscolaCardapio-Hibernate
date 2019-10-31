/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import model.FotoCardapio;
 /**
 *
 * @author Huilton
 */
public class FotoCardapioDao extends GenericDao<FotoCardapio, Serializable>{
    
    public FotoCardapioDao() {
        super(new FotoCardapio());
    }
    
}
