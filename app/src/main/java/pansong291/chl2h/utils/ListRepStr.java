package pansong291.chl2h.utils;

import java.util.ArrayList;

public class ListRepStr
{
 private static ListRepStr mlrs=new ListRepStr();
 private ArrayList<ReplaceStr>
  klist=new ArrayList<ReplaceStr>(),
  zlist=new ArrayList<ReplaceStr>();
  
 private ListRepStr()
 {
  initKeyWordList();
 }
 
 public static ListRepStr getListRS()
 {
  return mlrs;
 }
 
 public ArrayList<ReplaceStr>getZList()
 {
  return zlist;
 }
 
 public ArrayList<ReplaceStr>getKList()
 {
  return klist;
 }
 
 public String addItem(String value)
 {
  ReplaceStr rs=new ReplaceStr(zlist.size(),value);
  zlist.add(rs);
  return rs.getKey();
 }
 
 public String getValue(String key)
 {
  for(ReplaceStr restr:klist)
  {
   if(key.equals(restr.getKey()))
   {
    return restr.getValue();
   }
  }
  return zlist.get(ReplaceStr.getId(key)).getValue();
 }
 
 public void clearAll()
 {
  zlist.clear();
  klist.clear();
  initKeyWordList();
 }
 
 public void clear()
 {
  zlist.clear();
 }
 
 private void initKeyWordList()
 {
  klist.add(new ReplaceStr(ReplaceStr.NBSP,"&nbsp;"));
  klist.add(new ReplaceStr(ReplaceStr.BR,"<br/>"));
  klist.add(new ReplaceStr(ReplaceStr.AMP,"<span style=\"color:rgb(0,155,0);\">&amp;</span>"));
  klist.add(new ReplaceStr(ReplaceStr.LT,"<span style=\"color:rgb(0,155,0);\">&lt;</span>"));
  klist.add(new ReplaceStr(ReplaceStr.GT,"<span style=\"color:rgb(0,155,0);\">&gt;</span>"));
  klist.add(new ReplaceStr(ReplaceStr.SPAN_DB,"<span style=\"color:rgb(44,130,200);font-weight:bold;\">"));
  klist.add(new ReplaceStr(ReplaceStr.SPAN_B,"<span style=\"color:rgb(0,150,255);\">"));
  klist.add(new ReplaceStr(ReplaceStr.SPAN_G,"<span style=\"color:rgb(0,155,0);\">"));
  klist.add(new ReplaceStr(ReplaceStr.SPAN_R,"<span style=\"color:rgb(188,0,0);\">"));
  klist.add(new ReplaceStr(ReplaceStr.SPAN_E,"</span>"));
 }
 
}
