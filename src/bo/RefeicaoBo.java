/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.RefeicaoDao;
import java.io.ByteArrayInputStream;
import java.util.List;
import javafx.scene.image.Image;
import model.FotoCardapio;
import model.Refeicao;
import util.Numeric;

/**
 *
 * @author Huilton
 */
public class RefeicaoBo implements IBo<Refeicao> {

    private RefeicaoDao refeicaoDao;

    public RefeicaoBo() {
        this.refeicaoDao = new RefeicaoDao();
    }

    @Override
    public void delete(Refeicao obj) throws Exception {
        if (refeicaoDao.findByCod(obj.getIdRefeicao()) == null) {
            throw new Exception("Refeição inexistente.");
        }
        refeicaoDao.delete(refeicaoDao.findByCod(obj.getIdRefeicao()));
    }

    @Override
    public List<Refeicao> findAll() throws Exception {
        return this.refeicaoDao.findAll();
    }

    @Override
    public void save(Refeicao refeicao) throws Exception {
        if (Numeric.isNumeric(String.valueOf(refeicao.getIdRefeicao()))) {
            if (this.refeicaoDao.findByCod(refeicao.getIdRefeicao()) != null) {
                refeicaoDao.update(refeicao);
                return;
            }
        }
        refeicaoDao.save(refeicao);
    }

    @Override
    public Refeicao find(Refeicao obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Refeicao findByCod(int cod) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Refeicao obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
