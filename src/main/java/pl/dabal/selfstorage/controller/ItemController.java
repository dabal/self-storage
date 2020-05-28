package pl.dabal.selfstorage.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dabal.selfstorage.model.*;
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
    public String addItem(@RequestParam(required = false) Long id, Model model, @AuthenticationPrincipal CurrentUser user) {
        ItemDto itemDto = itemService.getItemDtoForItemId(id);
        model.addAttribute("itemDto", itemDto);
        model.addAttribute("storageList", storageService.getStorageListForUser(user.getUser()));
        return "item/addItem";
    }

    @PostMapping("/add")
    public String saveItem(@RequestParam(required = false) Long id, @Validated ItemDto itemDto, BindingResult result, @AuthenticationPrincipal CurrentUser user) {
        if (result.hasErrors()) {
            return "item/addItem";
        }
        try {
            itemService.saveItemFromDtoForUser(itemDto, user.getUser());
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


