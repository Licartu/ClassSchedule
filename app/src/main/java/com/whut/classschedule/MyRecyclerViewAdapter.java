package com.whut.classschedule;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whut.classschedule.bean.Course;
import com.whut.classschedule.bean.Exam;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 2019/5/18.
 */

class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{
    private List<Exam> list;
    public MyRecyclerViewAdapter (List<Exam> list) {
        this.list=list;
    }
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        MyRecyclerViewAdapter.ViewHolder viewHolder = new MyRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        //设置item的显示neir
        String examName=LitePal.where("exam_id=?",String.valueOf(list.get(position).getId())).find(Course.class).get(0).getCourseName();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        String examTime=ft.format(list.get(position).getTime());
        Date date=new Date();
        int differ=(int) ((list.get(position).getTime().getTime()-date.getTime()) / (1000*3600*24));
        holder.tvExamName.setText(examName);
        holder.tvExamAddress.setText(list.get(position).getAddress());
        holder.tvExamTime.setText(examTime);
        holder.tvExamDiffer.setText(String.valueOf(differ));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvExamName;
        TextView tvExamTime;
        TextView tvExamAddress;
        TextView tvExamDiffer;
        ViewHolder(View itemView) {
            super(itemView);
            tvExamName = itemView.findViewById(R.id.tv_examname);
            tvExamTime=itemView.findViewById(R.id.tv_examtime);
            tvExamAddress=itemView.findViewById(R.id.tv_examaddress);
            tvExamDiffer=itemView.findViewById(R.id.tv_differ);
        }
    }


}
