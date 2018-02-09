package com.wdzsj.cmn.feign;

import org.springframework.beans.factory.FactoryBean;

import feign.Client;
import feign.Feign;
import feign.Request.Options;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

public class FeignServiceFactory<T> implements FactoryBean<T> {
	private Class<T> serviceInterface;
	// feign
	private Client client;
	private Encoder encoder;
	private Decoder decoder;
	private ErrorDecoder errorDecoder;
	private Retryer retryer;
	private int connectTimeoutMillis;
	private int readTimeoutMillis;

	public T getObject() throws Exception {
		HttpService anno = serviceInterface.getAnnotation(HttpService.class);
		FeignOptions optionAnno = serviceInterface.getAnnotation(FeignOptions.class);
		initFeignConfig(optionAnno);
		return Feign.builder().client(client).encoder(encoder).decoder(decoder).errorDecoder(errorDecoder)
				.retryer(retryer).options(new Options(connectTimeoutMillis, readTimeoutMillis))
				.target(serviceInterface, anno.domain());
	}

	public Class<T> getObjectType() {
		return serviceInterface;
	}

	public boolean isSingleton() {
		return true;
	}

	/**
	 * 初始化每个service单独配置的属性，会覆盖掉全局的配置
	 * 
	 * @param anno
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private void initFeignConfig(FeignOptions anno)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (anno.client() != null && feign.Client.Default.class != anno.client()) {
			client = anno.client().newInstance();
		}

		if (anno.encoder() != null && feign.codec.Encoder.Default.class != anno.encoder()) {
			encoder = anno.encoder().newInstance();
		}

		if (anno.decoder() != null && feign.codec.Decoder.Default.class != anno.decoder()) {
			decoder = anno.decoder().newInstance();
		}

		if (anno.errorDecoder() != null && feign.codec.ErrorDecoder.Default.class != anno.errorDecoder()) {
			errorDecoder = anno.errorDecoder().newInstance();
		}

		if (anno.retryer() != null && feign.Retryer.Default.class != anno.retryer()) {
			retryer = anno.retryer().newInstance();
		}

		connectTimeoutMillis = anno.connectTimeoutMillis();
		readTimeoutMillis = anno.readTimeoutMillis();
	}

	public void setServiceInterface(Class<T> serviceInterface) {
		this.serviceInterface = serviceInterface;
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

	public void setRetryer(Retryer retryer) {
		this.retryer = retryer;
	}

	public void setConnectTimeoutMillis(int connectTimeoutMillis) {
		this.connectTimeoutMillis = connectTimeoutMillis;
	}

	public void setReadTimeoutMillis(int readTimeoutMillis) {
		this.readTimeoutMillis = readTimeoutMillis;
	}

}
