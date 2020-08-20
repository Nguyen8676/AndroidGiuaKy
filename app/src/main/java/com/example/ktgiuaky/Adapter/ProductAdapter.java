package com.example.ktgiuaky.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ktgiuaky.Entity.Product;
import com.example.ktgiuaky.MainActivity;
import com.example.ktgiuaky.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Product> productList;

    public ProductAdapter(Context context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtId,txtTenSp,txtCost;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtId=(TextView)view.findViewById(R.id.txvID);
            holder.txtTenSp=(TextView)view.findViewById(R.id.txvName);
            holder.txtCost=(TextView)view.findViewById(R.id.txvCost);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        Product product=productList.get(i);


       holder.txtId.setText("id:"+product.getId());
        holder.txtTenSp.setText("ten sp:"+product.getTenSP());

        Double cost=product.getCost();

        holder.txtCost.setText("gia:"+cost.toString()+"$");

        return view;
    }
}
