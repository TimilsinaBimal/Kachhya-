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
        super(context, R.layout.home_routine_items, Routines);
        this.context = context;
        this.Routines = Routines;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.home_routine_items, null, true);

        TextView SubjectName = (TextView) listViewItem.findViewById(R.id.subject);
        TextView StartTime = (TextView) listViewItem.findViewById(R.id.timestart);
        TextView EndTime= (TextView)listViewItem.findViewById(R.id.timeend);

        Routine_Data Routine = Routines.get(position);
        
        SubjectName.setText(Routine.getSubjectName());
        StartTime.setText(Routine.getStartTime());
        EndTime.setText(Routine.getEndTime());
        return listViewItem;
    }
    }
