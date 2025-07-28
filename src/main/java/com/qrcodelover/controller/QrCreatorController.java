package com.qrcodelover.controller;

import com.qrcodelover.domain.QrCodeCreator;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/qrcode")
public class QrCreatorController {

    @GetMapping(value = "/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generator(@PathVariable("barcode") String barcode) throws Exception {
        return ResponseEntity.ok(QrCodeCreator.generateQrcode(barcode));
    }
}
