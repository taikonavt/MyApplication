package com.example.maxim.p0453mylist;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    String[] groups = new String[] {"First List", "Second List", "Third List"};

    String[] elements = new String[] {"Element one", "Element two", "Element three"};

    ArrayList<Map<String, String>> groupList;

    ArrayList<Map<String, String>> childList;

    ArrayList<ArrayList<Map<String, String>>> listOfchildList;

    HashMap<String, String> attribOfgroup; //добавил hashMap
    Map<String, String> attribOfchild;

    ExpandableListView expList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupList = new ArrayList<Map<String, String>> ();
        for(String i : groups) {
            attribOfgroup = new HashMap<String, String>();
            attribOfgroup.put("groupName", i);
            groupList.add(attribOfgroup);
        }

        String groupFrom[] = new String[] {"groupName"};
        int groupTo[] = new int[] {R.id.text1};

        listOfchildList = new ArrayList<ArrayList<Map<String, String>>>();

        childList = new ArrayList<Map<String, String>> ();
        for(String str : elements) {
            attribOfchild = new HashMap<String, String>();
            attribOfchild.put("childName", str);
            childList.add(attribOfchild);
        }
        listOfchildList.add(childList);

        String childFrom[] = new String[] {"childName"};
        int childTo[] = new int[] {R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter (
                this,
                groupList,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                listOfchildList,
                R.layout.my_simple_list,
                childFrom,
                childTo);

        expList = (ExpandableListView) findViewById(R.id.expList);
        expList.setAdapter(adapter);

    }




}
