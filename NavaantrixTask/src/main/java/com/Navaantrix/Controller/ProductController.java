package com.Navaantrix.Controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Navaantrix.Service.ProductService;

@RestController

public class ProductController {

	@Autowired
    private ProductService productService;
	
	
	@PostMapping("/upload")
    public String uploadCsv( MultipartFile file, String operation) {
        try {
            
            	productService.loadDataFromCsv(file);
            return (".......................... successfully ..............................");
        } catch (IOException e) {
            return  e.getMessage();
        }
    }
	
	@PostMapping("/update")
    public void updateProduct( MultipartFile file) {
        try {
        	productService.updateProduct(file);
        } catch (IOException e) {
            System.out.print(e);
        }
    }
	
	
	@PostMapping("/delete")
    public void deleteProduct( MultipartFile file) {
        try {
        	productService.updateProduct(file);
        } catch (IOException e) {
            System.out.print(e);
        }
    }
	

    
}
