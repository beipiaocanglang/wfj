package com.wdzsj.cmn.feign;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import feign.Client;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface HttpService {
	String domain();
}
