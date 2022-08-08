package com.springboot.eu.service;

import java.util.List;

import com.springboot.eu.entity.Alta;


public interface IExcelDataService {

	List<Alta> getExcelDataAsList();
	
	int saveExcelData(List<Alta> altas);
}
