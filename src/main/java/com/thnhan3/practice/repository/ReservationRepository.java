package com.thnhan3.practice.repository;

import com.thnhan3.practice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // jpql java persistent query language
   @Query(value = "select r from Reservation  r where r.reservationDate = :date")
    List<Reservation> findAllByDate(@Param("date") LocalDate date);
}
