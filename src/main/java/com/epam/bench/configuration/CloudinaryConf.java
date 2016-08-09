package com.epam.bench.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

/**
 * Created by root on 09.08.16.
 */
public class CloudinaryConf {
    private static Cloudinary cloudinary;

    public static Cloudinary cloud () {

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "agab",
                "api_key", "552974875763661",
                "api_secret", "d02QwJXtWLhetEXCgCadaCoInW8"));

        return cloudinary;
    }
}
