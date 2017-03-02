package com.kelly.media;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.kelly.slidemenu.SlideMenu;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    private ImageButton buttonToggle;   //显示菜单按钮
    private SlideMenu menu;

    @Bind(R.id.tapBarMenu)
    TapBarMenu mTapBarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToorBar();

        initView();
    }

    private void initToorBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);

        toolbar.setNavigationIcon(R.drawable.ic_drawer_home);

        toolbar.setTitle(R.string.home_page);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
    }

    private void initView() {
        menu = (SlideMenu)findViewById(R.id.slideMenu);
//        buttonToggle = (ImageButton)findViewById(R.id.buttonToggle);
//        buttonToggle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                menu.toggleMenu();
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.tapBarMenu)
    public void onMenuButtonClick(){
        mTapBarMenu.toggle();
    }

    @OnClick({ R.id.item1, R.id.item2, R.id.item3, R.id.item4 })
    public void onMenuItemClick(View view) {
        mTapBarMenu.close();
        switch (view.getId()) {
            case R.id.item1:
                Toast.makeText(this,"Item 1 selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item2:
                Toast.makeText(this,"Item 2 selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item3:
                Toast.makeText(this,"Item 3 selected",Toast.LENGTH_SHORT).show();
                break;
            case R.id.item4:
                Toast.makeText(this,"Item 4 selected",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
