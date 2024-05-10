package org.udemy.globant.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties props;

    public static void initialize() throws IOException {
        props = loadProperties(); // carga lo que tenemos en el archivo config

        for(String key: props.stringPropertyNames()) // validamos que no haya cambios en el archivo iterando sobre todas las propiedades
            if(System.getProperties().containsKey(key)) // en caso de cambios, se restablecen las propiedades
                props.setProperty(key, System.getProperty(key));

        log.info("Test Properties");
        for(String key: props.stringPropertyNames())
            log.info("{}={}", key, props.getProperty(key));

    }

    private static Properties loadProperties() throws IOException {
        Properties props = new Properties();

        try(InputStream file = ResourceLoader.getResource(DEFAULT_PROPERTIES)){
            props.load(file);
        } catch (Exception e) {
            log.error("Unable to read the property file {}", DEFAULT_PROPERTIES, e);
        }
        return props;
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}
