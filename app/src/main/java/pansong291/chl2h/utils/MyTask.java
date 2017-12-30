package pansong291.chl2h.utils;

import android.os.AsyncTask;
import pansong291.chl2h.ui.MainActivity;
import pansong291.chl2h.R;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;

public class MyTask extends AsyncTask<String,Integer,String>
{
 MainActivity ma;
 Pattern p;
 Matcher m;
 ArrayList<StrIndex>listSI=new ArrayList<StrIndex>();
 
 public MyTask(MainActivity m)
 {
  ma=m;
 }
 
 @Override
 protected void onPreExecute()
 {
  super.onPreExecute();
  ma.setResult("转换中...");
 }
 
 private void matchReplace(String regex,StringBuilder s,String color)
 {
  p=Pattern.compile(regex);
  m=p.matcher(s);
  while(m.find())
  {
   StrIndex si=new StrIndex(m.start(),m.end());
   si.setKey(ListRepStr.getListRS().addItem(
              color+s.substring(si.start,si.end)
              .replace("&","&amp;")
              .replace(" ","&nbsp;")
              .replace("<","&lt;")
              .replace(">","&gt;")+ReplaceStr.SPAN_E));
   listSI.add(si);
  }
  for(int i=listSI.size()-1;i>=0;i--)
  {
   s.replace(listSI.get(i).start,listSI.get(i).end,listSI.get(i).key);
   //s.append("\n"+ListRepStr.getListRS().getValue(listSI.get(i).key));
  }
  listSI.clear();
 }
 
 private void matchResult(String regex,StringBuilder s,String color)
 {
  p=Pattern.compile(regex);
  m=p.matcher(s);
  while(m.find())
  {
   StrIndex si=new StrIndex(m.start(),m.end());
   si.setKey(color+s.substring(si.start,si.end)+ReplaceStr.SPAN_E);
   listSI.add(si);
  }
  for(int i=listSI.size()-1;i>=0;i--)
  {
   s.replace(listSI.get(i).start,listSI.get(i).end,listSI.get(i).key);
  }
  listSI.clear();
 }
 
 @Override
 protected String doInBackground(String[] p1)
 {
  StringBuilder b=new StringBuilder(p1[1].replace("\n",ReplaceStr.BR));
  matchReplace("\\/\\*.*?\\*\\/",b,ReplaceStr.SPAN_G); //多行注释
  matchReplace("\\/\\/.*?(?="+ReplaceStr.BR+")|\\/\\/.*$",b,ReplaceStr.SPAN_G); //单行注释
  matchReplace("(?<!\\\\)\".*?(?<!\\\\)\"",b,ReplaceStr.SPAN_R); //双引号字符串
  matchReplace("(?<!\\\\)'.*?(?<!\\\\)'",b,ReplaceStr.SPAN_R); //单引号字符
  
  String s=b.toString().replaceAll(" +(?=\\n)","")
   .replaceAll("(?<![\\n ]) +(?!/[/\\*])",ReplaceStr.NBSP)
   .replace(" ",ReplaceStr.NBSP)
   .replace("&",ReplaceStr.AMP)
   .replace("<",ReplaceStr.LT)
   .replace(">",ReplaceStr.GT);
  b=new StringBuilder(s);
  s=p1[0];
  
  matchReplace(ma.getResources().getString(R.string.regex_class),b,ReplaceStr.SPAN_B); //部分类名(声明,实例化,静态调用)
  matchReplace("\\b\\d+\\.\\d+[dDfF]?\\b|\\.\\d+[dDfF]?\\b",b,ReplaceStr.SPAN_R); //小数(D,F型)
  matchResult("[\\+\\-\\*\\/\\!\\=\\|\\^\\~\\?\\%]+",b,ReplaceStr.SPAN_G); //绿色符号*+-/!=|^~?%
  matchResult("[\\(\\)\\[\\]\\{\\}\\:\\;\\,\\.]+",b,ReplaceStr.SPAN_B); //蓝色符号.,:;()[]{}
  matchResult("\\b0x?\\d+\\b|(?<!┠)\\b\\d+[lL]?\\b(?!┨)",b,ReplaceStr.SPAN_R); //整数(十,八,十六进制,L型)
  matchResult("\\btrue\\b|\\bfalse\\b|\\bnull\\b",b,ReplaceStr.SPAN_R); //布尔值,空值
  matchResult(ma.getResources().getString(R.string.regex_keyword),b,ReplaceStr.SPAN_DB); //Java保留关键字
  matchResult(ma.getResources().getString(R.string.regex_basic),b,ReplaceStr.SPAN_B); //void和8种基本数据类型
  if(!s.equals(""))matchResult(s,b,ReplaceStr.SPAN_B); //其它类名
  
  s=b.toString();
  for(ReplaceStr rs:ListRepStr.getListRS().getZList())
  {
   s=s.replace(rs.getKey(),rs.getValue());
  }
  for(ReplaceStr rs:ListRepStr.getListRS().getKList())
  {
   s=s.replace(rs.getKey(),rs.getValue());
  }
  
  return s;
 }

 @Override
 protected void onProgressUpdate(Integer[] values)
 {
  super.onProgressUpdate(values);
 }

 @Override
 protected void onCancelled(String result)
 {
  super.onCancelled(result);
 }

 @Override
 protected void onPostExecute(String result)
 {
  super.onPostExecute(result);
  ma.setResult(result);
  ListRepStr.getListRS().clear();
 }
 
 
 
}
