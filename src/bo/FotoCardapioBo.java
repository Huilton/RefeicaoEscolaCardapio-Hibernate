/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.FotoCardapioDao;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import model.FotoCardapio;
import util.Numeric;

/**
 *
 * @author Huilton
 */
public class FotoCardapioBo implements IBo<FotoCardapio> {

    private FotoCardapioDao fotoCardapioDao;

    public FotoCardapioBo() {
        this.fotoCardapioDao = new FotoCardapioDao();
    }

    @Override
    public void delete(FotoCardapio obj) throws Exception {
        if (fotoCardapioDao.findByCod(obj.getIdFotoCardapio()) == null) {
            throw new Exception("Foto Cardapio inexistente.");
        }
        fotoCardapioDao.delete(fotoCardapioDao.findByCod(obj.getIdFotoCardapio()));
    }

    @Override
    public List<FotoCardapio> findAll() throws Exception {
        return this.fotoCardapioDao.findAll();
    }

    @Override
    public void save(FotoCardapio fotoCardapio) throws Exception {
        if (Numeric.isNumeric(String.valueOf(fotoCardapio.getIdFotoCardapio()))) {
            if (this.fotoCardapioDao.findByCod(fotoCardapio.getIdFotoCardapio()) != null) {
                fotoCardapioDao.update(fotoCardapio);
                return;
            }
        }
        fotoCardapioDao.save(fotoCardapio);
    }

    @Override
    public FotoCardapio findByCod(int cod) throws Exception {
        return (FotoCardapio) findByCod(cod);
    }

    @Override
    public FotoCardapio find(FotoCardapio obj) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Image loadImage(File file) throws Exception {
        Image img = null;
        if (file != null) {
            try {
                BufferedImage bimg = ImageIO.read(file);
                img = SwingFXUtils.toFXImage(bimg, null);
            } catch (IOException ex) {
                throw new Exception(ex.getMessage());
            }
        } else {
            throw new Exception("Não foi possivel carregar o arquivo.");
        }
        return img;
    }

    public byte[] selecioneImagem(File file) throws Exception {
        byte[] arr = null;
        try {

            arr = new byte[(int) file.length()];
            loadImage(file);
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                fileInputStream.read(arr);
//                obj.setFoto(arr);
//                bo.save(obj);
                fileInputStream.close();
            } catch (IOException ex) {
                throw new Exception("Erro inesperado.");
            }
        } catch (Exception ex) {
            throw new Exception("Não foi possivel salvar a imagem.");
        }
        return arr;
    }

    @Override
    public void update(FotoCardapio obj) throws Exception {

    }
}
