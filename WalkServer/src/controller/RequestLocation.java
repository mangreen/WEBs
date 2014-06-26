package controller;

/*
 * Simple container for storing the requested location information
 */
public class RequestLocation {
	public int latE6;
	public int lonE6;
	public String buildingID;
	public int floorID;
	
	/*
	 * Parse the location information string and store the translated data into "Location"
	 */
	public static RequestLocation locationParser(String locationStr)
	{
		String[] tokens = locationStr.split(",");
		
		if(tokens.length >= 2)
		{
			RequestLocation location = new RequestLocation();
			
			try
			{
				location.latE6 = (int)(Double.parseDouble(tokens[0]) * 1E6);
				location.lonE6 = (int)(Double.parseDouble(tokens[1]) * 1E6);
				
				
				if(tokens.length == 4)	// Try to parse the indoor information if exist
				{
					try
					{
						location.buildingID = tokens[2];
						location.floorID = Integer.parseInt(tokens[3], 10);
					}
					catch(NumberFormatException e)	// Failed if the floor information cannot be retrieved correctly
					{
						location.buildingID = null;
						location.floorID = 0;
					}
				}
				else
				{
					location.buildingID = null;
					location.floorID = 0;
				}
				
				return location;
			}
			catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		return null;
	}
}
