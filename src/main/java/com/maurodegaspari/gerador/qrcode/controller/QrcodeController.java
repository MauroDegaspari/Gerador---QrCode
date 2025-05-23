package com.maurodegaspari.gerador.qrcode.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maurodegaspari.gerador.qrcode.dto.GeracaoRequest;
import com.maurodegaspari.gerador.qrcode.dto.GeracaoResponse;
import com.maurodegaspari.gerador.qrcode.services.QrCodeService;

@RestController
@RequestMapping("/qrcodegerador")
public class QrcodeController {
	
	private QrCodeService service;
	
	public QrcodeController(QrCodeService qrCodeServic) {
		this.service = qrCodeServic;
	}
	
	@PostMapping
	public ResponseEntity<GeracaoResponse> gerador(@RequestBody GeracaoRequest request) {
		try {
			
			GeracaoResponse response = this.service.geradorQrCode(request.texto());
			return ResponseEntity.ok(response);
			
			
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		
	}
}
