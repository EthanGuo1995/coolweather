package com.coolweather.android.util;

import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
	//解析和处理服务器返回的省级数据
	public static boolean handleProvinceResponse(String response) {

		if (!TextUtils.isEmpty(response)) {		//response是否不为空 -- 字符串两种空值的判断
			try {
				JSONArray allProvinces = new JSONArray(response);	//创建JSON字符数组
				for (int i = 0; i < allProvinces.length(); i++) {	//遍历数组
					JSONObject provinceObject = allProvinces.getJSONObject(i);	//获得每个元素
					Province province = new Province();	//新建一个Province对象
					province.setProvinceName(provinceObject.getString("name"));	//将数据库name指向的数据存进去
					province.setProvinceCode(provinceObject.getInt("id"));	//将数据库id指向的数据存进去
					province.save();	//保存
				}
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	//解析和处理服务器返回的市级数据
	public static boolean handleCityResponse(String response, int ProvinceId) {

		if (!TextUtils.isEmpty(response)) {
			try {
				JSONArray allCities = new JSONArray(response);
				for (int i = 0; i < allCities.length(); i++) {
					JSONObject cityObject = allCities.getJSONObject(i);
					City city = new City();
					city.setCityName(cityObject.getString("name"));
					city.setCityCode(cityObject.getInt("id"));
					city.setProvinceId(ProvinceId);
					city.save();
				}
				return  true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	//解析和处理服务器返回的县级数据
	public static boolean handleCountyResponse(String response, int cityId) {

		if (!TextUtils.isEmpty(response)) {
			try {
				JSONArray allCounties = new JSONArray(response);
				for (int i = 0; i < allCounties.length(); i++) {
					JSONObject countyObject = allCounties.getJSONObject(i);
					County county = new County();
					county.setCountyName(countyObject.getString("name"));
					county.setWeatherId(countyObject.getString("weather_id"));
					county.setCityId(cityId);
					county.save();
				}
				return true;
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
