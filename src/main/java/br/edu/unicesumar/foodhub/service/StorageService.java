package br.edu.unicesumar.foodhub.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.IllegalBucketNameException;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

	private static String BUCKETNAME = "foodhub-midias";

	@Autowired
	private AmazonS3 s3Client;

	public String uploadFile(MultipartFile file, String restaurante) {

		StringBuilder fileName = new StringBuilder();
		fileName.append("/" + restaurante + "/");
		fileName.append(System.currentTimeMillis() + "_");
		fileName.append(file.getOriginalFilename());

		File fileObj = convertMultiPartFileToFile(file);
		s3Client.putObject(new PutObjectRequest(BUCKETNAME, fileName.toString(), fileObj));
		fileObj.delete();
		return fileName.toString();

	}

	public byte[] downloadFile(String fileName) {

		existsArquivo(fileName);

		S3Object s3Object = s3Client.getObject(BUCKETNAME, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();

		try {
			return IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteFile(String fileName) {
		existsArquivo(fileName);

		s3Client.deleteObject(BUCKETNAME, fileName);

	}

	private void existsArquivo(String fileName) {
		if (!s3Client.doesObjectExist(BUCKETNAME, fileName)) {
			throw new IllegalBucketNameException(String.format("Arquivo não encontrado: %s.", fileName));
		}
	}

	private File convertMultiPartFileToFile(MultipartFile file) {

		File convertedFile = new File(file.getOriginalFilename());

		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error("Erro para converter o arquivo: ", e);
		}
		return convertedFile;

	}

}
