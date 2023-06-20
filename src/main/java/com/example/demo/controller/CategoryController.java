package com.example.demo.controller;

import com.example.demo.dto.response.ResponMessage;
import com.example.demo.model.Category;
import com.example.demo.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("category")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;


    @GetMapping
    public ResponseEntity<?> showListCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailCategory(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category) {
        if (categoryService.existsByName(category.getName())) {
            return new ResponseEntity<>(new ResponMessage("name_exists"), HttpStatus.OK);
        }
        categoryService.save(category);
        return new ResponseEntity<>(new ResponMessage("successful"), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<?> pageCategory(@PageableDefault(size = 3) Pageable pageable) {
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> category1 = categoryService.findById(id);
        if (!category1.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        if (!category1.get().getAvatar().equals(category.getAvatar())) {
            category.setId(category1.get().getId());
        }
        if (!category.getName().equals(category1.get().getName())) {
            if (categoryService.existsByName(category.getName())) {
                return new ResponseEntity<>(new ResponMessage("name_existed"), HttpStatus.OK);
            }
        }
        if (category.getName().equals(category1.get().getName()) && category.getAvatar().equals(category1.get().getAvatar())) {
            return new ResponseEntity<>(new ResponMessage("no_change"), HttpStatus.OK);
        }
        category.setId(category1.get().getId());
        categoryService.save(category);
        return new ResponseEntity<>(new ResponMessage("update_successful"), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(new ResponMessage("not_found"), HttpStatus.OK);
        }
        categoryService.deleteById(id);
        return new ResponseEntity<>(new ResponMessage("delete_successful"), HttpStatus.OK);
    }
}
