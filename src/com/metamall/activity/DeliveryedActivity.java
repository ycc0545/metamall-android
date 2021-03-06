package com.metamall.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.metamall.R;
import com.metamall.adapter.DeliveryedAdapter;
import com.metamall.model.Global;
import com.metamall.model.ProductData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/16.
 */
public class DeliveryedActivity extends Activity {

    public static DeliveryedActivity deliveryedActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliveryed);
        deliveryedActivity=this;
        initView();
        initData();
    }


    /**
     * 顶部
     */
    private ImageButton ibback;
    /**
     * 提示登录
     */
    private RelativeLayout suggestLayout;
    private Button btnLogin;
    /**
     * 购物车为空
     */
    private TextView tvEmpty;
    /**
     * 购物车列表
     */
    private LinearLayout listLayout;
    private ListView listView;
    private DeliveryedAdapter adapter;
    private ArrayList<ProductData> products;




    @Override
    public void onStart() {
        super.onStart();
        if (Global.isLogin) {
            // 已登录
            // 隐藏提示登录的布局
            suggestLayout.setVisibility(View.GONE);
            getCartProducts();
        } else {

            // 隐藏购物车列表
            listLayout.setVisibility(View.GONE);

            // 显示提示登录的布局
            suggestLayout.setVisibility(View.VISIBLE);
            // 显示购物车为空的布局
            tvEmpty.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 初始化视图
     *
     * @param
     *
     */
    private void initView() {
        ibback=(ImageButton) findViewById(R.id.ib_back_deliveryed);
        ibback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        suggestLayout = (RelativeLayout) findViewById(R.id.deliveryed_suggest_layout);
        btnLogin = (Button) findViewById(R.id.deliveryed_btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DeliveryedActivity.this,LoginActivity.class);
                startActivity(intent);
                DeliveryedActivity.this.finish();
                overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
            }
        });
        tvEmpty = (TextView) findViewById(R.id.deliveryed_tv_empty);
        listLayout = (LinearLayout) findViewById(R.id.deliveryed_list_layout);
        listView = (ListView) findViewById(R.id.deliveryed_lv);
        listView.setAdapter(adapter);


    }

    /**
     * 初始化数据
     */
    private void initData() {
        products = new ArrayList<ProductData>();
        adapter = new DeliveryedAdapter(this, products,
                R.layout.item_deliveryed_lv);
    }

    /**
     * 从服务器获取购物车商品数据
     */
    private void getCartProducts() {
        ArrayList<ProductData> list = new ArrayList<ProductData>();
        for (int i = 0; i < 5; i++) {
            ProductData data = new ProductData();
            data.setId(i);
            data.setImgUrl("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b6006bae3d86ea50352ac65cb79a.jpg");
            data.setInfo("上岛咖啡上岛咖啡上岛咖啡上岛咖啡上岛咖啡上岛咖啡");
            data.setPrice(120);
            list.add(data);
        }
        products.addAll(list);

    }



}
