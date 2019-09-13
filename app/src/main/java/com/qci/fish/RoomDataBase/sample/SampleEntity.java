package com.qci.fish.RoomDataBase.sample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

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

   @ColumnInfo(name = "Remote_image_path1")
   private String pic1;

   @ColumnInfo(name = "Remote_image_path2")
   private String pic2;

   @ColumnInfo(name = "Remote_image_path3")
   private String pic3;

   @ColumnInfo(name = "Local_image_path1")
   private String local_image_path1;

   @ColumnInfo(name = "Local_image_path2")
   private String local_image_path2;

   @ColumnInfo(name = "Local_image_path3")
   private String local_image_path3;

   @ColumnInfo(name = "Sample_result")
   private String result;

   @ColumnInfo(name = "Sample_available")
   private String sample_available;

   @ColumnInfo(name = "Scanned_qrcode")
   private String scanned_qrcode;

   @ColumnInfo(name = "Latitude")
   private String latitude;

   @ColumnInfo(name = "Longitude")
   private String longitude;

   private List<SampleFishTypeList> fishtypes;

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

   public String getPic1() {
      return pic1;
   }

   public void setPic1(String pic1) {
      this.pic1 = pic1;
   }

   public String getPic2() {
      return pic2;
   }

   public void setPic2(String pic2) {
      this.pic2 = pic2;
   }

   public String getPic3() {
      return pic3;
   }

   public void setPic3(String pic3) {
      this.pic3 = pic3;
   }

   public String getLocal_image_path1() {
      return local_image_path1;
   }

   public void setLocal_image_path1(String local_image_path1) {
      this.local_image_path1 = local_image_path1;
   }

   public String getLocal_image_path2() {
      return local_image_path2;
   }

   public void setLocal_image_path2(String local_image_path2) {
      this.local_image_path2 = local_image_path2;
   }

   public String getLocal_image_path3() {
      return local_image_path3;
   }

   public void setLocal_image_path3(String local_image_path3) {
      this.local_image_path3 = local_image_path3;
   }

   public String getResult() {
      return result;
   }

   public void setResult(String result) {
      this.result = result;
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

   public List<SampleFishTypeList> getFishtypes() {
      return fishtypes;
   }

   public void setFishtypes(List<SampleFishTypeList> fishtypes) {
      this.fishtypes = fishtypes;
   }



}