package com.wdzsj.cmn.feign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import feign.Client;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface FeignOptions {
	Class<? extends Encoder> encoder() default feign.codec.Encoder.Default.class;

	Class<? extends Decoder> decoder() default feign.codec.Decoder.Default.class;

	Class<? extends Client> client() default feign.Client.Default.class;

	Class<? extends ErrorDecoder> errorDecoder() default feign.codec.ErrorDecoder.Default.class;

	Class<? extends Retryer> retryer() default feign.Retryer.Default.class;

	int connectTimeoutMillis() default 10000;

	int readTimeoutMillis() default 10000;
}
