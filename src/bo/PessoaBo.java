/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PessoaDao;
import java.util.List;
import model.Pessoa;
import util.Numeric;
import util.ValidaEmail;

/**
 *
 * @author Huilton
 */
public class PessoaBo implements IBo<Pessoa> {

    private PessoaDao pessoaDao;

    public PessoaBo() {
        this.pessoaDao = new PessoaDao();
    }

    @Override
    public void delete(Pessoa obj) throws Exception {
        if (pessoaDao.findByCod(obj.getIdPessoa()) == null) {
            throw new Exception("pessoa inexistente.");
        }
        pessoaDao.delete(pessoaDao.findByCod(obj.getIdPessoa()));
    }

    @Override
    public List<Pessoa> findAll() throws Exception {
        return this.pessoaDao.findAll();
    }

    @Override
    public void save(Pessoa pessoa) throws Exception {
        if (Numeric.isNumeric(String.valueOf(pessoa.getIdPessoa()))) {
            if (this.pessoaDao.findByCod(pessoa.getIdPessoa()) != null) {
                pessoaDao.update(pessoa);
                return;
            }
        }
        pessoaDao.save(pessoa);
    }

    @Override
    public Pessoa find(Pessoa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa findByCod(int cod) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Pessoa obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
