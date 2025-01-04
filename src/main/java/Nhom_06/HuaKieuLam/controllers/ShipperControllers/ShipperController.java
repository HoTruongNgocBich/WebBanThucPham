package Nhom_06.HuaKieuLam.controllers.ShipperControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shipper")
public class ShipperController {

    @GetMapping
    public String shipper(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang Shipper!");
        return "/shipper/layout";
    }

    @GetMapping("/home")
    public String shipperHome(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang Shipper!");
        return "/shipper/home/index";
    }

    @GetMapping("/order")
    public String shipperOrder(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang đơn hàng!");
        return "/shipper/order/index";
    }

    @GetMapping("/historyOrder")
    public String shipperHistoryOrder(Model model) {
        model.addAttribute("message", "Chào mừng bạn đến với trang lịch sử đơn hàng!");
        return "/shipper/historyOrder/index";
    }
}
