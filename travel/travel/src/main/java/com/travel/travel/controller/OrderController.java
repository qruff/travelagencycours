package com.travel.travel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travel.dao.CartDao;
import com.travel.travel.dao.ExploiterDao;
import com.travel.travel.dao.OrderDao;
import com.travel.travel.dao.TourDao;
import com.travel.travel.dto.MyOrderResponse;
import com.travel.travel.dto.OrderDataResponse;
import com.travel.travel.dto.UpdateGuideStatusRequest;
import com.travel.travel.model.Cart;
import com.travel.travel.model.Exploiter;
import com.travel.travel.model.Orders;
import com.travel.travel.utility.Constants;
import com.travel.travel.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/exploiter/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ExploiterDao exploiterDao;
    @Autowired
    private TourDao tourDao;

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("order")
    public ResponseEntity customerOrder(@RequestParam("exploiterId") int exploiterId) throws JsonProcessingException{
        System.out.println("ORDER FOR CUSTOMER ID: "+exploiterId);
        String orderId= Helper.getAplphaNumericOrderId();
        List<Cart> exploiterCarts = cartDao.findByExploiter_id(exploiterId);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formatDateTime = currentDateTime.format(formatter);
        for(Cart cart:exploiterCarts){
            Orders order = new Orders();
            order.setOrderId(orderId);
            order.setExploiter(cart.getExploiter());
            order.setTour(cart.getTour());
            order.setQuantity(cart.getQuantity());
            order.setOrderDate(formatDateTime);
            order.setGuideDate(Constants.GuideStatus.PENDING.value());
            order.setGuideStatus(Constants.GuideStatus.PENDING.value());
            order.setGuideTime(Constants.GuideTime.DEFAULT.value());
            order.setGuideAssigned(Constants.IsGuideAssigned.NO.value());
            orderDao.save(order);
            cartDao.delete(cart);
        }

        System.out.println("response sent");
        return new ResponseEntity("ORDER SUCCESS", HttpStatus.OK);
    }

    @GetMapping("myorder")
    public ResponseEntity getMyOrder(@RequestParam("exploiterId") int exploiterId) throws JsonProcessingException{
        System.out.println("MY ORDER for EXPLOITER ID: " + exploiterId);
        List<Orders> exploiterOrder = orderDao.findByExploiter_id(exploiterId);
        OrderDataResponse orderResponse = new OrderDataResponse();
        List<MyOrderResponse> orderDatas = new ArrayList<>();
        for(Orders order: exploiterOrder){
            MyOrderResponse orderData = new MyOrderResponse();
            orderData.setOrderId(order.getOrderId());
            orderData.setTourDescription(order.getTour().getDescription());
            orderData.setTourName(order.getTour().getTitle());
            orderData.setTourImage(order.getTour().getImageName());
            orderData.setQuantity(order.getQuantity());
            orderData.setOrderDate(order.getOrderDate());
            orderData.setTourId(order.getTour().getId());
            orderData.setGuideDate(order.getGuideDate() + " " + order.getGuideTime());
            orderData.setGuideStatus(order.getGuideStatus());
            orderData.setTotalPrice(
                    String.valueOf(order.getQuantity() * Double.parseDouble(order.getTour().getPrice().toString())));
            if (order.getGuidePersonId() == 0){
                orderData.setGuidePersonContact(Constants.GuideStatus.PENDING.value());
                orderData.setGuidePersonName(Constants.GuideStatus.PENDING.value());
            }else{
                Exploiter guidePerson = null;
                Optional<Exploiter> optionalGuidePerson = this.exploiterDao.findById(order.getGuidePersonId());
                guidePerson = optionalGuidePerson.get();
                orderData.setGuidePersonContact(guidePerson.getPhone());
                orderData.setGuidePersonName(guidePerson.getFirstName());
            }
            orderDatas.add(orderData);
        }
        String json = objectMapper.writeValueAsString(orderDatas);
        System.out.println(json);
        return new ResponseEntity(orderDatas, HttpStatus.OK);
    }

    @GetMapping("admin/allorder")
    public ResponseEntity getAllOrder() throws JsonProcessingException {
        System.out.println("FETCH ALL ORDERS");
        List<Orders> exploiterOrder = orderDao.findAll();
        OrderDataResponse orderResponse = new OrderDataResponse();
        List<MyOrderResponse> orderDatas = new ArrayList<>();
        for(Orders order:exploiterOrder){
            MyOrderResponse orderData = new MyOrderResponse();
            orderData.setOrderId(order.getOrderId());
            orderData.setTourDescription(order.getTour().getDescription());
            orderData.setTourName(order.getTour().getTitle());
            orderData.setTourImage(order.getTour().getImageName());
            orderData.setQuantity(order.getQuantity());
            orderData.setOrderDate(order.getOrderDate());
            orderData.setTourId(order.getTour().getId());
            orderData.setGuideDate(order.getGuideDate() + " "+ order.getGuideTime());
            orderData.setGuideStatus(order.getGuideStatus());
            orderData.setTotalPrice(String.valueOf(order.getQuantity() * Double.parseDouble(order.getTour().getPrice().toString())));
            orderData.setExploiterId(order.getExploiter().getId());
            orderData.setUserName(order.getExploiter().getFirstName() + " "+ order.getExploiter().getLastName());
            orderData.setExploiterPhone(order.getExploiter().getPhone());
            if (order.getGuidePersonId()==0){
                orderData.setGuidePersonContact(Constants.GuideStatus.PENDING.value());
                orderData.setGuidePersonName(Constants.GuideStatus.PENDING.value());
            }else{
                Exploiter guidePerson = null;
                Optional<Exploiter> optionalGuidePerson = this.exploiterDao.findById(order.getGuidePersonId());
                guidePerson = optionalGuidePerson.get();
                orderData.setGuidePersonContact(guidePerson.getPhone());
                orderData.setGuidePersonName(guidePerson.getFirstName());
            }
            orderDatas.add(orderData);
        }
        String json = objectMapper.writeValueAsString(orderDatas);
        System.out.println(json);
        System.out.println("response sent");
        return new ResponseEntity(orderDatas, HttpStatus.OK);
    }

    @GetMapping("admin/showorder")
    public ResponseEntity getOrdersByOrderId(@RequestParam("orderId") String orderId) throws JsonProcessingException{
        System.out.println("FETCH ALL ORDERS");
        List<Orders> exploiterOrder = orderDao.findByOrderId(orderId);
        List<MyOrderResponse> orderDatas = new ArrayList<>();
        for(Orders order:exploiterOrder){
            MyOrderResponse orderData = new MyOrderResponse();
            orderData.setOrderId(order.getOrderId());
            orderData.setTourDescription(order.getTour().getDescription());
            orderData.setTourName(order.getTour().getTitle());
            orderData.setTourImage(order.getTour().getImageName());
            orderData.setQuantity(order.getQuantity());
            orderData.setOrderDate(order.getOrderDate());
            orderData.setTourId(order.getTour().getId());
            orderData.setGuideDate(order.getGuideDate() + " "+ order.getGuideTime());
            orderData.setGuideStatus(order.getGuideStatus());
            orderData.setTotalPrice(String.valueOf(order.getQuantity() * Double.parseDouble(order.getTour().getPrice().toString())));
            orderData.setExploiterId(order.getExploiter().getId());
            orderData.setUserName(order.getExploiter().getFirstName() + " "+ order.getExploiter().getLastName());
            orderData.setExploiterPhone(order.getExploiter().getPhone());
            if (order.getGuidePersonId()==0){
                orderData.setGuidePersonContact(Constants.GuideStatus.PENDING.value());
                orderData.setGuidePersonName(Constants.GuideStatus.PENDING.value());
            }else{
                Exploiter guidePerson = null;
                Optional<Exploiter> optionalGuidePerson = this.exploiterDao.findById(order.getGuidePersonId());
                guidePerson = optionalGuidePerson.get();
                orderData.setGuidePersonContact(guidePerson.getPhone());
                orderData.setGuidePersonName(guidePerson.getFirstName());
            }
            orderDatas.add(orderData);
        }
        String json = objectMapper.writeValueAsString(orderDatas);
        System.out.println(json);
        System.out.println("response sent");
        return new ResponseEntity(orderDatas, HttpStatus.OK);
    }

    @PostMapping("admin/order/guideStatus/update")
    public ResponseEntity updateOrderGuideStatus(@RequestBody UpdateGuideStatusRequest guideRequest) throws JsonProcessingException{
        System.out.println("UPDATE GUIDE STATUS");
        System.out.println(guideRequest);
        List<Orders> orders = orderDao.findByOrderId(guideRequest.getOrderId());
        for(Orders order: orders){
            order.setGuideDate(guideRequest.getGuideDate());
            order.setGuideStatus(guideRequest.getGuideStatus());
            order.setGuideTime(guideRequest.getGuideTime());
            orderDao.save(order);
        }

        List<Orders> exploiterOrder = orderDao.findByOrderId(guideRequest.getOrderId());

        List<MyOrderResponse> orderDatas = new ArrayList<>();

        for(Orders order:exploiterOrder){
            MyOrderResponse orderData = new MyOrderResponse();
            orderData.setOrderId(order.getOrderId());
            orderData.setTourDescription(order.getTour().getDescription());
            orderData.setTourName(order.getTour().getTitle());
            orderData.setTourImage(order.getTour().getImageName());
            orderData.setQuantity(order.getQuantity());
            orderData.setOrderDate(order.getOrderDate());
            orderData.setTourId(order.getTour().getId());
            orderData.setGuideDate(order.getGuideDate() + " "+ order.getGuideTime());
            orderData.setGuideStatus(order.getGuideStatus());
            orderData.setTotalPrice(String.valueOf(order.getQuantity() * Double.parseDouble(order.getTour().getPrice().toString())));
            orderData.setExploiterId(order.getExploiter().getId());
            orderData.setUserName(order.getExploiter().getFirstName() + " "+ order.getExploiter().getLastName());
            orderData.setExploiterPhone(order.getExploiter().getPhone());
            if (order.getGuidePersonId()==0){
                orderData.setGuidePersonContact(Constants.GuideStatus.PENDING.value());
                orderData.setGuidePersonName(Constants.GuideStatus.PENDING.value());
            }else{
                Exploiter guidePerson = null;
                Optional<Exploiter> optionalGuidePerson = this.exploiterDao.findById(order.getGuidePersonId());
                guidePerson = optionalGuidePerson.get();
                orderData.setGuidePersonContact(guidePerson.getPhone());
                orderData.setGuidePersonName(guidePerson.getFirstName());
            }
            orderDatas.add(orderData);
        }
        String json = objectMapper.writeValueAsString(orderDatas);
        System.out.println(json);
        System.out.println("response sent");
        return new ResponseEntity(orderDatas, HttpStatus.OK);
    }

    @PostMapping("admin/order/assignGuide")
    public ResponseEntity assignGuidePersonForOrder(@RequestBody UpdateGuideStatusRequest guideRequest) throws JsonProcessingException{
        System.out.println("ASSIGN GUIDE PERSON FROM ORDERS");
        System.out.println(guideRequest);
        List<Orders> orders = orderDao.findByOrderId(guideRequest.getOrderId());
        Exploiter guidePerson = null;
        Optional<Exploiter> optionalGuidePerson = this.exploiterDao.findById(guideRequest.getGuideId());
        if (optionalGuidePerson.isPresent()){
            guidePerson = optionalGuidePerson.get();
        }
        for(Orders order:orders){
            order.setGuideAssigned(Constants.IsGuideAssigned.YES.value());
            order.setGuidePersonId(guideRequest.getGuideId());
            orderDao.save(order);
        }


        List<Orders> exploiterOrder = orderDao.findByOrderId(guideRequest.getOrderId());

        List<MyOrderResponse> orderDatas = new ArrayList<>();
        for(Orders order:exploiterOrder){
            MyOrderResponse orderData = new MyOrderResponse();
            orderData.setOrderId(order.getOrderId());
            orderData.setTourDescription(order.getTour().getDescription());
            orderData.setTourName(order.getTour().getTitle());
            orderData.setTourImage(order.getTour().getImageName());
            orderData.setQuantity(order.getQuantity());
            orderData.setOrderDate(order.getOrderDate());
            orderData.setTourId(order.getTour().getId());
            orderData.setGuideDate(order.getGuideDate() + " "+ order.getGuideTime());
            orderData.setGuideStatus(order.getGuideStatus());
            orderData.setTotalPrice(String.valueOf(order.getQuantity() * Double.parseDouble(order.getTour().getPrice().toString())));
            orderData.setExploiterId(order.getExploiter().getId());
            orderData.setUserName(order.getExploiter().getFirstName() + " "+ order.getExploiter().getLastName());
            orderData.setExploiterPhone(order.getExploiter().getPhone());
            if (order.getGuidePersonId()==0){
                orderData.setGuidePersonContact(Constants.GuideStatus.PENDING.value());
                orderData.setGuidePersonName(Constants.GuideStatus.PENDING.value());
            }else{
                Exploiter gPerson = null;
                Optional<Exploiter> optionalPerson = this.exploiterDao.findById(order.getGuidePersonId());
                gPerson = optionalPerson.get();
                orderData.setGuidePersonContact(gPerson.getPhone());
                orderData.setGuidePersonName(gPerson.getFirstName());
            }
            orderDatas.add(orderData);
        }
        String json = objectMapper.writeValueAsString(orderDatas);
        System.out.println(json);
        System.out.println("response sent");
        return new ResponseEntity(orderDatas, HttpStatus.OK);
    }

    @GetMapping("guide/myorder")
    public ResponseEntity getMyGuideOrders(@RequestParam("guidePersonId") int guidePersonId) throws JsonProcessingException{
        System.out.println("MY  ORDER for EXPLOITER ID: " + guidePersonId);
        Exploiter person =null;
        Optional<Exploiter> oD = this.exploiterDao.findById(guidePersonId);
        if(oD.isPresent()){
            person = oD.get();
        }

        List<Orders> exploiterOrder = orderDao.findByGuidePersonId(guidePersonId);

        List<MyOrderResponse> orderDatas = new ArrayList<>();
        for(Orders order:exploiterOrder){
            MyOrderResponse orderData = new MyOrderResponse();
            orderData.setOrderId(order.getOrderId());
            orderData.setTourDescription(order.getTour().getDescription());
            orderData.setTourName(order.getTour().getTitle());
            orderData.setTourImage(order.getTour().getImageName());
            orderData.setQuantity(order.getQuantity());
            orderData.setOrderDate(order.getOrderDate());
            orderData.setTourId(order.getTour().getId());
            orderData.setGuideDate(order.getGuideDate() + " "+ order.getGuideTime());
            orderData.setGuideStatus(order.getGuideStatus());
            orderData.setTotalPrice(String.valueOf(order.getQuantity() * Double.parseDouble(order.getTour().getPrice().toString())));
            orderData.setExploiterId(order.getExploiter().getId());
            orderData.setUserName(order.getExploiter().getFirstName() + " "+ order.getExploiter().getLastName());
            orderData.setExploiterPhone(order.getExploiter().getPhone());
            if (order.getGuidePersonId()==0){
                orderData.setGuidePersonContact(Constants.GuideStatus.PENDING.value());
                orderData.setGuidePersonName(Constants.GuideStatus.PENDING.value());
            }else{
                orderData.setGuidePersonContact(person.getPhone());
                orderData.setGuidePersonName(person.getFirstName());
            }
            orderDatas.add(orderData);
        }
        String json = objectMapper.writeValueAsString(orderDatas);
        System.out.println(json);
        System.out.println("response sent");
        return new ResponseEntity(orderDatas, HttpStatus.OK);
    }
}
