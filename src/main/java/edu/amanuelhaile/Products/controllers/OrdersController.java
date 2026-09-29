package edu.amanuelhaile.Products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.amanuelhaile.Products.data.OrdersDataService;
import edu.amanuelhaile.Products.models.OrderModel;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersDataService ordersDataService;

    @GetMapping("")
    public String showAllOrders(Model model) {

        model.addAttribute("title", "All Orders");
        model.addAttribute("orders", ordersDataService.getAll());

        return "allOrders";
    }
    @GetMapping("/showOrder/{id}")
    public String showOrder(@PathVariable int id, Model model) {

        model.addAttribute("title", "Order Details");
        model.addAttribute("order", ordersDataService.getById(id));

        return "showOrder";
    }
    @GetMapping("/editOrder/{id}")
    public String editOrder(@PathVariable int id, Model model) {

        OrderModel order = ordersDataService.getById(id);

        model.addAttribute("title", "Edit Order");
        model.addAttribute("order", order);

        return "editOrder";
    }

    @PostMapping("/processEditOrder")
    public String processEditOrder(@ModelAttribute("order") OrderModel order) {

        ordersDataService.update(order);

        return "redirect:/orders";
    }
    @GetMapping("/newOrder")
    public String newOrder(Model model) {

        model.addAttribute("title", "New Order");
        model.addAttribute("order", new OrderModel());

        return "newOrder";
    }

    @PostMapping("/processNewOrder")
    public String processNewOrder(@ModelAttribute("order") OrderModel order) {

        ordersDataService.create(order);

        return "redirect:/orders";
    }
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable int id) {

        ordersDataService.deleteById(id);

        return "redirect:/orders";
    }
    
}