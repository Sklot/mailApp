import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Main {
    /*
    Тестовое задание такое. Нужно на Java написать программу.
    Можно без визуального интерфейса. В программе должно быть 2 файла:

1.       Конфигурационный файл (где хранятся настройки smtp сервера и другие настройки)

2.       Файл шаблон письма, в котором есть 2 параметра для подстановки ФИО и телефона

При запуске программа должна подставить в шаблон письма параметры ФИО и телефон
(прочитав их из конфигурационного файла) и отправить на мой адрес (rvaleev@elg-sys.com) письмо.

В качестве SMTP сервера для отправки почты нужно использовать smtp.yandex.ru
(на яндекс почте можно создать себе почтовый ящик и от его имени отправлять)

//        "mail.smtp.host", "smtp.yandex.ru"
//        "mail.smtp.auth", "true"
//        "mail.smtp.port", "465"
//        "mail.smtp.socketFactory.port", "465"
//        "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"
     */


    public static void main(String[] args) {

        Properties props = new Properties();
        Configuration cfg = new Configuration();
        cfg.configurationAll();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String login = props.getProperty("login");
        final String pass = props.getProperty("password");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(login, pass);
                    }
                });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("ryskal.denis@yandex.ru"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("rvaleev@elg-sys.com"));
            message.setSubject("Тема");
            try {
                message.setText(cfg.TextSample()+String.format("\n  С уважением %s. Телефон для связи %s.",
                        props.getProperty("fio"),props.getProperty("phone_number")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
