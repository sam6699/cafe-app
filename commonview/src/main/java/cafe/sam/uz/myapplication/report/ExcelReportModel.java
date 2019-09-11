package cafe.sam.uz.myapplication.report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import cafe.sam.uz.myapplication.repository.entities.Sales;

public class ExcelReportModel {
    Workbook book ;
    Sheet sheet ;

    public ExcelReportModel() {
        book = new HSSFWorkbook();
        sheet = book.createSheet("Xisobot");
        initDoc();

    }

    private void initDoc() {
        // Нумерация начинается с нуля
        Row dateRow = sheet.createRow(0);
        Cell date = dateRow.createCell(0);
    DataFormat format = book.createDataFormat();
    CellStyle dateStyle = book.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("dd/MM/yy"));
            date.setCellStyle(dateStyle);

        Date date1= new Date();
            date.setCellValue(date1);
        Row headerRow = sheet.createRow(1);
        Cell productName = headerRow.createCell(0);
        productName.setCellValue("Nomi");
        Cell productQuanity = headerRow.createCell(1);
        productQuanity.setCellValue("Hajmi");
        Cell productAmount=headerRow.createCell(2);
        productAmount.setCellValue("Summa");
    }


    public void addData(List<Sales> dataList){
        double q=0;
        int a = 0;
        for (int i=0;i<dataList.size();i++){
            Row data = sheet.createRow(i+2);
            Cell name = data.createCell(0);
            name.setCellValue(dataList.get(i).getProduct_id().getName());
            Cell quantity = data.createCell(1);
            quantity.setCellValue(dataList.get(i).getQuantity());
            q+=(dataList.get(i).getQuantity());
            Cell amount = data.createCell(2);
            amount.setCellValue(dataList.get(i).getAmount());
            a+=(dataList.get(i).getAmount());
        }

        Row last1 = sheet.createRow(dataList.size()+2);
        Cell lab1 = last1.createCell(0);
        lab1.setCellValue("Jami:");
        Cell val1 = last1.createCell(1);
        val1.setCellValue(q);
        Cell val2 = last1.createCell(2);
        val2.setCellValue(a);



    }

    public void createFile(String path){



        try{
//                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//                context.startActivity(intent);
//
                File sd = new File(path);
                sd = new File(sd.getAbsolutePath()+"/"+"hisobot");
                if (!sd.exists())
                     sd.mkdir();
                File file = new File(sd,"two.xls");
                OutputStream stream = new FileOutputStream(file);
                try {
                    book.write(stream);
                }finally {
                    stream.close();
                }
                book.close();

                FileInputStream fileInputStream = new FileInputStream(file);
                InputStream input = fileInputStream;
                book = WorkbookFactory.create(input);
                book.close();




            }catch (Exception e){
                e.printStackTrace();
            }



    }
}
