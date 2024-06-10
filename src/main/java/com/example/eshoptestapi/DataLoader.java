//package com.example.eshoptestapi;
//
//import com.example.eshoptestapi.entity.Brand;
//import com.example.eshoptestapi.repository.BrandRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataLoader implements CommandLineRunner {
//
//    @Autowired
//    private BrandRepository brandRepository;
//
//    @Override
//    public void run(String... args) throws Exception {
//        Brand brand1 = new Brand();
//        brand1.setNameBrand("Brand1");
//        brand1.setSlugBrand("brand1");
//        brand1.setStatus("Active");
//
//        Brand brand2 = new Brand();
//        brand2.setNameBrand("Brand2");
//        brand2.setSlugBrand("brand2");
//        brand2.setStatus("Active");
//
//        brandRepository.save(brand1);
//        brandRepository.save(brand2);
//
//        System.out.println("Data loaded into Brand table.");
//    }
//}
