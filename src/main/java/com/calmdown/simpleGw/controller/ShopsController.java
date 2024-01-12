package com.calmdown.simpleGw.controller;

import com.calmdown.simpleGw.domain.Shop;
import com.calmdown.simpleGw.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("v1/admin/shops")
@Controller
public class ShopsController {

    private final ShopService shopService;

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("shopForm", new ShopForm());
        return "shops/createShopForm";
    }

    @PostMapping("/new")
    public String create(@Valid ShopForm form, BindingResult result) {

        if(result.hasErrors()) {

            return "shops/createShopForm";
        }

        Shop shop = new Shop();
        shop.setId(form.getId());
        shop.setName(form.getName());
        shop.setKey(UUID.randomUUID().toString().substring(0,8));

        shopService.join(shop);
        return "redirect:/v1/admin/shops";
    }

    @GetMapping()
    public String list(Model model) {
        List<Shop> shops = shopService.findShops();
        model.addAttribute("shops", shops);
        return "shops/shopList";
    }

}
