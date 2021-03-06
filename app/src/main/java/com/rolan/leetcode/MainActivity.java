package com.rolan.leetcode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    public static String[] datas = {
            "001", "002","003","004","005","007","008","009","011","014",
            "019","020", "021","023","024","026", "028","033","043","046",
            "061","075", "078","082","083","088","092","094","100","102",
            "104","108", "111", "121","122","123","136","141","146","147",
            "148", "155","160","203", "206","226", "231","234","237", "268","275",
            "301", "310","326", "344","374","415","445", "455",
            "Z001", "Z002","Z003","Z004","Z005","Z006","Z007",
            "J07","J09","J55",
            "M1002"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.activities.push(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ContainerAdapter containerAdapter = new ContainerAdapter(this);
        containerAdapter.setItemClickListener(this::doItemClick);
        recyclerView.setAdapter(containerAdapter);
    }

    private static IEngine newInstanceByName(String name) {
        try {
            Class<?> aClass = Class.forName(name);
            return (IEngine) aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void doItemClick(int position) {
        String className = "com.rolan.leetcode.tasks.Chapter" + datas[position];
        IEngine iEngine = newInstanceByName(className);
        if (iEngine != null) iEngine.doMath();
    }

    public static class ContainerAdapter extends RecyclerView.Adapter<ContainerAdapter.ContainerViewHolder> {
        Context mContext;
        OnItemClickListener itemClickListener;

        ContainerAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @NonNull
        @Override
        public ContainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View inflate = View.inflate(mContext, R.layout.item_container, null);
            return new ContainerViewHolder(inflate);
        }

        @Override
        public void onBindViewHolder(@NonNull ContainerViewHolder holder, int position) {
            holder.button.setText(datas[position]);
            String className = "com.rolan.leetcode.tasks.Chapter" + datas[position];
            IEngine iEngine = newInstanceByName(className);
            if (iEngine != null)
                holder.desc.setText(iEngine.getQuestion());
            holder.button.setOnClickListener(v -> {
                if (itemClickListener != null) itemClickListener.click(position);
            });
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }

        void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        class ContainerViewHolder extends RecyclerView.ViewHolder {
             Button button;
             TextView desc;

             ContainerViewHolder(@NonNull View itemView) {
                super(itemView);
                button = itemView.findViewById(R.id.btn_item);
                desc = itemView.findViewById(R.id.tv_desc);
            }
        }

        public  interface OnItemClickListener {
            void click(int postion);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.activities.pop();
    }
}
