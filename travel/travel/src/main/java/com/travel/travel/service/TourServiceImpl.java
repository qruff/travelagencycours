package com.travel.travel.service;

import com.travel.travel.dao.TourDao;
import com.travel.travel.model.Tour;
import com.travel.travel.utility.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TourServiceImpl implements TourService{
    @Autowired
    private TourDao tourDao;

    @Autowired
    private StorageService storageService;
    @Override
    public void addTour(Tour tour, MultipartFile tourImage) {
        String tourImageName = storageService.store(tourImage);
        tour.setImageName(tourImageName);
        this.tourDao.save(tour);
    }
}
