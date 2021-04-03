package com.coffeeMachine.service;

import com.coffeeMachine.pojos.CoffeeMachine;

import java.util.Map;
import java.text.MessageFormat;

import static com.coffeeMachine.constants.ApplicationConstants.BEVERAGE_CANNOT_PREPARE;
import static com.coffeeMachine.constants.ApplicationConstants.BEVERAGE_PREPARED;

/**
 * @author swaroop
 * @created 03/04/2021
 */

public class CoffeeMachineService {
    private volatile static CoffeeMachineService coffeeMachineService;

    private CoffeeMachineService() {

    }

    public static CoffeeMachineService getInstance() {
        if (coffeeMachineService == null) {
            synchronized (CoffeeMachineService.class) {
                if (coffeeMachineService == null) {
                    coffeeMachineService = new CoffeeMachineService();
                }
            }
        }
        return coffeeMachineService;
    }

    /*
     * This Method is useful for Process Coffee Machine and prepare beverages from given ingredients
     * @Param coffeeMachine
     *
     * */
    public void process(CoffeeMachine coffeeMachine) {
        Map<String, Integer> totalItemsQuantity = coffeeMachine.getTotalItems().getTotalItemsQuantity();
        Map<String, Map<String, Integer>> beverages = coffeeMachine.getBeverages().getBeverages();
        for (Map.Entry<String, Map<String, Integer>> entry : beverages.entrySet()) {
            String beverage = entry.getKey();
            Map<String, Integer> ingredients = entry.getValue();
            String message = prepareBeverage(beverage, ingredients, totalItemsQuantity);
            System.out.println(message);
        }
    }


    /*
     * This Method is useful for prepare beverage from required ingredients and total available quantity of ingredients
     * @Param beverage - beverage which we need to prepare
     * @Param ingredients - required ingredients to prepare beverage
     * @Param totalItemsQuantity - Total available of each ingredient
     *
     * @Return message - will return message which will represent whether a beverage can prepare or not
     *
     * */

    private String prepareBeverage(String beverage, Map<String, Integer> ingredients, Map<String, Integer> totalItemsQuantity) {
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            String ingredient = entry.getKey();
            Integer quantity = entry.getValue();
            if (!totalItemsQuantity.containsKey(ingredient) || totalItemsQuantity.get(ingredient) < quantity) {
                String msg = MessageFormat.format(BEVERAGE_CANNOT_PREPARE, beverage, ingredient);
                return msg;
            }
            totalItemsQuantity.put(ingredient, totalItemsQuantity.get(ingredient) - quantity);
        }
        String msg = MessageFormat.format(BEVERAGE_PREPARED, beverage);
        return msg;
    }


    /*
     * This Method is useful to refill ingredient
     * @Param ingredient - ingredient which we need to refill
     * @Param quantity - quantity of ingredient will add in total Items Quantity
     * @Param totalItemsQuantity - Total available of each ingredient
     *
     *
     * */

    private void ingredientRefill(String ingredient, Integer quantity, Map<String, Integer> totalItemsQuantity) {
        totalItemsQuantity.put(ingredient, totalItemsQuantity.getOrDefault(ingredient, 0) + quantity);
    }
}
