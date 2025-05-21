package com.maurodegaspari.gerador.qrcode.infrastructure;

import org.springframework.beans.factory.annotation.Value;

import com.maurodegaspari.gerador.qrcode.ports.StoregePort;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3StoregeAdpter implements StoregePort {

	private final S3Client s3Client;
	private final String bucketName;
	private final String region;
	
	
	public S3StoregeAdpter(@Value("${aws.s3.region}") String region,
						   @Value("${aws.s3.bucket.name}") String bucketName) {
		
		this.region = region;
		this.bucketName = bucketName;
		this.s3Client = S3Client.builder()
				.region(Region.of(this.region))
				.build();
		
	}
	
	@Override
	public String uploadFiles(byte[] fileData, String fileName, String contentType) {
		
		PutObjectRequest putObjectRequest = PutObjectRequest.builder()
				.bucket(bucketName)
				.key(fileName)
				.contentType(contentType)
				.build();
		
		s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));
		
		return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName,region, fileData);
	}
}
