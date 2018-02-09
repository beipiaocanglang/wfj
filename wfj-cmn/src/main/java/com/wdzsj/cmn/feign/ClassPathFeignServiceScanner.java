package com.wdzsj.cmn.feign;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import feign.Client;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;

public class ClassPathFeignServiceScanner extends ClassPathBeanDefinitionScanner {
	private Class<? extends Annotation> defaultAnnotationClass = HttpService.class;
	private Class<? extends Annotation> annotationClass;
	private Class<?> markerInterface;

	// feign
	private Client client;
	private Encoder encoder;
	private Decoder decoder;
	private ErrorDecoder errorDecoder;
	private Retryer retryer;
	private int connectTimeoutMillis;
	private int readTimeoutMillis;

	public ClassPathFeignServiceScanner(BeanDefinitionRegistry registry) {
		super(registry, false);
	}

	public void registerFilters() {
		boolean acceptAllInterfaces = true;

		// if specified, use the given annotation and / or marker interface
		if (this.annotationClass == null) {
			this.annotationClass = defaultAnnotationClass;
		}
		addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
		acceptAllInterfaces = false;

		// override AssignableTypeFilter to ignore matches on the actual marker
		// interface
		if (this.markerInterface != null) {
			addIncludeFilter(new AssignableTypeFilter(this.markerInterface) {
				@Override
				protected boolean matchClassName(String className) {
					return false;
				}
			});
			acceptAllInterfaces = false;
		}

		if (acceptAllInterfaces) {
			// default include filter that accepts all classes
			addIncludeFilter(new TypeFilter() {
				public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
						throws IOException {
					return true;
				}
			});
		}

		// exclude package-info.java
		addExcludeFilter(new TypeFilter() {
			public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
					throws IOException {
				String className = metadataReader.getClassMetadata().getClassName();
				return className.endsWith("package-info");
			}
		});
	}

	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

		for (BeanDefinitionHolder holder : beanDefinitions) {
			GenericBeanDefinition definition = (GenericBeanDefinition) holder.getBeanDefinition();

			if (logger.isDebugEnabled()) {
				logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName() + "' and '"
						+ definition.getBeanClassName() + "' serviceInterface");
			}

			// the service interface is the original class of the bean
			// but, the actual class of the bean is FeignServiceFactory
			definition.getPropertyValues().add("serviceInterface", definition.getBeanClassName());
			definition.getPropertyValues().add("client", client);
			definition.getPropertyValues().add("encoder", encoder);
			definition.getPropertyValues().add("decoder", decoder);
			definition.getPropertyValues().add("errorDecoder", errorDecoder);
			definition.getPropertyValues().add("retryer", retryer);
			definition.getPropertyValues().add("connectTimeoutMillis", connectTimeoutMillis);
			definition.getPropertyValues().add("readTimeoutMillis", readTimeoutMillis);
			definition.setBeanClass(FeignServiceFactory.class);

			if (logger.isDebugEnabled()) {
				logger.debug(
						"Enabling autowire by type for MapperFactoryBean with name '" + holder.getBeanName() + "'.");
			}
			definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
		}

		return beanDefinitions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean checkCandidate(String beanName, BeanDefinition beanDefinition) throws IllegalStateException {
		if (super.checkCandidate(beanName, beanDefinition)) {
			return true;
		} else {
			logger.warn("Skipping FeignServiceFactory with name '" + beanName + "' and '"
					+ beanDefinition.getBeanClassName() + "' serviceInterface"
					+ ". Bean already defined with the same name!");
			return false;
		}
	}

	public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
		this.annotationClass = annotationClass;
	}

	public void setMarkerInterface(Class<?> markerInterface) {
		this.markerInterface = markerInterface;
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
