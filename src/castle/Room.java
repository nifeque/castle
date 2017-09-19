package castle;

import java.util.HashMap;

public class Room {
    public String description;
    private HashMap<String, Room> rooms = new HashMap<String, Room>();

    public Room(String description) {
        this.description = description;
    }

    public void setExit(String desc, Room room) { 
    	rooms.put(desc, room);
    }
    
    public String getExitDesc() {
    	StringBuffer sb = new StringBuffer();
    	for (String dir: rooms.keySet()) {
    		sb.append(dir+" ");
    	}
    	return sb.toString();
    }
    
    public Room getExit(String desc) {
    	return rooms.get(desc);
    }
    
    @Override
    public String toString()
    {
        return description;
    }
}
