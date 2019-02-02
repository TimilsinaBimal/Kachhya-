package com.example.prati.kachhya;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Routine_List extends ArrayAdapter<Routine_Data> {
    private Activity context;
    private List<Routine_Data> Routines;

    public Routine_List(Activity context, List<Routine_Data>Routines) {
        super(context, R.layout.routine_list, Routines);
        this.context = context;
        this.Routines = Routines;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.routine_list, null, true);

        TextView SubjectName = (TextView) listViewItem.findViewById(R.id.subject);
        TextView StartTime = (TextView) listViewItem.findViewById(R.id.substarttime);
        TextView EndTime= (TextView)listViewItem.findViewById(R.id.subendtime);
        TextView  Day= (TextView)  listViewItem.findViewById(R.id.subday);

        Routine_Data Routine = Routines.get(position);
        SubjectName.setText(Routine_Data.getSubjectName());
        Day.setText(Routine_Data.getDay());
        StartTime.setText(Routine_Data.getStartTime());
        EndTime.setText(Routine_Data.getEndTime());
        return listViewItem;
    }
    }
