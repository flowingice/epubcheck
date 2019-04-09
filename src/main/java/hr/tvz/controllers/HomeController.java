package hr.tvz.controllers;

import hr.tvz.services.StorageService;
import hr.tvz.util.InputHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class HomeController {

    private final StorageService storageService;

    @Autowired
    public HomeController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping("/")
    public String home() {
        return "uploadForm";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, Model model) {

        final String name = this.storageService.store(file) + ".epub";
        final String path = "upload-dir/" + name;
        List<String> res = InputHandler.handle(path);
        this.storageService.deleteFile(name);
        model.addAttribute("res", res);

        return "result";
    }

}
