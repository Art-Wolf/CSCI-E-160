package cscie160.lecture7;

import java.io.InputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {

	public static void main(String [] args) {
		Socket socket = null;

		try {
			socket = new Socket("time-a.timefreq.bldrdoc.gov", 13);
			InputStream inputStream = socket.getInputStream();
			Scanner scanner = new Scanner(inputStream);

			while(scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException eio) {
					System.out.println(eio);
				}
			}
		}
	}
}
