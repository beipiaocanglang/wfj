package com.wdzsj.mgr.castor.util;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.xml.sax.InputSource;

@SuppressWarnings("unchecked")
public class CastorUtils {
	
	private ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	
	private CastorUtils(){}
	
	public static CastorUtils getInstance(){
		return new CastorUtils();
	}
	
	public <T> List<T> listByCastor(Class<T> clazz, String mappingFilePath, String confFilePath){
		try {
			List<T> configs = new ArrayList<T>();
			
			Resource[] resources = resolver.getResources(confFilePath);
			for (Resource resource : resources) {
				InputSource confIS = new InputSource(new InputStreamReader(resource.getInputStream(), MginfConstants.CHARSET));
				Unmarshaller unmarshaller = new Unmarshaller(clazz);
				//禁用ID校验，castor对于局部对象的Identity属性有误
				unmarshaller.setProperty("org.exolab.castor.xml.lenient.id.validation", "true");
				unmarshaller.setMapping(getCasterMapping(mappingFilePath));
				T config = (T)unmarshaller.unmarshal(confIS);
				configs.add(config);
			}
			
			return configs;
		} catch (Exception e) {
			throw new RuntimeException("mappingFilePath:"+mappingFilePath+", confFilePath:"+confFilePath, e);
		} 
	}
	
	public <T> T loadByCastor(Class<T> clazz, String mappingFilePath, String confFilePath){
		try {
			InputStream confInput = CastorUtils.class.getResourceAsStream(confFilePath);
			InputSource confIS = new InputSource(new InputStreamReader(confInput, MginfConstants.CHARSET));
			Unmarshaller unmarshaller = new Unmarshaller(clazz);
			//禁用ID校验，castor对于局部对象的Identity属性有误
			unmarshaller.setProperty("org.exolab.castor.xml.lenient.id.validation", "true");
			unmarshaller.setMapping(getCasterMapping(mappingFilePath));
			T config = (T)unmarshaller.unmarshal(confIS);
			
			return config;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("mappingFilePath:"+mappingFilePath+", confFilePath:"+confFilePath, e);
		} 
	}
	
	public <T> void updateByCastor(T object, String mappingFilePath, String confFilePath){
		try {
			File confFile = ResourceUtils.getFile(confFilePath);
			Marshaller marshaller = new Marshaller(new FileWriter(confFile)); 			
			marshaller.setMapping(getCasterMapping(mappingFilePath));
			marshaller.marshal(object);
		} catch (Exception e) {
			throw new RuntimeException("mappingFilePath:"+mappingFilePath+", confFilePath:"+confFilePath, e);
		}
	}
	
	private Mapping getCasterMapping(String mappingFilePath) throws UnsupportedEncodingException{
		InputStream mappingInput = CastorUtils.class.getResourceAsStream(mappingFilePath);
		InputSource mappingIS = new InputSource(new InputStreamReader(mappingInput, MginfConstants.CHARSET));
		Mapping mapping = new Mapping();
		mapping.loadMapping(mappingIS);
		return mapping;
	}
}
