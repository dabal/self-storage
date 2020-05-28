package pl.dabal.selfstorage.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.dabal.selfstorage.exception.FormFraudException;
import pl.dabal.selfstorage.model.*;
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
    public String getStorageDetails(@RequestParam Long id, Model model, @AuthenticationPrincipal CurrentUser user) throws FormFraudException {
        log.error(user.toString());
        Storage storage = storageService.getStorageByIdAndUser(id, user.getUser()).orElseThrow(() -> new FormFraudException("someone try to modify form"));
        model.addAttribute("items", storageService.getItemsForStorage(storage));
        return "storage/storageDetails";
    }
}


