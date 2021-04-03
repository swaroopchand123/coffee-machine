package com.coffeeMachine.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author swaroop
 * @created 03/04/2021
 */


/*
 * This Class will have coffee machine details
 * like no of outlets
 * totalItems
 * totalItems
 *
 * */
@Data
@AllArgsConstructor
public class CoffeeMachine {
    private int outlets;
    private TotalItems totalItems;
    private Beverages beverages;
}
