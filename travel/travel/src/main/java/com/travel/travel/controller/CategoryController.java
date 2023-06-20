package com.travel.travel.controller;

import com.travel.travel.dao.CategoryDao;
import com.travel.travel.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @GetMapping("all")
    public ResponseEntity<List<Category>> getAllCategories(){
        System.out.println("get all categories");
        List<Category> categories = this.categoryDao.findAll();
        ResponseEntity<List<Category>> response = new ResponseEntity<>(categories, HttpStatus.OK);
        System.out.println("response sent");
        return response;
    }

    @PostMapping("add")
    public ResponseEntity add(@RequestBody Category category){
        System.out.println("add category");
        Category c = categoryDao.save(category);
        if (c!=null){
            System.out.println("response sent");
            return new ResponseEntity(c, HttpStatus.OK);
        }
        else {
            System.out.println("response sent");
            return new ResponseEntity("Failed add category", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
