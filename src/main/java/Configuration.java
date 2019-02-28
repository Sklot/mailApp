import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Configuration {
/*
         "mail.smtp.host", "smtp.yandex.ru"
        "mail.smtp.auth", "true"
        "mail.smtp.port", "465"
        "mail.smtp.socketFactory.port", "465"
        "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"
 */

    public void configurationAll() {
        File properties_file = new File("src/main/resources/config.properties");
        Scanner scanner = new Scanner(System.in);
        if (properties_file.exists()) {
            FileInputStream fis;
            Properties property = new Properties();
            try {
                fis = new FileInputStream("src/main/resources/config.properties");
                property.load(fis);
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
            System.out.println("Использовать default настройки?  Y/N");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("y")) {
                try {
                    System.out.println("Загружаю настройки...");
                    setDefaultConfig();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (choice.equals("n")) {
                try {
                    setAllConfig();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                properties_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                setDefaultConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void setAllConfig() throws IOException {
        FileOutputStream out = new FileOutputStream("src/main/resources/config.properties");
        FileInputStream in = new FileInputStream("src/main/resources/config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        Scanner sc = new Scanner(System.in);


        System.out.println("Введите новые настройки:");

        System.out.println("login:");
        props.setProperty("login", sc.nextLine());
        System.out.println("ok");

        System.out.println("password:");
        props.setProperty("password", sc.nextLine());
        System.out.println("ok");

        System.out.println("fio:");
        props.setProperty("fio", sc.nextLine());
        System.out.println("ok");

        System.out.println("phone_number:");
        props.setProperty("phone_number", sc.nextLine());
        System.out.println("ok");

        System.out.println("mail.smtp.host:");
        props.setProperty("mail.smtp.host", sc.nextLine());

        System.out.println("ok");
        System.out.println("mail.smtp.auth:");
        props.setProperty("mail.smtp.auth", sc.nextLine());

        System.out.println("ok");
        System.out.println("mail.smtp.port:");
        props.setProperty("mail.smtp.port", String.valueOf(sc.nextInt()));

        System.out.println("ok");
        System.out.println("mail.smtp.socketFactory.port:");
        props.setProperty("mail.smtp.socketFactory.port", String.valueOf(sc.nextInt()));

        System.out.println("ok");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        props.store(out, null);
        out.close();
        System.out.println("Ready");
    }

    public void setDefaultConfig() throws IOException {
        FileOutputStream out = new FileOutputStream("src/main/resources/config.properties");
        FileInputStream in = new FileInputStream("src/main/resources/config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();
        props.setProperty("mail.smtp.host", "smtp.yandex.ru");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("login", "ryskal.denis@yandex.ru");
        props.setProperty("password", "Password1");
        props.setProperty("fio", "Рыскаль Денис Евгеньевич");
        props.setProperty("phone_number", "89155609534");
        props.store(out, null);
        out.close();

        System.out.println("Ready");
    }

    public String TextSample() throws IOException {
        File text_file = new File("src/main/resources/textSample.txt");
        BufferedReader reader = new BufferedReader(new FileReader(text_file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }

    }

}
