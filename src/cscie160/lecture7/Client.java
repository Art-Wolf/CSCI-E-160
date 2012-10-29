package cscie160.lecture7;

public interface Client {

        String serverResponse();
        
        void sendMessage(String message);

        boolean closeConnection();

}
