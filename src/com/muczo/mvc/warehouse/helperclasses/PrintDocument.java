package com.muczo.mvc.warehouse.helperclasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;

import com.muczo.mvc.warehouse.blueprint.Document;
import com.muczo.mvc.warehouse.db.DbUtil;

public class PrintDocument {

	public static DataSource dataSource;

	public static Workbook printDocument(Document theDocument) {

		Workbook wb = null;
		try {

			// int id = Integer.parseInt(docId);
			String customer = theDocument.getCustomer();
			String reciepient = theDocument.getReciepient();
			int noOfDoc = theDocument.getNoOfDoc();
			String date = theDocument.getDate();
			String product1 = theDocument.getProduct1();
			int qty1 = theDocument.getQty1();
			String info = theDocument.getInfo();

			// https://www.tutorialspoint.com/apache_poi_word/apache_poi_word_quick_guide.htm
			// Blank Document with tables
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet("new sheet");

			// double leftMarginInches = sheet.getMargin(Sheet.LeftMargin);
			sheet.setMargin(Sheet.RightMargin, 0.5 /* inches */ );
			sheet.setMargin(Sheet.LeftMargin, 0.8 /* inches */ );
			sheet.setMargin(Sheet.TopMargin, 0.5 /* inches */ );

			sheet.getFooter().equals(null);
			sheet.getHeader().equals(null);
			Row row = sheet.createRow((short) 0);
			Row row2 = sheet.createRow((short) 5);
			Row row3 = sheet.createRow((short) 7);
			Row row4 = sheet.createRow((short) 8);
			Row row5 = sheet.createRow((short) 9);
			Row row6 = sheet.createRow((short) 10);
			Row row7 = sheet.createRow((short) 11);
			Row row8 = sheet.createRow((short) 12);
			Row row9 = sheet.createRow((short) 13);
			Row row10 = sheet.createRow((short) 15);
			
			Cell cell0, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10, cell11, cell12, cell13,
					cell14, cell15, cell16, cell17, cell18, cell19, cell20, cell21;
			cell0 = cell1 = cell2 = cell3 = cell4 = cell5 = cell6 = cell7 = cell8 = cell9 = cell10 = cell11 = cell12 = cell13 = 
					cell14 = cell15 = cell16 = cell17 = cell18 = cell19 = cell20 = cell21 = null;
			
			CellRangeAddress cellRangeAddress1, cellRangeAddress2, cellRangeAddress3, cellRangeAddress4,
					cellRangeAddress5, cellRangeAddress6, cellRangeAddress7, cellRangeAddress8, cellRangeAddress9,
					cellRangeAddress10, cellRangeAddress11, cellRangeAddress12, cellRangeAddress13, cellRangeAddress14,
					cellRangeAddress15, cellRangeAddress16, cellRangeAddress17, cellRangeAddress18, cellRangeAddress19,
					cellRangeAddress20, cellRangeAddress21;
			cellRangeAddress1 = cellRangeAddress2 = cellRangeAddress3 = cellRangeAddress4 = 
					cellRangeAddress5 = cellRangeAddress6 = cellRangeAddress7 = cellRangeAddress8 = cellRangeAddress9 = 
					cellRangeAddress10 = cellRangeAddress11 = cellRangeAddress12 = cellRangeAddress13 = cellRangeAddress14 = 
					cellRangeAddress15 = cellRangeAddress16 = cellRangeAddress17 = cellRangeAddress18 = cellRangeAddress19 = 
					cellRangeAddress20 = cellRangeAddress21 = null;

			Cell cell = row.createCell((short) 0);
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			//cell.setCellStyle(cellStyle);
			cell.setCellValue("KP-Karton K. Piekarniak Sp. J. \n" + "Kobylin 35, 05-600 Grójec \n"
					+ " NIP 797 18 60 878 \n " + "tel. 502 04 04 05");
			cellRangeAddress1 = new CellRangeAddress(0, // first row (0-based)
					3, // last row (0-based)
					0, // first column (0-based)
					2 // last column (0-based)
			);
			sheet.addMergedRegion(cellRangeAddress1);
			borderToRegion(cellRangeAddress1, sheet, wb);

			cell2 = row.createCell((short) 3);
			CellStyle cellStyle2 = wb.createCellStyle();
			cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cell2.setCellStyle(cellStyle2);
			cell2.setCellValue("Nabywca: \n" + customer);
			cellRangeAddress2 = new CellRangeAddress(0, 3, 3, 5);
			sheet.addMergedRegion(cellRangeAddress2);
			borderToRegion(cellRangeAddress2, sheet, wb);

			cell0 = row.createCell((short) 6);
			CellStyle cellStyle0 = wb.createCellStyle();
			cellStyle0.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle0.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			cellStyle0.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cell0.setCellStyle(cellStyle0);
			cell0.setCellValue("WZ " + noOfDoc + "  Dnia " + date);
			CellRangeAddress cellRangeAddress0 = new CellRangeAddress(0, 3, 6, 9);
			sheet.addMergedRegion(cellRangeAddress0);
			borderToRegion(cellRangeAddress0, sheet, wb);

			cell5 = row2.createCell((short) 0);
		//	cell5.setCellStyle(cellStyle);
			try {
				cell5.setCellValue("Odbiorca: " + selectQuery(reciepient, "name") + "; "
						+ selectQuery(reciepient, "address") + "; " + selectQuery(reciepient, "telephone"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cellRangeAddress5 = new CellRangeAddress(5, 5, 0, 9);
			sheet.addMergedRegion(cellRangeAddress5);
			borderToRegion(cellRangeAddress5, sheet, wb);

			cell6 = row3.createCell((short) 0);
		//	cell6.setCellStyle(cellStyle);
			cell6.setCellValue("1. " + product1);
			cellRangeAddress6 = new CellRangeAddress(7, 7, 0, 3);
			sheet.addMergedRegion(cellRangeAddress6);
			borderToRegion(cellRangeAddress6, sheet, wb);

			cell15 = row3.createCell((short) 4);
			cell15.setCellValue("szt. " + qty1);
			cellRangeAddress15 = new CellRangeAddress(7, 7, 4, 5);
			sheet.addMergedRegion(cellRangeAddress15);
			borderToRegion(cellRangeAddress15, sheet, wb);

			cell14 = row10.createCell((short) 0);
		//	cell14.setCellStyle(cellStyle);
			cell14.setCellValue("Wystawi³:                                                              Odebra³: ");
			cellRangeAddress14 = new CellRangeAddress(15, 15, 0, 9);
			sheet.addMergedRegion(cellRangeAddress14);
			borderToRegion(cellRangeAddress14, sheet, wb);

			cell13 = row3.createCell((short) 6);
		//	cell13.setCellStyle(cellStyle);
			cell13.setCellValue(info);
			CellStyle cellStyle3 = wb.createCellStyle();
			cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cell13.setCellStyle(cellStyle3);
			cellRangeAddress13 = new CellRangeAddress(7, 13, 6, 9);
			sheet.addMergedRegion(cellRangeAddress13);
		//	borderToRegion(cellRangeAddress13, sheet, wb);

			createSheetCell(cell7, cell16, row4, theDocument.getProduct2(), theDocument.getQty2(),
					cellRangeAddress7, cellRangeAddress16, "2. ", theDocument, sheet, wb, cellStyle, 8, 
					8, 0, 3, 8, 8, 4, 5, 0, 4);
			
			createSheetCell(cell8, cell17, row5, theDocument.getProduct3(), theDocument.getQty3(),
					cellRangeAddress8, cellRangeAddress17, "3. ", theDocument, sheet, wb, cellStyle, 9, 
					9, 0, 3, 9, 9, 4, 5, 0, 4);
			
			createSheetCell(cell9, cell18, row6, theDocument.getProduct4(), theDocument.getQty4(),
					cellRangeAddress9, cellRangeAddress18, "4. ", theDocument, sheet, wb, cellStyle, 10, 
					10, 0, 3, 10, 10, 4, 5, 0, 4);
			
			createSheetCell(cell10, cell19, row7, theDocument.getProduct5(), theDocument.getQty5(),
					cellRangeAddress10, cellRangeAddress19, "5. ", theDocument, sheet, wb, cellStyle, 11, 
					11, 0, 3, 11, 11, 4, 5, 0, 4);
			
			createSheetCell(cell11, cell20, row8, theDocument.getProduct6(), theDocument.getQty6(),
					cellRangeAddress11, cellRangeAddress20, "6. ", theDocument, sheet, wb, cellStyle, 12, 
					12, 0, 3, 12, 12, 4, 5, 0, 4);
			
			createSheetCell(cell12, cell21, row9, theDocument.getProduct7(), theDocument.getQty7(),
					cellRangeAddress12, cellRangeAddress21, "7. ", theDocument, sheet, wb, cellStyle, 13, 
					13, 0, 3, 13, 13, 4, 5, 0, 4);
	
		} catch (IndexOutOfBoundsException eb) {
			JOptionPane.showMessageDialog(new JFrame(), "Mark some document for printing", "Printing error",
					JOptionPane.ERROR_MESSAGE);
		}

		return wb;

	}

	private static void createSheetCell(Cell cell, Cell cell2, Row row, String product, int qty,
			CellRangeAddress cellRangeAddress, CellRangeAddress cellRangeAddress2, String noOfProduct,
			Document theDocument, Sheet sheet, Workbook wb, CellStyle cellStyle, int x1, int x2, int x3, int x4, int y1,
			int y2, int y3, int y4, int row1, int row2) {

		if (qty > 0) {

			cell = row.createCell((short) row1);
		//	cell.setCellStyle(cellStyle);
			cell.setCellValue(noOfProduct + product);
			cellRangeAddress = new CellRangeAddress(x1, x2, x3, x4);
			sheet.addMergedRegion(cellRangeAddress);
			borderToRegion(cellRangeAddress, sheet, wb);

			cell2 = row.createCell((short) row2);
			cell2.setCellValue("szt. " + qty);
			cellRangeAddress2 = new CellRangeAddress(y1, y2, y3, y4);
			sheet.addMergedRegion(cellRangeAddress2);
			borderToRegion(cellRangeAddress2, sheet, wb);
			
		} else {
			cell = row.createCell((short) row1);
		//	cell.setCellStyle(cellStyle);
			cell.setCellValue(noOfProduct);
		//	cellRangeAddress = new CellRangeAddress(x1, x2, x3, x4);
		//	sheet.addMergedRegion(cellRangeAddress);
		}

	}

	// method helps generating doc for printing
	private static void borderToRegion(CellRangeAddress cellRangeAddress, Sheet sheet, Workbook wb) {

		// RegionUtil.setBorderTop(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		// RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		// RegionUtil.setBorderRight(CellStyle.BORDER_THIN, cellRangeAddress, sheet,
		// wb);
		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);

	}

	// select any column for choosen reciepient
	private static String selectQuery(String name, String column) throws SQLException {

		String ret;
		String sql = "select " + column + " from reciepients where name ='" + name + "'";

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet results = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			myStmt = myConn.prepareStatement(sql);

			results = myStmt.executeQuery();
			results.next();
			ret = results.getString(column);
		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, results);

		}

		return ret;

	}

}
