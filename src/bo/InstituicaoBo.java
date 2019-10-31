/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.InstituicaoDao;
import java.util.List;
import model.Instituicao;
import util.Numeric;

/**
 *
 * @author Huilton
 */
public class InstituicaoBo implements IBo<Instituicao> {

    private InstituicaoDao instituicaoDao;

    public InstituicaoBo() {
        this.instituicaoDao = new InstituicaoDao();
    }

    @Override
    public void delete(Instituicao obj) throws Exception {
        if (instituicaoDao.findByCod(obj.getIdInstituicao()) == null) {
            throw new Exception("instituicao inexistente.");
        }
        instituicaoDao.delete(instituicaoDao.findByCod(obj.getIdInstituicao()));
    }

    @Override
    public List<Instituicao> findAll() throws Exception {
        return this.instituicaoDao.findAll();
    }

    @Override
    public void save(Instituicao instituicao) throws Exception {
        if (Numeric.isNumeric(String.valueOf(instituicao.getIdInstituicao()))) {
            if (this.instituicaoDao.findByCod(instituicao.getIdInstituicao()) != null) {
                instituicaoDao.update(instituicao);
                return;
            }
        }
        instituicaoDao.save(instituicao);
    }

    @Override
    public Instituicao find(Instituicao obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Instituicao findByCod(int cod) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Instituicao obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
