import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Server {
	public static void main(String args[]) {
		ServerSocket s = null;

		//Input From Keyboard
		DataInputStream indata = new  DataInputStream (System.in);
		System.out.println("Type in Something & Press Enter to Send it To The >>C L I E N T<<: ");
		
		
		// Register your service on port 5432
		try {
			s = new ServerSocket(5432);
		} catch (IOException e) {
			System.out.println(e);
		}

	}
}