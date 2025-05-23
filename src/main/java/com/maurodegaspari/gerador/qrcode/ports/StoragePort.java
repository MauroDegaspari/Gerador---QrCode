package com.maurodegaspari.gerador.qrcode.ports;

public interface StoragePort {

	String uploadFiles(byte[] fileData, String fileName, String  contentType);
}
