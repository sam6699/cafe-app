package cafe.sam.uz.myapplication.print;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import cafe.sam.uz.myapplication.R;
import cafe.sam.uz.myapplication.repository.entities.Product;

public class Main extends AppCompatActivity {
    private Button btn;
    private ScrollView llScroll;
    private TableLayout gl;
    private Bitmap bitmap;
    private String startDate;
    public Map<Integer,Product> order;
    public ArrayList<Product>  products;
    public ArrayList<Integer>  quan;
    private PrintedPdfDocument pdfDocument;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_order_layout);
        btn = (Button) findViewById(R.id.btn);
        llScroll = (ScrollView) findViewById(R.id.scroll);
        gl = (TableLayout) findViewById(R.id.l_one);
        loadData();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                doPrint();
                DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
                int display = displaymetrics.heightPixels;
                int scrol = llScroll.getHeight();
                Log.d("display size",displaymetrics.widthPixels+" "+displaymetrics.heightPixels );
                Log.d("scroll size"," "+llScroll.getWidth() +"  "+llScroll.getHeight());
                    bitmap = loadBitmapFromView(llScroll, llScroll.getWidth(), llScroll.getHeight());
                createPdf();

            }
        });
    }

    public static Bitmap loadBitmapFromView(View v, int width, int height) {
        Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);

        return b;
    }

    private void createPdf(){

        int convertHighet = (int) llScroll.getHeight(), convertWidth = llScroll.getWidth();


        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth, convertHighet, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);
        Paint paint1 = new Paint();

        bitmap = Bitmap.createScaledBitmap(bitmap, convertWidth, convertHighet, true);

        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0 , null);
        paint1.setColor(Color.BLUE);

        document.finishPage(page);

        // write the document content
        String targetPdf = "/sdcard/pdffromScroll.pdf";
        File filePath;
        filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Something wrong: " + e.toString(), Toast.LENGTH_LONG).show();
        }

        // close the document
        document.close();
        Toast.makeText(this, "PDF of Scroll is created!!!", Toast.LENGTH_SHORT).show();

        openGeneratedPDF();

    }

    private void openGeneratedPDF(){
        File file = new File("/sdcard/pdffromScroll.pdf");
        if (file.exists())
        {
            Uri uri = Uri.fromFile(file);
            Intent intent=new Intent();
            intent.setDataAndType(uri, "application/pdf");


            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);





            try
            {
                startActivity(intent);
            }
            catch(ActivityNotFoundException e)
            {
                Toast.makeText(cafe.sam.uz.myapplication.print.Main.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void loadData(){
        Intent intent = getIntent();
        startDate = intent.getStringExtra("startDate");
        order = cafe.sam.uz.myapplication.Main.instance.or;
        products = new ArrayList<>(order.values());
        Log.d("products",products.size()+"");
            for (int i=0;i<50;i++){
                Row row = new Row(this);
//                row.setData(i+1,products.get(i).getName(),products.get(i).getDim_name()
//                        ,quan.get(i),products.get(i).getPrice(),products.get(i).getPrice()*quan.get(i));
                row.setData(i+1,"sadasd","sadasd"
                        ,i,i,i);


                gl.addView(row.getRow());


            }

    }

    private void doPrint() {
        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        // Set job name, which will be displayed in the print queue
        String jobName = " Document";
        // Start a print job, passing in a PrintDocumentAdapter implementation
        // to handle the generation of a print document
        printManager.print(jobName, new MyPrintDocumentAdapter(this),
                null); //
    }





}

