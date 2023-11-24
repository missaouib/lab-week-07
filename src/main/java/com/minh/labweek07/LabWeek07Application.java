package com.minh.labweek07;

import com.cloudinary.Cloudinary;
import com.minh.labweek07.backend.models.Product;
import com.minh.labweek07.backend.models.ProductPrice;
import com.minh.labweek07.backend.models.ProductStatus;
import com.minh.labweek07.backend.repository.CustomerRepository;
import com.minh.labweek07.backend.repository.EmployeeRepository;
import com.minh.labweek07.backend.repository.ProductPriceRepository;
import com.minh.labweek07.backend.repository.ProductRepository;
import net.datafaker.Faker;
import net.datafaker.providers.base.Address;
import net.datafaker.providers.base.Device;
import net.datafaker.providers.base.Name;
import net.datafaker.providers.base.Number;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class LabWeek07Application {
    private final String CLOUD_NAME = "dbxogj6oe";
    private final String API_KEY = "967651379553858";
    private final String API_SECRET = "SoImI0NrKq5nMWIJ6kaEnL11cgw";
    public static void main(String[] args) {
        SpringApplication.run(LabWeek07Application.class, args);
    }
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name",CLOUD_NAME);
        config.put("api_key",API_KEY);
        config.put("api_secret",API_SECRET);
        return new Cloudinary(config);
    }


}
