package Nhom_06.HuaKieuLam.controllers.AdminControllers;

import Nhom_06.HuaKieuLam.entities.Invoice;
import Nhom_06.HuaKieuLam.entities.Product;
import Nhom_06.HuaKieuLam.services.CartService;
import Nhom_06.HuaKieuLam.services.CategoryService;
import Nhom_06.HuaKieuLam.services.ProductService;
import Nhom_06.HuaKieuLam.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CartService cartService;

    public AdminController(ProductService productService, CategoryService categoryService,
                           UserService userService, CartService cartService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @GetMapping
    public String admin(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang Admin!");
        return "/admin/layout";
    }

    @GetMapping("/home")
    public String adminHome(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang home của admin!");
        return "/admin/home/index";
    }

    @GetMapping("/product")
    public String adminProduct(@NonNull Model model,
                               @RequestParam(defaultValue = "0")
                               Integer pageNo,
                               @RequestParam(defaultValue = "100")
                                   Integer pageSize,
                               @RequestParam(defaultValue = "id")
                                   String sortBy) {
        model.addAttribute("message", "Chào mừng bạn đến với trang product của admin!");
        model.addAttribute("products", productService.getAllProducts(pageNo, pageSize, sortBy));
        return "/admin/product/index";
    }

    @GetMapping("/category")
    public String adminCategory(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang category của admin!");
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/admin/category/index";
    }

    @GetMapping("/order")
    public String adminOrder(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang order của admin!");
        List<Invoice> orders = cartService.getAllOrders();
        model.addAttribute("orders", orders);
        return "/admin/order/index";
    }

    @GetMapping("/user")
    public String adminUser(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang user của admin!");
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/user/index";
    }

    @GetMapping("/userShipper")
    public String adminUserShipper(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang quản lý người giao hàng trong trang admin!");
        return "/admin/shipper/userShipper";
    }

    @GetMapping("/orderShipper")
    public String adminShipper(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang quản lý đơn hàng của người giao hàng trong trang admin!");
        return "/admin/shipper/orderShipper";
    }
}
