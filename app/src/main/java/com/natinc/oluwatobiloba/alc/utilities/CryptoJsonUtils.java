package com.natinc.oluwatobiloba.alc.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class CryptoJsonUtils {

    /**
     * @return LinkedHashMap of data in key value pair
     */
    public static LinkedHashMap<String, LinkedHashMap<String, Double>> getSimpleDataFromJson(String cryptoJsonString) throws JSONException {

        LinkedHashMap<String, LinkedHashMap<String, Double>> map = new LinkedHashMap<>(); // Initialise the map

        JSONObject jsonObject = new JSONObject(cryptoJsonString); // Initialise the jsonObject

        Iterator<String> keys = jsonObject.keys(); // Iterator keys used to iterate through the object


        while (keys.hasNext()) {
            String key = keys.next();
            JSONObject value = jsonObject.getJSONObject(key);
            JSONObject innerObject = new JSONObject(value.toString());
            LinkedHashMap<String, Double> innerMap = new LinkedHashMap<>();

            Iterator<String> innerKeys = innerObject.keys();

            while (innerKeys.hasNext()) {
                String innerKey = innerKeys.next();
                double innerValue = innerObject.getDouble(innerKey);
                innerMap.put(innerKey, innerValue);
            }
            map.put(key, innerMap);
        }

        return map; // returning the data
    }
}
