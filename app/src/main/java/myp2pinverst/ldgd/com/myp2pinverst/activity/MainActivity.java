package myp2pinverst.ldgd.com.myp2pinverst.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.fragment.HomeFragment;
import myp2pinverst.ldgd.com.myp2pinverst.fragment.InvestFragment;
import myp2pinverst.ldgd.com.myp2pinverst.fragment.MoreFragment;

import static myp2pinverst.ldgd.com.myp2pinverst.R.id.rg_bottom_tag;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgBottomTag;
    private List<Fragment> basePagers;

    /**
     *  当前RadioGroup选中的位置
     */
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        rgBottomTag = (RadioGroup) findViewById(rg_bottom_tag);

        basePagers = new ArrayList<>();
        basePagers.add(new HomeFragment(this));//添加本地视频页面-0
        basePagers.add(new InvestFragment(this));//添加本地音乐页面-1
        basePagers.add(new HomeFragment(this));//添加网络视频页面-2
        basePagers.add(new MoreFragment(this));//添加网络音频页面-3

        //设置RadioGroup的监听
        rgBottomTag.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkId) {

                switch (checkId) {
                    default:
                        position = 0;
                        break;
                    case R.id.rb_invest:
                        position = 1;
                        break;
                    case R.id.rb_me:
                        position = 2;
                        break;
                    case R.id.rb_more:
                        position = 3;
                        break;
                }
                setFragment();

            }
        });


    }


    /**
     * 把界面显示到Pager中
     */
    private void setFragment() {

        // 得到FragmentManger
        FragmentManager fm = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction ft = fm.beginTransaction();
        // 替换
        ft.replace(R.id.fl_main_content,basePagers.get(position));
        // 提交事务
        ft.commit();
    }


   /* public static class ReplaceFragment extends Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            BasePager basePager = getBasePager();
            if (basePager != null) {
                //各个页面的视图
                return basePager.rootView;
            }
            return null;
        }
    }*/


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            flag = true;
            super.handleMessage(msg);
        }
    };

    public boolean flag = true;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {

            if (flag) {
                flag = false;
                handler.sendEmptyMessageDelayed(0, 2000);
                Toast.makeText(this,"再按一次退出！",Toast.LENGTH_SHORT).show();
                return true;
            }

        }


        return super.onKeyUp(keyCode, event);
    }


}
