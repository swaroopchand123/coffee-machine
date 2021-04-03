package com.coffeeMachine.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

/**
 * @author swaroop
 * @created 03/04/2021
 */

/*
* This Class will have Beverages and their ingredients and quantity mapping
*
* */
@Data
@AllArgsConstructor
public class Beverages {
    private Map<String, Map<String, Integer>> beverages;
}
