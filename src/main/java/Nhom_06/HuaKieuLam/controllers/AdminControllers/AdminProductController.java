package Nhom_06.HuaKieuLam.controllers.AdminControllers;

import Nhom_06.HuaKieuLam.entities.Product;
import Nhom_06.HuaKieuLam.services.CategoryService;
import Nhom_06.HuaKieuLam.services.ProductService;
import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }
    @GetMapping("/{id}")
    public String productDetails(@PathVariable long id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            model.addAttribute("message", "đây là trang chi tiết sản phẩm!");
            model.addAttribute("product", product.get());
            return "admin/product/details";
        } else {
            model.addAttribute("error", "Product not found!");
            throw new IllegalArgumentException("Product not found");
        }
    }

    @GetMapping("/add")
    public String addProductForm(@NonNull Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "admin/product/add";
    }

    @PostMapping("/add")
    public String addProduct(
            @Validated @ModelAttribute("product") Product product,
            @NonNull BindingResult bindingResult,
            @RequestParam("image") MultipartFile multipartFile,
            Model model) {

        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories",
                    categoryService.getAllCategories());
            return "admin/product/add";
        }

        try {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            String uploadDir = "src/main/resources/static/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Sử dụng try-with-resources để đảm bảo rằng InputStream được đóng đúng cách
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            }
            product.setImageUrl("/images/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Có lỗi xảy ra.");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/product/add";
        }

        productService.addProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        try {
            // Logic xóa sản phẩm
            productService.deleteProductById(id);
            model.addAttribute("successProductId", id);
        } catch (Exception e) {
            model.addAttribute("errorProductId", id);
            model.addAttribute("errorMessage", "Error occurred while deleting product with id: " + id);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@NonNull Model model, @PathVariable long id) {
        var product = productService.getProductById(id);
        model.addAttribute("message", "đây là trang chỉnh sửa sản phẩm!");
        model.addAttribute("product", product.orElseThrow(() -> new IllegalArgumentException("Product not found")));
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "/admin/product/edit";
    }

    @PostMapping("/edit")
    public String editProduct(@Validated @ModelAttribute("product") Product product, @NonNull BindingResult bindingResult,
                              @RequestParam("image") MultipartFile multipartFile,
                              Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/product/edit";
        }
        try {
            if (!multipartFile.isEmpty()) {
                // Xử lý lưu ảnh mới nếu có tải lên
                String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
                String uploadDir = "src/main/resources/static/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                try (InputStream inputStream = multipartFile.getInputStream()) {
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageUrl("/images/" + fileName);
            } else {
                Product existingProduct = productService.getProductById(product.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Product not found"));
                product.setImageUrl(existingProduct.getImageUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Có lỗi xảy ra.");
            model.addAttribute("categories", categoryService.getAllCategories());
            return "admin/product/edit";
        }
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }

}
