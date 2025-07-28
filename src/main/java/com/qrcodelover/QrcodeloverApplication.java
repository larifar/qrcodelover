package com.qrcodelover;

import com.qrcodelover.domain.QrCodeCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.BufferedImage;

@SpringBootApplication
public class QrcodeloverApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(QrcodeloverApplication.class, args);

		BufferedImage img =QrCodeCreator.generateQrcode("Olá Mundo!");
		System.out.println(img);
	}

}
