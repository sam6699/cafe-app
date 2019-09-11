package cafe.sam.uz.myapplication.print;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;

public class MyPrintDocumentAdapter extends PrintDocumentAdapter {
    private Context context;
    PrintedPdfDocument pdfDocument;
    public MyPrintDocumentAdapter(Context activity)  {
        context = activity;


    }

    @Override
    public void onLayout(PrintAttributes printAttributes, PrintAttributes printAttributes1, CancellationSignal cancellationSignal, LayoutResultCallback layoutResultCallback, Bundle bundle) {
        // Create a new PdfDocument with the requested page attributes
        pdfDocument = new PrintedPdfDocument(context, printAttributes1);

        // Respond to cancellation request
        if (cancellationSignal.isCanceled() ) {
            layoutResultCallback.onLayoutCancelled();
            return;
        }

        // Compute the expected number of printed pages
        int pages = 1;

        if (pages > 0) {
            // Return print information to print framework
            PrintDocumentInfo info = new PrintDocumentInfo
                    .Builder("print_output.pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(pages)
                    .build();
            // Content layout reflow is complete
            layoutResultCallback.onLayoutFinished(info, true);
        } else {
            // Otherwise report an error to the print framework
            layoutResultCallback.onLayoutFailed("Page count calculation failed.");
        }
    }

    @Override
    public void onWrite(PageRange[] pageRanges, ParcelFileDescriptor parcelFileDescriptor, CancellationSignal cancellationSignal, WriteResultCallback writeResultCallback) {
    }



}
