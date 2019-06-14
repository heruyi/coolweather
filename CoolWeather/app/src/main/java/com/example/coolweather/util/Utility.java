package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
//    解析处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String rsp){
        if (!TextUtils.isEmpty(rsp)){
            try {
                JSONArray allProvinces = new JSONArray(rsp);
                for (int i=0;i< allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

//    解析处理服务器返回的市级数据
    public static boolean handleCityResponse(String rsp,int provinceId){
        if (!TextUtils.isEmpty(rsp)){
            try {
                JSONArray allCities = new JSONArray(rsp);
                for (int i=0;i<allCities.length();i++){
                    JSONObject object = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(object.getInt("id"));
                    city.setCityName(object.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

//    处理服务器返回的县级数据
    public static boolean handleCountyResponse(String  rsp,int cityId){
        if (!TextUtils.isEmpty(rsp)){

            try {
                JSONArray allCounties = new JSONArray(rsp);
                for (int i=0;i<allCounties.length();i++){
                    JSONObject object = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(object.getString("name"));
                    county.setWeatherId(object.getString("weather_id"));
                    county.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
