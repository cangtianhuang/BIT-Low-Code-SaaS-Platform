package org.lcsp.module6.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class ModuleLoader {

    /**
     * Load a module from a JAR file.
     *
     * @param jarFile The JAR file of the module.
     */
    public void load(MultipartFile jarFile) throws IOException {
        File file = new File("/path/to/the/file");
        jarFile.transferTo(file);
    }
}