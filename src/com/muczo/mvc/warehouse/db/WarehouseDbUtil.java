package com.muczo.mvc.warehouse.db;

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

import com.muczo.mvc.warehouse.blueprint.Activity;
import com.muczo.mvc.warehouse.blueprint.Document;
import com.muczo.mvc.warehouse.blueprint.Document2;
import com.muczo.mvc.warehouse.blueprint.Product;
import com.muczo.mvc.warehouse.blueprint.User;

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
			String sql = "select * from documents ORDER BY id DESC LIMIT 50";

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
			DbUtil.close(myConn, myStmt, myRs);
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
			DbUtil.close(myConn, myStmt, myRs);
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
			if (theDocument.getQty1() > 0) {
				Product product = getProductByName(theDocument.getProduct1());
				int qty = product.getQty() - theDocument.getQty1();
				product.setQty(qty);
				updateProductQty(product);
				if (theDocument.getQty2() > 0) {
					Product product2 = getProductByName(theDocument.getProduct2());
					int qty2 = product.getQty() - theDocument.getQty2();
					product.setQty(qty2);
					updateProductQty(product2);
					if (theDocument.getQty3() > 0) {
						Product product3 = getProductByName(theDocument.getProduct3());
						int qty3 = product.getQty() - theDocument.getQty3();
						product.setQty(qty3);
						updateProductQty(product3);
						if (theDocument.getQty4() > 0) {
							Product product4 = getProductByName(theDocument.getProduct4());
							int qty4 = product.getQty() - theDocument.getQty4();
							product.setQty(qty4);
							updateProductQty(product4);
							if (theDocument.getQty5() > 0) {
								Product product5 = getProductByName(theDocument.getProduct5());
								int qty5 = product.getQty() - theDocument.getQty5();
								product.setQty(qty5);
								updateProductQty(product5);
								if (theDocument.getQty6() > 0) {
									Product product6 = getProductByName(theDocument.getProduct6());
									int qty6 = product.getQty() - theDocument.getQty6();
									product.setQty(qty6);
									updateProductQty(product6);
									if (theDocument.getQty7() > 0) {
										Product product7 = getProductByName(theDocument.getProduct7());
										int qty7 = product.getQty() - theDocument.getQty7();
										product.setQty(qty7);
										updateProductQty(product7);
									}
								}
							}
						}
					}
				}
			}

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
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
			DbUtil.close(myConn, myStmt, myRs);
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

			DbUtil.close(myConn, myStmt, null);

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

			DbUtil.close(myConn, myStmt, null);

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
			DbUtil.close(myConn, myStmt, myRs);
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

								if ((int) theDocument.getQty7() > 0) {
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
	////////////////////////// DOCUMENTS2 ZONE ////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	public List<Document2> getDocuments2() throws Exception {

		List<Document2> documents2 = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql statement
			String sql = "select * from documents2";

			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String provider = myRs.getString("provider");
				String product = myRs.getString("product");
				int qty = myRs.getInt("qty");

				// create new student object
				Document2 tempDocument2 = new Document2(id, provider, product, qty);

				// add it to the list of students
				documents2.add(tempDocument2);
			}

			return documents2;
		} finally {
			// close JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void addDocument2(Document2 theDocument2) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into documents2 " + "(provider, product, qty) " + "values (?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, theDocument2.getProvider());
			myStmt.setString(2, theDocument2.getProduct());
			myStmt.setDouble(3, theDocument2.getQty());
			if (theDocument2.getQty() > 0) {

				Product product = getProductByName(theDocument2.getProduct());
				int qty = product.getQty() + theDocument2.getQty();
				product.setQty(qty);
				updateProductQty(product);

			}

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}

	}

	public Document2 getDocument2(String theDocument2id) throws Exception {

		Document2 document2 = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int document2id;

		try {

			// convert student id to int
			document2id = (int) Integer.parseInt(theDocument2id);

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected student
			String sql = "select * from documents2 where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, document2id);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String provider = myRs.getString("provider");
				String product = myRs.getString("product");
				int qty = myRs.getInt("qty");

				// use the document2id during construction
				document2 = new Document2(document2id, provider, product, qty);
			} else {
				throw new Exception("Could not find price id: " + theDocument2id);
			}

			return document2;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	public void updateDocument2(Document2 theDocument) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update documents2 " + "set provider=?, product=?, qty=? " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theDocument.getProvider());
			myStmt.setString(2, theDocument.getProduct());
			myStmt.setDouble(3, theDocument.getQty());
			myStmt.setInt(4, theDocument.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	public void deleteDocument2(int id) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "delete from documents2 " + "where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, id);

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.close(myConn, myStmt, null);

		}

	}

	

	

	


	

	

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// OTHER ZONE ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	public User getUserByName(String theUserName) throws Exception {

		User theUser = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from users where name=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theUserName);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				String name = myRs.getString("name");
				String password = myRs.getString("password");
				int id = myRs.getInt("id");

				// use the studentId during construction
				theUser = new User(id, name, password);
			} else {
				//throw new Exception("Could not find user name: " + theUserName);
			}

			return theUser;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
		}
	}

	//////////////////////////////////////////////////////////////////////////////
	////////////////////////// OTHER ZONE ////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////
	public void monitorActivity(Activity activity) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create sql for insert
			String sql = "insert into activity " + "(userName, activityName, dateTime, activityId) " + "values (?, ?, ?, ?)";

			myStmt = myConn.prepareStatement(sql);

			// set the param values for the student
			myStmt.setString(1, activity.getUserName());
			myStmt.setString(2, activity.getActivityName());
			myStmt.setString(3, activity.getDateTime());
			myStmt.setInt(4, activity.getActivityId());

			// execute sql insert
			myStmt.execute();
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, null);
		}
	}
	
	public void updateProductQty(Product theProduct) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get db connection
			myConn = dataSource.getConnection();

			// create SQL update statement
			String sql = "update products " + "set qty=?" + " where id=?";

			// prepare statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, theProduct.getQty());
			myStmt.setInt(2, theProduct.getId());

			// execute SQL statement
			myStmt.execute();

		} finally {
			// clean up JDBC objects

			DbUtil.	close(myConn, myStmt, null);

		}

	}
	public Product getProductByName(String theProductName) throws Exception {

		Product theProduct = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int productId;

		try {

			// get connection to database
			myConn = dataSource.getConnection();

			// create sql to get selected product
			String sql = "select * from products where name=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setString(1, theProductName);

			// execute statement
			myRs = myStmt.executeQuery();

			// retrive data from result set row
			if (myRs.next()) {
				int id = myRs.getInt("id");
				String name = myRs.getString("name");
				String warehouse = myRs.getString("warehouse");
				int qty = myRs.getInt("qty");

				// use the studentId during construction
				theProduct = new Product(id, name, warehouse, qty);
			} else {
				throw new Exception("Could not find product id: " + theProductName);
			}

			return theProduct;
		} finally {
			// clean up JDBC objects
			DbUtil.close(myConn, myStmt, myRs);
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

	//method helps generating doc for printing
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

			DbUtil.close(myConn, myStmt, results);

		}

		return ret;

	}

}
