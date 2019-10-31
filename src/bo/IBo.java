/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.util.List;

/**
 * @author Huilton
 * @version 1.1
 */
public interface IBo<T> {

    public void save(T obj) throws Exception;

    public void update(T obj) throws Exception;

    public void delete(T obj) throws Exception;

    public List<T> findAll() throws Exception;

    public T find(T obj) throws Exception;

    public T findByCod(int cod) throws Exception;

}
