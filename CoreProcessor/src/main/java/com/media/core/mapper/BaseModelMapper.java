package com.media.core.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.data.domain.Page;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BaseModelMapper {
	
	private static ModelMapper modelMapper;
	
	public static <T> T mapper(Object srcData, Class<T> destinationClassType) {
		return getInstance().map(srcData, destinationClassType);
	}
	
	public static <T> T mapper(Object srcData, T targetData) {
		getInstance().map(srcData, targetData);
		return targetData;
	}
	
	public static <S, T> List<T> mapList(List<S> source, Class<T> targetClass){
		return source.stream().map(e -> getInstance().map(e, targetClass)).toList();
	}
	
	public static <S, T> Page<T> mapPage(Page<S> source, Class<T> targetClass){
		return source.map(e -> getInstance().map(e, targetClass));
	}
	

	private static ModelMapper getInstance() {
		if(modelMapper == null) {
			modelMapper = new ModelMapper();
			modelMapper.getConfiguration().setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
			modelMapper.getConfiguration().setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
		}
		return modelMapper;
	}
}
