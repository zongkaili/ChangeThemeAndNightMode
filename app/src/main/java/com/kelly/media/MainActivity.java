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
import android.widget.Switch;
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

        initView();

        initToorBar();
    }

    private void initView() {
        menu = (SlideMenu)findViewById(R.id.slideMenu);
    }

    private void initToorBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setNavigationIcon(R.drawable.ic_drawer_home);
        toolbar.setTitle(R.string.home_page);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.toggleMenu();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this,"home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_search:
                Toast.makeText(this,"action_search",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_notification:
                Toast.makeText(this,"action_notification",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_settings:
                Toast.makeText(this,"action_settings",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_about:
                Toast.makeText(this,"action_about",Toast.LENGTH_SHORT).show();
                break;
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
