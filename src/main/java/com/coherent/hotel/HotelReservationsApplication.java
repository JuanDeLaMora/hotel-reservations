package com.coherent.hotel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Hotel Reservations", version = "1.0", description = "Hotel Reservations Service API",
		contact = @Contact(name = "Juan De La Mora", url = "https://github.com/JuanDeLaMora", email = "JuanDeLaMora@outlook.com")))
public class HotelReservationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReservationsApplication.class, args);
	}

}
