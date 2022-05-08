package com.tonmoy.bartender.service;

import com.tonmoy.bartender.dao.BarTenderDAO;
import com.tonmoy.bartender.dto.CustomerAndServedDrinkListDTO;
import com.tonmoy.bartender.dto.OrderDrinkDTO;
import com.tonmoy.bartender.enumeration.DrinkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class BarTenderService {
    @Autowired
    private BarTenderDAO barTenderDAO;


    private static Integer noOfCustomerServed = 0;
    private static Integer noOfServedBEER = 0;
    private static Integer noOfServedDRINK = 0;

    @Value("${drinkPreparationTime.perDrink}")
    private Integer drinkPreparationTime;

    private int secondPassed = 0;
    Timer myTimer  = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {

                secondPassed--;

        }
    };

    public Boolean orderDrink(OrderDrinkDTO orderDrinkDTO) throws InterruptedException {
          Boolean orderedDrinkWillServed = false;

          if(secondPassed == 0 || secondPassed < 0 ){

              if(secondPassed < 0 ){
                  secondPassed = orderDrinkDTO.getNumberOfCustomer()*drinkPreparationTime;
                  myTimer.scheduleAtFixedRate(new TimerTask() {
                      @Override
                      public void run() {

                          secondPassed--;

                      }
                  },1000,1000);
              }else {
                  secondPassed = orderDrinkDTO.getNumberOfCustomer()*drinkPreparationTime;
                  myTimer.scheduleAtFixedRate(task,1000,1000);
              }

              /*getSecondPassed(secondPassed);*/
              if(DrinkType.BEER.value().equals(orderDrinkDTO.getDrinkType())){
                  noOfServedBEER = noOfServedBEER+orderDrinkDTO.getNumberOfCustomer();
                  noOfCustomerServed = noOfCustomerServed+orderDrinkDTO.getNumberOfCustomer();
                  orderedDrinkWillServed = true;
              }else if (DrinkType.DRINK.value().equals(orderDrinkDTO.getDrinkType())){
                  noOfServedDRINK = noOfServedDRINK+orderDrinkDTO.getNumberOfCustomer();
                  noOfCustomerServed = noOfCustomerServed+orderDrinkDTO.getNumberOfCustomer();
                  orderedDrinkWillServed = true;
              }
          }else {
              orderedDrinkWillServed = false;
          }

        return orderedDrinkWillServed;
    }

    public CustomerAndServedDrinkListDTO getCustomerAndServedDrinkList(){
        CustomerAndServedDrinkListDTO customerAndServedDrinkListDTO = new CustomerAndServedDrinkListDTO();
        customerAndServedDrinkListDTO.setNoOfCustomerServed(noOfCustomerServed);
        customerAndServedDrinkListDTO.setNoOfServedBEER(noOfServedBEER);
        customerAndServedDrinkListDTO.setNoOfServedDRINK(noOfServedDRINK);

        return customerAndServedDrinkListDTO;
    }


    /*private int getSecondPassed (int secondPassed) throws InterruptedException {

        while (secondPassed != 0){
            secondPassed--;
            Thread.sleep(1000);
        }
        return secondPassed;
    }*/
}
