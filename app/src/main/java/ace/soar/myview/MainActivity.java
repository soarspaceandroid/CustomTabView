package ace.soar.myview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CustomTabView mCustomTabView2;
    private CustomTabView mCustomTabView3;
    private CustomTabView mCustomTabView4;
    private CustomTabView mCustomTabView5;
    private CustomTabView mCustomTabView6;
    private CustomTabView mCustomTabView7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCustomTabView2 = (CustomTabView)findViewById(R.id.custom_tab_view2);
        List<CustomTabView.TabViewData> list2 = new ArrayList<>();
        list2.add(mCustomTabView2.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 10000));
        list2.add(mCustomTabView2.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 9));
        mCustomTabView2.setData(list2);
        mCustomTabView2.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this, "2个item中的第 " + position + "个", Toast.LENGTH_SHORT).show();
            }
        });

        mCustomTabView3 = (CustomTabView)findViewById(R.id.custom_tab_view3);
        List<CustomTabView.TabViewData> list3 = new ArrayList<>();
        list3.add(mCustomTabView3.new TabViewData(R.mipmap.ic_launcher ,R.mipmap.ic_launcher1 , R.string.test , 1000));
        list3.add(mCustomTabView3.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 0));
        list3.add(mCustomTabView3.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 0));
        mCustomTabView3.setData(list3);
        mCustomTabView3.setBackgroundColor(Color.GREEN, Color.RED);

        mCustomTabView3.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this , "3个item中的第 "+position+"个" , Toast.LENGTH_SHORT).show();
            }
        });

        mCustomTabView4 = (CustomTabView)findViewById(R.id.custom_tab_view4);
        List<CustomTabView.TabViewData> list4 = new ArrayList<>();
        list4.add(mCustomTabView4.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test , 100));
        list4.add(mCustomTabView4.new TabViewData(R.mipmap.ic_launcher ,R.mipmap.ic_launcher1 , R.string.test1 , 0));
        list4.add(mCustomTabView4.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test2, 0));
        list4.add(mCustomTabView4.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test3, 0));
        mCustomTabView4.setData(list4);
        mCustomTabView4.setCanClickSelected(false);
        mCustomTabView4.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this , "4个item中的第 "+position+"个" , Toast.LENGTH_SHORT).show();
            }
        });

        mCustomTabView5 = (CustomTabView)findViewById(R.id.custom_tab_view5);
        List<CustomTabView.TabViewData> list5 = new ArrayList<>();
        list5.add(mCustomTabView5.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test , 100));
        list5.add(mCustomTabView5.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test , 0));
        list5.add(mCustomTabView5.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test, 0));
        list5.add(mCustomTabView5.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 0));
        list5.add(mCustomTabView5.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 0));
        mCustomTabView5.setData(list5);
        mCustomTabView5.setDefaultSelectItem(2);
        mCustomTabView5.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this , "5个item中的第 "+position+"个" , Toast.LENGTH_SHORT).show();
            }
        });

        mCustomTabView6 = (CustomTabView)findViewById(R.id.custom_tab_view6);
        List<CustomTabView.TabViewData> list6 = new ArrayList<>();
        list6.add(mCustomTabView6.new TabViewData(R.mipmap.ic_launcher ,R.mipmap.ic_launcher1 , R.string.test , 100));
        list6.add(mCustomTabView6.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test , 0));
        list6.add(mCustomTabView6.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test, 0));
        list6.add(mCustomTabView6.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test, 60));
        list6.add(mCustomTabView6.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test, 0));
        list6.add(mCustomTabView6.new TabViewData(R.mipmap.ic_launcher, R.mipmap.ic_launcher1, R.string.test, 0));
        mCustomTabView6.setData(list6);
        mCustomTabView6.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this , "6个item中的第 "+position+"个" , Toast.LENGTH_SHORT).show();
            }
        });



        mCustomTabView7 = (CustomTabView)findViewById(R.id.custom_tab_view7);
        List<CustomTabView.TabViewData> list7 = new ArrayList<>();
        list7.add(mCustomTabView7.new TabViewData(R.mipmap.ic_launcher ,R.mipmap.ic_launcher1 , R.string.test , 100));
        list7.add(mCustomTabView7.new TabViewData(R.mipmap.ic_launcher ,R.mipmap.ic_launcher1 , R.string.test , 0));
        list7.add(mCustomTabView7.new TabViewData(R.mipmap.ic_launcher,R.mipmap.ic_launcher1 , R.string.test, 0));
        mCustomTabView7.setLeftmargin(200);
        mCustomTabView7.setData(list7);
        mCustomTabView7.setOnTabClickListener(new CustomTabView.OnTabClickListener() {
            @Override
            public void onTabClick(View itemView, int position) {
                Toast.makeText(MainActivity.this , "7个item中的第 "+position+"个" , Toast.LENGTH_SHORT).show();
            }
        });

    }
}
