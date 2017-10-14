package com.example.phamm.lichnhacnho;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

/**
 * Created by phamm on 10/12/2017.
 */

public class CustomApdapter extends BaseAdapter {
    Context context;
    private DataSnapshot ref;

    public CustomApdapter(Context context, int layout, ArrayList<NhacNhoCongViec> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    int layout;
    ArrayList<NhacNhoCongViec> list;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder = new ViewHolder();
            viewHolder.txtNgay = (TextView) convertView.findViewById(R.id.txtday);
            viewHolder.txttieude = (TextView) convertView.findViewById(R.id.txtTitle);
            viewHolder.txtNoiDung = (TextView) convertView.findViewById(R.id.txtnd);
            viewHolder.txtGio = (TextView) convertView.findViewById(R.id.txthour);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        NhacNhoCongViec nhacNhoCongViec = list.get(position);
        viewHolder.txtNgay.setText(nhacNhoCongViec.getNgayThongBao());
        viewHolder.txttieude.setText(nhacNhoCongViec.getTieuDe());
        viewHolder.txtNoiDung.setText(nhacNhoCongViec.getNoidung());
        viewHolder.txtGio.setText(nhacNhoCongViec.getGioThongBao());

        return convertView;
    }

    public class ViewHolder{
        TextView txtNgay ;
        TextView txttieude;
        TextView txtNoiDung;
        TextView txtGio;
    }
}
