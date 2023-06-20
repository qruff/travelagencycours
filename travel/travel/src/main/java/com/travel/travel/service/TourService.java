package com.travel.travel.service;

import com.travel.travel.model.Tour;
import org.springframework.web.multipart.MultipartFile;

public interface TourService {
    void addTour(Tour tour, MultipartFile tourImage);
}
