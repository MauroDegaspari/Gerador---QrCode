package com.maurodegaspari.gerador.qrcode.ports;

public interface StoregePort {

	String uploadFiles(byte[] fileData, String fileName, String  contentType);
}
