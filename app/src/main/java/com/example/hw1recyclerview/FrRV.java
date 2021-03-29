package com.example.hw1recyclerview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FrRV extends Fragment {

    public FrRV() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rv_fr, container, false);

        List<DataSource.NumberModel> elements = DataSource.getInstance().getData();
        final RecyclerView recyclerView = view.findViewById(R.id.recycler);
        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            columns = 3;
        } else columns = 4;

        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), columns));
        final MyAdapter adapter = new MyAdapter(elements);
        recyclerView.setAdapter(adapter);

        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(adapter.getItemCount());
                DataSource.getInstance().addDataOnClick();
                adapter.updateData(DataSource.getInstance().getData());
                adapter.notifyItemInserted(adapter.getItemCount());
            }
        });
        return view;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number_from_rv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FrOfNumber NewNumber = new FrOfNumber();
                    NewNumber.SetArguments(((TextView) v.findViewById(R.id.number_from_rv)).getText().toString()
                            , ((TextView) v.findViewById(R.id.number_from_rv)).getCurrentTextColor());
                    getFragmentManager().beginTransaction()
                            .replace(R.id.container_of_fragments, NewNumber)
                            .addToBackStack(FrOfNumber.class.getSimpleName()).commit();
                }
            });
        }

    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        public List<DataSource.NumberModel> Data;

        public MyAdapter(List<DataSource.NumberModel> data) {
            this.Data = data;
        }

        public void updateData(List<DataSource.NumberModel> data) {
            this.Data = data;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_elem, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            DataSource.NumberModel element = Data.get(position);
            holder.number.setText(String.valueOf(element.getNumber()));
            holder.number.setTextColor(element.getColor());
        }

        @Override
        public int getItemCount() {
            return Data.size();
        }
    }


}