package com.qrcodelover.controller;

import com.qrcodelover.domain.QrCodeCreator;
import com.qrcodelover.domain.QrCodeZXing;
import com.qrcodelover.dto.QrCodeDataRequestDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/qrcode")
public class QrCreatorController {

    @GetMapping(value = "/nayuki/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generator(@PathVariable("barcode") String barcode) throws Exception {
        BufferedImage image = QrCodeCreator.generateQrcode(barcode);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @GetMapping(value = "/zxing/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> generatorZxing(@PathVariable("barcode") String barcode) throws Exception {
        BufferedImage image = QrCodeZXing.generateQRCode(barcode);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @PostMapping("/nayuki/gen")
    public ResponseEntity<BufferedImage> generateQrCode(@RequestBody QrCodeDataRequestDto request) throws Exception {
        BufferedImage image = QrCodeCreator.generateQrcode(request.data());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @PostMapping("/zxing/gen")
    public ResponseEntity<BufferedImage> generateQrCodeZxing(@RequestBody QrCodeDataRequestDto request) throws Exception {
        BufferedImage image = QrCodeZXing.generateQRCode(request.data());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }

    @PostMapping("/zxing/genImg")
    public ResponseEntity<BufferedImage> generateQrCodeZxingWithImg(@RequestBody QrCodeDataRequestDto request) throws Exception {
        BufferedImage overlayImage = ImageIO.read(new ClassPathResource("logo.png").getInputStream());
        BufferedImage image = QrCodeZXing.generateQRCodeWithCenterImage(request.data(), overlayImage, 0.2f, 1.0f);

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
