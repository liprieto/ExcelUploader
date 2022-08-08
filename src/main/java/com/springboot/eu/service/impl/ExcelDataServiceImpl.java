package com.springboot.eu.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.springboot.eu.entity.Alta;
import com.springboot.eu.repository.AltaRepository;
import com.springboot.eu.service.IExcelDataService;

@Service
public class ExcelDataServiceImpl implements IExcelDataService {

	@Value("${app.upload.file:${user.home}}")
	public String EXCEL_FILE_PATH;

	@Autowired
	AltaRepository altaRepo;

	Workbook workbook;

	public List<Alta> getExcelDataAsList() {

		List<String> list = new ArrayList<String>();

		// DataFormatter para formatear y obtener el valor de cada celda como String
		DataFormatter dataFormatter = new DataFormatter();

		// Crear Workbook
		try {
			workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}

		// Recuperar el número de hojas en el Workbook
		System.out.println("-Workbook contiene '" + workbook.getNumberOfSheets() + "' Hojas-");

		// Obtener la hoja en el índice cero
		Sheet sheet = workbook.getSheetAt(0);

		// Obtener el número de columnas en la hoja
		int noOfColumns = sheet.getRow(0).getLastCellNum();
		System.out.println("-Sheet contiene '" + noOfColumns + "' columnas-");

		// Usando de for-each para iterar sobre las filas y columnas
		for (Row row : sheet) {
			for (Cell cell : row) {
				String cellValue = dataFormatter.formatCellValue(cell);
				list.add(cellValue);
			}
		}

		// Llenando datos de Excel y creando una lista como List<Alta>
		List<Alta> altasList = createList(list, noOfColumns);

		// Cerrando Workbook
		/*try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		return altasList;
	}

	private List<Alta> createList(List<String> excelData, int noOfColumns) {

		ArrayList<Alta> altasList = new ArrayList<Alta>();

		int i = noOfColumns;
		do {
			Alta altas = new Alta();

			altas.setNombre(excelData.get(i));
			altas.setDni(Double.valueOf(excelData.get(i + 1)));
			altas.setTelefono(excelData.get(i + 2));
			altas.setApellido(excelData.get(i + 3));
			//inv.setDireccion(excelData.get(i + 4));

			altasList.add(altas);
			i = i + (noOfColumns);

		} while (i < excelData.size());
		return altasList;
	}

	@Override
	public int saveExcelData(List<Alta> altas) {
		altas = altaRepo.saveAll(altas);
		return altas.size();
	}

}