package com.qci.fish.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qci.fish.R;
import com.qci.fish.RoomDataBase.sample.SampleEntity;
import com.qci.fish.activity.SampleListActivity;
import com.qci.fish.util.AppConstants;
import com.qci.fish.viewModel.SampleListViewModel;
import com.qci.fish.viewModel.SampleModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionStage_third extends BaseFragment {

    TextView tv_title,tv_count;

    @BindView(R.id.radio_negative)
    RadioButton radio_negative;

    @BindView(R.id.radio_Positive)
    RadioButton radio_Positive;

    @BindView(R.id.radio_Send_back)
    RadioButton radio_Send_back;

    @BindView(R.id.radio_Exempted_Trucks)
    RadioButton radio_Exempted_Trucks;

    @BindView(R.id.submit_third_stage)
    Button submit_third_stage;

    private String staus_result = "";

    private int local_id;

    private String click_type;

    private SampleModel SampleModel;

    private SampleListViewModel sampleListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collectionstage_third, container, false);

        ButterKnife.bind(this, view);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_count = (TextView) view.findViewById(R.id.tv_count);

        sampleListViewModel = ViewModelProviders.of(this).get(SampleListViewModel.class);

        // local_id
        local_id = getArguments().getInt("local_id");

        click_type = getArguments().getString("click_type");

        if (click_type.equalsIgnoreCase("first")){
            getList();
        }else {
            SampleGetData(local_id);
        }

        tv_title.setText("Capture Result");
        tv_count.setText("3/4 >");

        radio_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                if (checked){
                    staus_result = "negative";
                }
            }
        });

        radio_Positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                if (checked){
                    staus_result = "Positive";
                }

            }
        });

        radio_Send_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                if (checked){
                  staus_result = "Send_back";
                }
            }
        });

        radio_Exempted_Trucks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();

                if (checked){
                  staus_result = "Exempted_Trucks";
                }
            }
        });

        submit_third_stage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sampleEntityView.setResult(staus_result);
                sampleListViewModel.UpdateSample(sampleEntityView);

                CollectionStage_fourth stage_fouth = new CollectionStage_fourth();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("local_id",local_id);
                bundle.putString("click_type",click_type);
                stage_fouth.setArguments(bundle);
                ft.replace(R.id.frame_layout,stage_fouth,"newFragment");
                ft.addToBackStack("my_fragment");
                ft.commit();
            }
        });

        return view;

    }

    private void SampleGetData(int localSampleId){

        com.qci.fish.viewModel.SampleModel.Factory factory = new SampleModel.Factory(getActivity().getApplication(),localSampleId);


        SampleModel = new ViewModelProvider(this,factory).get(com.qci.fish.viewModel.SampleModel.class);

        SampleModel.getObservableSample().observe(this, new Observer<SampleEntity>() {
            @Override
            public void onChanged(SampleEntity sampleEntity) {
                if (sampleEntity != null) {

                    sampleEntityView = sampleEntity;
                    if (sampleEntityView.getResult() != null){
                        staus_result = sampleEntityView.getResult();

                        if (staus_result.equalsIgnoreCase("negative")){
                            radio_negative.setChecked(true);
                        }else if (staus_result.equalsIgnoreCase("Positive")){
                            radio_Positive.setChecked(true);
                        }else if (staus_result.equalsIgnoreCase("Send_back")){
                            radio_Send_back.setChecked(true);
                        }else if (staus_result.equalsIgnoreCase("Exempted_Trucks")){
                            radio_Exempted_Trucks.setChecked(true);
                        }
                    }
                }
            }
        });
    }
}
