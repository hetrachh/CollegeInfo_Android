package com.example.hetrachh.collegeinformation;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class IntegratedActivity extends AppCompatActivity {

    ImageView imgvw;
    ProgressBar superprgbar;
    WebView wbv;
    public static final String downloadDirectory = "ClgInfo Downloads";
    private static int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrated);


        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, REQUEST_CODE);

        superprgbar = (ProgressBar) findViewById(R.id.Myprgbar);
        imgvw = (ImageView) findViewById(R.id.Myimgview);
        wbv = (WebView) findViewById(R.id.wbview);

        superprgbar.setMax(100);

        WebSettings webSettings = wbv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wbv.loadUrl("http://collegemasti.dx.am/");
        wbv.setWebViewClient(new WebViewClient());
        wbv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superprgbar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                imgvw.setImageBitmap(icon);
            }
        });

        wbv.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {

                DownloadManager.Request  myRequest = new DownloadManager.Request(Uri.parse(url));

                myRequest.allowScanningByMediaScanner();

                myRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                String fileName = url.substring(url.lastIndexOf('/') + 1);
                //URLUtil.guessFileName(url, null, null)
                myRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);
                DownloadManager myDownloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

                myDownloadManager.enqueue(myRequest);


                Toast.makeText(IntegratedActivity.this,"Your file is Downloading...",Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.sper_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_back:
                onBKPressed();
                break;
            case R.id.menu_forward:
                onForwardPressed();
                break;
            case R.id.menu_about:
                onAboutPressed();
                break;
            case R.id.menu_reload:
                wbv.reload();
                break;
            case R.id.menu_acd:
                onacdPressesd();
                break;
            case R.id.menu_exit:
                onExitPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void onAboutPressed()
    {
        Intent abt = new Intent(IntegratedActivity.this,AboutActivity.class);
        startActivity(abt);
    }

    public void onExitPressed()
    {
        final AlertDialog.Builder builder =new AlertDialog.Builder(IntegratedActivity.this);
        builder.setMessage("Are you sure want to exit?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void onForwardPressed(){

        if(wbv.canGoForward())
        {
            wbv.goForward();
        }
        else
        {
            Toast.makeText(this, "Can't go further!", Toast.LENGTH_SHORT).show();
        }
    }

    //@Override
    public void onBKPressed() {
        if(wbv.canGoBack())
        {
            wbv.goBack();
        }
        else
        {
            Toast.makeText(this, "Can't go Back!", Toast.LENGTH_SHORT).show();
            //super.onBackPressed();
        }
    }

    public void onacdPressesd()
    {
        Intent acd = new Intent(IntegratedActivity.this,SelectActivity.class);
        startActivity(acd);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent acd = new Intent(IntegratedActivity.this,SelectActivity.class);
        startActivity(acd);
        finish();
    }
}
