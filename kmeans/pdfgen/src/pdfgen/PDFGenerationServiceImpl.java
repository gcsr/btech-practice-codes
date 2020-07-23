package pdfgen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class PDFGenerationServiceImpl {

	class MyFooter extends PdfPageEventHelper {

		public void onEndPage(PdfWriter writer, Document document) {

			Font ffont = new Font(Font.FontFamily.UNDEFINED, 12, Font.NORMAL);
			PdfContentByte cb = writer.getDirectContent();
			try {
				Image img = Image.getInstance(getClass().getResource(
						"/inverted_fm_logo_72x242.png"));
				img.scalePercent(70);
				img.setAbsolutePosition(10, 790);
				img.setAlignment(Element.ALIGN_TOP);
				writer.getDirectContent().addImage(img);

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			LineSeparator line = new LineSeparator();
			line.setLineColor(new BaseColor(222, 225, 226));

			try {
				document.add(line);
			} catch (DocumentException e) {
				e.printStackTrace();
			}

			// page x of y
			BaseFont baseFont2;
			try {
				baseFont2 = BaseFont.createFont("Helvetica", "winansi", false, false, null, null);
				PdfContentByte ex = writer.getDirectContent();
				ex.saveState();
				String pageString = String.format(
						"Page %s of " + writer.getPageNumber(),
						document.getPageNumber(), 0);

				float textBase = document.bottom() - 20;

				ex.beginText();
				ex.setFontAndSize(baseFont2, 8);
				ex.showText(pageString);
				ex.endText();

				ex.restoreState();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Phrase footer = new Phrase("Copyright 2017 Fannie Mae");
			ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer,
					document.right() + 30, document.bottom() - 28, 0);

		}

		@Override
		public void onCloseDocument(PdfWriter writer, Document document) {

			// Font ffont = new Font(Font.FontFamily.UNDEFINED, 12,
			// Font.NORMAL);
			/*
			 * PdfContentByte cb = writer.getDirectContent(); Phrase footer =
			 * new Phrase("Copyright 2017 Fannie Mae");
			 * ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer,
			 * document.right() + 30, document.bottom() - 28, 0);
			 */
			System.out.println("-------------------------------------");
			// ColumnText.showTextAligned(template, Element.ALIGN_LEFT, new
			// Phrase("__________________________"+writer.getPageNumber()),
			// document.right() + 30, document.bottom() - 28, 0);

			Font ffont = new Font(Font.FontFamily.UNDEFINED, 5, Font.NORMAL);
			PdfContentByte t = writer.getDirectContent();
			PdfTemplate tmp = writer.getDirectContent().createTemplate(30, 30);

			PdfTemplate tmp2 = writer.getDirectContent()
					.createTemplate(500, 50);
			Phrase footer1 = new Phrase(
					"--------TESTttttttttttttttttttttttttttttttttts----------",
					ffont);
			ColumnText.showTextAligned(tmp2, Element.ALIGN_LEFT, footer1, 2, 2,
					0);
		}
	}

	
	public String getMegaPDF(String response,
			boolean isReCommitEmail) throws Exception {

		// TODO : Validate RESPONSE

		File pdfFile = File.createTempFile("Mega-" + response,"--.pdf");
		Document document = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(pdfFile));
		// Setting Copyright statement
		MyFooter event = new MyFooter();
		writer.setPageEvent(event);
		document.setMargins(40, 40, 40, 40);
		document.open();

		/*BaseFont baseFont1 = BaseFont.;
		// LineSeparator line = new LineSeparator();
		// line.setLineColor(new BaseColor(222, 225, 226));
		baseFont1 = BaseFont.createFont("/UniversLT Light.TTF",
				BaseFont.WINANSI, BaseFont.EMBEDDED);*/

		Font f2 = new Font(FontFamily.COURIER,24);

		// document.add(line);
		f2.setColor(new BaseColor(239, 144, 36));
		if (isReCommitEmail) {
			Paragraph p2 = new Paragraph("Mega Edits Confirmed ("
					+ "1234" + ")", f2);
			document.add(p2);
		} else {
			Paragraph p2 = new Paragraph("Mega Confirmed", f2);
			document.add(p2);
		}
		document.add(Chunk.NEWLINE);

		Paragraph p3 = new Paragraph(
				"Below is an overview of the Mega you created. Changes can be made up to 2 business days before settlement.\n",
				new Font(FontFamily.COURIER, 11));
		p3.setIndentationRight(100);
		document.add(p3);

		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		// document.add(line);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		PdfContentByte cb1 = writer.getDirectContent();
		Paragraph p4 = new Paragraph("Dealer: " + "Sekhar",
				new Font(FontFamily.COURIER, 11));

		Paragraph p5 = new Paragraph("Dealer Contact: "
				+ "Sekhar", new Font(FontFamily.COURIER, 11));
		Paragraph p6 = new Paragraph("Committed Date: ", new Font(FontFamily.COURIER, 11));
		ColumnText.showTextAligned(cb1, Element.ALIGN_LEFT, p4,
				document.right() - 200, document.top() - 170, 0);
		ColumnText.showTextAligned(cb1, Element.ALIGN_LEFT, p5,
				document.right() - 200, document.top() - 185, 0);
		ColumnText.showTextAligned(cb1, Element.ALIGN_LEFT, p6,
				document.right() - 200, document.top() - 200, 0);

		if (isReCommitEmail) {
			Paragraph p7 = new Paragraph("Edited Date: "
					, new Font(FontFamily.COURIER, 11));
			ColumnText.showTextAligned(cb1, Element.ALIGN_LEFT, p7,
					document.right() - 200, document.top() - 215, 0);
			// document.add(p7);
		}
		document.add(Chunk.NEWLINE);

		PdfPTable table = new PdfPTable(2);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setTotalWidth(375);
		table.setLockedWidth(true);

		List<List<String>> dataset = new ArrayList<List<String>>();
		List<String> ss = new ArrayList<String>();
		ss.add("1234");
		ss.add("1234");
		ss.add("1234");
		ss.add("1234");
		
		dataset.add(ss);
		dataset.add(ss);
		dataset.add(ss);
		
		
		int count = 0;
		for (List<String> record : dataset) {
			for (String field : record) {
				count++;
				PdfPCell cell = new PdfPCell();
				cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
				cell.setPaddingTop(15);
				cell.setPaddingBottom(15);
				cell.setBorderColor(new BaseColor(222, 225, 226));
				cell.setBorderWidth(1);
				Paragraph cellInfo = new Paragraph(field);
				cellInfo.getFont().setStyle(Font.BOLD);

				if (field.matches("[A-Za-z]{2}[0-9]{4}")) {
					cellInfo.getFont().setColor(new BaseColor(239, 144, 36));
				} else if (count == 4) {
					cellInfo.getFont().setColor(new BaseColor(239, 144, 36));
				}

				if (count % 2 == 0) {
					cellInfo.setAlignment(Element.ALIGN_RIGHT);
				} else {
					cellInfo.setAlignment(Element.ALIGN_LEFT);
				}
				cell.addElement(cellInfo);
				table.addCell(cell);
			}
		}

		document.add(table);

		// Checking if MegaPricingSummary details is empty or null
		/*if (response.getMegaPricingSummary() != null
				&& !response.getMegaPricingSummary().isEmpty()) {
			// Adding new page
			document.newPage();
			Paragraph p8 = new Paragraph(
					"\nBelow is a summary of your current committed mega fees. Note that by"
							+ " making changes to this Mega, fees for other megas may have been affected.",
					new Font(baseFont1, 15));
			document.add(p8);
			// Set the Month and year
			Date date = response.getTradeSettlementDate();
			Calendar ca1 = Calendar.getInstance();
			ca1.setTime(date);
			java.util.Date d = new java.util.Date(ca1.getTimeInMillis());
			Paragraph p9 = new Paragraph("\n"
					+ new SimpleDateFormat("MMMM").format(d) + " "
					+ new SimpleDateFormat("YYYY").format(d), new Font(
					baseFont1, 15));
			p9.getFont().setStyle(Font.BOLD);
			document.add(p9);

			// Creating new table for Mega Pricing Details
			PdfPTable summaryTable = new PdfPTable(3);
			summaryTable.setHorizontalAlignment(Element.ALIGN_LEFT);
			summaryTable.setTotalWidth(500);
			summaryTable.setLockedWidth(true);
			summaryTable.setHeaderRows(1);

			// Setting Headers for the Table
			setHeaders(summaryTable);

			// List<MegaPricingSummary> megaPricingSummaryList = response
			// .getMegaPricingSummary();

			// Iterator<MegaPricingSummary> priceItr = megaPricingSummaryList
			// .iterator();

			// Set the megapricing values to the table
			// setValuesInTable(priceItr, summaryTable);
			document.add(summaryTable);

		}*/

		document.close();

		return pdfFile.getAbsolutePath();
	}

	private void setHeaders(PdfPTable summaryTable) {
		PdfPCell hcell = new PdfPCell();
		hcell.setPaddingTop(15);
		hcell.setPaddingBottom(15);
		hcell.setBorderWidth(0);
		Paragraph hcellInfo = new Paragraph("MEGA DETAILS");
		hcellInfo.getFont().setStyle(Font.BOLD);
		hcellInfo.setAlignment(Element.ALIGN_LEFT);

		hcell.addElement(hcellInfo);
		summaryTable.addCell(hcell);
		PdfPCell hcell2 = new PdfPCell();
		hcell2.setBorderWidth(0);
		hcell2.setPaddingTop(15);
		hcell2.setPaddingBottom(15);
		Paragraph hcell2Info = new Paragraph("AMOUNT");
		hcell2Info.getFont().setStyle(Font.BOLD);
		hcell2Info.setAlignment(Element.ALIGN_RIGHT);

		hcell2.addElement(hcell2Info);
		summaryTable.addCell(hcell2);
		PdfPCell hcell3 = new PdfPCell();
		hcell3.setBorderWidth(0);
		hcell3.setPaddingTop(15);
		hcell3.setPaddingBottom(15);
		Paragraph hcell3Info = null;
		hcell3Info = new Paragraph("FEE");
		hcell3Info.getFont().setStyle(Font.BOLD);
		hcell3Info.setAlignment(Element.ALIGN_RIGHT);
		hcell3.addElement(hcell3Info);
		summaryTable.addCell(hcell3);

	}

	/*
	 * private void setValuesInTable(Iterator<MegaPricingSummary> priceItr,
	 * PdfPTable summaryTable) { MegaPricingSummary temp = null; while
	 * (priceItr.hasNext()) { temp = priceItr.next();
	 * 
	 * List<MegaCommitDetails> megaDetails = temp.getMegaList();
	 * MegaCommitDetails temp2 = null; Iterator<MegaCommitDetails> itr =
	 * megaDetails.iterator();
	 * 
	 * while (itr.hasNext()) { temp2 = itr.next(); PdfPCell cell = new
	 * PdfPCell(); cell.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
	 * cell.setPaddingTop(15); cell.setPaddingBottom(15);
	 * cell.setBorderColor(new BaseColor(222, 225, 226));
	 * cell.setBorderWidth(1); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * Paragraph cellInfo = new Paragraph("Pool #: " + temp2.getPoolNumber());
	 * cellInfo.setAlignment(Element.ALIGN_LEFT);
	 * 
	 * cell.addElement(cellInfo); summaryTable.addCell(cell); PdfPCell cell2 =
	 * new PdfPCell(); cell2.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
	 * cell2.setPaddingTop(15); cell2.setPaddingBottom(15);
	 * cell2.setBorderColor(new BaseColor(222, 225, 226));
	 * cell2.setBorderWidth(1);
	 * 
	 * NumberFormat numberFormat = NumberFormat.getCurrencyInstance(); Paragraph
	 * cell2Info = new Paragraph(numberFormat.format(
	 * temp2.getTradeAmount()).toString());
	 * cell2Info.setAlignment(Element.ALIGN_RIGHT);
	 * 
	 * cell2.addElement(cell2Info); summaryTable.addCell(cell2); PdfPCell cell3
	 * = new PdfPCell(); cell3.setBorder(Rectangle.TOP | Rectangle.BOTTOM);
	 * cell3.setPaddingTop(15); cell3.setPaddingBottom(15);
	 * cell3.setBorderColor(new BaseColor(222, 225, 226));
	 * cell3.setBorderWidth(1); Paragraph cell3Info = null; if
	 * (temp2.getFeeTicks() != null) { cell3Info = new
	 * Paragraph(temp2.getFeeTicks().toString() + "/32"); } else if
	 * (temp2.getFeeDollars() != null) { cell3Info = new
	 * Paragraph(numberFormat.format( temp2.getFeeDollars()).toString()); } else
	 * { cell3Info = new Paragraph(""); }
	 * cell3Info.setAlignment(Element.ALIGN_RIGHT);
	 * 
	 * cell3.addElement(cell3Info); summaryTable.addCell(cell3); } }
	 * 
	 * }
	 * 
	 * private List<List<String>> getData(CommitMegaResponse response) {
	 * DecimalFormat df = new DecimalFormat("###,###,##0.###");
	 * df.setRoundingMode(RoundingMode.HALF_DOWN); NumberFormat numberFormat =
	 * NumberFormat.getCurrencyInstance();
	 * numberFormat.setMaximumFractionDigits(0); List<List<String>> megaInfo =
	 * new ArrayList<>(); List<String> megaAttribute = new ArrayList<>();
	 * megaAttribute.add("Pool Number");
	 * megaAttribute.add(response.getPoolNumber()); megaInfo.add(megaAttribute);
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("CUSIP");
	 * megaAttribute.add(response.getCusip()); megaInfo.add(megaAttribute);
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("Deal Amount");
	 * megaAttribute.add(numberFormat.format(response.getTradeAmount())
	 * .toString()); megaInfo.add(megaAttribute); megaAttribute = new
	 * ArrayList<>(); megaAttribute.add("Settlement Date");
	 * 
	 * megaAttribute.add(new SimpleDateFormat("MM/dd/yyyy").format(response
	 * .getTradeSettlementDate())); megaInfo.add(megaAttribute); megaAttribute =
	 * new ArrayList<>(); megaAttribute.add("Prefix");
	 * megaAttribute.add(response.getPrefixCode()); megaInfo.add(megaAttribute);
	 * 
	 * if (response.getMegaType().equals(AppConstants.SINGLE_FIXED_MEGA)) {
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("Coupon");
	 * megaAttribute.add(df.format(response.getSfFixedCoupon()));
	 * megaInfo.add(megaAttribute); } else if
	 * (response.getMegaType().equals(AppConstants.SINGLE_ARM_MEGA) ||
	 * response.getMegaType().equals(AppConstants.MULTI_ARM_MEGA)) {
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("Subtype");
	 * megaAttribute.add(response.getSfARMSubType());
	 * megaInfo.add(megaAttribute); } else if
	 * (response.getMegaType().equals(AppConstants.MULTI_FIXED_MEGA)) {
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("High Coupon");
	 * megaAttribute.add(df.format(response.getMfHighCoupon()));
	 * 
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("Low Coupon");
	 * megaAttribute.add(df.format(response.getMfLowCoupon()));
	 * megaInfo.add(megaAttribute); }
	 * 
	 * megaAttribute = new ArrayList<>(); megaAttribute.add("Fee"); String fee =
	 * null; if (response.getFeeTicks() != null) { fee =
	 * df.format(response.getFeeTicks()) + "/32"; megaAttribute.add(fee); } else
	 * if (response.getFeeDollars() != null) { fee =
	 * numberFormat.format(response.getFeeDollars()); megaAttribute.add(fee); }
	 * else { megaAttribute.add(""); }
	 * 
	 * megaInfo.add(megaAttribute); return megaInfo; }
	 */

	public static void main(String[] args) {
		/*
		 * CommitMegaResponse response = new CommitMegaResponse();
		 * response.setMegaType("SFFMEGA");
		 * response.setDealerName("Wells Fargo");
		 * response.setTraderName("John Doe");
		 * response.setCommittedDate(STTSWSDateUtils
		 * .getTodayWithoutTime().getTime()); response.setPoolNumber("AA1103");
		 * response.setCusip("3138EPDG6"); response.setTradeAmount(new
		 * BigDecimal(456000000.00)); response.setSfFixedCoupon(new
		 * BigDecimal(1.00));
		 * response.setTradeSettlementDate(STTSWSDateUtils.getTodayWithoutTime
		 * ().getTime()); response.setPrefixCode("LC"); //
		 * response.setSfARMSubType("P9D"); response.setFeeDollars(new
		 * BigDecimal("7500")); response.setMfHighCoupon(new
		 * BigDecimal(4.2754454)); response.setMfLowCoupon(new
		 * BigDecimal(1.000));
		 * response.setEditedDate(STTSWSDateUtils.getTodayWithoutTime
		 * ().getTime()); //
		 * 
		 * List<MegaPricingSummary> megaPricingSummaryList = new
		 * ArrayList<MegaPricingSummary>(); MegaPricingSummary
		 * megaPricingSummary = new MegaPricingSummary();
		 * megaPricingSummary.setMonthlyVolume(new BigDecimal(563547354));
		 * megaPricingSummary.setPricingMonth(new Date());
		 * List<MegaCommitDetails> megaList = new
		 * ArrayList<MegaCommitDetails>(); MegaCommitDetails megaList1 = new
		 * MegaCommitDetails(); megaList1.setFeeTicks(new BigDecimal(1));
		 * megaList1.setPoolNumber("AB1001"); megaList1.setTradeAmount(new
		 * BigDecimal("6574676.65")); // second MegaCommitDetails megaList2 =
		 * new MegaCommitDetails(); megaList2.setFeeDollars(new
		 * BigDecimal("26746764.4")); megaList2.setPoolNumber("AB1001");
		 * megaList2.setTradeAmount(new BigDecimal("6574676.65")); // third
		 * MegaCommitDetails megaList3 = new MegaCommitDetails();
		 * megaList3.setFeeTicks(new BigDecimal(1));
		 * megaList3.setPoolNumber("AB1001"); megaList3.setTradeAmount(new
		 * BigDecimal("6574676.65")); // fourth
		 * 
		 * 
		 * MegaCommitDetails megaList4 = new MegaCommitDetails();
		 * megaList4.setFeeDollars(new BigDecimal("26746764.4"));
		 * megaList4.setPoolNumber("AB1001"); megaList4.setTradeAmount(new
		 * BigDecimal("6574676.65")); // fifth MegaCommitDetails megaList5 = new
		 * MegaCommitDetails(); megaList5.setFeeTicks(new BigDecimal(1));
		 * megaList5.setPoolNumber("AB1001"); megaList5.setTradeAmount(new
		 * BigDecimal("6574676.65"));
		 * 
		 * megaList.add(megaList1); megaList.add(megaList2);
		 * megaList.add(megaList3); megaList.add(megaList4);
		 * megaList.add(megaList5);
		 * 
		 * MegaPricingSummary megaPricingSummary1 = new MegaPricingSummary();
		 * megaPricingSummary1.setMonthlyVolume(new BigDecimal(563547354));
		 * megaPricingSummary1.setPricingMonth(new Date());
		 * List<MegaCommitDetails> megaLists = new
		 * ArrayList<MegaCommitDetails>(); MegaCommitDetails megaLists1 = new
		 * MegaCommitDetails(); megaLists1.setFeeTicks(new BigDecimal(1));
		 * megaLists1.setPoolNumber("AB1001"); megaLists1.setTradeAmount(new
		 * BigDecimal("6574676.65"));
		 * 
		 * 
		 * MegaCommitDetails megaLists2 = new MegaCommitDetails();
		 * megaLists2.setFeeDollars(new BigDecimal("26746764.4"));
		 * megaLists2.setPoolNumber("AB1001"); megaLists2.setTradeAmount(new
		 * BigDecimal("6574676.65")); // third MegaCommitDetails megaLists3 =
		 * new MegaCommitDetails(); megaLists3.setFeeTicks(new BigDecimal(1));
		 * megaLists3.setPoolNumber("AB1001"); megaLists3.setTradeAmount(new
		 * BigDecimal("6574676.65")); // fourth MegaCommitDetails megaLists4 =
		 * new MegaCommitDetails(); megaLists4.setFeeDollars(new
		 * BigDecimal("26746764.4")); megaLists4.setPoolNumber("AB1001");
		 * megaLists4.setTradeAmount(new BigDecimal("6574676.65")); // fifth
		 * MegaCommitDetails megaLists5 = new MegaCommitDetails();
		 * megaLists5.setFeeTicks(new BigDecimal(1));
		 * megaLists5.setPoolNumber("AB1001"); megaLists5.setTradeAmount(new
		 * BigDecimal("6574676.65"));
		 * 
		 * 
		 * megaLists.add(megaLists1); megaLists.add(megaLists2);
		 * megaLists.add(megaLists3); megaLists.add(megaLists4);
		 * megaLists.add(megaLists5); megaLists.add(megaLists1);
		 * megaLists.add(megaLists2); megaLists.add(megaLists3);
		 * megaLists.add(megaLists4); megaLists.add(megaLists5);
		 * megaLists.add(megaLists1); megaLists.add(megaLists2);
		 * megaLists.add(megaLists3); megaLists.add(megaLists4);
		 * megaLists.add(megaLists5);
		 * 
		 * megaPricingSummary1.setMegaList(megaLists);
		 * megaPricingSummary.setMegaList(megaList);
		 * 
		 * megaPricingSummaryList.add(megaPricingSummary1);
		 * megaPricingSummaryList.add(megaPricingSummary);
		 * response.setMegaPricingSummary(megaPricingSummaryList);
		 */

		PDFGenerationServiceImpl pdfGenerator = new PDFGenerationServiceImpl();
		try {
			System.out.println(pdfGenerator.getMegaPDF(null, true));
		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

}
