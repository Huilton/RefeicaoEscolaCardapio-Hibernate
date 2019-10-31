/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import model.Refeicao;

/**
 *
 * @author Huilton
 */
public class RefeicaoDao extends GenericDao<Refeicao, Serializable> {

    public RefeicaoDao() {
        super(new Refeicao());
    }
//     public Refeicao findByDesc(String des) {
//
//        if (des == null) {
//            return null;
//        }
//
//        List<Refeicao> lista = (List<Refeicao>) this.findAll();
//
//        if (lista == null) {
//            return null;
//        }
//
//        for (Refeicao refe : lista) {
//            if (des.equalsIgnoreCase(refe.get)) {
//                return refe;
//            }
//        }
//
//        return null;
//
//    }  
}
