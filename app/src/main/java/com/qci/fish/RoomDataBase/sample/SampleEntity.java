package com.qci.fish.RoomDataBase.sample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.qci.fish.pojo.ImageCapturePojo;
import com.qci.fish.pojo.ResultCapturePojo;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Samples")
public class SampleEntity {

    @PrimaryKey(autoGenerate = true)
    private int localSampleId;

    @ColumnInfo(name = "unique_id")
    private int unique_id;

    @ColumnInfo(name = "Sample_Id")
    private String sampleid;

    @ColumnInfo(name = "Location_Name")
    private String locationname;

    @ColumnInfo(name = "Sample_collection_Time")
    private String samplecollectiondate_str;

    @ColumnInfo(name = "Consignee_name")
    private String consigneename;

    @ColumnInfo(name = "Consignee_number")
    private String consignmentno;

    @ColumnInfo(name = "Truck_number")
    private String truckno;

    @ColumnInfo(name = "Truck_driver_name")
    private String truckdrivername;

    @ColumnInfo(name = "Truck_driver_mobile_number")
    private String truckdrivermobile;

    @ColumnInfo(name = "fda_licence_number")
    private String fssai_fda_licenceno;

    @ColumnInfo(name = "Sample_available")
    private String sample_available;

    @ColumnInfo(name = "Scanned_qrcode")
    private String scanned_qrcode;

    @ColumnInfo(name = "Latitude")
    private String latitude;

    @ColumnInfo(name = "Longitude")
    private String longitude;

    private ArrayList<SampleFishTypeList> fishtypes;

    private ArrayList<ImageCapturePojo> imageCapture_list;

    private ArrayList<ResultCapturePojo>resultCapture_list;

    public ArrayList<ResultCapturePojo> getResultCapture_list() {
        return resultCapture_list;
    }

    public void setResultCapture_list(ArrayList<ResultCapturePojo> resultCapture_list) {
        this.resultCapture_list = resultCapture_list;
    }



    public ArrayList<ImageCapturePojo> getImageCapture_list() {
        return imageCapture_list;
    }

    public void setImageCapture_list(ArrayList<ImageCapturePojo> imageCapture_list) {
        this.imageCapture_list = imageCapture_list;
    }

    public ArrayList<SampleFishTypeList> getFishtypes() {
        return fishtypes;
    }

    public void setFishtypes(ArrayList<SampleFishTypeList> fishtypes) {
        this.fishtypes = fishtypes;
    }


    public int getLocalSampleId() {
        return localSampleId;
    }

    public void setLocalSampleId(int localSampleId) {
        this.localSampleId = localSampleId;
    }

    public int getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(int unique_id) {
        this.unique_id = unique_id;
    }

    public String getSampleid() {
        return sampleid;
    }

    public void setSampleid(String sampleid) {
        this.sampleid = sampleid;
    }

    public String getLocationname() {
        return locationname;
    }

    public void setLocationname(String locationname) {
        this.locationname = locationname;
    }

    public String getSamplecollectiondate_str() {
        return samplecollectiondate_str;
    }

    public void setSamplecollectiondate_str(String samplecollectiondate_str) {
        this.samplecollectiondate_str = samplecollectiondate_str;
    }

    public String getConsigneename() {
        return consigneename;
    }

    public void setConsigneename(String consigneename) {
        this.consigneename = consigneename;
    }

    public String getConsignmentno() {
        return consignmentno;
    }

    public void setConsignmentno(String consignmentno) {
        this.consignmentno = consignmentno;
    }

    public String getTruckno() {
        return truckno;
    }

    public void setTruckno(String truckno) {
        this.truckno = truckno;
    }

    public String getTruckdrivername() {
        return truckdrivername;
    }

    public void setTruckdrivername(String truckdrivername) {
        this.truckdrivername = truckdrivername;
    }

    public String getTruckdrivermobile() {
        return truckdrivermobile;
    }

    public void setTruckdrivermobile(String truckdrivermobile) {
        this.truckdrivermobile = truckdrivermobile;
    }

    public String getFssai_fda_licenceno() {
        return fssai_fda_licenceno;
    }

    public void setFssai_fda_licenceno(String fssai_fda_licenceno) {
        this.fssai_fda_licenceno = fssai_fda_licenceno;
    }

    public String getSample_available() {
        return sample_available;
    }

    public void setSample_available(String sample_available) {
        this.sample_available = sample_available;
    }

    public String getScanned_qrcode() {
        return scanned_qrcode;
    }

    public void setScanned_qrcode(String scanned_qrcode) {
        this.scanned_qrcode = scanned_qrcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}