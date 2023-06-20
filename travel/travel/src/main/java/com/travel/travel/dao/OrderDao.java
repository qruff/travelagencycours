package com.travel.travel.dao;

import com.travel.travel.model.Exploiter;
import com.travel.travel.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {
    List<Orders> findByExploiter_id(int exploiterId);
    List<Orders> findByOrderId(String orderId);
    List<Orders> findByExploiter_idAndTour_id(int exploiterId, int tourId);
    List<Orders> findByExploiter(Exploiter exploiter);
    List<Orders> findByGuidePersonId(int guidePersonId);
}
