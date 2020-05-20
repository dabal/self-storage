package pl.dabal.selfstorage.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dabal.selfstorage.exception.FormFraudException;
import pl.dabal.selfstorage.model.Category;
import pl.dabal.selfstorage.model.Metric;
import pl.dabal.selfstorage.model.Storage;
import pl.dabal.selfstorage.model.User;
import pl.dabal.selfstorage.model.dto.ItemDto;
import pl.dabal.selfstorage.service.*;

import java.util.List;

@Controller
@RequestMapping("/storage")
@AllArgsConstructor
@Slf4j
public class StorageController {

    private CategoryService categoryService;
    private MetricService metricService;
    private UserService userService;
    private StorageService storageService;
    private ItemService itemService;

    @GetMapping("/details")
    public String getStorageDetails(@RequestParam Long id, Model model) throws FormFraudException {
        User user = userService.findByUserName("username");
        log.error(id.toString());
        Storage storage = storageService.getStorageById(id).orElseThrow(() -> new FormFraudException("someone try to modify form"));
        if (storageService.getStorageById(id).get().getUser() != user) {
            throw new FormFraudException("someone try to modify form");
        }
        model.addAttribute("items", storageService.getItemsForStorage(storage));
        return "storage/storageDetails";
    }
}


