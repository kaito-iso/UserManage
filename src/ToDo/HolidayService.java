package com.example.demo.service;

import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HolidayService {

	/**
	 * Web Apiにデータをリクエストするもの
	 * 
	 * .get() : 取ってきて
	 * .uri(...) : ここに
	 * .retrieve() : 実行して
	 * .body(...) : この型に変換して
	 * 
	 */
	private final RestClient restClient;

	// コンストラクタでRestClientを初期化
	public HolidayService(RestClient.Builder builder) {
		this.restClient = builder.baseUrl("https://holidays-jp.github.io/api/v1").build();
	}

	/**
	 * 日本の祝日一覧を取得し、特定の日の名前を返す
	 */
	public String getHolidayName(String date) {

		try {

			var typeRef = new ParameterizedTypeReference<Map<String, String>>() {};

			Map<String, String> holidays = restClient.get()
			        .uri("/date.json")
			        .retrieve()
			        .body(typeRef);
			
			System.out.println(holidays);

			return (holidays != null) ? holidays.get(date) : null;

		} catch (Exception e) {
			e.printStackTrace();
			return "取得失敗";
		}
	}
}