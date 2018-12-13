package com.coolweather.android.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {

	public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(address).build();	//请求网络
		client.newCall(request).enqueue(callback);	//回调，得到返回的数据
	}
}
