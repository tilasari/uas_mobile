package com.hendra.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hendra.myapplication.model.Pahlawan;

import java.util.List;

public class daftarpahlawanAdapter  extends ArrayAdapter<Pahlawan> {
    Context context;

    public daftarpahlawanAdapter(@NonNull Context context, @NonNull List<Pahlawan> objects) {
        super(context, R.layout.row_pahlawan, objects);
        this.context = context;
    }

    class ViewHolder{
        TextView txnama;
        TextView txasal;
        TextView txprofil;
        TextView txthnlahir;
        TextView txthnwafat;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pahlawan tr = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_pahlawan, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txnama = convertView.findViewById(R.id.row_nama);
            viewHolder.txprofil = convertView.findViewById(R.id.row_profil);
            viewHolder.txasal = convertView.findViewById(R.id.row_asal);
            viewHolder.txthnlahir = convertView.findViewById(R.id.row_thnlahir);
            viewHolder.txthnwafat = convertView.findViewById(R.id.row_thnwafat);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txnama.setText(tr.getNama());
        viewHolder.txprofil.setText(tr.getProfil());
        viewHolder.txthnlahir.setText(tr.getThnlahir());
        viewHolder.txthnwafat.setText(tr.getThnwafat());
        if (tr.getAsal().equals(Pahlawan.BANDUNG)) {
            viewHolder.txasal.setText("BANDUNG");
        } else if (tr.getAsal().equals(Pahlawan.YOGYAKARTA)) {
            viewHolder.txasal.setText("YOGYAKARTA");
        } else if (tr.getAsal().equals(Pahlawan.LOMBOK)) {
            viewHolder.txasal.setText("LOMBOK");
        } else {
            viewHolder.txasal.setText("UMUM");
        }
        return convertView;
    }
}
