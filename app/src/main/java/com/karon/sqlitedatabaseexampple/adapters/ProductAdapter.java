package com.karon.sqlitedatabaseexampple.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.karon.sqlitedatabaseexampple.R;
import com.karon.sqlitedatabaseexampple.classfiles.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> productlist;


    public ProductAdapter(Context context, ArrayList<Product> productlist)
    {
        this.context = context;
        this.productlist = productlist;
    }

    @Override
    public int getCount() {
        return productlist.size();
    }

    @Override
    public Object getItem(int i) {
        return productlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.productsingleitem,null);
        TextView lblProductName = (TextView) view.findViewById(R.id.lblProductName);
        TextView lblProductQty = (TextView) view.findViewById(R.id.lblProductQty);
        TextView lblProductPrice = (TextView) view.findViewById(R.id.lblProductPrice);

        Product obj = productlist.get(position);
        lblProductName.setText(obj.product_name.toString());
        lblProductQty.setText(String.valueOf(obj.product_qty));
        lblProductPrice.setText("Rs." + String.valueOf(obj.product_price));


        return view;
    }
}
