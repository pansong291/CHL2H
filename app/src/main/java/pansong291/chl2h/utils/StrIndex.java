package pansong291.chl2h.utils;

public class StrIndex
{
 public int start,end;
 public String key;
 
 public StrIndex(int s,int e)
 {
  start=s;end=e;
 }
 
 public StrIndex(int s,int e,String k)
 {
  start=s;end=e;key=k;
 }
 
 public void setKey(String k)
 {
  key=k;
 }
}
