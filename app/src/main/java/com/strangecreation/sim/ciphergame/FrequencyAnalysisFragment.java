package com.strangecreation.sim.ciphergame;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrequencyAnalysisFragment extends TalkyFragment {

    int[] letterUsage;
    int totalLetterUsage;

    public FrequencyAnalysisFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        letterUsage = new int[26];

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frequency_analysis, container, false);

        EditText analysisTextBox = (EditText)v.findViewById(R.id.frequencyAnalysisEditText);

        final BarChart frequencyChart = (BarChart)v.findViewById(R.id.frequencyAnalysisChart);

        ArrayList<BarEntry> freqVals= new ArrayList<>();
        for(int i=0;i<26;++i){freqVals.add(new BarEntry(0,i));}

        BarDataSet bSet= new BarDataSet(freqVals, "Frequency");

        final ArrayList<String> names = new ArrayList<>();

        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        names.add("e");
        names.add("f");
        names.add("g");
        names.add("h");
        names.add("i");
        names.add("j");
        names.add("k");
        names.add("l");
        names.add("m");
        names.add("n");
        names.add("o");
        names.add("p");
        names.add("q");
        names.add("r");
        names.add("s");
        names.add("t");
        names.add("u");
        names.add("v");
        names.add("w");
        names.add("x");
        names.add("y");
        names.add("z");

        BarData freqData = new BarData(names, bSet);
        frequencyChart.setData(freqData);

        analysisTextBox.addTextChangedListener(new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //
                   }

                   @Override
                   public void onTextChanged(CharSequence s, int start, int before, int count) {
                        //todo:use this for fast implementation
                       //todo: use addentry and removeentry for speed
                   }

                   @Override
                   public void afterTextChanged(Editable s) {
                        totalLetterUsage = s.length();

                       for(int i=0;i<26;++i){letterUsage[i]=0;}

                       for(int i=0; i<totalLetterUsage; ++i){
                           Character a = 'a';
                           Character c = Character.toLowerCase(s.charAt(i));
                           if(c.compareTo(a)>0 && c.compareTo(a)<26){
                               letterUsage[c-a]++;
                           }
                       }

                       //List<BarDataSet> sets = frequencyChart.getBarData().getDataSets();
                       frequencyChart.getBarData().removeDataSet(0);

                       ArrayList<BarEntry> freqVals= new ArrayList<>();
                       for(int i=0;i<26;++i){freqVals.add(new BarEntry(letterUsage[i],i));}

                       BarDataSet bSet= new BarDataSet(freqVals, "Frequency");

                       BarData freqData = new BarData(names, bSet);

                       frequencyChart.setData(freqData);

                       frequencyChart.invalidate();
                   }
               }

        );

        return v;
    }


}
