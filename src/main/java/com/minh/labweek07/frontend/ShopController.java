package com.minh.labweek07.frontend;

import com.minh.labweek07.backend.enums.Color;
import com.minh.labweek07.backend.models.Product;
import com.minh.labweek07.backend.models.ProductCategory;
import com.minh.labweek07.backend.repository.ProductCategoryRepository;
import com.minh.labweek07.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ShopController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @GetMapping("/shop")
    public String shop(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage=page.orElse(1);
        int pageSize=size.orElse(6);
        List<ProductCategory> productCategories=productCategoryRepository.findAll();
        model.addAttribute("productCategories",productCategories);
        model.addAttribute("color", Color.values());

        Page<Product> productPage=productRepository.findAll(org.springframework.data.domain.PageRequest.of(currentPage-1,pageSize));
        model.addAttribute("productPage",productPage);
        int totalPages=productPage.getTotalPages();
        if(totalPages>0){
            int start=Math.max(1,currentPage-2);
            int end=Math.min(currentPage+2,totalPages);
            if(totalPages>5){
                if(end==totalPages){
                    start=end-5;
                }
                else if(start==1){
                    end=start+5;
                }
            }
            model.addAttribute("start",start);
            model.addAttribute("end",end);

        }
        return "user/shop";
    }
    @GetMapping("/shop/category/{id}")
    public String filterProductByCategory(@PathVariable("id") long id,Model model,@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        if(id==0){
            return "redirect:/shop";
        }
        int currentPage=page.orElse(1);
        int pageSize=size.orElse(6);
        Page<Product> products=productRepository.findProductsByProductCategoryId(id,org.springframework.data.domain.PageRequest.of(currentPage-1,pageSize));
        products.getContent().forEach(n->{
            System.out.println(n.getName());
        });
        model.addAttribute("productPage",products);
        List<ProductCategory> productCategories=productCategoryRepository.findAll();
        model.addAttribute("productCategories",productCategories);
        model.addAttribute("color", Color.values());
        int totalPages=products.getTotalPages();
        if(totalPages>0){
            int start=Math.max(1,currentPage-2);
            int end=Math.min(currentPage+2,totalPages);
            if(totalPages>5){
                if(end==totalPages){
                    start=end-5;
                }
                else if(start==1){
                    end=start+5;
                }
            }
            model.addAttribute("start",start);
            model.addAttribute("end",end);

        }

        return "user/shop";
    }
    @GetMapping("/shop/color/{id}")
    public String filterProductByColor(@PathVariable("id") int id,Model model,@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        if(id==0){
            return "redirect:/shop";
        }
        int currentPage=page.orElse(1);
        int pageSize=size.orElse(6);
        Page<Product> products=productRepository.findProductsByColorId(id,org.springframework.data.domain.PageRequest.of(currentPage-1,pageSize));
        products.getContent().forEach(n->{
            System.out.println(n.getName());
        });
        model.addAttribute("productPage",products);
        List<ProductCategory> productCategories=productCategoryRepository.findAll();
        model.addAttribute("productCategories",productCategories);
        model.addAttribute("color", Color.values());
        int totalPages=products.getTotalPages();
        if(totalPages>0){
            int start=Math.max(1,currentPage-2);
            int end=Math.min(currentPage+2,totalPages);
            if(totalPages>5){
                if(end==totalPages){
                    start=end-5;
                }
                else if(start==1){
                    end=start+5;
                }
            }
            model.addAttribute("start",start);
            model.addAttribute("end",end);

        }

        return "user/shop";
    }

}
