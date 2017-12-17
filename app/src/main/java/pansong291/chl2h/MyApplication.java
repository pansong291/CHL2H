package pansong291.chl2h;

import pansong291.chl2h.ui.CrashActivity;
import pansong291.crash.CrashApplication;

public class MyApplication extends CrashApplication
{
 @Override
 public Class<?> getPackageActivity()
 {
  setShouldShowDeviceInfo(false);
  return CrashActivity.class;
 }

}
