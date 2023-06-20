package com.travel.travel.dao;

import com.travel.travel.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourDao extends JpaRepository<Tour, Integer> {
    List<Tour> findByCategoryId(int category);
}
