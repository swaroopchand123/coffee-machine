package com.coffeeMachine.utils;


import com.coffeeMachine.pojos.Beverages;
import com.coffeeMachine.pojos.CoffeeMachine;
import com.coffeeMachine.pojos.TotalItems;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author swaroop
 * @created 03/04/2021
 */


public class InputParser {


    /*
     * This Method is useful for parse Input Json and return CoffeeMachine object
     * @Param json- input json
     *
     * @Return CoffeeMachine
     * */
    public static CoffeeMachine parseInputAndGetDetails(String json) {
        JSONObject obj = new JSONObject(json);
        obj = new JSONObject(obj.get("machine").toString());
        Integer outlets = Integer.parseInt(new JSONObject(obj.get("outlets").toString()).get("count_n").toString());
        Map<String, Object> totalItemsQuantityInputMap = new JSONObject(obj.get("total_items_quantity").toString()).toMap();
        Map<String, Object> beveragesInputMap = new JSONObject(obj.get("beverages").toString()).toMap();
        TotalItems totalItems = new TotalItems(convertIntoIntegerMap(totalItemsQuantityInputMap));
        Beverages beverages = new Beverages(convertIntoMapOfMap(beveragesInputMap));
        return new CoffeeMachine(outlets, totalItems, beverages);
    }

    /*
     * This Method is useful for to convert Map<String, Object> into Map<String, Map<String, Integer>>
     * @Param inputMap- input map
     *
     * @Return Map<String, Map<String, Integer>>
     * */

    private static Map<String, Map<String, Integer>> convertIntoMapOfMap(Map<String, Object> inputMap) {
        Map<String, Map<String, Integer>> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
            String key = entry.getKey();
            Map value = (Map) entry.getValue();
            resultMap.put(key, value);
        }
        return resultMap;
    }


    /*
     * This Method is useful for to convert Map<String, Object> into Map<String,Integer>
     * @Param inputMap- input map
     *
     * @Return Map<String,Integer>
     * */
    private static Map<String, Integer> convertIntoIntegerMap(Map<String, Object> inputMap) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
            String key = entry.getKey();
            Integer value = (Integer) entry.getValue();
            resultMap.put(key, value);
        }
        return resultMap;
    }
}
