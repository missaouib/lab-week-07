package com.minh.labweek07.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.minh.labweek07.backend.enums.Color;
import com.minh.labweek07.backend.enums.Size;
import com.minh.labweek07.backend.models.*;
import com.minh.labweek07.backend.repository.ProductCategoryRepository;
import com.minh.labweek07.backend.repository.ProductDetailRepository;
import com.minh.labweek07.backend.repository.ProductImageRepository;
import com.minh.labweek07.backend.repository.ProductRepository;
import com.minh.labweek07.backend.service.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    private final FileUpload fileUpload;
    @GetMapping("/get-size")
    @ResponseBody
    public String getSize(@RequestParam("color") int color, @RequestParam("productId") long id) {
        Color colorE = Color.getColorFromInt(color);
        System.out.println("color" + colorE.ordinal());
        System.out.println("id" + id);
        List<ProductDetail> productDetails = productDetailRepository.findDistinctByProductAndColor(id, colorE.ordinal());
        List<SizeInfo> sizeInfos = new ArrayList<>();

        for (ProductDetail productDetail : productDetails) {
            Size size = productDetail.getSize();
            sizeInfos.add(new SizeInfo(size.ordinal(), size.name()));
        }

        sizeInfos.forEach(n -> {
            System.out.println("id: " + n.getId() + ", name: " + n.getName());
        });

        System.out.println("size" + sizeInfos.size());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        try {
            json = objectMapper.writeValueAsString(sizeInfos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(json);
        return json;
    }

    @GetMapping("/update-product")
    public String updateProduct(@RequestParam("id") long id, Model model) {
        Product product = productRepository.findById(id).get();
        model.addAttribute("product", product);
        return "admin/update-product";
    }
    @GetMapping("/filter-product")
    public String filterProduct(@RequestParam("status") int status, Model model,@RequestParam("sort") String sort) {
        ProductStatus productStatus = ProductStatus.getByCode(status);

        List<Product> products = productRepository.findProductsByStatus(productStatus);
        if (sort.equals("desc")) {
            products = products.stream().sorted(Comparator.comparing(Product::getProductId).reversed()).collect(Collectors.toList());
        } else {
            products = products.stream().sorted(Comparator.comparing(Product::getProductId)).collect(Collectors.toList());
        }
        model.addAttribute("products", products);
        model.addAttribute("status", ProductStatus.values());
        return "admin/product";
    }

    @GetMapping("/add-product")
    public String addProduct() {

        return "admin/add-product";
    }
    @GetMapping("/search-product")
    public String searchProduct(Model model,@RequestParam("query") String query){
        List<Product> products;
        try{
            long id=Long.parseLong(query);
            products=productRepository.findById(id).stream().toList();

        }catch (NumberFormatException e){
           products=new ArrayList<Product>();
        }
        if(products.size()==0){
            products=productRepository.findProductsByNameContainingIgnoreCase(query);
        }
        if(products.size()==0){
            products=productRepository.findProductsByDescriptionContaining(query);
        }
        model.addAttribute("products", products);
        model.addAttribute("status", ProductStatus.values());
        return "admin/product";
    }
    @PostMapping("/add-product")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("price") double price,
                             @RequestParam("description") String description,
                             @RequestParam("unit") String unit,
                             @RequestParam("manu") String manu,
                             @RequestParam("status") int status,
                             @RequestParam("category") long category

    ) {

        ProductStatus productStatus = ProductStatus.getByCode(status);

        ProductCategory productCategory = productCategoryRepository.findById(category).get();
        List<ProductPrice> productPrices = new ArrayList<ProductPrice>();
        ProductPrice productPrice = new ProductPrice();
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setUnit(unit);
        product.setManufacturerName(manu);
        product.setStatus(productStatus);
        product.setProductCategory(productCategory);
        productPrice.setPrice(price);
        productPrice.setProduct(product);
        productPrice.setPriceDate(Date.valueOf(LocalDate.now()));
        productPrices.add(productPrice);
        product.setPrices(productPrices);
        productRepository.save(product);
        return "redirect:/admin/product";
    }

    @PostMapping("/update-product")
    public String updateProduct(@RequestParam("id") long productId,@RequestParam("name") String name,@RequestParam("des") String des,@RequestParam("unit") String unit,@RequestParam("manu") String manu,@RequestParam("price") double price,@RequestParam("status") int status,@RequestParam("category") long categoryId) {
        Product product = productRepository.findById(productId).get();
        ProductStatus productStatus = ProductStatus.getByCode(status);
        ProductCategory productCategory = productCategoryRepository.findById(categoryId).get();
        product.setName(name);
        product.setDescription(des);
        product.setUnit(unit);
        product.setManufacturerName(manu);
        product.setStatus(productStatus);
        product.setProductCategory(productCategory);
        List<ProductPrice> productPrices = product.getPrices();
        ProductPrice productPrice = new ProductPrice();
        productPrice.setPrice(price);
        productPrice.setProduct(product);
        productPrice.setPriceDate(Date.valueOf(LocalDate.now()));
        productPrices.add(productPrice);
        product.setPrices(productPrices);
        productRepository.save(product);
        return "redirect:/update-product?id=" + productId;
    }


    @PostMapping("/delete-product")
    public String deleteProduct(@RequestParam("id") long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/product";
    }

    @PostMapping("/upload-image")
    public String uploadFile(@RequestParam("image") MultipartFile multipartFile, @RequestParam long id,
                             Model model) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);
        Product product = productRepository.findById(id).get();
        ProductImage productImage = new ProductImage(product, 0, imageURL, null);
        productImageRepository.save(productImage);
        return "redirect:/update-product?id=" + id;
    }
    @PostMapping("/add-product-detail")
    public String addProductDetail(@RequestParam("id") long id,
                                   @RequestParam("color") int color, @RequestParam("size") int size,
                                   @RequestParam("quantity") int quantity, Model model) throws IOException {

        Product product = productRepository.findById(id).get();
        Color colorE=Color.getColorFromInt(color);
        Size sizeE=Size.getSizeFromInt(size);
        ProductDetail productDetail = new ProductDetail(product, colorE, sizeE, quantity);
        product.getProductDetails().add(productDetail);
        productRepository.save(product);
        return "redirect:/update-product?id=" + id;
    }

    @PostMapping("/remove-image")
    public String removeImage(@RequestParam("id") long id, @RequestParam("productId") long productId) {
        try {
            Optional<ProductImage> optionalProductImage = Optional.of(productImageRepository.findById(id).get());

            if (optionalProductImage.isPresent()) {
                productImageRepository.deleteById(id);
                System.out.println("đã xóaaaaaaaaaaaaaaaaaaaaaa");
                return "redirect:/update-product?id=" + productId;
            } else {
                System.out.println("không tìm thấy");
                return "redirect:/update-product?id=" + productId;
            }
        } catch (Exception e) {
            // Log the exception for debugging purposes
            System.out.println("Error removing image: " + e.getMessage());
            return "redirect:/update-product?id=" + productId;
        }
    }

}

