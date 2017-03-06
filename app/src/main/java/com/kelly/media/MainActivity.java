package com.kelly.media;

import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.bilibili.magicasakura.utils.ThemeUtils;
import com.kelly.media.adapter.MenuItemAdapter;
import com.kelly.media.dialog.CardPickerDialog;
import com.kelly.media.fragment.BaseFragment;
import com.kelly.media.fragment.LocalAudioFragment;
import com.kelly.media.fragment.LocalVideoFragment;
import com.kelly.media.fragment.NetAudioFragment;
import com.kelly.media.fragment.NetVideoFragment;
import com.kelly.media.util.ThemeHelper;
import com.kelly.slidemenu.SlideMenu;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements CardPickerDialog.ClickListener {

    private ImageButton buttonToggle;   //显示菜单按钮
    private ArrayList<BaseFragment> mFragments;
    private int position = 0;//Fragment页面的下标位置
    private Fragment tempFragment;//缓存的Fragment

    @Bind(R.id.tapBarMenu)
    TapBarMenu mTapBarMenu;
    @Bind(R.id.slideMenu)
    SlideMenu menu;
    @Bind(R.id.id_lv_left_menu)
    ListView mLvLeftMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏ActionBar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToorBar();

        initLeftMenu();

        initFragment();

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

    private void initLeftMenu(){
        LayoutInflater inflater = LayoutInflater.from(this);
        mLvLeftMenu.addHeaderView(inflater.inflate(R.layout.nav_header_main, mLvLeftMenu, false));
        mLvLeftMenu.setAdapter(new MenuItemAdapter(this));
        mLvLeftMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1://夜间模式
                         menu.toggleMenu();
                        break;
                    case 2:
                        CardPickerDialog dialog = new CardPickerDialog();
                        dialog.setClickListener(MainActivity.this);
                        dialog.show(getSupportFragmentManager(), "theme");
                        menu.toggleMenu();

                        break;
                    case 3:
//                        TimingFragment fragment3 = new TimingFragment();
//                        fragment3.show(getSupportFragmentManager(), "timing");
                        menu.toggleMenu();

                        break;
                    case 4:
//                        BitSetFragment bfragment = new BitSetFragment();
//                        bfragment.show(getSupportFragmentManager(), "bitset");
                        menu.toggleMenu();

                        break;
                    case 5:
//                        if (MusicPlayer.isPlaying()) {
//                            MusicPlayer.playOrPause();
//                        }
//                        unbindService();
//                        finish();
                        menu.toggleMenu();
                        break;

                }
            }
        });
    }

    private void initFragment(){
        mFragments = new ArrayList<>();
        mFragments.add(new LocalVideoFragment());//本地视频
        mFragments.add(new LocalAudioFragment());//本地音乐
        mFragments.add(new NetAudioFragment());//网络音乐
        mFragments.add(new NetVideoFragment());//网络视频

        //默认选中本地视频
        getSupportFragmentManager().beginTransaction().add(R.id.fl_home_content, mFragments.get(0)).commit();
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
                position = 0;
                break;
            case R.id.item2:
                Toast.makeText(this,"Item 2 selected",Toast.LENGTH_SHORT).show();
                position = 1;
                break;
            case R.id.item3:
                Toast.makeText(this,"Item 3 selected",Toast.LENGTH_SHORT).show();
                position = 2;
                break;
            case R.id.item4:
                Toast.makeText(this,"Item 4 selected",Toast.LENGTH_SHORT).show();
                position = 3;
                break;
        }
        //Fragment-当前的Fragment
        Fragment currentFragment = mFragments.get(position);
        switchFragment(currentFragment);
    }

    private void switchFragment(Fragment currentFragment){
        if (tempFragment != currentFragment) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (currentFragment != null) {
                if (!currentFragment.isAdded()) {
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果没有添加就添加
                    ft.add(R.id.fl_home_content, currentFragment);
                } else {
                    //把之前的隐藏
                    if (tempFragment != null) {
                        ft.hide(tempFragment);
                    }
                    //如果添加了就直接显示
                    ft.show(currentFragment);
                }
                ft.commit();
            }
            tempFragment = currentFragment;
        }
    }

    /**
     * 切换主题的实现方法
     * @param currentTheme
     */
    @Override
    public void onConfirm(int currentTheme) {
        if (ThemeHelper.getTheme(MainActivity.this) != currentTheme) {
            ThemeHelper.setTheme(MainActivity.this, currentTheme);
            ThemeUtils.refreshUI(MainActivity.this, new ThemeUtils.ExtraRefreshable() {
                        @Override
                        public void refreshGlobal(Activity activity) {
                            //for global setting, just do once
                            if (Build.VERSION.SDK_INT >= 21) {
                                final MainActivity context = MainActivity.this;
                                ActivityManager.TaskDescription taskDescription = new ActivityManager.TaskDescription(null, null, ThemeUtils.getThemeAttrColor(context, android.R.attr.colorPrimary));
                                setTaskDescription(taskDescription);
                                getWindow().setStatusBarColor(ThemeUtils.getColorById(context, R.color.theme_color_primary));
                            }
                        }

                        @Override
                        public void refreshSpecificView(View view) {
                        }
                    }
            );
        }
//        changeTheme();
    }
}
