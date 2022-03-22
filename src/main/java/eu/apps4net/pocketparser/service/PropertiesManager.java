package eu.apps4net.pocketparser.service;

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
 */

public class PropertiesManager {
    private static final File file = new File("property.dat");
    private Properties properties;

    public PropertiesManager(Properties properties) {
        this.properties = properties;
    }

    public void saveProperties() throws IOException
    {
        FileOutputStream fr = new FileOutputStream(file);
        properties.store(fr, "Properties");
        fr.close();
    }

    public void loadProperties() throws IOException
    {
        FileInputStream fi=new FileInputStream(file);
        properties.load(fi);
        fi.close();
    }

}
