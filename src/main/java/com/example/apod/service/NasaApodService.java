package com.example.apod.service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.apod.cache.TtlCache;
import com.example.apod.model.ApodResponse;

import jakarta.annotation.PostConstruct;
	@Service
	public class NasaApodService {

	    @Value("${nasa.apod.base-url}")
	    private String apodBaseUrl;

	    @Value("${nasa.api.key}")
	    private String apiKey;

	    //@Value("${apod.cache.ttl-ms}")
	    @Value("${apod.cache.ttlMillis}")
        private long ttlMillis;

//	    @Value("${apod.cache.max-size}")
//	    private int maxSize;
	    @Value("${apod.cache.maxSize}")
	    private int maxSize;


	    private TtlCache<String, Object> cache;
	    private RestTemplate restTemplate;

	    @PostConstruct
	    public void init() {
	        this.cache = new TtlCache<>(ttlMillis, maxSize);
	        this.restTemplate = new RestTemplate();
	    }

	    private <T> T getFromNasa(Map<String, String> params, Class<T> responseType) {
	        String cacheKey = params.toString();

	        @SuppressWarnings("unchecked")
	        T cached = (T) cache.get(cacheKey);
	        if (cached != null) {
	            return cached;
	        }

	        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apodBaseUrl)
	                .queryParam("api_key", apiKey);
	        params.forEach(builder::queryParam);

	        String url = builder.toUriString();
	        ResponseEntity<T> response = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

	        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
	            cache.put(cacheKey, response.getBody());
	            return response.getBody();
	        } else {
	            throw new RuntimeException("NASA APOD API error");
	        }
	    }

	    public ApodResponse getTodayApod() {
	        return getFromNasa(Map.of(), ApodResponse.class);
	    }

	    public ApodResponse getApodByDate(String date) {
	        return getFromNasa(Map.of("date", date), ApodResponse.class);
	    }

	    public List<ApodResponse> getApodRange(String startDate, String endDate) {
	        ApodResponse[] array = getFromNasa(
	                Map.of("start_date", startDate, "end_date", endDate),
	                ApodResponse[].class
	        );
	        return Arrays.asList(array);
	    }

}
