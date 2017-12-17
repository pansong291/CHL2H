package pansong291.chl2h.ui;

import android.app.*;
import android.os.*;
import pansong291.chl2h.R;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import pansong291.chl2h.utils.MyTask;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import java.util.regex.Pattern;

public class MainActivity extends Zactivity 
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
   .setMessage("复制内容")
   .setPositiveButton("确定",new OnClickListener()
    {
     @Override
     public void onClick(DialogInterface p1,int p2)
     {
      ((android.text.ClipboardManager)getSystemService(CLIPBOARD_SERVICE)).setText(txt.getText());
      toast("已复制");
     }
    })
   .setNegativeButton("取消",null)
   .create();
  }
  altdlg.show();
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
 
}
