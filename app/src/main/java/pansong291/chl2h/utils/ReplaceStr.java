package pansong291.chl2h.utils;

public class ReplaceStr
{
 private static final String left="┠",right="┨";
 
 public static final String
  KEYWORD="keyWORD",ZIFU="ziFU",
  
  NBSP=left+"nbsp"+right,
  BR=left+"br"+right,AMP=left+"amp"+right,
  LT=left+"lt"+right,GT=left+"gt"+right,
  
  SPAN_DB=left+"drak"+right,SPAN_B=left+"blue"+right,
  SPAN_G=left+"green"+right,SPAN_R=left+"red"+right,
  SPAN_E=left+"end"+right;
 
 private int id=-1;
 private String key,value;
 
 public ReplaceStr(String k,String v)
 {
  key=k;value=v;
 }
 
 public ReplaceStr(int i,String v)
 {
  id=i;key=left+i+right;value=v;
 }
 
 public static int getId(String k)
 {
  return Integer.parseInt(k.substring(1,k.indexOf(right)));
 }
 
 public static String replace(String str,int p1,int p2,String s)
 {
  return str.substring(0,p1)+s+str.substring(p2);
 }
 
 public int getId()
 {
  return id;
 }
 
 public String getKey()
 {
  return key;
 }

 public String getValue()
 {
  return value;
 }
 
}
