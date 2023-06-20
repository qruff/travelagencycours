package com.travel.travel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travel.travel.dao.CartDao;
import com.travel.travel.dao.ExploiterDao;
import com.travel.travel.dao.TourDao;
import com.travel.travel.dto.AddToCartRequest;
import com.travel.travel.dto.CartDataResponse;
import com.travel.travel.dto.CartResponse;
import com.travel.travel.model.Cart;
import com.travel.travel.model.Exploiter;
import com.travel.travel.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/exploiter/")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
    @Autowired
    private CartDao cartDao;
    @Autowired
    private ExploiterDao exploiterDao;
    @Autowired
    private TourDao tourDao;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("cart/add")
    public ResponseEntity add(@RequestBody AddToCartRequest addToCartRequest){
        System.out.println("Add tour to cart");
        System.out.println(addToCartRequest);
        Optional<Exploiter> optionalExploiter = exploiterDao.findById(addToCartRequest.getExploiterId());
        Exploiter exploiter = null;
        if (optionalExploiter.isPresent()){
            exploiter = optionalExploiter.get();
        }

        Optional<Tour> optionalTour = tourDao.findById(addToCartRequest.getTourId());
        Tour tour = null;
        if (optionalTour.isPresent()){
            tour = optionalTour.get();
        }

        Cart cart = new Cart();
        cart.setTour(tour);
        cart.setQuantity(addToCartRequest.getQuantity());
        cart.setExploiter(exploiter);
        cartDao.save(cart);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("mycart")
    public ResponseEntity getMyCart(@RequestParam("exploiterId") int exploiterId) throws JsonProcessingException{
        System.out.println("My cart for exploiter id: " + exploiterId);
        List<CartDataResponse> cartDatas = new ArrayList<>();
        List<Cart> exploiterCarts = cartDao.findByExploiter_id(exploiterId);

        double totalCartPrice = 0;
        for (Cart cart:exploiterCarts){
            CartDataResponse cartData = new CartDataResponse();
            cartData.setCartId(cart.getId());
            cartData.setTourDescription(cart.getTour().getDescription());
            cartData.setTourName(cart.getTour().getTitle());
            cartData.setTourImage(cart.getTour().getImageName());
            cartData.setQuantity(cart.getQuantity());
            cartData.setTourId(cart.getTour().getId());
            cartDatas.add(cartData);
            double tourPrice = Double.parseDouble(cart.getTour().getPrice().toString());
            totalCartPrice = totalCartPrice + (cart.getQuantity()*tourPrice);
        }
        CartResponse cartResponse = new CartResponse();
        cartResponse.setTotalCartPrice(String.valueOf(totalCartPrice));
        cartResponse.setCartData(cartDatas);
        String json = objectMapper.writeValueAsString(cartResponse);
        System.out.println(json);
        return new ResponseEntity(cartResponse, HttpStatus.OK);
    }

    @GetMapping("mycart/remove")
    public ResponseEntity removeCartItem(@RequestParam("cartId") int cartId) throws JsonProcessingException{
        System.out.println("DELETE CART ITEM WHOSE ID IS: " + cartId);
        Optional<Cart> optionalCart = this.cartDao.findById(cartId);
        Cart cart = new Cart();
        if (optionalCart.isPresent()){
            cart = optionalCart.get();
        }
        this.cartDao.delete(cart);
        return new ResponseEntity("SUCCESS", HttpStatus.OK);
    }
}
