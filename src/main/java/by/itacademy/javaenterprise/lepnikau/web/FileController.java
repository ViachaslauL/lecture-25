package by.itacademy.javaenterprise.lepnikau.web;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);

    @GetMapping
    public String mainPage() {
        LOG.info("mainPage method");
        return "file";
    }

    @PostMapping("/save")
    public String addFile(
            @RequestParam(name = "directory") String directory,
            @RequestParam(name = "fileName") String fileName,
            @RequestParam(name = "savingFile", required = false) MultipartFile image,
            Model model
    ) {
        LOG.info("addFile method");

        String message = "";

        try {
            if (image != null && !image.isEmpty()) {
                validateImage(image);
                message = saveImage(directory, fileName, image);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }

        model.addAttribute("message", message);

        return "saved";
    }

    private void validateImage(MultipartFile image) throws IOException {
        LOG.info("validateImage method");
        if (!Objects.equals(image.getContentType(), "image/jpeg")) {
            throw new IOException("Only JPG image accepted");
        }
    }

    private String saveImage(String directory, String fileName, MultipartFile image) throws IOException {
        File file = new File(directory, fileName + ".jpg");
        LOG.info("saveImage method");
        FileUtils.writeByteArrayToFile(file, image.getBytes());

        return "File '" + file.getName() + "' saved by path: " + file.getPath();
    }

}
