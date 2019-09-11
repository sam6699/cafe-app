package cafe.sam.uz.myapplication.report;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.repository.dao.SalesDao;
import cafe.sam.uz.myapplication.repository.entities.Sales;
import cafe.sam.uz.myapplication.utils.FileUtils;

public class Report extends AppCompatActivity {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy ");
    private LinearLayout prod;
    private LinearLayout quan;
    private LinearLayout amount;
    private TextView date;
    private Button safe;
    private ArrayList<Sales> list;
    private static final int FILE_SELECT_CODE = 0;
    final ExcelReportModel excelReportModel = new ExcelReportModel();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);


        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            System.out.println("access granted");
        } else {
            // Request permission from the user
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
        safe = (Button) findViewById(R.id.safe);
        prod = (LinearLayout) findViewById(R.id.prod);
        quan = (LinearLayout) findViewById(R.id.quan);
        amount = (LinearLayout) findViewById(R.id.amount);
        date =(TextView) findViewById(R.id.dateLabel);
        date.setText(dateFormat.format(new Date()));
        safe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            excelReportModel.addData(list);
                showFileChooser();

//            excelReportModel.createFile(safe.getContext());
            }
        });

        readFromDB();


    }

    private void readFromDB() {
        list=SalesDao.getInstance().getSales(dateFormat.format(new Date()).toString());
        System.out.println(list.size()+"----"+dateFormat.format(new Date()).toString());
            for (Sales s:list) {

                System.out.println(s.getProduct_id().getName()+" p");
                System.out.println(s.getAmount()+" a");
                System.out.println(s.getDate()+" d");
                System.out.println(s.getQuantity()+" q");
                System.out.println(s.getId()+" i");
                TextView tv1 = new TextView(this);
                tv1.setText(s.getProduct_id().getName());
                tv1.setTextColor(Color.BLACK);
                prod.addView(tv1);
                TextView tv2 = new TextView(this);
                tv2.setText(s.getQuantity()+"");
                tv2.setTextColor(Color.BLACK);
                quan.addView(tv2);
                TextView tv3 = new TextView(this);
                tv3.setText(s.getAmount()+"");
                tv3.setTextColor(Color.BLACK);
                amount.addView(tv3);

            }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = data.getData();
        Log.d("test", "File Uri: " + uri.toString());
        // Get the path
        String path = null;
        try {
            path = FileUtils.getPath(getContentResolver(), uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        excelReportModel.createFile(uri.toString());
        Log.d("sadadad", "File Path: " + path);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }

    }
}
