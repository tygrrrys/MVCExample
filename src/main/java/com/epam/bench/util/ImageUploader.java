package com.epam.bench.util;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.epam.bench.configuration.CloudinaryConf;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by root on 09.08.16.
 */
public class ImageUploader {

    private static final Cloudinary cloudinary = CloudinaryConf.cloud();

    public static String upload(File file) {
        String url = "";
        try {
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            url = uploadResult.get("url").toString();

        } catch (IOException e) {

                    e.printStackTrace();
        }

        return url;
    }
}
