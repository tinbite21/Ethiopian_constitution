package com.selah.constitution;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.selah.constitution.utils.LanguageConfig;
import com.selah.constitution.utils.SharedPrefs;

import java.io.File;

public class Home extends AppCompatActivity {
    SharedPrefs sharedPreferences;
    articleListAdapter articleListAdapterAdapter;
    ListView listView;
    private Integer[] images = new Integer[110];
    @Override
    protected void attachBaseContext(Context newBase) {
        sharedPreferences = new SharedPrefs(newBase);
        String languageCode = sharedPreferences.getLocale();
        Context context = LanguageConfig.changeLanguage(newBase, languageCode);
        super.attachBaseContext(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Resources resources = getResources();
        String[] articles= resources.getStringArray(R.array.article_names);
        String[] description = resources.getStringArray(R.array.article_description);
        String[] detail = resources.getStringArray(R.array.article_detail);

        for(int i=0;i<articles.length;i++){
            images[i] = R.drawable.icon_article;
        }
        articleListAdapterAdapter = new articleListAdapter(this,articles,description,images,detail);
        listView = findViewById(R.id.articles_list_view);
        listView.setAdapter(articleListAdapterAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i  = new Intent(Home.this,articlesDetail.class);
                        i.putExtra("title",articles[position]);
                        i.putExtra("description",description[position]);
                        i.putExtra("detail",detail[position]);
                        startActivity(i);
                    }
                });
    }
    int menu1 = R.menu.option_menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
//        MenuItem menuItem =  menu.findItem(R.id.item_search);
//
//        SearchView searchView = (SearchView) menuItem.getActionView();


        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
//            case R.id.item_search:
//                SearchView searchView = (SearchView) item.getActionView();
//                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                    @Override
//                    public boolean onQueryTextSubmit(String query) {
//                        exitApp();
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onQueryTextChange(String newText) {
//
//                        return false;
//                    }
//                });
//                return true;
//
            case R.id.item_amharic:
            {
                sharedPreferences.setLocale("am");
                Home.this.recreate();
            }
                return true;
            case R.id.item_Enlgish: {
                sharedPreferences.setLocale("en");
                Home.this.recreate();
            }
                return true;
            case R.id.item_affan_oromo:{
                sharedPreferences.setLocale("om");
                Home.this.recreate();
            }
                return true;
            case R.id.item_exit:
                exitApp();
                return true;
            case R.id.item_share:
                shareApp();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    public void exitApp(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
        builder.setMessage("Do You Want To Exit ?");
        builder.setCancelable(true);
        //
        builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        //
        builder.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
    public void shareApp(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND); // because we sent some txt
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Ethiopian Constitution");
        String shareMessage;
        shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n"; // add package name
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(shareIntent, "Share Using"));
    }
}