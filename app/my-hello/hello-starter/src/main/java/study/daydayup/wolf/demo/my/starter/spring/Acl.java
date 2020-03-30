package study.daydayup.wolf.demo.my.starter.spring;

import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * study.daydayup.wolf.demo.my.starter.spring
 *
 * @author Wingle
 * @since 2020/3/30 9:58 下午
 **/
public class Acl {
    public static void main(String[] args) throws IOException {
        new Acl().show();
        new Acl().show2();
    }

    public void show() throws IOException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("acl/acl.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        System.out.println(properties);
    }

    public void show2() throws IOException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("acl/acl.yml");
        Yaml yaml = new Yaml();
        Map<String, Integer> obj = yaml.load(inputStream);

        System.out.println(obj);
    }
}
