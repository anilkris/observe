package com.myc.observe;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporterBuilder;
// import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.trace.export.SpanExporter;

@SpringBootApplication
public class ObserveApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObserveApplication.class, args);
	}
	/*
	 * 
	 * @Bean
	 * SpanExporter otlpHttpSpanExporter(@Value("${tracing.url}") String url) {
	 * return OtlpHttpSpanExporter
	 * .builder()
	 * .addHeader("Content-Type", "application/x-protobuf")
	 * .setEndpoint(url)
	 * .build();
	 * }
	 * 
	 */

	// OtlpAutoConfiguration use HTTP by default, we update it to use GRPC
	// https://github.com/spring-projects/spring-boot/blob/main/spring-boot-project/spring-boot-actuator-autoconfigure/src/main/java/org/springframework/boot/actuate/autoconfigure/tracing/otlp/OtlpAutoConfiguration.java
	@Bean
	public OtlpHttpSpanExporter otlpExporter(@Value("${tracing.url}") String url) {

		return OtlpHttpSpanExporter.builder()
				.setEndpoint(url)
				.build();

	}
}
