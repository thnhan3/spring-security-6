package com.thnhan3.practice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thnhan3.practice.model.Reservation;
import com.thnhan3.practice.model.ReservationRequest;
import com.thnhan3.practice.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    public ReservationService(ReservationRepository reservationRepository, EmailService emailService, ObjectMapper objectMapper) {
        this.reservationRepository = reservationRepository;
        this.objectMapper = objectMapper;
        this.emailService = emailService;
    }

    @CacheEvict(value = "reservations", key = "#reservationRequest.getReservationDate()")
    public ReservationRequest createReservation(ReservationRequest reservationRequest) {
            Reservation reservation = new Reservation();
            reservation.setReservationDate(reservationRequest.getReservationDate());
            reservation.setReservationTime(reservationRequest.getReservationTime());
            reservation.setId(null);
            reservation.setCustomerName(reservationRequest.getCustomerName());
            reservation.setNumberOfGuests(reservationRequest.getNumberOfGuests());
            reservation.setSpecialRequests(reservationRequest.getSpecialRequests());
            Reservation savedReservation = reservationRepository.save(reservation);
            // send confirm email
        emailService.sendReservationConfirmationEmail(
                reservationRequest.getCustomerName(),
                "xac nhan dat cho",
                "thong tin chi tiet dat cho cua ban ..."
        );
            return objectMapper.convertValue(savedReservation, ReservationRequest.class);
    }

    // update all field, except id
    public ReservationRequest update
            (Long reservationId, ReservationRequest reservationRequest) {
        Reservation savedReservation = reservationRepository.findById(reservationId).orElse(null);

        if (savedReservation == null) {
            return null;
        }
        savedReservation.setReservationTime(reservationRequest.getReservationTime());
        savedReservation.setCustomerName(reservationRequest.getCustomerName());
        savedReservation.setNumberOfGuests(reservationRequest.getNumberOfGuests());
        savedReservation.setSpecialRequests(reservationRequest.getSpecialRequests());
        savedReservation.setReservationDate(reservationRequest.getReservationDate());
        Reservation savedReservation1 = reservationRepository.save(savedReservation);
        return objectMapper.convertValue(savedReservation1, ReservationRequest.class);
    }

    public boolean delete(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        if (reservation == null) {
            return false;
        }
        reservationRepository.delete(reservation);
        return true;
    }

    public List<ReservationRequest> getAll() {
        return reservationRepository.findAll().stream().map(v -> objectMapper.convertValue(v, ReservationRequest.class)).toList();
    }

    @Cacheable(value = "reservations", key = "#date")
    public List<ReservationRequest> getAllByDate(LocalDate date){
            List<Reservation> reservationRequests = reservationRepository.findAllByDate(date);
            return reservationRequests.stream().map(v -> objectMapper.convertValue(v, ReservationRequest.class)).toList();
    }

    public ReservationRequest getInfoById(Long reservationId)  {
        Reservation reservation = reservationRepository.findById(reservationId).orElse(null);
        return objectMapper.convertValue(reservation, ReservationRequest.class);
    }
}
