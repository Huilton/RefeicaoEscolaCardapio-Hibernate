/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Huilton
 */
public class Data {

    public static Date formatarData(String data) throws ParseException, Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Verifica se a data é válida
        sdf.setLenient(false);
        Date dataConvertida = null;
        try {
            dataConvertida = new Date(sdf.parse(data).getTime());
        } catch (Exception e) {
            throw new Exception("Data inválida");
        }
        return dataConvertida;
    }
}
