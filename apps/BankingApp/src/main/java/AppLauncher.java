import io.github.blobst.bankingapp.guis.LoginGui;

import javax.swing.*;

public class AppLauncher {
	static void main() {
		SwingUtilities.invokeLater(() -> {
			new LoginGui().setVisible(true);
//				new RegisterGui().setVisible(true);
//				new BankingAppGui(new User(1, "username", "password", new BigDecimal("20.00"))).setVisible(true);

		});
	}
}
