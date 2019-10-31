/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import model.Refeicao;

/**
 *
 * @author Huilton
 */
public class EnvioEmail {

    public void enviarEmail(Refeicao refeicao, String caminhoArquivo) throws Exception {

        try {
            //config. do gmail
            Properties mailProps = new Properties();
            mailProps.put("mail.transport.protocol", "smtp");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.host", "smtp.gmail.com");
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.user", "Revy");
            mailProps.put("mail.debug", "true");
            mailProps.put("mail.smtp.port", "465");
            mailProps.put("mail.smtp.socketFactory.port", "465");
            mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            mailProps.put("mail.smtp.socketFactory.fallback", "false");

            //eh necessario autenticar
            Session mailSession = Session.getInstance(mailProps, new Authenticator() {

                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("revytwohand0@gmail.com", "binho#h8");
                }
            });
            mailSession.setDebug(false);

            //config. da mensagem
            Message mailMessage = new MimeMessage(mailSession);

            //destinatario
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(refeicao.getInstituicao().getPessoa().getEmail()));

            //mensagem que vai no corpo do email
            MimeBodyPart mbpMensagem = new MimeBodyPart();
            mbpMensagem.setText("Responsavel: " + refeicao.getInstituicao().getPessoa().getNome()
                    + "\nInstituição: " + refeicao.getInstituicao().getNomeInst()
                    + "\nNome Refeição: " + refeicao.getNomeRef()
                    + "\nTipo Refeição: " + refeicao.getTipoRef()
                    + "\nData Refeição: " + refeicao.getDataCadastro()
                    + "\nDescrição Refeição: " + refeicao.getDescricao()
            );

            //partes do email
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mbpMensagem);

            File Arquivo = new File(caminhoArquivo);
            //setando o anexo
            MimeBodyPart mbpAnexo = new MimeBodyPart();
            mbpAnexo.setDataHandler(new DataHandler(new FileDataSource(Arquivo)));
            mbpAnexo.setFileName(refeicao.getNomeRef());
            mp.addBodyPart(mbpAnexo);

            //assunto do email
            mailMessage.setSubject(
                    "Refeição " + refeicao.getInstituicao().getNomeInst() + " Data " + refeicao.getDataRef());
            //seleciona o conteudo 
            mailMessage.setContent(mp);

            //envia o email
            Transport.send(mailMessage);

        } catch (Exception e) {
            throw new Exception("Erro no Envio De Autenticiação");
        }
    }
}
