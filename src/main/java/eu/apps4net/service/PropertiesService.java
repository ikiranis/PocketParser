package eu.apps4net.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Yiannis Kiranis <yiannis.kiranis@gmail.com>
 * https://apps4net.eu
 * Date: 22/3/22
 * Time: 8:27 μ.μ.
 *
 * Save and load properties in file
 *
 */

public class PropertiesService {
    private static final File file = new File("property.dat");
    private Properties properties = new Properties();

    public void setProperty(String name, String value) {
        try {
            load();
            properties.setProperty(name, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public void save() throws IOException
    {
        FileOutputStream fr = new FileOutputStream(file);
        properties.store(fr, "Properties");
        fr.close();
    }

    public void load() throws IOException
    {
        FileInputStream fi=new FileInputStream(file);
        properties.load(fi);
        fi.close();
    }

}
