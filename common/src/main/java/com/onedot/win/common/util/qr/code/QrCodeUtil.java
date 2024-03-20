package com.onedot.win.common.util.qr.code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.onedot.win.common.util.qr.code.exception.QrCodeGenerateException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * QrCodeUtil
 *
 * @author rocky
 * @since 2022/9/29 15:40
 **/
public class QrCodeUtil {

    public static final String PNG = "png";

    public static String generate(String content,int width,int height) {
        HashMap<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8);
        hints.put(EncodeHintType.MARGIN, 2);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix;
        try {
            bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            throw new QrCodeGenerateException(e.getMessage());
        }
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(image, PNG, os);
            return "data:image/png;base64," + Base64.encode(os.toByteArray());
        } catch (IOException e) {
            throw new QrCodeGenerateException(e.getMessage());
        }
    }
}
