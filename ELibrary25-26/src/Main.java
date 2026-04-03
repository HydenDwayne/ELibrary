

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import view.front_pages.Dashboard;
import view.front_pages.LoginWindow;

public class Main {

    public static void main(String[] args) {

    	SwingUtilities.invokeLater(() -> {
    	    try {
    	        Dashboard dashboard = new Dashboard();
    	        LoginWindow login = new LoginWindow(dashboard);
    	        login.setVisible(true);
    	        dashboard.setVisible(false);
    	    } catch (Throwable t) {
    	        JOptionPane.showMessageDialog(
    	            null,
    	            t.toString(),
    	            "Application Error",
    	            JOptionPane.ERROR_MESSAGE
    	        );
    	        t.printStackTrace();
    	    }
    	});

    }
}
