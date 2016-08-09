package com.epam.bench.controller;

import com.epam.bench.model.FileBucket;
import com.epam.bench.util.FileValidator;
import com.epam.bench.util.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by root on 08.08.16.
 */
@Controller
public class FileUploadController {

    @Autowired
    private HttpServletRequest request;


    @Autowired
    FileValidator fileValidator;

    @InitBinder("fileBucket")
    protected void initBinderFileBucket(WebDataBinder binder) {
        binder.setValidator(fileValidator);
    }
     @RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
    public String getHomePage(ModelMap model) {
        return "welcome";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.GET)
    public String getSingleUploadPage(ModelMap model) {
        FileBucket fileModel = new FileBucket();
        model.addAttribute("fileBucket", fileModel);
        return "singleFileUploader";
    }

    @RequestMapping(value = "/singleUpload", method = RequestMethod.POST)
    public String singleFileUpload(@Valid FileBucket fileBucket,
                                   BindingResult result, ModelMap model) throws IOException {

        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "singleFileUploader";
        } else {

            String realPathtoUploads =  request.getServletContext().getRealPath("resources/");

            MultipartFile multipartFile = fileBucket.getFile();
            InputStream in = multipartFile.getInputStream();
            File resultingFile = new File (realPathtoUploads + fileBucket.getFile().getOriginalFilename());
            FileCopyUtils.copy(fileBucket.getFile().getBytes(), resultingFile);
            String url = ImageUploader.upload(resultingFile);
            resultingFile.delete();
            model.addAttribute("fileName", url);

            return "success";
        }
    }
}
