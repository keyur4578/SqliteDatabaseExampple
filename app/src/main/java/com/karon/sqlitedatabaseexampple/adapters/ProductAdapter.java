package com.karon.sqlitedatabaseexampple.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.karon.sqlitedatabaseexampple.R;
import com.karon.sqlitedatabaseexampple.classfiles.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    Context context;
    ArrayList<Product> productlist;
    OnUserClickListner listner;

    public interface OnUserClickListner
    {
        void onDeleteButtonClick(Product obj);
        void onEditButtonClick(Product obj);
    }


    public ProductAdapter(Context context, ArrayList<Product> productlist,OnUserClickListner listner)
    {
        this.context = context;
        this.productlist = productlist;
        this.listner = listner;
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
        Button btnDelete = (Button) view.findViewById(R.id.btnDelete);
        Button btnEdit = (Button) view.findViewById(R.id.btnEdit);


        Product obj = productlist.get(position);
        lblProductName.setText(obj.getProduct_name().toString());
        lblProductQty.setText(String.valueOf(obj.getProduct_qty()));
        lblProductPrice.setText("Rs." + String.valueOf(obj.getProduct_price()));
//        lblProductName.setText(obj.product_name.toString());
//        lblProductQty.setText(String.valueOf(obj.product_qty));
//        lblProductPrice.setText("Rs." + String.valueOf(obj.product_price));

        btnDelete.setOnClickListener(v->listner.onDeleteButtonClick(obj));
        btnEdit.setOnClickListener(v->listner.onEditButtonClick(obj));


        return view;
    }

}
