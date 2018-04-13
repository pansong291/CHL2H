package pansong291.chl2h.utils;

public class StrIndex
{
 public int start,end;
 public String key,value;
 
 public StrIndex(int s,int e)
 {
  start=s;end=e;
 }
 
 public StrIndex(int s,int e,String kv)
 {
  start=s;end=e;key=kv;value=kv;
 }
 
 public void setKey(String k)
 {
  key=k;value=k;
 }
 
 public void setValue(String v)
 {
  key=v;value=v;
 }

}
