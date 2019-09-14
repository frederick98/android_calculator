package com.example.tugasbesar_01;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HalamanUtama extends Fragment implements View.OnClickListener {
    //Button btnAdd;
    Button btnResult;
    Button btnClear;
    FragmentListener listener;
    TextView resultVal;
    FloatingActionButton fab;
    Button btnDel;

    protected Presenter presenter;
    protected Adapter nla;
    protected ListView numopList;


    public HalamanUtama(){

    }

    public static HalamanUtama newInstance(){
        HalamanUtama fragment = new HalamanUtama();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.halaman_utama,container,false);

        this.resultVal = view.findViewById(R.id.tv_resultValue);

        //this.btnAdd = view.findViewById(R.id.btn_add);

        this.btnResult = view.findViewById(R.id.btn_result);
        this.btnResult.setOnClickListener(this);




         this.fab = view.findViewById(R.id.floating_action_button);
         this.btnClear = view.findViewById(R.id.btn_clear);
         this.btnDel = view.findViewById(R.id.btn_delete);



       // this.add_Button.setOnClickListener(this);
        this.fab.setOnClickListener(this);
        this.btnClear.setOnClickListener(this);

        this.numopList = view.findViewById(R.id.lv_history);
        this.nla = new Adapter(getActivity());
        numopList.setAdapter(this.nla);
        return view;
    }

    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;

        }
        else{
            throw new ClassCastException(context.toString()
                    +"must implement FragmentListener");
        }

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==this.fab.getId()){
            this.listener.changePage(2);
        }
        else if (view.getId() == this.btnClear.getId()){
            this.listener.clearList();
        }
        else if(view.getId()==this.btnResult.getId())
        {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ResultDialogFragment resultDialogFragment = new ResultDialogFragment();
            resultDialogFragment.show(ft,resultVal.getText().toString());
        }
    }


    public void addListview(Numop numops) {
        this.nla.add(numops);
    }


    public void clearListView(){
        this.nla.clearItem();
        this.resultVal.setText("0");
    }


}
