package com.qci.fish.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.qci.fish.R;
import com.qci.fish.RoomDataBase.sample.SampleEntity;
import com.qci.fish.RoomDataBase.sampleImage.SampleImageEntity;
import com.qci.fish.activity.SampleListActivity;
import com.qci.fish.viewModel.SampleImageViewModel;
import com.qci.fish.viewModel.SampleListViewModel;
import com.qci.fish.viewModel.SampleModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionStage_second extends BaseFragment {

    TextView tv_title,tv_count;

    private static final String CAMERA_DIR = "/dcim/";
    private Uri picUri;
    private File imageF;

    @BindView(R.id.llImageCollection1)
    LinearLayout llImageCollection1;

    @BindView(R.id.llImageCollection2)
    LinearLayout llImageCollection2;

    @BindView(R.id.llImageCollection3)
    LinearLayout llImageCollection3;



    @BindView(R.id.ivImageCollection1)
    ImageView ivImageCollection1;

    @BindView(R.id.ivImageCollection2)
    ImageView ivImageCollection2;

    @BindView( R.id.ivImageCollection3)
    ImageView ivImageCollection3;

    private String imagepath_1,imagepath_2,imagepath_3;

    @BindView(R.id.btn_Image_submit)
    Button btn_Image_submit;

    private SampleImageViewModel sampleImageViewModel;

    private int local_id;

    private String local;

    private SampleModel SampleModel;

    private String click_type;


    private SampleListViewModel sampleListViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collectionstage_second, container, false);

        ButterKnife.bind(this, view);

        sampleListViewModel = ViewModelProviders.of(this).get(SampleListViewModel.class);

        // local_id
        local_id = getArguments().getInt("local_id");

        click_type = getArguments().getString("click_type");

        if (click_type.equalsIgnoreCase("first")){
            getList();
        }else {
            SampleGetData(local_id);
        }


        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_count = (TextView) view.findViewById(R.id.tv_count);

        tv_title.setText("Capture Photos");
        tv_count.setText("2/4 >");


        return view;

    }

    @OnClick({R.id.llImageCollection1,R.id.llImageCollection2,R.id.llImageCollection3,R.id.btn_Image_submit})
    public void onViewClicked(View view){
     switch (view.getId()){
         case R.id.llImageCollection1:
             captureImage(1);
             break;

         case R.id.llImageCollection2:
             if (imagepath_1 != null){
              captureImage(2);
             }else {
                 Toast.makeText(getActivity(),"Please Capture first image before capture second image ",Toast.LENGTH_LONG).show();
             }
             break;

         case R.id.llImageCollection3:
               if (imagepath_1 != null && imagepath_2 != null){
               captureImage(3);
               }else {
                   Toast.makeText(getActivity(),"Please Capture first and second image before capture third image ",Toast.LENGTH_LONG).show();
               }
             break;

         case R.id.btn_Image_submit:

             sampleEntityView.setLocal_image_path1(imagepath_1);
             sampleEntityView.setLocal_image_path2(imagepath_2);
             sampleEntityView.setLocal_image_path3(imagepath_3);

             sampleListViewModel.UpdateSample(sampleEntityView);

             CollectionStage_third stage_third = new CollectionStage_third();
             FragmentTransaction ft = getFragmentManager().beginTransaction();
             Bundle bundle = new Bundle();
             bundle.putInt("local_id",local_id);
             bundle.putString("click_type",click_type);
             stage_third.setArguments(bundle);
             ft.replace(R.id.frame_layout,stage_third,"newFragment");
             ft.addToBackStack("my_fragment");
             ft.commit();

             break;
      }
    }




    private void captureImage(int CAMERA_REQUEST) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "bmh" + timeStamp + "_";
            File albumF = getAlbumDir();
            imageF = File.createTempFile(imageFileName, "bmh", albumF);
            picUri = Uri.fromFile(imageF);

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageF));
            } else {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getActivity(), getActivity().getApplicationContext().getPackageName() + ".provider", imageF));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        startActivityForResult(takePictureIntent, CAMERA_REQUEST);

    }

    private File getAlbumDir() {
        File storageDir = null;

        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
                storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "CameraPicture");
            } else {
                storageDir = new File(Environment.getExternalStorageDirectory() + CAMERA_DIR + "CameraPicture");
            }

            if (storageDir != null) {
                if (!storageDir.mkdirs()) {
                    if (!storageDir.exists()) {
                        //		Log.d("CameraSample", "failed to create directory");
                        return null;
                    }
                }
            }

        } else {
            //		Log.v(getString(R.string.app_name), "External storage is not mounted READ/WRITE.");
        }

        return storageDir;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {
                if (picUri != null) {
                    Uri uri = picUri;
                    imagepath_1 = compressImage(uri.toString());

                    try {
                        Glide.with(getActivity()).load(imagepath_1)
                                //           .thumbnail(0.5f)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivImageCollection1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (requestCode == 2) {
                if (picUri != null) {
                    Uri uri = picUri;
                    imagepath_2 = compressImage(uri.toString());

                    try {
                        Glide.with(getActivity()).load(imagepath_2)
                                //           .thumbnail(0.5f)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivImageCollection2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
            else if (requestCode == 3) {
                if (picUri != null) {
                    Uri uri = picUri;
                    imagepath_3 = compressImage(uri.toString());

                    try {
                        Glide.with(getActivity()).load(imagepath_3)
                                //           .thumbnail(0.5f)
                                .crossFade()
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(ivImageCollection3);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //


    private void SampleGetData(int localSampleId){

        com.qci.fish.viewModel.SampleModel.Factory factory = new SampleModel.Factory(getActivity().getApplication(),localSampleId);


        SampleModel = new ViewModelProvider(this,factory).get(com.qci.fish.viewModel.SampleModel.class);

        SampleModel.getObservableSample().observe(this, new Observer<SampleEntity>() {
            @Override
            public void onChanged(SampleEntity sampleEntity) {
                if (sampleEntity != null){
                    sampleEntityView = sampleEntity;
                    if (sampleEntity.getLocal_image_path1() != null){
                        try {
                            imagepath_1 = sampleEntity.getLocal_image_path1();
                            Glide.with(getActivity()).load(imagepath_1)
                                    //           .thumbnail(0.5f)
                                    .crossFade()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivImageCollection1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (sampleEntity.getLocal_image_path2() != null){
                        try {
                            imagepath_2 = sampleEntity.getLocal_image_path2();
                            Glide.with(getActivity()).load(imagepath_2)
                                    //           .thumbnail(0.5f)
                                    .crossFade()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivImageCollection2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (sampleEntity.getLocal_image_path3() != null){
                        try {
                            imagepath_3 = sampleEntity.getLocal_image_path3();
                            Glide.with(getActivity()).load(imagepath_3)
                                    //           .thumbnail(0.5f)
                                    .crossFade()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(ivImageCollection3);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
