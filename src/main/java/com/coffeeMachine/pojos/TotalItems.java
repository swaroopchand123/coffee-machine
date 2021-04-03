package com.coffeeMachine.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author swaroop
 * @created 03/04/2021
 */


/*
 * This Class will have total available item details

 *
 * */

@Data
@AllArgsConstructor
public class TotalItems {
    private Map<String, Integer>  totalItemsQuantity;
}
