package bean;

public class JNIIdrRoute {
	static{
        System.loadLibrary("CJNIIdrRoute");
    }

	private int sFixLon;
	private int sFixLat;
	private int sFixFloor;
	
	private int eFixLon;
	private int eFixLat;
	private int eFixFloor;
	
	private int sFixDist;
	private int eFixDist;
	
	private String idrResult;
	
	public native static int startRoute(int slon, int slat, int sfloor, int elon, int elat, int efloor, String lockFile);
	//public native int getStart();
	
	//public native JNILockRock setEnd(int lon, int lat, int floor);
}
