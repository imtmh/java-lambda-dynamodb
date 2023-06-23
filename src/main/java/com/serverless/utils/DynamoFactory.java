package com.serverless.utils;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoFactory {

    private static final String ACCESS_KEY = System.getenv("DYNAMO_ACCESS_KEY");
    private static final String SECRET_KEY = System.getenv("DYNAMO_SECRET_KEY");
    private static final String REGION = System.getenv("APP_AWS_REGION");

    private static AmazonDynamoDB dynamoInstance = null;

    private DynamoFactory() {}

    public static AmazonDynamoDB getDynamoInstance() {
        if (dynamoInstance == null) {
            AmazonDynamoDBClientBuilder amazonDynamoDBClientBuilder = AmazonDynamoDBClientBuilder
                    .standard().withCredentials(getAwsCredentials()).withRegion(REGION);
            dynamoInstance = amazonDynamoDBClientBuilder.build();
        }
        return dynamoInstance;
    }

    private static AWSCredentialsProvider getAwsCredentials() {
        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY));
    }
}
