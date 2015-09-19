package com.strangecreation.sim.ciphergame;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToolSelectFragment extends TalkyFragment {
    private OnTalkyFragmentListener tListener;

    public class FragmentLinkWithTitle{
        Fragment fragment;
        public String title;

        FragmentLinkWithTitle(Fragment _fragment, String _title){
            fragment=_fragment;
            title=_title;
        }
    }

    //thanks to: http://www.101apps.co.za/index.php/articles/expandable-lists.html
    //for much of this class
    public class ToolListAdapter extends BaseExpandableListAdapter{
        private Context context;
        private HashMap<String, List<FragmentLinkWithTitle>> linkMap;
        private List<String> sectionList;

        public ToolListAdapter(Context _context,
                               HashMap<String, List<FragmentLinkWithTitle>> _linkMap,
                               List<String> _sectionList){

            context=_context;
            linkMap=_linkMap;
            sectionList=_sectionList;

        }

        @Override
        public int getGroupCount() {
            return sectionList.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return linkMap.get(sectionList.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return sectionList.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return linkMap.get(sectionList.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            //todo: find fast arbitrary solution
            return groupPosition*1000+childPosition;//there probably won't be 1000 tools for 1 group
            //so this works here
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            String groupTitle = (String) getGroup(groupPosition);
            if(convertView == null){
                LayoutInflater inflator = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflator.inflate(R.layout.tool_select_parent_layout, parent, false);
            }
            TextView parentTextView = (TextView) convertView.findViewById(R.id.toolSelectParent);
            parentTextView.setText(groupTitle);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String childTitle = ((FragmentLinkWithTitle)getChild(groupPosition, childPosition)).title;
            if(convertView == null){
                LayoutInflater inflator = (LayoutInflater)
                        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflator.inflate(R.layout.tool_select_child_layout, parent, false);
            }
            TextView childTextView = (TextView) convertView.findViewById(R.id.toolSelectChild);
            childTextView.setText(childTitle);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }


    public ToolSelectFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            tListener = (OnTalkyFragmentListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement OnTalkyFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ExpandableListView v = (ExpandableListView)inflater.inflate(R.layout.fragment_tool_select, container, false);
        HashMap<String, List<FragmentLinkWithTitle>> toolsHashMap = new HashMap<>();//todo:will set later
        List<FragmentLinkWithTitle> basicList=new ArrayList<>();

        FragmentLinkWithTitle frequencyAnalysis= new FragmentLinkWithTitle(
                new FrequencyAnalysisFragment(), "Frequency Analysis");
        basicList.add(frequencyAnalysis);

        toolsHashMap.put("Basic Tools", basicList);

        List<String> sections = new ArrayList<>(toolsHashMap.keySet());

        ToolListAdapter toolListAdapter = new ToolListAdapter(getActivity().getApplicationContext(),
                toolsHashMap, sections);
        v.setAdapter(toolListAdapter);
        v.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                FrequencyAnalysisFragment childFrag =
                        (FrequencyAnalysisFragment)
                                (
                                        (FragmentLinkWithTitle)
                                                (parent.getExpandableListAdapter()
                                                        .getChild(groupPosition, childPosition)
                                                )
                                )
                                        .fragment;
                tListener.switchFragment(childFrag);
                return true;
            }
        });

        //todo:check if view should be modified before or after creation.
        return v;

    }


}
