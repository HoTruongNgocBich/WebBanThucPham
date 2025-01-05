package Nhom_06.HuaKieuLam.controllers.AdminControllers;

import Nhom_06.HuaKieuLam.entities.Category;
import Nhom_06.HuaKieuLam.services.CategoryService;
import Nhom_06.HuaKieuLam.services.ProductService;
import lombok.NonNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    private final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add")
    public String addCategory(@NonNull Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang thêm sản phẩm của admin!");
        model.addAttribute("category", new Category());
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "admin/category/add";
    }

    @PostMapping("/add")
    public String addCategory(
            @Validated @ModelAttribute("category") Category category,
            @NonNull BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories",
                    categoryService.getAllCategories());
            return "admin/category/add";
        }
        categoryService.addCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.getCategoryById(id)
                .ifPresentOrElse(
                        category -> categoryService.deleteCategoryById(id),
                        () -> {
                            throw new IllegalArgumentException("Category not found");
                        });
        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{id}")
    public String editCategoryForm(@NonNull Model model, @PathVariable long id) {
        model.addAttribute("message", "Chào mừng bạn đến với trang chỉnh sửa danh mục của admin!");
        var category = categoryService.getCategoryById(id);
        model.addAttribute("category", category.orElseThrow(() -> new IllegalArgumentException("Category not found")));
        return "admin/category/edit";
    }

    @PostMapping("/edit")
    public String editCategory(@Validated @ModelAttribute("category") Category category,
                               @NonNull BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories",
                    categoryService.getAllCategories());
            return "admin/category/edit";
        }
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }

}
