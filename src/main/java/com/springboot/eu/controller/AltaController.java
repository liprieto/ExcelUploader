package com.springboot.eu.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.eu.entity.Alta;
import com.springboot.eu.repository.AltaRepository;
import com.springboot.eu.service.IExcelDataService;
import com.springboot.eu.service.IFileUploaderService;

@Controller
public class AltaController {
	
	@Autowired
	IFileUploaderService fileService;
	
	@Autowired
	IExcelDataService excelservice;
	
	@Autowired
	AltaRepository repo;
	
	@GetMapping("/")
    public String index() {
        return "uploadPage";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        fileService.uploadFile(file);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss:");
        redirectAttributes.addFlashAttribute("message",
            "Archivo guardado '"+ dtf.format(LocalDateTime.now())+ file.getOriginalFilename()+"' ");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return "redirect:/";
    }
    
    @GetMapping("/saveData")
    public String saveExcelData(Model model) {
    	
    	List<Alta> excelDataAsList = excelservice.getExcelDataAsList();
    	int noOfRecords = excelservice.saveExcelData(excelDataAsList);
    	model.addAttribute("noOfRecords",noOfRecords);
    	return "success";	
    	
    	
    }
}
