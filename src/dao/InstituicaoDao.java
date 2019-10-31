/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import model.Instituicao;

/**
 *
 * @author Huilton
 */
public class InstituicaoDao extends GenericDao<Instituicao, Serializable> {

    public InstituicaoDao() {
        super(new Instituicao());
    }

}
