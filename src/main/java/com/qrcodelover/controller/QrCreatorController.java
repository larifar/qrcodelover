package com.qrcodelover.controller;

import com.qrcodelover.domain.QrCodeCreator;
import com.qrcodelover.dto.QrCodeDataRequestDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/qrcode")
public class QrCreatorController {

    @GetMapping(value = "/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generator(@PathVariable("barcode") String barcode) throws Exception {
        BufferedImage image = QrCodeCreator.generateQrcode(barcode);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @PostMapping("/gen")
    public ResponseEntity<BufferedImage> generateQrCode(@RequestBody QrCodeDataRequestDto request) throws Exception {
        BufferedImage image = QrCodeCreator.generateQrcode(request.data());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
