package com.qci.fish.fragment;


import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.qci.fish.R;
import com.qci.fish.RoomDataBase.sample.SampleEntity;
import com.qci.fish.RoomDataBase.sample.SampleFishTypeList;
import com.qci.fish.activity.SampleListActivity;
import com.qci.fish.viewModel.SampleListViewModel;
import com.qci.fish.viewModel.SampleModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionStage_fourth extends BaseFragment implements QRCodeReaderView.OnQRCodeReadListener {

    private View view;

    private static QRCodeReaderView qrCodeReaderView;

    TextView tv_title,tv_count;

    @BindView(R.id.btn_Image_submit)
    Button btn_Image_submit;

    private int local_id;

    private String click_type;

    private SampleModel SampleModel;

    private String qr_result;

    private SampleListViewModel sampleListViewModel;


    public CollectionStage_fourth() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_collection_stage_fourth, container, false);

        ButterKnife.bind(this, view);

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_count = (TextView) view.findViewById(R.id.tv_count);

        tv_title.setText("Sample Qr scan");
        tv_count.setText("4/4 >");

        sampleListViewModel = ViewModelProviders.of(this).get(SampleListViewModel.class);

        // local_id
        local_id = getArguments().getInt("local_id");

        click_type = getArguments().getString("click_type");

        if (click_type.equalsIgnoreCase("first")){
            getList();
        }else {
            SampleGetData(local_id);
        }

        btn_Image_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Intent intent = new Intent(getActivity(), SampleListActivity.class);
                startActivity(intent);*/
                getActivity().finish();
            }
        });

        qrCodeReaderView = (QRCodeReaderView) view.findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(1000);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
        qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        return view;
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        qr_result = text;

        sampleEntityView.setScanned_qrcode(qr_result);
        sampleListViewModel.UpdateSample(sampleEntityView);

        getActivity().finish();

    }

    @Override
    public void onResume() {
        super.onResume();

        qrCodeReaderView.startCamera();
    }

    private void SampleGetData(int localSampleId){

        com.qci.fish.viewModel.SampleModel.Factory factory = new SampleModel.Factory(getActivity().getApplication(),localSampleId);

        SampleModel = new ViewModelProvider(this,factory).get(com.qci.fish.viewModel.SampleModel.class);

        SampleModel.getObservableSample().observe(this, new Observer<SampleEntity>() {
            @Override
            public void onChanged(SampleEntity sampleEntity) {
                if (sampleEntity != null) {

                    sampleEntityView = sampleEntity;
                }
            }
        });
    }
}
