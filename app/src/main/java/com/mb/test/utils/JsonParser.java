package com.mb.test.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class JsonParser {

	public static Gson gson = new Gson();

	@SuppressWarnings("hiding")
	public static <T> T deserializeByJson(String data, Type type) {
		if (StringUtils.isSpace(data)) {
			return gson.fromJson(data, type);
		}
		return null;
	}

	/**
	 *  String 转对象
	 * @param data
	 * @param clz
	 * @param <T>
     * @return
     */
	@SuppressWarnings("hiding")
	public static <T> T deserializeByJson(String data, Class<T> clz) {
		if ((StringUtils.isSpace(data))){
			return gson.fromJson(data, clz);
		}
		return null;
	}

	/**
	 * 对象转String
	 * @param t
	 * @param <T>
	 * @return
     */

	@SuppressWarnings("hiding")
	public static <T> String serializeToJson(T t) {
		if (t == null) {
			return "";
		}
		return gson.toJson(t);
	}

	@SuppressWarnings("hiding")
	public static <T> String serializeToJsonForGsonBuilder(T t) {
		if (t == null) {
			return "";
		}
		Gson gs = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gs.toJson(t);
	}

}
