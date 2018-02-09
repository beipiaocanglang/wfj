package com.wdzsj.cmn.feign;

import java.lang.annotation.Annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import feign.Client;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

public class FeignServiceConfig implements BeanDefinitionRegistryPostProcessor {
	private String basePackage;
	private Class<? extends Annotation> annotationClass;
	private Class<?> markerInterface;

	// feign
	private Client client = new Client.Default(null, null);
	private Encoder encoder = new Encoder.Default();
	private Decoder decoder = new Decoder.Default();
	private ErrorDecoder errorDecoder = new ErrorDecoder.Default();
	private Retryer retryer = new Retryer.Default();
	private int connectTimeoutMillis = 10000;
	private int readTimeoutMillis = 10000;

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public void setMarkerInterface(Class<?> markerInterface) {
		this.markerInterface = markerInterface;
	}

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

	}

	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		ClassPathFeignServiceScanner scanner = new ClassPathFeignServiceScanner(registry);
		scanner.setAnnotationClass(this.annotationClass);
		scanner.setMarkerInterface(this.markerInterface);
		scanner.setClient(client);
		scanner.setEncoder(encoder);
		scanner.setDecoder(decoder);
		scanner.setErrorDecoder(errorDecoder);
		scanner.setRetryer(retryer);
		scanner.setConnectTimeoutMillis(connectTimeoutMillis);
		scanner.setReadTimeoutMillis(readTimeoutMillis);
		scanner.registerFilters();
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	public void setBasePackage(String basePackage) {
		this.basePackage = basePackage;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}

	public void setDecoder(Decoder decoder) {
		this.decoder = decoder;
	}

	public void setErrorDecoder(ErrorDecoder errorDecoder) {
		this.errorDecoder = errorDecoder;
	}

}
