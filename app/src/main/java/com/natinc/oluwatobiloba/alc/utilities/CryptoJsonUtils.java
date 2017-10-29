package com.natinc.oluwatobiloba.alc.utilities;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by oluwatobiloba on 10/23/17.
 */

public class CryptoJsonUtils {

    public static LinkedHashMap<String, LinkedHashMap<String, Double>> getSimpleDataFromJson(String cryptoJsonString) throws JSONException {

        LinkedHashMap<String, LinkedHashMap<String, Double>> map = new LinkedHashMap<String, LinkedHashMap<String, Double>>();

        JSONObject jsonObject = new JSONObject(cryptoJsonString);

        Iterator<String> keys = jsonObject.keys();

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

        return map;
    }
}
