package com.maurodegaspari.gerador.qrcode.services;

import java.io.ByteArrayOutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.maurodegaspari.gerador.qrcode.dto.GeracaoResponse;
import com.maurodegaspari.gerador.qrcode.ports.StoragePort;

@Service
public class QrCodeService {

	private final StoragePort storage;
	
	public QrCodeService(StoragePort storagePort) {
		this.storage = storagePort;
	}
	
	
	public GeracaoResponse geradorQrCode (String text) {
		try {
			//todas essas biblioteca vem dentro da biblioteca do Kching da goolge.
			QRCodeWriter code = new QRCodeWriter();
			BitMatrix bitMatrix = code.encode(text, BarcodeFormat.QR_CODE, 200, 200);
			
			ByteArrayOutputStream imagemPng = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix,"PNG", imagemPng);
			byte[] QrDAta =  imagemPng.toByteArray();
			
			String url = storage.uploadFiles(QrDAta, UUID.randomUUID().toString(), "image/png");
			
			return new GeracaoResponse(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;
		
	}
}
