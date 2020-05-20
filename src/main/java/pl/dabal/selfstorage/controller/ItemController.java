package pl.dabal.selfstorage.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dabal.selfstorage.model.Category;
import pl.dabal.selfstorage.model.Metric;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.ItemDto;
import pl.dabal.selfstorage.service.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private CategoryService categoryService;
    private MetricService metricService;
    private UserService userService;
    private StorageService storageService;
    private ItemService itemService;


    @GetMapping("/add")
    public String addItem(@RequestParam(required = false) Long id, Model model) {
        User user = userService.findByUserName("username");

        ItemDto itemDto = itemService.getItemDtoForItemId(id);
        model.addAttribute("itemDto", itemDto);
        model.addAttribute("storageList", storageService.getStorageListForUser(user));
        return "item/addItem";
    }

    @PostMapping("/add")
    public String saveItem(@RequestParam(required = false) Long id, @Validated ItemDto itemDto, BindingResult result) {
        if (result.hasErrors()) {
            return "item/addItem";
        }
        try {
            User user = userService.findByUserName("username");
            itemService.saveItemFromDtoForUser(itemDto, user);
        } catch (Exception e) {
            ;
        }
        return "redirect:/storage/details?id=" + itemDto.getStorage().getId();
    }

    @ModelAttribute("categories")
    public List<Category> categoryList() {
        return categoryService.categoryList();
    }

    @ModelAttribute("metrics")
    public List<Metric> metricList() {
        return metricService.metricList();
    }
}


