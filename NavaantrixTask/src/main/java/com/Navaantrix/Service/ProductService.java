package com.Navaantrix.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Navaantrix.Model.Product;
import com.Navaantrix.Repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public void loadDataFromCsv(MultipartFile file) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				Product product = new Product();
				product.setName(values[1]);
				product.setPrice(Integer.parseInt(values[2]));
				product.setDescription(values[3]);

				productRepo.save(product); 
			}
		}
	}

	public void updateProduct(MultipartFile file) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");

				Optional<Product> optionalProduct = productRepo.findById(Integer.parseInt(values[0]));
				if (optionalProduct.isPresent()) {
					Product product = optionalProduct.get();
					product.setName(values[1]);
					product.setPrice(Integer.parseInt(values[2]));
					product.setDescription(values[3]);

					productRepo.save(product); 
				}
			}
		}
	}

	public void deleteProduct(MultipartFile file) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				Optional<Product> optionalProduct = productRepo.findById(Integer.parseInt(values[0]));
				optionalProduct.ifPresent(productRepo::delete);
			}
		}

	}
}
