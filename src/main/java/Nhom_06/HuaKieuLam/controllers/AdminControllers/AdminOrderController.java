package Nhom_06.HuaKieuLam.controllers.AdminControllers;

import Nhom_06.HuaKieuLam.entities.Invoice;
import Nhom_06.HuaKieuLam.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {
    private final CartService cartService;

    public AdminOrderController(CartService cartService) {
        this.cartService = cartService;
    }

    //chi tiết đơn hàng
    @GetMapping("/{id}")
    public String viewOrderDetails(@PathVariable("id") Long id, Model model) {
        Invoice order = cartService.getOrderById(id);
        model.addAttribute("order", order);
        return "/admin/order/details";
    }

    @GetMapping("/confirm/{id}")
    public String confirmOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.confirmOrder(id);
            redirectAttributes.addFlashAttribute("message", "Order confirmed successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order";
    }

    @GetMapping("/ship/{id}")
    public String shipOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.shipOrder(id);
            redirectAttributes.addFlashAttribute("message", "Order shipped successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order";
    }

    @GetMapping("/deliver/{id}")
    public String deliverOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.deliverOrder(id);
            redirectAttributes.addFlashAttribute("message", "Order delivered successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order";
    }

    @GetMapping("/complete/{id}")
    public String completeOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.completeOrder(id);
            redirectAttributes.addFlashAttribute("message", "Order completed successfully.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order";
    }

    @PostMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.cancelOrder(id);
            redirectAttributes.addFlashAttribute("message", "Order has been requested to be cancelled.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order";
    }

    @PostMapping("/cancel-confirm")
    public String cancelConfirmOrder(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            cartService.cancelConfirmOrder(id);
            redirectAttributes.addFlashAttribute("message", "Confirm successful order cancellation.");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/order";
    }

}
