package com.tonmoy.bartender.controller;

import com.tonmoy.bartender.BartenderApplication;
import com.tonmoy.bartender.dto.OrderDrinkDTO;
import com.tonmoy.bartender.service.BarTenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barTenderController")
public class BarTenderController {

    @Autowired
    private BarTenderService barTenderService;
    Logger logger =  LoggerFactory.getLogger(BarTenderController.class);

   /* @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String helloWorld(){
        return "Hello World";
    }

    @RequestMapping(value = "/goodbyeHello",method = RequestMethod.POST)
    public String goodbyeHelloWorld(){
        return "Good Bye Hello World";
    }*/

    @RequestMapping(value = "/orderDrink", method = RequestMethod.POST)
    public ResponseEntity orderDrink(@RequestBody OrderDrinkDTO orderDrinkDTO) throws InterruptedException {

        Boolean serveOrNot = barTenderService.orderDrink(orderDrinkDTO);
        if (serveOrNot){
            logger.info("Order accepted");
            return ResponseEntity.ok("Your order is accepted.");
        }else {
            logger.info("Order not accepted");
            return ResponseEntity.ok(HttpStatus.TOO_MANY_REQUESTS);
        }
    }

    @RequestMapping(value = "/getCustomerAndServedDrinkList", method = RequestMethod.GET)
    public ResponseEntity getCustomerAndServedDrinkList()  {
            logger.info("getCustomerAndServedDrinkList api invoked");
            return ResponseEntity.ok(barTenderService.getCustomerAndServedDrinkList());

    }

}
