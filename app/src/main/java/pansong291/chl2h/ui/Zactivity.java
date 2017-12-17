package pansong291.chl2h.ui;

import android.app.Activity;
import android.os.Bundle;
import pansong291.crash.ActivityControl;
import android.widget.Toast;
import android.content.SharedPreferences;

public class Zactivity extends Activity
{
 public static final String
  edtUnknownStr="edtUnknownStr",edtSrcStr="edtSrcStr",txtStr="txtStr";
 private SharedPreferences sp;
 @Override
 protected void onResume()
 {
  super.onResume();
  
 }
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  ActivityControl.getActivityControl().addActivity(this);
  sp=getSharedPreferences(getPackageName()+"_preferences",0);
 }

 @Override
 protected void onDestroy()
 {
  super.onDestroy();
  ActivityControl.getActivityControl().removeActivity(this);
 }
 
 public void ipPreference(String k,String v)
 {
  sp.edit().putString(k,v).commit();
 }
 
 public String opPreference(String k,String v)
 {
  return sp.getString(k,v);
 }
 
 public void toast(String s)
 {
  toast(s,0);
 }
 
 public void toast(String s,int i)
 {
  Toast.makeText(this,s,i).show();
 }
 
}
