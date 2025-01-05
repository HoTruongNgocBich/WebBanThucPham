package Nhom_06.HuaKieuLam.controllers.AdminControllers;

import Nhom_06.HuaKieuLam.entities.Product;
import Nhom_06.HuaKieuLam.entities.User;
import Nhom_06.HuaKieuLam.services.CategoryService;
import Nhom_06.HuaKieuLam.services.ProductService;
import Nhom_06.HuaKieuLam.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    private final UserService userService;

    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String productDetails(@PathVariable long id, Model model) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            model.addAttribute("message", "đây là trang chi tiết sản phẩm!");
            model.addAttribute("user", user.get());
            return "admin/user/details";
        } else {
            model.addAttribute("error", "Product not found!");
            throw new IllegalArgumentException("Product not found");
        }
    }
}
