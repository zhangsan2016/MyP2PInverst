package myp2pinverst.ldgd.com.myp2pinverst.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.loopj.android.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.sharesdk.onekeyshare.OnekeyShare;
import myp2pinverst.ldgd.com.myp2pinverst.R;
import myp2pinverst.ldgd.com.myp2pinverst.activity.GestureEditActivity;
import myp2pinverst.ldgd.com.myp2pinverst.base.BaseFragment;
import myp2pinverst.ldgd.com.myp2pinverst.util.LogUtil;
import myp2pinverst.ldgd.com.myp2pinverst.util.UIUtils;

/**
 * Created by ldgd on 2018/9/25.
 * 功能：更多的Fragment
 * 说明：
 */

public class MoreFragment extends BaseFragment {

    @BindView(R.id.iv_title_back)
    ImageView ivTitleBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_title_setting)
    ImageView ivTitleSetting;
    Unbinder unbinder;
    @BindView(R.id.toggle_more)
    ToggleButton toggleMore;
    @BindView(R.id.tv_more_reset)
    TextView tvMoreReset;
    @BindView(R.id.tv_more_phone)
    TextView tvMorePhone;
    @BindView(R.id.rl_more_contact)
    RelativeLayout rlMoreContact;
    @BindView(R.id.tv_more_fankui)
    TextView tvMoreFankui;
    @BindView(R.id.tv_more_share)
    TextView tvMoreShare;

    private SharedPreferences sp;

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected void initTitle() {
        ivTitleBack.setVisibility(View.INVISIBLE);
        tvTitle.setText("用户信息");
        ivTitleSetting.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_more;
    }

    public MoreFragment(Context context) {
        super(context);
    }

    @Override
    public View initView(View view) {
        //   View view = View.inflate(context,getLayoutId() , null);
        return view;
    }

    @Override
    protected void initData(String content) {

        //初始化SharedPreferences
        sp = this.getActivity().getSharedPreferences("secret_protect", Context.MODE_PRIVATE);

        //获取当前设置手势密码的ToggleButton的状态
        getGestureStatus();

        //设置手势密码
        setGesturePassword();

        //重置手势密码
        resetGesture();

        //联系客服
        contactService();

        // 用户反馈
        commitFanKui();

        // 分享
        share();


    }

    private void share() {

        tvMoreShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showShare();
            }
        });
    }


    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("我的分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

       // 启动分享GUI
        oks.show(context);
    }

    private String department = "不明确";

    private void commitFanKui() {

        tvMoreFankui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = View.inflate(context, R.layout.view_fankui, null);
                final RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg_fankui);
                final EditText et_fankui_content = (EditText) view.findViewById(R.id.et_fankui_content);
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton rb = (RadioButton) rg.findViewById(checkedId);
                        department = rb.getText().toString();
                    }
                });

                new AlertDialog.Builder(context)
                        .setView(view)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //获取反馈的信息
                                String content = et_fankui_content.getText().toString();
                                LogUtil.e("content = " + content);
                                LogUtil.e("department = " + department);
                                UIUtils.toast("发送反馈信息成功", false);

                            }
                        }).setNegativeButton("取消", null)
                        .show();

            }
        });

    }

    private void contactService() {

        rlMoreContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取手机号码
                String phone = tvMorePhone.getText().toString().trim();
                //使用隐式意图，启动系统拨号应用界面
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));

                // 判断有无权限
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                context.startActivity(intent);

            }
        });
    }

    private void resetGesture() {

        tvMoreReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = toggleMore.isChecked();
                if (checked) {
                    Intent intent = new Intent(context, GestureEditActivity.class);
                    startActivity(intent);
                } else {
                    UIUtils.toast("手势密码操作已关闭，请开启后再设置", false);
                }
            }
        });
    }

    private void setGesturePassword() {
        toggleMore.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String inputCode = sp.getString("inputCode", "");
                    if (TextUtils.isEmpty(inputCode)) { // 之前没有设置过
                        new AlertDialog.Builder(MoreFragment.this.getActivity())
                                .setTitle("设置手势密码")
                                .setMessage("是否现在设置手势密码")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        sp.edit().putBoolean("isOpen", true).commit();
//                                            toggleMore.setChecked(true);
                                        //开启新的activity:
                                        Intent intent = new Intent(context, GestureEditActivity.class);
                                        context.startActivity(intent);
                                    }
                                })
                                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Toast.makeText(context, "取消了现在设置手势密码", Toast.LENGTH_SHORT).show();
                                        sp.edit().putBoolean("isOpen", false).commit();
                                        toggleMore.setChecked(false);
                                    }
                                })
                                .show();
                    } else {
                        UIUtils.toast("开启手势密码", false);
                        sp.edit().putBoolean("isOpen", true).commit();
                    }

                } else {
                    Toast.makeText(context, "关闭了手势密码", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void getGestureStatus() {
        boolean isOpen = sp.getBoolean("isOpen", false);
        toggleMore.setChecked(isOpen);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
