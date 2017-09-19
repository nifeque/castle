package castle;

import java.util.HashMap;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();
        
    public Game() 
    {
    	handlers.put("go", new HanlerGo());
        createRooms();
    }

    private void createRooms()
    {
        Room outside, lobby, pub, study, bedroom, kitchen, stairs;
      
        //	制造房间
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        kitchen = new Room("厨房");
        stairs = new Room("楼梯");
        
        //	初始化房间的出口
        outside.setExit("west", lobby);
        lobby.setExit("east", outside);
        lobby.setExit("north", outside);
        outside.setExit("south", lobby);
        lobby.setExit("west", study);
        study.setExit("east", lobby);
        outside.setExit("west", pub);
        pub.setExit("east", outside);
        
        

        currentRoom = outside;  //	从城堡门外开始
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
    }

    // 以下为用户命令
    public void showPrompt() {
    	 System.out.println("你在" + currentRoom);
         System.out.print("出口有: ");
         System.out.print(currentRoom.getExitDesc());
         System.out.println();
    }

    private void printHelp() 
    {
        System.out.print("迷路了吗？你可以做的命令有：go bye help");
        System.out.println("如：\tgo east");
    }

    private void goRoom(String direction) 
    {	
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("那里没有门！");
        }
        else {
            currentRoom = nextRoom;
           showPrompt();
        }
    }
    
    public void play() {
    	Scanner in = new Scanner(System.in);
    	while ( true ) {
    		String line = in.nextLine();
    		String[] words = line.split(" ");
    		Handler handler = handlers.get(words[0]);
    		if (handler != null) {
    			handler.doCmd(words[1]);
    			if (Handler.isBye()) {
    				break;
    			}
    		}
    		if ( words[0].equals("help") ) {
    			printHelp();
    		} else if (words[0].equals("go") ) {
    			goRoom(words[1]);
    		} else if ( words[0].equals("bye") ) {
    			break;
    		}
    	}
        in.close();
    }
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.printWelcome();
		game.play();
        System.out.println("感谢您的光临。再见！");
    
	}

}
