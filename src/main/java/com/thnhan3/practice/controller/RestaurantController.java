package com.thnhan3.practice.controller;

import com.thnhan3.practice.model.ReservationRequest;
import com.thnhan3.practice.service.ReservationService;
import jakarta.servlet.ServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class RestaurantController {
    @Autowired
    private  ReservationService reservationService;

    @PostMapping("/api/reservations")
    public ResponseEntity<ReservationRequest> createReservation(@RequestBody ReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(request));
    }

    @PutMapping("/api/reservations/{reservationId}")
    public ResponseEntity<ReservationRequest> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationRequest request, ServletRequest servletRequest) {
       ReservationRequest result = reservationService.update(reservationId, request);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // only themselves can delete their
    @DeleteMapping("/api/reservations/{reservationId}")
    public ResponseEntity<ReservationRequest> deleteReservation(@PathVariable Long reservationId, ServletRequest servletRequest) {
        if (reservationService.delete(reservationId)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/reservations")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationRequest> getAllReservations(@RequestParam(defaultValue = "",required = false, name = "date") String date) {
        if (date.isEmpty()) {
            return reservationService.getAll();
        }

        LocalDate date1 = LocalDate.parse(date);
        return reservationService.getAllByDate(date1);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/reservations/{reservationId}")
    public ResponseEntity<ReservationRequest> getReservation(@PathVariable Long reservationId) {
        ReservationRequest result = reservationService.getInfoById(reservationId);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
