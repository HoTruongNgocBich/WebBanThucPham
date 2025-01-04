package Nhom_06.HuaKieuLam.controllers.AdminControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

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
    public String adminProduct(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang product của admin!");
        return "/admin/product/index";
    }

    @GetMapping("/category")
    public String adminCategory(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang category của admin!");
        return "/admin/category/index";
    }

    @GetMapping("/order")
    public String adminOrder(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang order của admin!");
        return "/admin/order/index";
    }

    @GetMapping("/user")
    public String adminUser(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang user của admin!");
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
