package com.example.apod.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.apod.model.ApodResponse;
import com.example.apod.service.NasaApodService;

import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/apod")
@CrossOrigin(origins = "http://localhost:5173") // adjust to your frontend port

public class ApodController {
	private final NasaApodService apodService;

    public ApodController(NasaApodService apodService) {
        this.apodService = apodService;
    }

    // GET /api/apod/today
    @GetMapping("/today")
    public ResponseEntity<ApodResponse> getToday() {
        return ResponseEntity.ok(apodService.getTodayApod());
    }

    // GET /api/apod/date?date=YYYY-MM-DD
    @GetMapping("/date")
    public ResponseEntity<ApodResponse> getByDate(
            @RequestParam
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "date must be YYYY-MM-DD")
            String date
    ) {
        return ResponseEntity.ok(apodService.getApodByDate(date));
    }

    // GET /api/apod/range?start_date=YYYY-MM-DD&end_date=YYYY-MM-DD
    @GetMapping("/range")
    public ResponseEntity<List<ApodResponse>> getRange(
            @RequestParam("start_date")
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "start_date must be YYYY-MM-DD")
            String startDate,
            @RequestParam("end_date")
            @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "end_date must be YYYY-MM-DD")
            String endDate
    ) {
        return ResponseEntity.ok(apodService.getApodRange(startDate, endDate));
    }

    // Health
    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok().body(
                java.util.Map.of("status", "ok", "service", "NASA APOD Explorer")
        );
    }

}
