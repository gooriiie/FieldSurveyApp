package com.example.myfirstapp;

import static android.widget.Toast.makeText;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable {
    private Context ctx;
    private List<Item> items = null;
    private ArrayList<Item> arrayList;
    private List<Item> unfilterList;
    private List<Item> filterList;

    OnAddressItemClickListener listener;

    public ItemAdapter(Context context, List<Item> items) {
        this.ctx = context;
        this.items = items;
        this.unfilterList = items;
        this.filterList = items;
        arrayList = new ArrayList<Item>();
        arrayList.addAll(items);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item, null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = filterList.get(position);
        holder.tv_nickName.setText(item.getNickName());
        holder.tv_address.setText(item.getAddress());
    }

    @Override
    public int getItemCount() {
        return this.filterList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String str = charSequence.toString();
                if (str.isEmpty()) {
                    filterList = unfilterList;
                } else {
                    List<Item> filteringList = new ArrayList<>();

                    for (Item item : unfilterList) {
                        if (item.getAddress().contains(str) || item.getNickName().contains(str)) {
                            filteringList.add(item);
                        }
                    }

                    filterList = filteringList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filterList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filterList = (List<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_nickName;
        TextView tv_address;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_nickName = (TextView) itemView.findViewById(R.id.item_nickName);
            tv_address = (TextView) itemView.findViewById(R.id.item_address);
            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(ctx, MainActivity7.class);

                        intent.putExtra("address", items.get(pos).getAddress());
                        intent.putExtra("nickName", items.get(pos).getNickName());

                        ctx.startActivity(intent);
                    }
                }
            });
        }
    }

}
