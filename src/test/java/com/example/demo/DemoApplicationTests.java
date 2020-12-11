package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.repositories.ReservationRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

/*
 * Coded by Ammad, Rasmus
 */

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	ReservationRepo reservationRepo;

	// Tests that the total price for a reservation is calculated correct
	@Test
	void calculateTotalPriceTest(){
		Address address = new Address(1, "2", "Østerbrogade", "2", "2100" );
		ZipCode zipCode = new ZipCode("2100", "København", "Danmark");
		Motorhome motorhome = new Motorhome(1, "Big", "Mercedes", "Sprinter", 200,
				500, "123123");

		Customer customer = new Customer(1, "Michael", "Berko", "292929",
				"2020-02-02", "123123", address.getId());

		Reservation reservation = new Reservation(1, "2020-01-29 10:00:00", "2020-01-30 12:00:00",
				0, "Lavsæson", "credit card", -1,
				customer.getFirst_name(), customer.getLast_name(), motorhome.getBrand_name(), motorhome.getModel(),
				motorhome.getType(), motorhome.getId(), customer.getId());

		double totalPrice = reservationRepo.calculateTotalPrice(motorhome.getPrice_per_day(), 100,
				"Lavsæson", reservation.getStart_date(), reservation.getEnd_date());

		assertThat(totalPrice).isEqualTo(1070);
	}

	// Checks that the correct season for a given date is found
	@Test
	void calculateSeasonTest(){
		String season = reservationRepo.calculateSeason("2020-05-31 23:59:59");
		String season2 = reservationRepo.calculateSeason("2020-01-31 10:00:00");
		String season3 = reservationRepo.calculateSeason("2020-06-04 15:26:30");
		assertThat(season).isEqualTo("Mellemsæson");
		assertThat(season2).isEqualTo("Lavsæson");
		assertThat(season3).isEqualTo("Højsæson");
		assertThat(false).isTrue();
	}


}