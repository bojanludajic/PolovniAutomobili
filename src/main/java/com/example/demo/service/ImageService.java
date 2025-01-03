package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class ImageService {

	public byte[] resizeAndRecompressImage(
			MultipartFile file,
			int width,
			int height,
			double quality
			) throws IOException {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Thumbnails.of(file.getInputStream())
				.size(width, height)
				.outputQuality(quality)
				.toOutputStream(outputStream);
		
		return outputStream.toByteArray();
		
	}
	
}
