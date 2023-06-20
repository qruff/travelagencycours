package com.travel.travel.controller;

import com.travel.travel.dao.CategoryDao;
import com.travel.travel.dao.ExploiterDao;
import com.travel.travel.dao.TourDao;
import com.travel.travel.dto.TourAddRequest;
import com.travel.travel.model.Category;
import com.travel.travel.model.Tour;
import com.travel.travel.service.TourService;
import com.travel.travel.utility.StorageService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/tour")
@CrossOrigin(origins = "http://localhost:3000")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private TourDao tourDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ExploiterDao exploiterDao;

    @PostMapping("add")
    public ResponseEntity<?> addTour(TourAddRequest tourDto){
        System.out.println("ADD PRODUCT");
        System.out.println(tourDto);
        Tour tour = TourAddRequest.toEntity(tourDto);
        Optional<Category> optional = categoryDao.findById(tourDto.getCategoryId());
        Category category = null;
        if(optional.isPresent()){
            category = optional.get();
        }
        tour.setCategory(category);
        tourService.addTour(tour, tourDto.getImage());
        System.out.println("response sent");
        return ResponseEntity.ok(tour);
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllTours(){
        System.out.println("getting all tours");
        List<Tour> tours = new ArrayList<Tour>();
        tours = tourDao.findAll();
        System.out.println("response sent");
        return ResponseEntity.ok(tours);
    }

    @GetMapping("id")
    public ResponseEntity<?> getTourById(@RequestParam("tourId") int tourId){
        System.out.println("get tour by tourid");
        Tour tour = new Tour();
        Optional<Tour> optional = tourDao.findById(tourId);
        if (optional.isPresent()){
            tour = optional.get();
        }
        System.out.println("response sent");
        return ResponseEntity.ok(tour);
    }

    @GetMapping("category")
    public ResponseEntity<?> getToursByCategories(@RequestParam("categoryId") int categoryId){
        System.out.println("get all tours by category");
        List<Tour> tours =new ArrayList<Tour>();
        tours = tourDao.findByCategoryId(categoryId);
        System.out.println("response sent");
        return ResponseEntity.ok(tours);
    }

    @GetMapping(value = "/{tourImageName}", produces = "image/*")
    public void fetchTourImage(@PathVariable("tourImageName") String tourImageName, HttpServletResponse resp){
        System.out.println("for fetching tour pic");
        System.out.println("Loading file: "+ tourImageName);
        Resource resource = storageService.load(tourImageName);
        if (resource != null){
            try(InputStream in = resource.getInputStream()){
                ServletOutputStream out = resp.getOutputStream();
                FileCopyUtils.copy(in, out);
            } catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("response sent");
    }
}
