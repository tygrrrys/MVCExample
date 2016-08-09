package com.epam.bench.util;

import com.epam.bench.model.FileBucket;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Created by root on 08.08.16.
 */
@Component
public class FileValidator {

    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;

        if (file.getFile() != null) {
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}
