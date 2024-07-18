package com.codegym.product_baitap.controller;

import com.codegym.product_baitap.model.Product;
import com.codegym.product_baitap.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


    @Controller
    @RequestMapping("/products")
    public class ProductController {

        @Autowired
        private IProductService productService;

        @GetMapping("")
        public String showAll(Model model) {
            List<Product> products = productService.showAll();
            model.addAttribute("products", products);
            return "/list";
        }

        @GetMapping("/create")
        public String showFormCreate(Model model) {
            model.addAttribute("product", new Product());
            return "/create";
        }

        @PostMapping("/create")
        public String save(Model model,
                           @ModelAttribute Product product,
                           BindingResult bindingResult,
                           RedirectAttributes redirect) {
            if (bindingResult.hasErrors()) {
                model.addAttribute("error", "Vui lòng nhập số");
                return "/create";
            }
            productService.save(product);
            redirect.addFlashAttribute("noti", "Thêm mới thành công!");
            return "redirect:/products";
        }

        @GetMapping("/{id}/delete")
        public String showFormDelete(@PathVariable Long id,
                                     Model model) {
            model.addAttribute("product", productService.findById(id));
            return "/delete";
        }

        @PostMapping("/delete")
        public String delete(@ModelAttribute Product product,
                             RedirectAttributes redirect) {
            productService.delete(product.getId());
            redirect.addFlashAttribute("noti", "Xóa thành công!");
            return "redirect:/products";
        }

        @GetMapping("/{id}/update")
        public String showFormUpdate(@PathVariable Long id,
                                     Model model) {
            model.addAttribute("product", productService.findById(id));
            return "/update";
        }

        @PostMapping("/update")
        public String update(@ModelAttribute Product product,
                             RedirectAttributes redirect) {
            productService.save(product);
            redirect.addFlashAttribute("noti", "Sửa thành công!");
            return "redirect:/products";
        }


        @GetMapping("/search")
        public String searchProductByName(@RequestParam String keyword,
                                          Model model) {
            model.addAttribute("products",productService.searchProductByName(keyword));
            return "/list";
        }
    }

