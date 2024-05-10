package org.udemy.globant.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.udemy.globant.tests.vendorportal.model.VendorPortalTestData;

import java.io.IOException;
import java.io.InputStream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T>T getTestData(String path, Class<T> type) throws IOException {
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        } catch(Exception e) {
            log.error("Unable to read test data {}", path, e);
        }
        return null;
    }
}
