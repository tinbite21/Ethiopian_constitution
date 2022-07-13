package com.selah.constitution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.selah.constitution.utils.LanguageConfig;
import com.selah.constitution.utils.SharedPrefs;

public class articlesDetail extends AppCompatActivity {
    Home h;
    SharedPrefs sharedPreferences;
    SeekBar textSizeSeekBar;
    int textSizeValue;
    TextView article_name,article_description,article_detail;
    //language preference
    @Override
    protected void attachBaseContext(Context newBase) {
        sharedPreferences = new SharedPrefs(newBase);
        String languageCode = sharedPreferences.getLocale();
        Context context = LanguageConfig.changeLanguage(newBase, languageCode);
        super.attachBaseContext(context);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_detail);
        Intent intent = getIntent();
        String name = intent.getStringExtra("title");
        String description = intent.getStringExtra("description");
        String detail = intent.getStringExtra("detail");

        article_name = findViewById(R.id.article_layout_title);
        article_description = findViewById(R.id.article_layout_description);
        article_detail = findViewById(R.id.article_layout_detail);

        article_name.setText(name);
        article_description .setText(description);
        article_detail .setText(detail);

    }
    Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater =  getMenuInflater();
        menuInflater.inflate(R.menu.menu_for_article_detail,menu);

        //change return to true
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_search:
            {

            }

            case R.id.item_text_size:
            {
                Dialog textSizeDialog = new Dialog(articlesDetail.this);
                textSizeDialog.setContentView(R.layout.activity_text_size_popup);
                textSizeDialog.show();
                textSizeSeekBar = textSizeDialog.findViewById(R.id.textSizeSeekBar);
                textSizeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,  int progress, boolean fromUser) {
                        if(progress > 13) {
                            textSizeValue = progress;
                            article_detail.setTextSize(textSizeValue);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });


            }

            return true;
            case R.id.item_amharic:
            {
                sharedPreferences.setLocale("am");
                Intent i  = new Intent(articlesDetail.this,Home.class);
                startActivity(i);
            }
            return true;
            case R.id.item_Enlgish: {
                sharedPreferences.setLocale("en");
                Intent i  = new Intent(articlesDetail.this,Home.class);
                 startActivity(i);
            }
            return true;
            case R.id.item_affan_oromo:{
                sharedPreferences.setLocale("om");
                Intent i  = new Intent(articlesDetail.this,Home.class);
                startActivity(i);
            }
            return true;
            case R.id.item_exit:
                h.exitApp();
                return true;
            case R.id.item_share:
                h.shareApp();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
