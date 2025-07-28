package com.qrcodelover.domain;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class QrCodeZXing {
    public static BufferedImage generateQRCode(String barcodeText) throws Exception {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix =
                barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static BufferedImage generateQRCodeWithCenterImage(String text, BufferedImage overlay, float overlayRatio, float overlayTransparency) throws Exception {

        QRCodeWriter barcodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = barcodeWriter.encode(text, BarcodeFormat.QR_CODE, 300, 300);
        BufferedImage qrCode = MatrixToImageWriter.toBufferedImage(bitMatrix);


        int overlayWidth = Math.round(qrCode.getWidth() * overlayRatio);
        int overlayHeight = Math.round(qrCode.getHeight() * overlayRatio);
        Image scaledOverlay = overlay.getScaledInstance(overlayWidth, overlayHeight, Image.SCALE_SMOOTH);


        BufferedImage combined = new BufferedImage(qrCode.getWidth(), qrCode.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = combined.createGraphics();
        g.drawImage(qrCode, 0, 0, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, overlayTransparency));
        g.drawImage(scaledOverlay, (qrCode.getWidth() - overlayWidth) / 2, (qrCode.getHeight() - overlayHeight) / 2, null);
        g.dispose();

        return combined;
    }
}
