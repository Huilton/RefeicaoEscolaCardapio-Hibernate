/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import model.Pessoa;

/**
 *
 * @author Huilton
 */
public class PessoaDao extends GenericDao<Pessoa, Serializable> {

    public PessoaDao() {
        super(new Pessoa());
    }

}
