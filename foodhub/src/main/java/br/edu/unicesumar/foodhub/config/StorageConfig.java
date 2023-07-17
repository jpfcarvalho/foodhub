package br.edu.unicesumar.foodhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class StorageConfig {

	private static String ACCESSKEY = "AKIA2XVFCNPMYXFL4AYX";

	private static String ACCESSSECRET = "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";

	private static String REGION = "sa-east-1";

	@Bean
	public AmazonS3 s3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY, ACCESSSECRET);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(REGION).build();
	}

}
