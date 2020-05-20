package pl.dabal.selfstorage.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.dabal.selfstorage.model.Category;
import pl.dabal.selfstorage.repository.CategoryRepository;
import pl.dabal.selfstorage.service.CategoryService;


import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> categoryList() {
        return categoryRepository.findAll();
    }
}
