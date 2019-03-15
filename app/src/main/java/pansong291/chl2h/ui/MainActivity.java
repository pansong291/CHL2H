package pansong291.chl2h.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import pansong291.chl2h.R;
import pansong291.chl2h.utils.MyTask;
import android.text.Html;
import android.content.Intent;

public class MainActivity extends Zactivity implements OnClickListener
{
 EditText edt_unknown,edt_src;
 TextView txt;
 
 AlertDialog altdlg=null;
 
 MyTask mt;
 Pattern pat;
 
 @Override
 protected void onCreate(Bundle savedInstanceState)
 {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  
  edt_unknown=(EditText)findViewById(R.id.edt_main_unknown_class);
  edt_src=(EditText)findViewById(R.id.edt_main_src);
  txt=(TextView)findViewById(R.id.txt_main_output);
 }
 
 public void onBtnClick(View v)
 {
  String s=edt_unknown.getText().toString();
  if(!s.equals(""))
  {
   if(startWithD(s))
   {
    toast("类名不能以数字开头");
    return;
   }
   s=s.replaceAll("\\.+",".");
   if(s.startsWith("."))
    s=s.substring(1);
   if(s.endsWith("."))
    s=s.substring(0,s.length()-1);
   s="\\b"+s.replace(".","\\b|\\b")+"\\b";
   //edt_unknown.setText(s);
  }
  mt=new MyTask(this);
  mt.execute(s,edt_src.getText().toString());
 }
 
 public void onTxtClick(View v)
 {
  if(altdlg==null)
  {
   altdlg=new AlertDialog.Builder(this)
   .setMessage("选择一个操作")
   .setPositiveButton("复制内容",this)
   .setNegativeButton("写到文件",this)
   .setNeutralButton("预览",this)
   .create();
  }
  altdlg.show();
 }
 
 public void onClick(DialogInterface p1,int p2)
 {
  switch(p2)
  {
   case DialogInterface.BUTTON_POSITIVE:
    ((android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setText(txt.getText());
    toast("已复制");
    break;
   
   case DialogInterface.BUTTON_NEGATIVE:
    write2File(txt.getText().toString());
    toast("已写到设备储存中");
    break;
    
   case DialogInterface.BUTTON_NEUTRAL:
    Intent it = new Intent(this, WebActivity.class);
    it.putExtra("data",txt.getText());
    startActivity(it);
    break;
  }
 }
 
 public void setResult(String s)
 {
  txt.setText(s);
 }
 
 private boolean startWithD(String s)
 {
  if(pat==null)pat=Pattern.compile("^\\d|\\.\\d");
  return pat.matcher(s).find();
 }
 
 @Override
 protected void onResume()
 {
  super.onResume();
  edt_unknown.setText(opPreference(edtUnknownStr,""));
  edt_src.setText(opPreference(edtSrcStr,""));
  txt.setText(opPreference(txtStr,""));
 }

 @Override
 protected void onStop()
 {
  super.onStop();
  ipPreference(edtUnknownStr,edt_unknown.getText().toString());
  ipPreference(edtSrcStr,edt_src.getText().toString());
  ipPreference(txtStr,txt.getText().toString());
 }

 private boolean write2File(String s)
 {
  boolean b=false;
  File dir=Environment.getExternalStorageDirectory();
  String fileNameString="JavaCode_"+System.currentTimeMillis()+".html";
  File file=new File(dir,fileNameString);

  BufferedWriter bw=null;
  try{
   bw=new BufferedWriter(new FileWriter(file));
   bw.write(s);
   b=true;
  }catch(IOException e)
  {
   e.printStackTrace();
  }
  try{
   if(bw!=null)bw.close();
  }catch(Exception e)
  {
   e.printStackTrace();
  }
  return b;
 }
 
}
