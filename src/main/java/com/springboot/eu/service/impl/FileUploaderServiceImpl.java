package com.springboot.eu.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.eu.entity.Alta;
import com.springboot.eu.service.IFileUploaderService;

@Service
public class FileUploaderServiceImpl implements IFileUploaderService {

	
	public List<Alta> invoiceExcelReaderService() {
		return null;
	}
	
	@Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    public void uploadFile(MultipartFile file) {

        try {
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss:");
            Path copyLocation = Paths
                .get(uploadDir + File.separator + StringUtils.cleanPath(dtf.format(LocalDateTime.now())+file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            //Ruta del archivo
            System.out.println(copyLocation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("No se pudo almacenar el archivo " + file.getOriginalFilename()
                + ". Inténtalo de nuevo!");
        }
    }
}
