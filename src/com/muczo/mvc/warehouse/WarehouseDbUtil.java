package com.muczo.mvc.warehouse;


import com.muczo.mvc.warehouse.Document;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class WarehouseDbUtil {

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// DECLARATION ZONE //////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private DataSource dataSource;

	public WarehouseDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////// DOCUMENTS ZONE //////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	public List<Document> getDocuments() throws Exception {

		List<Document> documents = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from documents";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String customer = myRs.getString("customer");
				String reciepient = myRs.getString("reciepient");
				int docId = myRs.getInt("docId");
				String date = myRs.getString("date");
				String product1 = myRs.getString("product1");
				int qty1 = myRs.getInt("qty1");
				String product2 = myRs.getString("product2");
				int qty2 = myRs.getInt("qty2");
				String product3 = myRs.getString("product3");
				int qty3 = myRs.getInt("qty3");
				String product4 = myRs.getString("product4");
				int qty4 = myRs.getInt("qty4");
				String product5 = myRs.getString("product5");
				int qty5 = myRs.getInt("qty5");
				String product6 = myRs.getString("product6");
				int qty6 = myRs.getInt("qty6");
				String product7 = myRs.getString("product7");
				int qty7 = myRs.getInt("qty7");
				String info = myRs.getString("info");

				// create new student object
				Document tempDocument = new Document(true, id, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);

				// add it to the list of students
				documents.add(tempDocument);
			}

			return documents;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public List<Document> getCustomerDocuments(String theCustomer) throws Exception {

		List<Document> documents = new ArrayList<>();
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from documents where customer=? ORDER BY id DESC LIMIT 50";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCustomer);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			while (myRs.next()) {

				String customer = myRs.getString("customer");
				String reciepient = myRs.getString("reciepient");
				int id = myRs.getInt("id");
				int docId = myRs.getInt("docId");
				String date = myRs.getString("date");
				String product1 = myRs.getString("product1");
				int qty1 = myRs.getInt("qty1");
				String product2 = myRs.getString("product2");
				int qty2 = myRs.getInt("qty2");
				String product3 = myRs.getString("product3");
				int qty3 = myRs.getInt("qty3");
				String product4 = myRs.getString("product4");
				int qty4 = myRs.getInt("qty4");
				String product5 = myRs.getString("product5");
				int qty5 = myRs.getInt("qty5");
				String product6 = myRs.getString("product6");
				int qty6 = myRs.getInt("qty6");
				String product7 = myRs.getString("product7");
				int qty7 = myRs.getInt("qty7");
				String info = myRs.getString("info");

				// use the documentId during construction
				Document theDocument = new Document(true, id, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);
				documents.add(theDocument);
			}

			return documents;

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addDocument(Document theDocument) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into documents (customer, reciepient, docId, date, product1, qty1, product2, qty2, product3, qty3, "
					+ "product4, qty4, product5, qty5, product6, qty6, product7, qty7, info) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
					+ ", ?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the document
			myStmt.setString(1, theDocument.getCustomer());
			myStmt.setString(2, theDocument.getReciepient());
			myStmt.setInt(3, theDocument.getNoOfDoc());
			myStmt.setString(4, theDocument.getDate());
			myStmt.setString(5, theDocument.getProduct1());
			myStmt.setInt(6, theDocument.getQty1());
			myStmt.setString(7, theDocument.getProduct2());
			myStmt.setInt(8, theDocument.getQty2());
			myStmt.setString(9, theDocument.getProduct3());
			myStmt.setInt(10, theDocument.getQty3());
			myStmt.setString(11, theDocument.getProduct4());
			myStmt.setInt(12, theDocument.getQty4());
			myStmt.setString(13, theDocument.getProduct5());
			myStmt.setInt(14, theDocument.getQty5());
			myStmt.setString(15, theDocument.getProduct6());
			myStmt.setInt(16, theDocument.getQty6());
			myStmt.setString(17, theDocument.getProduct7());
			myStmt.setInt(18, theDocument.getQty7());
			myStmt.setString(19, theDocument.getInfo());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Document getDocument(String theDocumentId) throws Exception {

		Document theDocument = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int documentId;

		try {

			// convert document id to int
			documentId = (int) Integer.parseInt(theDocumentId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from documents where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, documentId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {

				String customer = myRs.getString("customer");
				String reciepient = myRs.getString("reciepient");
				int docId = myRs.getInt("docId");
				String date = myRs.getString("date");
				String product1 = myRs.getString("product1");
				int qty1 = myRs.getInt("qty1");
				String product2 = myRs.getString("product2");
				int qty2 = myRs.getInt("qty2");
				String product3 = myRs.getString("product3");
				int qty3 = myRs.getInt("qty3");
				String product4 = myRs.getString("product4");
				int qty4 = myRs.getInt("qty4");
				String product5 = myRs.getString("product5");
				int qty5 = myRs.getInt("qty5");
				String product6 = myRs.getString("product6");
				int qty6 = myRs.getInt("qty6");
				String product7 = myRs.getString("product7");
				int qty7 = myRs.getInt("qty7");
				String info = myRs.getString("info");

				// use the documentId during construction
				theDocument = new Document(true, documentId, customer, reciepient, docId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);
			} else {
				throw new Exception("Could not find document id: " + documentId);
			}

			return theDocument;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateDocument(Document theDocument) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update documents set customer=?, reciepient=?, docId=?, date=?, product1=?, qty1=?, product2=?, qty2=?, "
					+ "product3=?, qty3=?, product4=?, qty4=?, product5=?, qty5=?, product6=?, qty6=?, product7=?, qty7=?, "
					+ "info=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theDocument.getCustomer());
			myStmt.setString(2, theDocument.getReciepient());
			myStmt.setInt(3, theDocument.getNoOfDoc());
			myStmt.setString(4, theDocument.getDate());
			myStmt.setString(5, theDocument.getProduct1());
			myStmt.setInt(6, theDocument.getQty1());
			myStmt.setString(7, theDocument.getProduct2());
			myStmt.setInt(8, theDocument.getQty2());
			myStmt.setString(9, theDocument.getProduct3());
			myStmt.setInt(10, theDocument.getQty3());
			myStmt.setString(11, theDocument.getProduct4());
			myStmt.setInt(12, theDocument.getQty4());
			myStmt.setString(13, theDocument.getProduct5());
			myStmt.setInt(14, theDocument.getQty5());
			myStmt.setString(15, theDocument.getProduct6());
			myStmt.setInt(16, theDocument.getQty6());
			myStmt.setString(17, theDocument.getProduct7());
			myStmt.setInt(18, theDocument.getQty7());
			myStmt.setString(19, theDocument.getInfo());
			myStmt.setInt(20, theDocument.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deleteDocument(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL delete document
			String sql = "delete from documents " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void printDocument(String docId) throws Exception {

		Document theDocument = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int documentId;

		try {

			// convert document id to int
			documentId = (int) Integer.parseInt(docId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from documents where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, documentId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {

				String customer = myRs.getString("customer");
				String reciepient = myRs.getString("reciepient");
				int localDocId = myRs.getInt("docId");
				String date = myRs.getString("date");
				String product1 = myRs.getString("product1");
				int qty1 = myRs.getInt("qty1");
				String product2 = myRs.getString("product2");
				int qty2 = myRs.getInt("qty2");
				String product3 = myRs.getString("product3");
				int qty3 = myRs.getInt("qty3");
				String product4 = myRs.getString("product4");
				int qty4 = myRs.getInt("qty4");
				String product5 = myRs.getString("product5");
				int qty5 = myRs.getInt("qty5");
				String product6 = myRs.getString("product6");
				int qty6 = myRs.getInt("qty6");
				String product7 = myRs.getString("product7");
				int qty7 = myRs.getInt("qty7");
				String info = myRs.getString("info");

				// use the documentId during construction
				theDocument = new Document(true, documentId, customer, reciepient, localDocId, date, product1, qty1,
						product2, qty2, product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7,
						info);
			} else {
				throw new Exception("Could not find document id: " + documentId);
			}

		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}

		Workbook wb = null;
		try {

			int id = Integer.parseInt(docId);
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
			double leftMarginInches = sheet.getMargin(Sheet.LeftMargin);
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
			CellRangeAddress cellRangeAddress1, cellRangeAddress2, cellRangeAddress3, cellRangeAddress4,
					cellRangeAddress5, cellRangeAddress6, cellRangeAddress7, cellRangeAddress8, cellRangeAddress9,
					cellRangeAddress10, cellRangeAddress11, cellRangeAddress12, cellRangeAddress13, cellRangeAddress14,
					cellRangeAddress15, cellRangeAddress16, cellRangeAddress17, cellRangeAddress18, cellRangeAddress19,
					cellRangeAddress20, cellRangeAddress21;

			Cell cell = row.createCell((short) 0);
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(CellStyle.ALIGN_LEFT);
			cellStyle.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
			cellStyle.setBorderTop(CellStyle.BORDER_THIN);
			cellStyle.setBorderRight(CellStyle.BORDER_THIN);
			cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
			cell.setCellStyle(cellStyle);
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
			cell5.setCellStyle(cellStyle);
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
			cell6.setCellStyle(cellStyle);
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
			cell14.setCellStyle(cellStyle);
			cell14.setCellValue("Wystawi³:                                                              Odebra³: ");
			cellRangeAddress14 = new CellRangeAddress(15, 15, 0, 9);
			sheet.addMergedRegion(cellRangeAddress14);
			borderToRegion(cellRangeAddress14, sheet, wb);

			cell13 = row3.createCell((short) 6);
			cell13.setCellStyle(cellStyle);
			cell13.setCellValue(info);
			CellStyle cellStyle3 = wb.createCellStyle();
			cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
			cellStyle3.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			cell13.setCellStyle(cellStyle3);
			cellRangeAddress13 = new CellRangeAddress(7, 13, 6, 9);
			sheet.addMergedRegion(cellRangeAddress13);
			borderToRegion(cellRangeAddress13, sheet, wb);

			if ((int) theDocument.getQty2() > 0) {

				String product2 = theDocument.getProduct2();
				int qty2 = theDocument.getQty2();
				System.out.println(" " + product2 + " " + qty2);

				cell7 = row4.createCell((short) 0);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue("2. " + product2);
				cellRangeAddress7 = new CellRangeAddress(8, 8, 0, 3);
				sheet.addMergedRegion(cellRangeAddress7);
				borderToRegion(cellRangeAddress7, sheet, wb);

				cell16 = row4.createCell((short) 4);
				cell16.setCellValue("szt. " + qty2);
				cellRangeAddress16 = new CellRangeAddress(8, 8, 4, 5);
				sheet.addMergedRegion(cellRangeAddress16);
				borderToRegion(cellRangeAddress16, sheet, wb);

				if ((int) theDocument.getQty3() > 0) {

					String product3 = theDocument.getProduct3();
					int qty3 = (int) theDocument.getQty3();
					System.out.println(" " + product3 + " " + qty3);

					cell8 = row5.createCell((short) 0);
					cell8.setCellStyle(cellStyle);
					cell8.setCellValue("3. " + product3);
					cellRangeAddress8 = new CellRangeAddress(9, 9, 0, 3);
					sheet.addMergedRegion(cellRangeAddress8);
					borderToRegion(cellRangeAddress8, sheet, wb);

					cell17 = row5.createCell((short) 4);
					cell17.setCellValue("szt. " + qty3);
					cellRangeAddress17 = new CellRangeAddress(9, 9, 4, 5);
					sheet.addMergedRegion(cellRangeAddress17);
					borderToRegion(cellRangeAddress17, sheet, wb);

					if ((int) theDocument.getQty4() > 0) {

						String product4 = theDocument.getProduct4();
						int qty4 = (int) theDocument.getQty4();
						System.out.println(" " + product4 + " " + qty4);

						cell9 = row6.createCell((short) 0);
						cell9.setCellStyle(cellStyle);
						cell9.setCellValue("4. " + product4);
						cellRangeAddress9 = new CellRangeAddress(10, 10, 0, 3);
						sheet.addMergedRegion(cellRangeAddress9);
						borderToRegion(cellRangeAddress9, sheet, wb);

						cell18 = row6.createCell((short) 4);
						cell18.setCellValue("szt. " + qty4);
						cellRangeAddress18 = new CellRangeAddress(10, 10, 4, 5);
						sheet.addMergedRegion(cellRangeAddress18);
						borderToRegion(cellRangeAddress18, sheet, wb);

						if ((int) theDocument.getQty5() > 0) {
							String product5 = theDocument.getProduct5();
							int qty5 = (int) theDocument.getQty5();

							cell10 = row7.createCell((short) 0);
							cell10.setCellStyle(cellStyle);
							cell10.setCellValue("5. " + product5);
							cellRangeAddress10 = new CellRangeAddress(11, 11, 0, 3);
							sheet.addMergedRegion(cellRangeAddress10);
							borderToRegion(cellRangeAddress10, sheet, wb);

							cell19 = row7.createCell((short) 4);
							cell19.setCellValue("szt. " + qty5);
							cellRangeAddress19 = new CellRangeAddress(11, 11, 4, 5);
							sheet.addMergedRegion(cellRangeAddress19);
							borderToRegion(cellRangeAddress19, sheet, wb);

							if ((int) theDocument.getQty6() > 0) {
								String product6 = theDocument.getProduct6();
								int qty6 = (int) theDocument.getQty6();
								System.out.println(" " + product6 + " " + qty6);

								cell11 = row8.createCell((short) 0);
								cell11.setCellStyle(cellStyle);
								cell11.setCellValue("6. " + product6);
								cellRangeAddress11 = new CellRangeAddress(12, 12, 0, 3);
								sheet.addMergedRegion(cellRangeAddress11);
								borderToRegion(cellRangeAddress11, sheet, wb);

								cell20 = row8.createCell((short) 4);
								cell20.setCellValue("szt. " + qty6);
								cellRangeAddress20 = new CellRangeAddress(12, 12, 4, 5);
								sheet.addMergedRegion(cellRangeAddress20);
								borderToRegion(cellRangeAddress20, sheet, wb);

								if ((int) theDocument.getQty7()  > 0) {
									String product7 = theDocument.getProduct7();
									int qty7 = (int) theDocument.getQty7();

									cell12 = row9.createCell((short) 0);
									cell12.setCellStyle(cellStyle);
									cell12.setCellValue("7. " + product7);
									cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
									sheet.addMergedRegion(cellRangeAddress12);
									borderToRegion(cellRangeAddress12, sheet, wb);

									cell21 = row9.createCell((short) 4);
									cell21.setCellValue("szt. " + qty7);
									cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
									sheet.addMergedRegion(cellRangeAddress21);
									borderToRegion(cellRangeAddress21, sheet, wb);
								} else {
									cell12 = row9.createCell((short) 0);
									cell12.setCellStyle(cellStyle);
									cell12.setCellValue("7. ");
									cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
									sheet.addMergedRegion(cellRangeAddress12);
									borderToRegion(cellRangeAddress12, sheet, wb);

									cell21 = row9.createCell((short) 4);
									cell21.setCellValue("szt. ");
									cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
									sheet.addMergedRegion(cellRangeAddress21);
									borderToRegion(cellRangeAddress21, sheet, wb);

								}
							} else {
								cell11 = row8.createCell((short) 0);
								cell11.setCellStyle(cellStyle);
								cell11.setCellValue("6. ");
								cellRangeAddress11 = new CellRangeAddress(12, 12, 0, 3);
								sheet.addMergedRegion(cellRangeAddress11);
								borderToRegion(cellRangeAddress11, sheet, wb);

								cell20 = row8.createCell((short) 4);
								cell20.setCellValue("szt. ");
								cellRangeAddress20 = new CellRangeAddress(12, 12, 4, 5);
								sheet.addMergedRegion(cellRangeAddress20);
								borderToRegion(cellRangeAddress20, sheet, wb);

								cell12 = row9.createCell((short) 0);
								cell12.setCellStyle(cellStyle);
								cell12.setCellValue("7. ");
								cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
								sheet.addMergedRegion(cellRangeAddress12);
								borderToRegion(cellRangeAddress12, sheet, wb);

								cell21 = row9.createCell((short) 4);
								cell21.setCellValue("szt. ");
								cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
								sheet.addMergedRegion(cellRangeAddress21);
								borderToRegion(cellRangeAddress21, sheet, wb);
							}
						} else {
							cell10 = row7.createCell((short) 0);
							cell10.setCellStyle(cellStyle);
							cell10.setCellValue("5. ");
							cellRangeAddress10 = new CellRangeAddress(11, 11, 0, 3);
							sheet.addMergedRegion(cellRangeAddress10);
							borderToRegion(cellRangeAddress10, sheet, wb);

							cell19 = row7.createCell((short) 4);
							cell19.setCellValue("szt. ");
							cellRangeAddress19 = new CellRangeAddress(11, 11, 4, 5);
							sheet.addMergedRegion(cellRangeAddress19);
							borderToRegion(cellRangeAddress19, sheet, wb);

							cell11 = row8.createCell((short) 0);
							cell11.setCellStyle(cellStyle);
							cell11.setCellValue("6. ");
							cellRangeAddress11 = new CellRangeAddress(12, 12, 0, 3);
							sheet.addMergedRegion(cellRangeAddress11);
							borderToRegion(cellRangeAddress11, sheet, wb);

							cell20 = row8.createCell((short) 4);
							cell20.setCellValue("szt. ");
							cellRangeAddress20 = new CellRangeAddress(12, 12, 4, 5);
							sheet.addMergedRegion(cellRangeAddress20);
							borderToRegion(cellRangeAddress20, sheet, wb);

							cell12 = row9.createCell((short) 0);
							cell12.setCellStyle(cellStyle);
							cell12.setCellValue("7. ");
							cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
							sheet.addMergedRegion(cellRangeAddress12);
							borderToRegion(cellRangeAddress12, sheet, wb);

							cell21 = row9.createCell((short) 4);
							cell21.setCellValue("szt. ");
							cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
							sheet.addMergedRegion(cellRangeAddress21);
							borderToRegion(cellRangeAddress21, sheet, wb);
						}
					} else {
						cell9 = row6.createCell((short) 0);
						cell9.setCellStyle(cellStyle);
						cell9.setCellValue("4. ");
						cellRangeAddress9 = new CellRangeAddress(10, 10, 0, 3);
						sheet.addMergedRegion(cellRangeAddress9);
						borderToRegion(cellRangeAddress9, sheet, wb);

						cell18 = row6.createCell((short) 4);
						cell18.setCellValue("szt. ");
						cellRangeAddress18 = new CellRangeAddress(10, 10, 4, 5);
						sheet.addMergedRegion(cellRangeAddress18);
						borderToRegion(cellRangeAddress18, sheet, wb);

						cell10 = row7.createCell((short) 0);
						cell10.setCellStyle(cellStyle);
						cell10.setCellValue("5. ");
						cellRangeAddress10 = new CellRangeAddress(11, 11, 0, 3);
						sheet.addMergedRegion(cellRangeAddress10);
						borderToRegion(cellRangeAddress10, sheet, wb);

						cell19 = row7.createCell((short) 4);
						cell19.setCellValue("szt. ");
						cellRangeAddress19 = new CellRangeAddress(11, 11, 4, 5);
						sheet.addMergedRegion(cellRangeAddress19);
						borderToRegion(cellRangeAddress19, sheet, wb);

						cell11 = row8.createCell((short) 0);
						cell11.setCellStyle(cellStyle);
						cell11.setCellValue("6. ");
						cellRangeAddress11 = new CellRangeAddress(12, 12, 0, 3);
						sheet.addMergedRegion(cellRangeAddress11);
						borderToRegion(cellRangeAddress11, sheet, wb);

						cell20 = row8.createCell((short) 4);
						cell20.setCellValue("szt. ");
						cellRangeAddress20 = new CellRangeAddress(12, 12, 4, 5);
						sheet.addMergedRegion(cellRangeAddress20);
						borderToRegion(cellRangeAddress20, sheet, wb);

						cell12 = row9.createCell((short) 0);
						cell12.setCellStyle(cellStyle);
						cell12.setCellValue("7. ");
						cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
						sheet.addMergedRegion(cellRangeAddress12);
						borderToRegion(cellRangeAddress12, sheet, wb);

						cell21 = row9.createCell((short) 4);
						cell21.setCellValue("szt. ");
						cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
						sheet.addMergedRegion(cellRangeAddress21);
						borderToRegion(cellRangeAddress21, sheet, wb);
					}
				} else {

					cell8 = row5.createCell((short) 0);
					cell8.setCellStyle(cellStyle);
					cell8.setCellValue("3. ");
					cellRangeAddress8 = new CellRangeAddress(9, 9, 0, 3);
					sheet.addMergedRegion(cellRangeAddress8);
					borderToRegion(cellRangeAddress8, sheet, wb);

					cell17 = row5.createCell((short) 4);
					cell17.setCellValue("szt. ");
					cellRangeAddress17 = new CellRangeAddress(9, 9, 4, 5);
					sheet.addMergedRegion(cellRangeAddress17);
					borderToRegion(cellRangeAddress17, sheet, wb);

					cell9 = row6.createCell((short) 0);
					cell9.setCellStyle(cellStyle);
					cell9.setCellValue("4. ");
					cellRangeAddress9 = new CellRangeAddress(10, 10, 0, 3);
					sheet.addMergedRegion(cellRangeAddress9);
					borderToRegion(cellRangeAddress9, sheet, wb);

					cell18 = row6.createCell((short) 4);
					cell18.setCellValue("szt. ");
					cellRangeAddress18 = new CellRangeAddress(10, 10, 4, 5);
					sheet.addMergedRegion(cellRangeAddress18);
					borderToRegion(cellRangeAddress18, sheet, wb);

					cell10 = row7.createCell((short) 0);
					cell10.setCellStyle(cellStyle);
					cell10.setCellValue("5. ");
					cellRangeAddress10 = new CellRangeAddress(11, 11, 0, 3);
					sheet.addMergedRegion(cellRangeAddress10);
					borderToRegion(cellRangeAddress10, sheet, wb);

					cell19 = row7.createCell((short) 4);
					cell19.setCellValue("szt. ");
					cellRangeAddress19 = new CellRangeAddress(11, 11, 4, 5);
					sheet.addMergedRegion(cellRangeAddress19);
					borderToRegion(cellRangeAddress19, sheet, wb);

					cell11 = row8.createCell((short) 0);
					cell11.setCellStyle(cellStyle);
					cell11.setCellValue("6. ");
					cellRangeAddress11 = new CellRangeAddress(12, 12, 0, 3);
					sheet.addMergedRegion(cellRangeAddress11);
					borderToRegion(cellRangeAddress11, sheet, wb);

					cell20 = row8.createCell((short) 4);
					cell20.setCellValue("szt. ");
					cellRangeAddress20 = new CellRangeAddress(12, 12, 4, 5);
					sheet.addMergedRegion(cellRangeAddress20);
					borderToRegion(cellRangeAddress20, sheet, wb);

					cell12 = row9.createCell((short) 0);
					cell12.setCellStyle(cellStyle);
					cell12.setCellValue("7. ");
					cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
					sheet.addMergedRegion(cellRangeAddress12);
					borderToRegion(cellRangeAddress12, sheet, wb);

					cell21 = row9.createCell((short) 4);
					cell21.setCellValue("szt. ");
					cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
					sheet.addMergedRegion(cellRangeAddress21);
					borderToRegion(cellRangeAddress21, sheet, wb);
				}
			} else {
				cell7 = row4.createCell((short) 0);
				cell7.setCellStyle(cellStyle);
				cell7.setCellValue("2. ");
				cellRangeAddress7 = new CellRangeAddress(8, 8, 0, 3);
				sheet.addMergedRegion(cellRangeAddress7);
				borderToRegion(cellRangeAddress7, sheet, wb);

				cell16 = row4.createCell((short) 4);
				cell16.setCellValue("szt. ");
				cellRangeAddress16 = new CellRangeAddress(8, 8, 4, 5);
				sheet.addMergedRegion(cellRangeAddress16);
				borderToRegion(cellRangeAddress16, sheet, wb);

				cell8 = row5.createCell((short) 0);
				cell8.setCellStyle(cellStyle);
				cell8.setCellValue("3. ");
				cellRangeAddress8 = new CellRangeAddress(9, 9, 0, 3);
				sheet.addMergedRegion(cellRangeAddress8);
				borderToRegion(cellRangeAddress8, sheet, wb);

				cell17 = row5.createCell((short) 4);
				cell17.setCellValue("szt. ");
				cellRangeAddress17 = new CellRangeAddress(9, 9, 4, 5);
				sheet.addMergedRegion(cellRangeAddress17);
				borderToRegion(cellRangeAddress17, sheet, wb);

				cell9 = row6.createCell((short) 0);
				cell9.setCellStyle(cellStyle);
				cell9.setCellValue("4. ");
				cellRangeAddress9 = new CellRangeAddress(10, 10, 0, 3);
				sheet.addMergedRegion(cellRangeAddress9);
				borderToRegion(cellRangeAddress9, sheet, wb);

				cell18 = row6.createCell((short) 4);
				cell18.setCellValue("szt. ");
				cellRangeAddress18 = new CellRangeAddress(10, 10, 4, 5);
				sheet.addMergedRegion(cellRangeAddress18);
				borderToRegion(cellRangeAddress18, sheet, wb);

				cell10 = row7.createCell((short) 0);
				cell10.setCellStyle(cellStyle);
				cell10.setCellValue("5. ");
				cellRangeAddress10 = new CellRangeAddress(11, 11, 0, 3);
				sheet.addMergedRegion(cellRangeAddress10);
				borderToRegion(cellRangeAddress10, sheet, wb);

				cell19 = row7.createCell((short) 4);
				cell19.setCellValue("szt. ");
				cellRangeAddress19 = new CellRangeAddress(11, 11, 4, 5);
				sheet.addMergedRegion(cellRangeAddress19);
				borderToRegion(cellRangeAddress19, sheet, wb);

				cell11 = row8.createCell((short) 0);
				cell11.setCellStyle(cellStyle);
				cell11.setCellValue("6. ");
				cellRangeAddress11 = new CellRangeAddress(12, 12, 0, 3);
				sheet.addMergedRegion(cellRangeAddress11);
				borderToRegion(cellRangeAddress11, sheet, wb);

				cell20 = row8.createCell((short) 4);
				cell20.setCellValue("szt. ");
				cellRangeAddress20 = new CellRangeAddress(12, 12, 4, 5);
				sheet.addMergedRegion(cellRangeAddress20);
				borderToRegion(cellRangeAddress20, sheet, wb);

				cell12 = row9.createCell((short) 0);
				cell12.setCellStyle(cellStyle);
				cell12.setCellValue("7. ");
				cellRangeAddress12 = new CellRangeAddress(13, 13, 0, 3);
				sheet.addMergedRegion(cellRangeAddress12);
				borderToRegion(cellRangeAddress12, sheet, wb);

				cell21 = row9.createCell((short) 4);
				cell21.setCellValue("szt. ");
				cellRangeAddress21 = new CellRangeAddress(13, 13, 4, 5);
				sheet.addMergedRegion(cellRangeAddress21);
				borderToRegion(cellRangeAddress21, sheet, wb);
			}

		} catch (IndexOutOfBoundsException eb) {
			JOptionPane.showMessageDialog(new JFrame(), "Mark some document for printing", "Printing error",
					JOptionPane.ERROR_MESSAGE);
		}

		// Write the output to a file
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("workbook.xls");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			wb.write(fileOut);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileOut.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Desktop dt = Desktop.getDesktop();
		try {
			dt.open(new File("workbook.xls"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	///////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRODUCST ZONE //////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

	public List<Product> getProducts() throws Exception {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from products";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// execute statement
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String warehouseSql = myRs.getString("warehouse");

				// create new student object
				Product product = new Product(id, name, warehouseSql);

				// add it to the list of students
				products.add(product);
			}

			return products;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into products " + "(name, warehouse) " + "values (?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the product
			myStmt.setString(1, theProduct.getProductName());
			myStmt.setString(2, theProduct.getWarehouse());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public List<Product> getProducts(String warehouse) throws Exception {

		List<Product> products = new ArrayList<>();

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from products where warehouse=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, warehouse);

			// execute statement
			myRs = myStmt.executeQuery();

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String warehouseSql = myRs.getString("warehouse");

				// create new student object
				Product product = new Product(id, name, warehouseSql);

				// add it to the list of students
				products.add(product);
			}

			return products;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public Product getProduct(String theProductId) throws Exception {

		Product theProduct = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int productId;

		try {

			// convert product id to int
			productId = (int) Integer.parseInt(theProductId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from products where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, productId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String warehouse = myRs.getString("warehouse");

				// use the studentId during construction
				theProduct = new Product(productId, name, warehouse);
			} else {
				throw new Exception("Could not find product id: " + productId);
			}

			return theProduct;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateProduct(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update products " + "set name=?, warehouse=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theProduct.getProductName());
			myStmt.setString(2, theProduct.getWarehouse());
			myStmt.setInt(3, theProduct.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deleteProduct(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from products " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	////////////////////////////////////////////////////////////////////////////////
	////////////////////////// CUSTOMERS ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Customer> getCustomers() throws Exception {

		List<Customer> customers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from customers";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// create new student object
				Customer tempCustomer = new Customer(id, name, address, telephone);

				// add it to the list of students
				customers.add(tempCustomer);
			}

			return customers;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}

	}

	public void addCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into customers " + "(name, address, telephone) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theCustomer.getName());
			myStmt.setString(2, theCustomer.getAddress());
			myStmt.setString(3, theCustomer.getTelephone());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Customer getCustomer(String theCustomerId) throws Exception {

		Customer theCustomer = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int customerId;

		try {

			// convert student id to int
			customerId = (int) Integer.parseInt(theCustomerId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from customers where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, customerId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// use the studentId during construction
				theCustomer = new Customer(customerId, name, address, telephone);
			} else {
				throw new Exception("Could not find customer id: " + customerId);
			}

			return theCustomer;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateCustomer(Customer theCustomer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update customers " + "set name=?, address=?, telephone=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theCustomer.getName());
			myStmt.setString(2, theCustomer.getAddress());
			myStmt.setString(3, theCustomer.getTelephone());
			myStmt.setInt(4, theCustomer.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deleteCustomer(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from customers " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	//////////////////////////////////////////////////////////////////////////////
	//////////////////////// RECIEPIENT ZONE /////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	public void addReciepient(Reciepient theReciepient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into reciepients " + "(name, address, telephone) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theReciepient.getName());
			myStmt.setString(2, theReciepient.getAddress());
			myStmt.setString(3, theReciepient.getTelephone());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public List<Reciepient> getReciepients() throws Exception {

		List<Reciepient> reciepients = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from reciepients";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// create new student object
				Reciepient tempReciepient = new Reciepient(id, name, address, telephone);

				// add it to the list of students
				reciepients.add(tempReciepient);
			}

			return reciepients;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public Reciepient getReciepient(String theRciepientId) throws Exception {

		Reciepient theReciepient = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int reciepientId;

		try {

			// convert student id to int
			reciepientId = (int) Integer.parseInt(theRciepientId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from reciepients where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, reciepientId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");

				// use the studentId during construction
				theReciepient = new Reciepient(reciepientId, name, address, telephone);
			} else {
				throw new Exception("Could not find customer id: " + reciepientId);
			}

			return theReciepient;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateReciepient(Reciepient theReciepient) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update reciepients " + "set name=?, address=?, telephone=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theReciepient.getName());
			myStmt.setString(2, theReciepient.getAddress());
			myStmt.setString(3, theReciepient.getTelephone());
			myStmt.setInt(4, theReciepient.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deleteReciepient(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from reciepients " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// PRICE LIST ZONE /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<PriceList> getPrices() throws Exception {

		List<PriceList> prices = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from prices";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String customer = myRs.getString("customer");
				String product = myRs.getString("product");
				Double price = myRs.getDouble("price");

				// create new student object
				PriceList tempPrice = new PriceList(true, id, customer, product, price);

				// add it to the list of students
				prices.add(tempPrice);
			}

			return prices;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addPrice(PriceList thePrice) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into prices " + "(customer, product, price) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, thePrice.getCustomer());
			myStmt.setString(2, thePrice.getProduct());
			myStmt.setDouble(3, thePrice.getPrice());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public PriceList getPrice(String thePriceId) throws Exception {

		PriceList thePrice = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int priceId;

		try {

			// convert student id to int
			priceId = (int) Integer.parseInt(thePriceId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from prices where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, priceId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String customer = myRs.getString("customer");
				String product = myRs.getString("product");
				Double price = myRs.getDouble("price");

				// use the studentId during construction
				thePrice = new PriceList(true, priceId, customer, product, price);
			} else {
				throw new Exception("Could not find price id: " + priceId);
			}

			return thePrice;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updatePrice(PriceList thePrice) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update prices " + "set customer=?, product=?, price=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, thePrice.getCustomer());
			myStmt.setString(2, thePrice.getProduct());
			myStmt.setDouble(3, thePrice.getPrice());
			myStmt.setInt(4, thePrice.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deletePrice(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from prices " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}
	}

	public Double loadPriceForCustomer(String product, String customer) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Double price = 0.00;

		try {
			// get db connection
			myConn = dataSource.getConnection();
			String sql = "select price from prices where customer=? and product=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, customer);
			myStmt.setString(2, product);
			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				price = myRs.getDouble("price");
			}
		} finally {
			close(myConn, myStmt, myRs);
		}

		return price;

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// EMPLOYEES ZONE //////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Employee> getEmployees() throws Exception {

		List<Employee> employees = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from employees";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String surname = myRs.getString("surname");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");
				String profession = myRs.getString("profession");
				String safetyTraining = myRs.getString("safetyTraining");
				String medicalVisit = myRs.getString("medicalVisit");
				String contractDate = myRs.getString("contractDate");

				// create new student object
				Employee tempEmployee = new Employee(id, name, surname, address, telephone, profession, safetyTraining,
						medicalVisit, contractDate);

				// add it to the list of students
				employees.add(tempEmployee);
			}

			return employees;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addEmployee(Employee theEmployee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into employees " + "(name, surname, address, telephone, profession, safetyTraining,"
					+ " medicalVisit, contractDate) " + "values (?, ?, ?, ?, ?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theEmployee.getName());
			myStmt.setString(2, theEmployee.getSurname());
			myStmt.setString(3, theEmployee.getAddress());
			myStmt.setString(4, theEmployee.getTelephone());
			myStmt.setString(5, theEmployee.getProfession());
			myStmt.setString(6, theEmployee.getSafetyTraining());
			myStmt.setString(7, theEmployee.getMedicalVisit());
			myStmt.setString(8, theEmployee.getContractDate());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Employee getEmployee(String theEmployeeId) throws Exception {

		Employee theEmployee = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int employeeId;

		try {

			// convert employee id to int
			employeeId = (int) Integer.parseInt(theEmployeeId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected employee
			String sql = "select * from employees where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, employeeId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String surname = myRs.getString("surname");
				String address = myRs.getString("address");
				String telephone = myRs.getString("telephone");
				String profession = myRs.getString("profession");
				String safetyTraining = myRs.getString("safetyTraining");
				String medicalVisit = myRs.getString("medicalVisit");
				String contractDate = myRs.getString("contractDate");

				// use the employeeId during construction
				theEmployee = new Employee(employeeId, name, surname, address, telephone, profession, safetyTraining,
						medicalVisit, contractDate);
			} else {
				throw new Exception("Could not find employee id: " + employeeId);
			}

			return theEmployee;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void updateEmployee(Employee employee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update employees "
					+ "set name=?, surname=?, address=?, telephone=?, profession=?, safetyTraining=?, "
					+ "medicalVisit=?, contractDate=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, employee.getName());
			myStmt.setString(2, employee.getSurname());
			myStmt.setString(3, employee.getAddress());
			myStmt.setString(4, employee.getTelephone());
			myStmt.setString(5, employee.getProfession());
			myStmt.setString(6, employee.getSafetyTraining());
			myStmt.setString(7, employee.getMedicalVisit());
			myStmt.setString(8, employee.getContractDate());
			myStmt.setInt(9, employee.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deleteEmployee(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from employees " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	/////////////////////////////////////////////////////////////////////////////////
	////////////////////////// WAREHOUSES ZONE /////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public List<Warehouse> getWarehouses() throws Exception {

		List<Warehouse> warehouses = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from warehouses";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String name = myRs.getString("name");

				// create new warehouse object
				Warehouse tempWarehouse = new Warehouse(id, name);

				// add it to the list of students
				warehouses.add(tempWarehouse);
			}

			return warehouses;
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
	}

	public void addWarehouse(Warehouse theWarehouse) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert

			String sql = "insert into warehouses (name) values (?)";
			System.out.println("warehouse upa upa upa");
			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theWarehouse.getName());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}

	}

	public Warehouse getWarehouse(String theWarehouseId) throws Exception {

		Warehouse theWarehouse = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int warehouseId;

		try {

			// convert employee id to int
			warehouseId = (int) Integer.parseInt(theWarehouseId);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected warehouse
			String sql = "select * from warehouses where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, warehouseId);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");

				// use the warehouseId during construction
				theWarehouse = new Warehouse(warehouseId, name);
			} else {
				throw new Exception("Could not find employee id: " + warehouseId);
			}

			return theWarehouse;
		} finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}

	}

	public void updateWarehouse(Warehouse warehouse) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update warehouses " + "set name=? where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, warehouse.getName());
			myStmt.setInt(2, warehouse.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	public void deleteWarehouse(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from warehouses " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			close(myConn, myStmt, null);

		}

	}

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// OTHER ZONE ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close(); // doesn't really close it ... just puts back in connection pool
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	///////////////////// next doc number /////////////////////////
	// Each customer has own numeration of documents.
	// When new year starts, numerations starts from one again.
	public int nextCustomerDoc(String name) throws Exception {

		List<Document> docs = new ArrayList<>();
		docs = getDocuments();
		Connection myConn = null;
		myConn = dataSource.getConnection();

		int idDoc = 0;
		int year1 = 0;
		int year2 = 0;
		int docId = 0;
		// docs.clear();

		String checkSql = "select count(*) as count from documents where customer=?";
		PreparedStatement checkSmt = myConn.prepareStatement(checkSql);

		String sql = "select id, customer, reciepient, docId, date, product1, qty1, product2, qty2, "
				+ "product3, qty3, product4, qty4, product5, qty5, product6, qty6, product7, qty7 from "
				+ "documents where customer=? order by id";

		PreparedStatement selectStatement = myConn.prepareStatement(sql);
		selectStatement.setString(1, name);

		checkSmt.setString(1, name);
		ResultSet checkResult = checkSmt.executeQuery();
		checkResult.next();

		ResultSet results = selectStatement.executeQuery();
		int count = checkResult.getInt(1);
		if (count > 0) {

			while (results.next()) {
				docId = results.getInt("docId");
			}

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date date2 = null;

			/*
			 * try { date2 = sdf.parse("31-08-2017"); } catch (ParseException e1) { // TODO
			 * Auto-generated catch block e1.printStackTrace(); }
			 */
			// DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
			date2 = new Date();
			System.out.println(sdf.format(date2));
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date2);

			// System.out.println(docs.get(docs.size()-1).getDate());
			String stringDate = docs.get(docs.size() - 1).getDate();
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Date date = null;
			try {
				date = format.parse(stringDate);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date);
				String newDateString = format.format(date);
				System.out.println(newDateString);
				int dni = (int) ((date.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24));
				year1 = cal1.get(Calendar.YEAR);
				year2 = cal2.get(Calendar.YEAR);

				if ((year1 - year2) == 1) {
					idDoc = 1;
				} else if ((year1 - year2 == 0)) {
					idDoc = ++docId;
				}

				System.out.println(year1 - year2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			results.close();
			selectStatement.close();
		} else {
			System.out.println("dupa dupa dupa");
			idDoc = 1;
		}

		return idDoc;
	}

	private void borderToRegion(CellRangeAddress cellRangeAddress, Sheet sheet, Workbook wb) {

		RegionUtil.setBorderTop(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		RegionUtil.setBorderLeft(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		RegionUtil.setBorderRight(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);
		RegionUtil.setBorderBottom(CellStyle.BORDER_THIN, cellRangeAddress, sheet, wb);

	}

	private String selectQuery(String name, String column) throws SQLException {

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

			close(myConn, myStmt, results);

		}

		return ret;

	}
}
