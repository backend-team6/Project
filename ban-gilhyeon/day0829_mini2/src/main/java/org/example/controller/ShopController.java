package org.example.controller;

import org.example.model.dto.ShopDTO;
import org.example.model.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopServicee;

    @GetMapping("/review")
   public String review() {return "review";}

    @PostMapping("/review")
    public String insertReview(ShopDTO shopDTO) throws SQLException {
        System.out.println("ShopDTO : " + shopDTO);
        shopServicee.insert(shopDTO);
        return "redirect:/shop/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) throws SQLException {
        List<ShopDTO> shopDTOList = shopServicee.findAll();
        model.addAttribute("shopDTOList", shopDTOList);
        System.out.println("shopDTOList" + shopDTOList);
        return "list2";
    }

}
